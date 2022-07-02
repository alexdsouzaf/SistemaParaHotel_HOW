package com.example.sistemaparahotel_how;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TelaPrincipalFragment extends Fragment {

    public TelaPrincipalFragment(){}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tela_principal_fragment);


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tela_principal_fragment, container, false);

        Button btnHospedes = view.findViewById(R.id.button_hospedes);
        btnHospedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //vai abrir uma nova tela que da as opcoes de consulta e cadastro
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesClienteFragment()).commit();
            }
        });

        Button btnOperacional = view.findViewById(R.id.button_operacional);
        btnOperacional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //vai abrir uma nova tela que da as opcoes de consulta e cadastro
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesFuncionarioFragment()).commit();
            }});

        Button btnReserva = view.findViewById(R.id.button_reserva);
        btnReserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //vai abrir uma nova tela que da as opcoes de consulta e cadastro
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesClienteFragment()).commit();
                }});

        Button btnAgenda = view.findViewById(R.id.button_agenda);
        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //vai abrir uma nova tela que da as opcoes de consulta e cadastro
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesClienteFragment()).commit();
                Toast.makeText(getActivity(),"ainda não implementado",Toast.LENGTH_LONG).show();
            }});

        return view;
    }




}
