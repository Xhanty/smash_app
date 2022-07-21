package com.sssas.smash.UsersPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sssas.smash.LoginActivity;
import com.sssas.smash.Models.Beneficiario;
import com.sssas.smash.R;
import com.sssas.smash.Utils.ConnectionSql;
import com.sssas.smash.Utils.DatabaseHelper;
import com.sssas.smash.Utils.UtilsNetwork;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class HomeActivity extends AppCompatActivity {

    private static final Pattern DOCUMENTO_PATTERN = Pattern.compile("^[0-9a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]{3,20}$");
    private static final Pattern NAMES_PATTERN = Pattern.compile("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]{3,40}$");

    FragmentManager fragmentManager;
    BottomAppBar bottomNav;
    DatabaseHelper DBSqlite;
    AppCompatImageView icon_wifi;
    FloatingActionButton search_beneficiario;

    private Statement stm;
    private Beneficiario beneficiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        icon_wifi = findViewById(R.id.icon_wifi_home);
        search_beneficiario = findViewById(R.id.menu_search);

        DBSqlite = new DatabaseHelper(this);

        bottomNav = findViewById(R.id.nav_menu_user);

        try {
            stm = new ConnectionSql().Connection(this).createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.content_menu, homeFragment).commit();
        }

        valid_wifi();

        bottomNav.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
                public boolean onMenuItemClick(MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.menu_account:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.menu_logout:
                        logout();
                        break;
                }

                if(fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_menu, fragment).commit();
                }
                return true;
            }
        });

        search_beneficiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_search_beneficiario, (LinearLayout)findViewById(R.id.container_modal_search));
                bottomSheetView.findViewById(R.id.btn_search_bene).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Spinner tipo_documento = bottomSheetDialog.findViewById(R.id.select_td_search);
                        EditText documento = bottomSheetDialog.findViewById(R.id.txt_documento_search);
                        EditText nombres = bottomSheetDialog.findViewById(R.id.txt_nombres_search);
                        EditText apellidos = bottomSheetDialog.findViewById(R.id.txt_apellidos_search);
                        Integer valid = 1;

                        if(documento.getText().toString().trim().length() > 0) {
                            if(!DOCUMENTO_PATTERN.matcher(documento.getText().toString().trim()).matches()){
                                Toast.makeText(HomeActivity.this, "Ingresa un documento válido", Toast.LENGTH_SHORT).show();
                            } else {
                                valid++;
                            }
                        }

                        else if(nombres.getText().toString().trim().length() > 0) {
                            if(!NAMES_PATTERN.matcher(nombres.getText().toString().trim()).matches()){
                                Toast.makeText(HomeActivity.this, "Ingresa un nombre válido", Toast.LENGTH_SHORT).show();
                            } else {
                                valid++;
                            }
                        }

                        else if(apellidos.getText().toString().trim().length() > 0) {
                            if(!NAMES_PATTERN.matcher(apellidos.getText().toString().trim()).matches()){
                                Toast.makeText(HomeActivity.this, "Ingresa un apellido válido", Toast.LENGTH_SHORT).show();
                            } else {
                                valid++;
                            }
                        }

                        else {
                            Toast.makeText(HomeActivity.this, "Debes ingresar mínimo dos parámetros", Toast.LENGTH_SHORT).show();
                        }

                        if(valid > 1) {

                            String query = "SELECT TOP 200 TBL_BENEFICIARIOS.*, TBL_DEPARTAMENTO_REG.ID AS DEPT_REG_ID, TBL_PAIS_REG.ID AS PAIS_REG_ID," +
                            "TBL_DEPARTAMENTO_PROC.ID AS DEPT_PROC_ID, TBL_PAIS_PROC.ID AS PAIS_PROC_ID " +
                            "FROM TBL_BENEFICIARIOS";

                            query = query + " INNER JOIN TBL_TIPO_DOCUMENTO ON TBL_TIPO_DOCUMENTO.ID = TBL_BENEFICIARIOS.ID_TIPO_DOCUMENTO";

                            //UBICACIÓN REGISTRO
                            query = query + " INNER JOIN TBL_CIUDAD AS TBL_CIUDAD_REG ON TBL_CIUDAD_REG.ID = TBL_BENEFICIARIOS.ID_MUNICIPIO_REGISTRO";
                            query = query + " INNER JOIN TBL_DEPARTAMENTO AS TBL_DEPARTAMENTO_REG ON TBL_DEPARTAMENTO_REG.ID = TBL_CIUDAD_REG.ID_DEPARTAMENTO";
                            query = query + " INNER JOIN TBL_PAIS AS TBL_PAIS_REG ON TBL_DEPARTAMENTO_REG.ID_PAIS = TBL_PAIS_REG.ID";

                            //UBICACIÓN PROCEDENCIA
                            query = query + " INNER JOIN TBL_CIUDAD AS TBL_CIUDAD_PROC ON TBL_CIUDAD_PROC.ID = TBL_BENEFICIARIOS.ESTADO_PROCEDENCIA";
                            query = query + " INNER JOIN TBL_DEPARTAMENTO AS TBL_DEPARTAMENTO_PROC ON TBL_DEPARTAMENTO_PROC.ID = TBL_CIUDAD_PROC.ID_DEPARTAMENTO";
                            query = query + " INNER JOIN TBL_PAIS AS TBL_PAIS_PROC ON TBL_DEPARTAMENTO_PROC.ID_PAIS = TBL_PAIS_PROC.ID";

                            query = query + " WHERE TBL_TIPO_DOCUMENTO.TIPO_DOCUMENTO LIKE '" + "DNI del país de origen" + "%'";

                            if(documento.getText().toString().trim().length() > 1) {
                                query = query + " AND TBL_BENEFICIARIOS.DOCUMENTO LIKE '" + documento.getText().toString().trim() + "%'";
                            }

                            if(nombres.getText().toString().trim().length() > 1) {
                                query = query + " AND TBL_BENEFICIARIOS.NOMBRES LIKE '" + nombres.getText().toString().trim() + "%'";
                            }

                            if(apellidos.getText().toString().trim().length() > 1) {
                                query = query + " AND TBL_BENEFICIARIOS.APELLIDOS LIKE '" + apellidos.getText().toString().trim() + "%'";
                            }

                            try {
                                ResultSet rs = stm.executeQuery(query);
                                Log.e("QUERY", query);
                                if (!rs.next()) {
                                    Toast.makeText(HomeActivity.this, "No se encontraron beneficiarios", Toast.LENGTH_SHORT).show();Toast.makeText(HomeActivity.this, "No se encontraron beneficiarios", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    ArrayList<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();


                                        //Log.d("WHILE_BENE", String.valueOf(1));
                                        beneficiario = new Beneficiario();
                                        beneficiario.ID = rs.getInt("ID");
                                        beneficiario.ID_MUNICIPIO_REGISTRO = rs.getInt("ID_MUNICIPIO_REGISTRO");
                                        beneficiario.IMAGEN = rs.getString("IMAGEN");
                                        beneficiario.NOMBRES = rs.getString("NOMBRES");
                                        beneficiario.APELLIDOS = rs.getString("APELLIDOS");
                                        beneficiario.FECHA_NACIMIENTO = rs.getDate("FECHA_NACIMIENTO");
                                        beneficiario.TELEFONO_REDES_SOCIALES = rs.getString("TELEFONO_REDES_SOCIALES");
                                        beneficiario.ID_BARRIO = rs.getInt("ID_BARRIO");
                                        beneficiario.DIRECCION = rs.getString("DIRECCION");
                                        beneficiario.ID_SITUACION_MIGRATORIA = rs.getInt("ID_SITUACION_MIGRATORIA");
                                        beneficiario.ID_TIPO_BENEFICIARIO = rs.getInt("ID_TIPO_BENEFICIARIO");
                                        beneficiario.ID_SEXO = rs.getInt("ID_SEXO");
                                        beneficiario.ID_GENERO = rs.getInt("ID_GENERO");
                                        beneficiario.ID_ESTADO_CIVIL = rs.getInt("ID_ESTADO_CIVIL");
                                        beneficiario.ID_ETNIA = rs.getInt("ID_ETNIA");
                                        beneficiario.ID_DISCAPACIDAD = rs.getInt("ID_DISCAPACIDAD");
                                        beneficiario.ID_ENFERMEDAD = rs.getInt("ID_ENFERMEDAD");
                                        beneficiario.ID_ESCOLARIDAD = rs.getInt("ID_ESCOLARIDAD");
                                        beneficiario.RETORNO_PAIS_ORIGEN = rs.getInt("RETORNO_PAIS_ORIGEN");
                                        beneficiario.MEJORA_RETORNO_PAIS_ORIGEN = rs.getInt("MEJORA_RETORNO_PAIS_ORIGEN");
                                        beneficiario.ID_VIVIENDA = rs.getInt("ID_VIVIENDA");
                                        beneficiario.AYUDA_SOSTENIMIENTO = rs.getInt("AYUDA_SOSTENIMIENTO");
                                        beneficiario.ID_OCUPACION = rs.getInt("ID_OCUPACION");
                                        beneficiario.ESTUDIA_ACTUALMENTE = rs.getInt("ESTUDIA_ACTUALMENTE");
                                        beneficiario.ESTA_EMBARAZADA = rs.getInt("ESTA_EMBARAZADA");
                                        beneficiario.ESTADO_PROCEDENCIA = rs.getInt("ESTADO_PROCEDENCIA");
                                        beneficiario.ID_ADMINISTRADOR = rs.getInt("ID_ADMINISTRADOR");
                                        beneficiario.FECHA_REGISTRO = rs.getDate("FECHA_REGISTRO");
                                        beneficiario.OBSERVACIONES = rs.getString("OBSERVACIONES");
                                        beneficiario.ID_ORGANIZACION = rs.getInt("ID_ORGANIZACION");
                                        beneficiario.ESTADO = rs.getInt("ESTADO");
                                        beneficiario.TIPO_FORMULARIO = rs.getInt("TIPO_FORMULARIO");
                                        beneficiario.ID_PROYECTO = rs.getInt("ID_PROYECTO");
                                        beneficiario.ID_TIPO_DOCUMENTO = rs.getInt("ID_TIPO_DOCUMENTO");
                                        beneficiario.DOCUMENTO = rs.getString("DOCUMENTO");
                                        beneficiario.CABEZA_HOGAR = rs.getInt("CABEZA_HOGAR");
                                        beneficiario.COORDENADA = rs.getString("COORDENADA");
                                        beneficiarios.add(beneficiario);

                                    //Log.d("ENVIAR_BENE", String.valueOf(beneficiarios.size()));
                                    fragmentManager = getSupportFragmentManager();
                                    ListBeneficiariosFragment listBeneficiariosFragment = new ListBeneficiariosFragment();
                                    fragmentManager.beginTransaction().replace(R.id.content_menu, listBeneficiariosFragment).commit();
                                    listBeneficiariosFragment.recibirBeneficiarios(beneficiarios);
                                    bottomSheetDialog.dismiss();

                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                                Toast.makeText(HomeActivity.this, "Ocurrió un error, intenta más tarde", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                valid_wifi();
            }
        },0,5000);
    }

    private void valid_wifi() {
        if(UtilsNetwork.isOnline(this)){
            icon_wifi.setImageResource(R.drawable.ic_online);
        } else {
            icon_wifi.setImageResource(R.drawable.ic_offline);
        }
    }

    private void logout() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Alerta");
            builder.setMessage("¿Deseas cerrar la sesión?");

            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean isDeleted = DBSqlite.delAllData("TBL_USUARIOS");
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
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

        } catch (Exception e) {
            Toast.makeText(this, "Ocurrió un error, intenta más tarde", Toast.LENGTH_SHORT).show();
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
}