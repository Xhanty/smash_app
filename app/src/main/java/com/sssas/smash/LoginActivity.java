package com.sssas.smash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sssas.smash.Models.Proyecto;
import com.sssas.smash.UsersPackage.HomeActivity;
import com.sssas.smash.Utils.ConnectionSql;
import com.sssas.smash.Utils.DatabaseHelper;
import com.sssas.smash.Utils.UtilsNetwork;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=\\w*\\d)(?=\\w*[a-zA-Z0-9])\\S{6,16}$");

    //Progress Dialog
    ProgressDialog progressDialog;

    //VARIABLES QUE VAMOS A REGISTRAR
    private String txt_usuario = "";
    private String txt_contrasena = "";
    private String txt_proyecto = "";

    AutoCompleteTextView spinner_proyectos;
    TextInputEditText usuario, clave;
    int REQUEST_CODE = 200;

    private Proyecto proyecto;
    private Statement stm;
    DatabaseHelper DBSqlite;

    @RequiresApi (api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        verificarPermisos();
        DBSqlite = new DatabaseHelper(this);
        
        spinner_proyectos = findViewById(R.id.select_proyect);
        usuario = findViewById(R.id.username);
        clave = findViewById(R.id.password);

        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();

        if(UtilsNetwork.isOnline(this)){
            try {
                stm = new ConnectionSql().Connection(this).createStatement();
                ResultSet rs = stm.executeQuery("SELECT ID, NOMBRE_PROYECTO FROM TBL_PROYECTOS WHERE ESTADO = 1");
                while (rs.next()) {
                    proyecto = new Proyecto();
                    proyecto.ID = rs.getInt("ID");
                    proyecto.NOMBRE_PROYECTO = rs.getString("NOMBRE_PROYECTO");
                    proyectos.add(proyecto);
                }
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Conectate a internet para iniciar sesión", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<Proyecto> adapter = new ArrayAdapter<>(this, R.layout.dropdown_spinner_item, proyectos);
        spinner_proyectos.setAdapter(adapter);

        spinner_proyectos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                proyecto = (Proyecto) parent.getItemAtPosition(position);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos() {
        int gps = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(gps != PackageManager.PERMISSION_GRANTED && storage != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else if (gps != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else if (storage != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    public void ResetPassword(View view) {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
    }

    public void InitLogin(View view) {
        if(!validateUsuario() | !validateclave() | !valideProyecto()){
            return;
        }

        if(UtilsNetwork.isOnline(this)){
            //Abrimos el progressDialog
            progressDialog = new ProgressDialog(this);
            progressDialog.show();

            //Contenido
            progressDialog.setContentView(R.layout.progress_dialog);

            //Transparente
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            //Ejecutamos la acción
            ingresarSesion();

        } else {
            Toast.makeText(this, "No tienes conexión a internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Alerta");
        builder.setMessage("¿Estás seguro de salir de la aplicación?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create();
        builder.show();
    }

    private void ingresarSesion() {
        try {
            txt_usuario = usuario.getText().toString().trim();
            txt_contrasena = String.valueOf(encryptPassword(clave.getText().toString().trim()));
            txt_proyecto = proyecto.PROYECTO_CUSTOM_LOGIN.trim();

            stm = new ConnectionSql().Connection(this).createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM TBL_USUARIOS WHERE USUARIO = '" + txt_usuario + "' AND CLAVE = '" + txt_contrasena + "'");

            if (rs.next()) {
                boolean isDeleted = DBSqlite.delAllData("TBL_USUARIOS");
                ContentValues values = new ContentValues();
                values.put("ID", rs.getInt("ID"));
                values.put("USUARIO", rs.getString("USUARIO"));
                values.put("NOMBRE_USUARIO", rs.getString("NOMBRE_USUARIO"));
                values.put("ID_INSTITUCION", rs.getInt("ID_INSTITUCION"));
                values.put("ID_PERFIL", rs.getInt("ID_PERFIL"));
                values.put("TELEFONO", rs.getString("TELEFONO"));
                values.put("EMAIL", rs.getString("EMAIL"));
                values.put("CLAVE", rs.getString("CLAVE"));
                values.put("ID_ESTATUS", rs.getInt("ID_ESTATUS"));
                values.put("FECHA_REGISTRO", String.valueOf(rs.getDate("FECHA_REGISTRO")));
                values.put("FECHA_UPDATE_CLAVE", String.valueOf(rs.getDate("FECHA_UPDATE_CLAVE")));
                values.put("ESTADO_CLAVE", rs.getInt("ESTADO_CLAVE"));
                values.put("PAIS", rs.getInt("PAIS"));
                boolean isInserted = DBSqlite.setData("TBL_USUARIOS", values);

                if(isDeleted && isInserted) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(this, "Ocurrió un error, intenta más tarde", Toast.LENGTH_SHORT).show();
                }
            } else {
                progressDialog.dismiss();
                Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //VALIDACIONES
    private boolean validateUsuario(){
        txt_usuario = usuario.getText().toString().trim();
        if(txt_usuario.isEmpty()){
            usuario.setError("Escribe un usuario");
            return false;

        } else {
            usuario.setError(null);
            return true;
        }
    }

    private boolean validateclave(){
        txt_contrasena = clave.getText().toString().trim();
        if(txt_contrasena.isEmpty()){
            clave.setError("Ingresa una contraseña");
            return false;

        } else if(!PASSWORD_PATTERN.matcher(txt_contrasena).matches()){
            clave.setError("La contraseña debe tener mínimo 6 dígitos y un número");
            return false;

        } else {
            clave.setError(null);
            return true;
        }
    }

    private boolean valideProyecto(){
        try {
            txt_proyecto = proyecto.PROYECTO_CUSTOM_LOGIN.trim();
            if(txt_proyecto.isEmpty() || spinner_proyectos.getText().toString().trim().isEmpty()){
                spinner_proyectos.setError("Seleccione un proyecto");
                return false;
            } else {
                spinner_proyectos.setError(null);
                return true;
            }
        } catch (Exception e) {
            spinner_proyectos.setError("Seleccione un proyecto");
            return false;
        }
    }

    private static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));

        return new BigInteger(1, crypt.digest()).toString(16);
    }
}