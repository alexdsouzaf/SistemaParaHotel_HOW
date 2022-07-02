package com.example.sistemaparahotel_how;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

//TO-DO implementar os botoes para a proxima tela (cadastro e consulta)
public class OpcoesFuncionarioFragment extends Fragment {

    public OpcoesFuncionarioFragment(){}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.opcoes_funcionario_fragment, container, false);

        Button btnVoltar = view.findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new TelaPrincipalFragment()).commit();
            }
        });


        Button btnAdicionar = view.findViewById(R.id.btnAdicionarFuncionario);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new AdicionarFuncionarioFragmente()).commit();
            }
        });

        Button btnListar = view.findViewById(R.id.btnListarFuncionario);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new ListarFuncionarioFragmente()).commit();
            }
        });

        return view;
    }
}
