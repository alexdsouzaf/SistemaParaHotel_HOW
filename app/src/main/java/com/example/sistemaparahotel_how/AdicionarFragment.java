package com.example.sistemaparahotel_how;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etCpf;


    public AdicionarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cliente_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editText_nome_cliente);
        etCpf = v.findViewById(R.id.editText_cpf_cliente);


        Button btnAdicionar = v.findViewById(R.id.button_adicionar_cliente);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    private void adicionar() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do hóspede", Toast.LENGTH_LONG).show();
        } else if (etCpf.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade do hóspede", Toast.LENGTH_LONG).show();
        } else {
            DataBaseHelper databaseHelper = new DataBaseHelper(getActivity());
            Cliente m = new Cliente();
            m.setNome(etNome.getText().toString());
            m.setCpf(etCpf.getText().toString());
            databaseHelper.createCliente(m);
            Toast.makeText(getActivity(), "Cliente salvo", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesClienteFragment()).commit();


        }
    }
}