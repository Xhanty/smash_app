package com.sssas.smash.UsersPackage.Beneficiario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.shuhart.stepview.StepView;
import com.sssas.smash.R;
import com.sssas.smash.Utils.DatePickerFragment;

public class FamiliaActivity extends AppCompatActivity implements View.OnClickListener {

    StepView stepView;

    int stepIndex = 0;
    Button next_1, next_2, next_3, guardar;
    Button back_1, back_2, back_3;
    LinearLayout step1, step2, step3, step4;
    TextInputEditText fecha_nacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);

        next_1 = findViewById(R.id.next_add_f_1);
        next_1.setOnClickListener(this);

        next_2 = findViewById(R.id.next_add_f_2);
        next_2.setOnClickListener(this);

        next_3 = findViewById(R.id.next_add_f_3);
        next_3.setOnClickListener(this);

        back_1 = findViewById(R.id.back_add_f_2);
        back_1.setOnClickListener(this);

        back_2 = findViewById(R.id.back_add_f_3);
        back_2.setOnClickListener(this);

        back_3 = findViewById(R.id.back_add_f_4);
        back_3.setOnClickListener(this);

        guardar = findViewById(R.id.next_add_f_4);
        guardar.setOnClickListener(this);

        step1 = findViewById(R.id.div_step_1_add_fm);
        step2 = findViewById(R.id.div_step_2_add_fm);
        step3 = findViewById(R.id.div_step_3_add_fm);
        step4 = findViewById(R.id.div_step_4_add_fm);

        stepView = findViewById(R.id.step_add_familia);
        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(4)
                .animationDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime))
                .commit();

        fecha_nacimiento = findViewById(R.id.txt_date_add_familia);

        fecha_nacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.next_add_f_1:
                step1.setVisibility(View.INVISIBLE);
                step2.setVisibility(View.VISIBLE);
                step3.setVisibility(View.INVISIBLE);
                step4.setVisibility(View.INVISIBLE);

                stepIndex = 1;
                stepView.go(stepIndex, true);
                break;

            case R.id.next_add_f_2:
                step1.setVisibility(View.INVISIBLE);
                step2.setVisibility(View.INVISIBLE);
                step3.setVisibility(View.VISIBLE);
                step4.setVisibility(View.INVISIBLE);

                stepIndex = 2;
                stepView.go(stepIndex, true);
                break;

            case R.id.next_add_f_3:
                step1.setVisibility(View.INVISIBLE);
                step2.setVisibility(View.INVISIBLE);
                step3.setVisibility(View.INVISIBLE);
                step4.setVisibility(View.VISIBLE);

                stepIndex = 3;
                stepView.go(stepIndex, true);
                break;

            case R.id.next_add_f_4:
                Toast.makeText(this, "Guardar", Toast.LENGTH_SHORT).show();
                break;

            //REGRESAR
            case R.id.back_add_f_2:
                step1.setVisibility(View.VISIBLE);
                step2.setVisibility(View.INVISIBLE);
                step3.setVisibility(View.INVISIBLE);
                step4.setVisibility(View.INVISIBLE);

                stepIndex = 0;
                stepView.go(stepIndex, true);
                break;

            case R.id.back_add_f_3:
                step1.setVisibility(View.INVISIBLE);
                step2.setVisibility(View.VISIBLE);
                step3.setVisibility(View.INVISIBLE);
                step4.setVisibility(View.INVISIBLE);

                stepIndex = 1;
                stepView.go(stepIndex, true);
                break;

            case R.id.back_add_f_4:
                step1.setVisibility(View.INVISIBLE);
                step2.setVisibility(View.INVISIBLE);
                step3.setVisibility(View.VISIBLE);
                step4.setVisibility(View.INVISIBLE);

                stepIndex = 2;
                stepView.go(stepIndex, true);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Alerta");
        builder.setMessage("??Est??s seguro de regresar, perderas lo que no hayas guardado?");

        builder.setPositiveButton("S??", new DialogInterface.OnClickListener() {
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

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                fecha_nacimiento.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }
}