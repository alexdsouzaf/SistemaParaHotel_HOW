package com.example.sistemaparahotel_how;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ListarFuncionarioFragmente extends Fragment {

    public ListarFuncionarioFragmente(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.funcionario_fragment_listar, container, false);

        DataBaseHelper databaseHelper = new DataBaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_view_listar_funcionarios);
        databaseHelper.GetAllFuncionarios(getActivity(), lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListarFuncionario);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                EditarFuncionarioFragmente editar = new EditarFuncionarioFragmente();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editar.setArguments(b);
                ft.replace(R.id.frame_main, editar).commit();
            }
        });


        Button btnVoltar = v.findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesFuncionarioFragment()).commit();
            }
        });
        return v;
    }
}
