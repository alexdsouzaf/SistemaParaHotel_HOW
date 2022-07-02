package com.example.sistemaparahotel_how;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TelaOpcoesCliente extends AppCompatActivity {

    public TelaOpcoesCliente(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_opcoes_cliente);

        if (savedInstanceState == null) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesClienteFragment()).commit();
        }
    }
}