package com.example.sistemaparahotel_how;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class EditarFuncionarioFragmente extends Fragment {

    private EditText etNome;
    private EditText etCpf;
    private DataBaseHelper databaseHelper;
    private Funcionario m;

    public EditarFuncionarioFragmente(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.funcionario_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_funcionario);
        etCpf = v.findViewById(R.id.editText_cpf_funcionario);

        Bundle b = getArguments();
        int id_funcionario = b.getInt("id");
        databaseHelper = new DataBaseHelper(getActivity());
        m = databaseHelper.GetByIdFuncionario(id_funcionario);

        etNome.setText(m.getNome());
        etCpf.setText(m.getCpf());

        Button btnEditar = v.findViewById(R.id.button_editar_funcionario);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_funcionario);
            }
        });
        Button btnExcluir = v.findViewById(R.id.button_excluir_funcionario);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Deseja excluir este registro?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_funcionario);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void editar(int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do colaborador", Toast.LENGTH_LONG).show();
        } else if (etCpf.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a cpf do colaborador", Toast.LENGTH_LONG).show();
        } else {
            m = new Funcionario();
            m.setId(id);
            m.setNome(etNome.getText().toString());
            m.setCpf(etCpf.getText().toString());
            databaseHelper.updateFuncionario(m);
            Toast.makeText(getActivity(), "Funcionario atualizado", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new ListarFuncionarioFragmente()).commit();
        }
    }

    private void excluir (int id) {
        m = new Funcionario();
        m.setId(id);
        databaseHelper.deleteFuncionario(m);
        Toast.makeText(getActivity(), "Cliente excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new ListarFuncionarioFragmente()).commit();
    }
}
