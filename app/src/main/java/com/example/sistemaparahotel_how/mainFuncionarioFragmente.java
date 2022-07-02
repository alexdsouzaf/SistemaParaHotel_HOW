package com.example.sistemaparahotel_how;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class mainFuncionarioFragmente extends Fragment {

    public mainFuncionarioFragmente(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.funcionario_fragment_main, container, false);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_funcionario, new AdicionarFuncionarioFragmente()).commit();
        }

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_funcionario);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_funcionario, new AdicionarFuncionarioFragmente()).commit();
            }
        });

        Button btnListar = v.findViewById(R.id.button_listar_funcionario);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_funcionario, new ListarFuncionarioFragmente()).commit();
            }
        });

        return v;
    }
}
