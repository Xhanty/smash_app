package com.sssas.smash.UsersPackage;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sssas.smash.R;
import com.sssas.smash.Utils.DatabaseHelper;

public class ProfileFragment extends Fragment {

    DatabaseHelper DBSqlite;
    TextView txt_name, txt_profile;
    TextInputEditText edit_usuario, edit_institucion, edit_pais, edit_email, edit_telefono;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        DBSqlite = new DatabaseHelper(getContext());
        txt_name = view.findViewById(R.id.txt_name_profile);
        txt_profile = view.findViewById(R.id.txt_profile_profile);
        edit_usuario = view.findViewById(R.id.edit_usuario_profile);
        edit_institucion = view.findViewById(R.id.edit_institucion_profile);
        edit_pais = view.findViewById(R.id.edit_pais_profile);
        edit_email = view.findViewById(R.id.edit_email_profile);
        edit_telefono = view.findViewById(R.id.edit_telefono_profile);

        Cursor data = DBSqlite.getData("SELECT * FROM TBL_USUARIOS");
        data.moveToFirst();

        if(data.getCount() > 0) {
            txt_name.setText(data.getString(2));
            txt_profile.setText(data.getString(4));
            edit_usuario.setText(data.getString(1));
            edit_institucion.setText(data.getString(3));
            edit_pais.setText(data.getString(12));
            edit_email.setText(data.getString(6));
            edit_telefono.setText(data.getString(5));
        } else {
            Toast.makeText(getContext(), "Ocurrió un error, intenta más tarde", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}