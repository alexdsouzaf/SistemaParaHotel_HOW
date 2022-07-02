package com.example.sistemaparahotel_how;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AdicionarFuncionarioFragmente extends Fragment {


    private EditText etNome;
    private EditText etCpf;

    public AdicionarFuncionarioFragmente(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.funcionario_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editText_nome_funcionario);
        etCpf = v.findViewById(R.id.editText_cpf_funcionario);


        Button btnAdicionar = v.findViewById(R.id.button_adicionar_funcionario);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });
        return v;
    }

    private void adicionar() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do Colaborador", Toast.LENGTH_LONG).show();
        } else if (etCpf.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade do Colaborador", Toast.LENGTH_LONG).show();
        } else {
            DataBaseHelper databaseHelper = new DataBaseHelper(getActivity());
            Funcionario pFuncionario = new Funcionario();
            pFuncionario.setNome(etNome.getText().toString());
            pFuncionario.setCpf(etCpf.getText().toString());
            databaseHelper.createFuncionario(pFuncionario);
            Toast.makeText(getActivity(), "Colaborador salvo", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new OpcoesFuncionarioFragment()).commit();


        }
    }
}
