package com.sssas.smash.UsersPackage;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.sssas.smash.Models.Beneficiario;
import com.sssas.smash.R;
import com.sssas.smash.Utils.AdapterExpandableBeneficiarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListBeneficiariosFragment extends Fragment {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListNombres;
    private HashMap<String, Beneficiario> listaBeneficiarios;
    private int lastExpandedPosition = -1;
    private View view;
    private HashMap<String, Beneficiario> listaC = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_beneficiarios, container, false);

        init();

        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        return view;
    }

    public void recibirBeneficiarios(ArrayList<Beneficiario> beneficiarios){
        Log.d("RECIBIR_BENE", String.valueOf(beneficiarios.size()));
        for (int i = 0; i < beneficiarios.size(); i++) {
            Log.d("ASDAASS", beneficiarios.get(i).NOMBRES);
            listaC.put(beneficiarios.get(i).NOMBRES + " " + beneficiarios.get(i).APELLIDOS, new Beneficiario(beneficiarios.get(i).ID,
                    beneficiarios.get(i).ID_MUNICIPIO_REGISTRO,
                    beneficiarios.get(i).IMAGEN,
                    beneficiarios.get(i).NOMBRES,
                    beneficiarios.get(i).APELLIDOS,
                    beneficiarios.get(i).FECHA_NACIMIENTO,
                    beneficiarios.get(i).TELEFONO_REDES_SOCIALES,
                    beneficiarios.get(i).ID_BARRIO,
                    beneficiarios.get(i).DIRECCION,
                    beneficiarios.get(i).ID_SITUACION_MIGRATORIA,
                    beneficiarios.get(i).ID_TIPO_BENEFICIARIO,
                    beneficiarios.get(i).ID_SEXO,
                    beneficiarios.get(i).ID_GENERO,
                    beneficiarios.get(i).ID_ESTADO_CIVIL,
                    beneficiarios.get(i).ID_ETNIA,
                    beneficiarios.get(i).ID_DISCAPACIDAD,
                    beneficiarios.get(i).ID_ENFERMEDAD,
                    beneficiarios.get(i).ID_ESCOLARIDAD,
                    beneficiarios.get(i).RETORNO_PAIS_ORIGEN,
                    beneficiarios.get(i).MEJORA_RETORNO_PAIS_ORIGEN,
                    beneficiarios.get(i).ID_VIVIENDA,
                    beneficiarios.get(i).AYUDA_SOSTENIMIENTO,
                    beneficiarios.get(i).ID_OCUPACION,
                    beneficiarios.get(i).ESTUDIA_ACTUALMENTE,
                    beneficiarios.get(i).ESTA_EMBARAZADA,
                    beneficiarios.get(i).ESTADO_PROCEDENCIA,
                    beneficiarios.get(i).ID_ADMINISTRADOR,
                    beneficiarios.get(i).FECHA_REGISTRO,
                    beneficiarios.get(i).OBSERVACIONES,
                    beneficiarios.get(i).ID_ORGANIZACION,
                    beneficiarios.get(i).ESTADO,
                    beneficiarios.get(i).TIPO_FORMULARIO,
                    beneficiarios.get(i).ID_PROYECTO,
                    beneficiarios.get(i).ID_TIPO_DOCUMENTO,
                    beneficiarios.get(i).DOCUMENTO,
                    beneficiarios.get(i).CABEZA_HOGAR,
                    beneficiarios.get(i).COORDENADA
                    ));
        }
    }

    private void init() {
        this.expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        this.listaBeneficiarios = listaC;
        this.expandableListNombres = new ArrayList<>(listaBeneficiarios.keySet());
        this.expandableListAdapter = new AdapterExpandableBeneficiarios(getActivity(),
                expandableListNombres, listaBeneficiarios);
    }
}