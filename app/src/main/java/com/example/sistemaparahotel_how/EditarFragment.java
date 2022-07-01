package com.example.sistemaparahotel_how;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditarFragment extends Fragment {

    private EditText etNome;
    private EditText etCpf;
    private DataBaseHelper databaseHelper;
    private Cliente m;

    public EditarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.cliente_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_cliente);
        etCpf = v.findViewById(R.id.editText_cpf_cliente);

        Bundle b = getArguments();
        int id_cliente = b.getInt("id");
        databaseHelper = new DataBaseHelper(getActivity());
        m = databaseHelper.GetByIdCliente(id_cliente);

        etNome.setText(m.getNome());
        etCpf.setText(m.getCpf());

        Button btnEditar = v.findViewById(R.id.button_editar_cliente);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_cliente);
            }
        });
        Button btnExcluir = v.findViewById(R.id.button_excluir_cliente);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Deseja excluir este registro?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_cliente);
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
            Toast.makeText(getActivity(), "Por favor, informe o nome do cliente", Toast.LENGTH_LONG).show();
        } else if (etCpf.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a cpf do cliente", Toast.LENGTH_LONG).show();
        } else {
            m = new Cliente();
            m.setId(id);
            m.setNome(etNome.getText().toString());
            m.setCpf(etCpf.getText().toString());
            databaseHelper.updateCliente(m);
            Toast.makeText(getActivity(), "Cliente atualizado", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
        }
    }

    private void excluir (int id) {
        m = new Cliente();
        m.setId(id);
        databaseHelper.deleteCliente(m);
        Toast.makeText(getActivity(), "Cliente excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
    }
}