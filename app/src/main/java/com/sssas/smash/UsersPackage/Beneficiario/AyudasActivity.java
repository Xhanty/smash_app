package com.sssas.smash.UsersPackage.Beneficiario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sssas.smash.Models.Ayuda;
import com.sssas.smash.Models.Proyecto;
import com.sssas.smash.R;
import com.sssas.smash.Utils.ConnectionSql;
import com.sssas.smash.Utils.DatabaseHelper;
import com.sssas.smash.Utils.DatePickerFragment;
import com.sssas.smash.Utils.UtilsNetwork;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AyudasActivity extends AppCompatActivity {

    AutoCompleteTextView spinner_ayudas;
    int REQUEST_CODE = 200;

    private Ayuda ayuda;
    private Statement stm, stm_two;
    DatabaseHelper DBSqlite;
    TextInputEditText cantidad, fecha, coordenadas, observaciones;

    //VARIABLES QUE VAMOS A REGISTRAR
    private String txt_fecha = "";
    private String txt_cantidad = "";
    private String txt_ayuda = "";
    private String txt_coordenadas = "";
    private String txt_observaciones = "";

    //Progress Dialog
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayudas);

        DBSqlite = new DatabaseHelper(this);
        spinner_ayudas = findViewById(R.id.select_ayudas_add);

        cantidad = findViewById(R.id.txt_cantidad_add_ayuda);
        fecha = findViewById(R.id.txt_date_add_ayuda);
        coordenadas = findViewById(R.id.txt_coord_add_ayuda);
        observaciones = findViewById(R.id.txt_obser_add_ayuda);

        ArrayList<Ayuda> ayudas = new ArrayList<Ayuda>();

        if(UtilsNetwork.isOnline(this)){
            try {
                stm = new ConnectionSql().Connection(this).createStatement();
                //stm_two = new ConnectionSql().Connection(this).createStatement();
                ResultSet rs = stm.executeQuery("SELECT ID, NOMBRE_AYUDA FROM TBL_AYUDAS WHERE ESTADO = 1 ORDER BY NOMBRE_AYUDA");
                while (rs.next()) {
                    /*ResultSet rs_detalle = stm_two.executeQuery("SELECT ID FROM TBL_DETALLE_ACTIVIDAD WHERE ID_AYUDA = " + rs.getInt("ID") + " AND ID_PROYECTO = " + 2);
                    Log.e("QUERY_DETALLE", String.valueOf("SELECT ID FROM TBL_DETALLE_ACTIVIDAD WHERE ID_AYUDA = " + rs.getInt("ID") + " AND ID_PROYECTO = " + 2));
                    if(rs_detalle.next()) {
                        ayuda = new Ayuda();
                        ayuda.ID = rs.getInt("ID");
                        ayuda.NOMBRE_AYUDA = rs.getString("NOMBRE_AYUDA");
                        ayudas.add(ayuda);
                    }*/
                    ayuda = new Ayuda();
                    ayuda.ID = rs.getInt("ID");
                    ayuda.NOMBRE_AYUDA = rs.getString("NOMBRE_AYUDA");
                    ayudas.add(ayuda);
                }
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Conectate a internet para agregar ayudas", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<Ayuda> adapter = new ArrayAdapter<>(this, R.layout.dropdown_spinner_item, ayudas);
        spinner_ayudas.setAdapter(adapter);

        spinner_ayudas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ayuda = (Ayuda) parent.getItemAtPosition(position);
            }
        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    public void InitAyuda(View view) {
        if(!validateCantidad() | !valideFecha() | !valideAyuda()){
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
            addAyuda();

        } else {
            Toast.makeText(this, "No tienes conexión a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void addAyuda() {
        txt_ayuda = ayuda.AYUDA_CUSTOM_ADD.trim();
        txt_cantidad = cantidad.getText().toString().trim();
        txt_fecha = fecha.getText().toString().trim();
        txt_coordenadas = coordenadas.getText().toString().trim();
        txt_observaciones = observaciones.getText().toString().trim();

        try {
            stm = new ConnectionSql().Connection(this).createStatement();
            Toast.makeText(this, "Ocurrió un error, intenta más tarde", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error, intenta más tarde", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                fecha.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

    //VALIDACIONES
    private boolean valideFecha() {
        txt_fecha = fecha.getText().toString().trim();
        if(txt_fecha.isEmpty()){
            fecha.setError("Selecciona una fecha");
            return false;

        } else {
            fecha.setError(null);
            return true;
        }
    }

    private boolean validateCantidad() {
        txt_cantidad = cantidad.getText().toString().trim();
        if(txt_cantidad.isEmpty() || Integer.valueOf(txt_cantidad) < 1){
            cantidad.setError("Ingresa una cantidad válida");
            return false;
        }
        else {
            cantidad.setError(null);
            return true;
        }
    }

    private boolean valideAyuda() {
        try {
            txt_ayuda = ayuda.AYUDA_CUSTOM_ADD.trim();
            if(txt_ayuda.isEmpty() || spinner_ayudas.getText().toString().trim().isEmpty()){
                spinner_ayudas.setError("Seleccione una ayuda");
                return false;
            } else {
                spinner_ayudas.setError(null);
                return true;
            }
        } catch (Exception e) {
            spinner_ayudas.setError("Seleccione una ayuda");
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Alerta");
        builder.setMessage("¿Estás seguro de regresar, perderas lo que no hayas guardado?");

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