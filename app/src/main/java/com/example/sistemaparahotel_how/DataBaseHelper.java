package com.example.sistemaparahotel_how;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DataBaseHelper extends SQLiteOpenHelper{

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null,1);
    }
    private static final String DATABASE_NAME = "hotelaria";
    private static final String TABELA_CLIENTE = "cliente";
    private static final String TABELA_FUNCIONARIO = "funcionario";


    private static final String CREATE_TABLE_CLIENTE = "CREATE TABLE " + TABELA_CLIENTE + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(50), " +
            "CPF VARCHAR(11)," + //SEM MASCARA
            "data_nascimento TEXT, " +
            "email VARCHAR(30), " +
            "endereco VARCHAR(30)," +
            "profissao VARCHAR(20), " +
            "RG VARCHAR(15), " +
            "nacionalidade VARCHAR(20)" + ")";

    private static final String CREATE_TABLE_FUNCIONARIO = "CREATE TABLE " + TABELA_FUNCIONARIO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(50), " +
            "CPF VARCHAR(11)," + //SEM MASCARA
            "data_nascimento TEXT, " +
            "email VARCHAR(30), " +
            "endereco VARCHAR(30)," +
            "profissao VARCHAR(20), " +
            "RG VARCHAR(15), " +
            "nacionalidade VARCHAR(20)" + ")";


    private static final String DROP_TABLE_CLIENTE = "DROP TABLE  IF EXISTS " + TABELA_CLIENTE;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_FUNCIONARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public long createCliente(Cliente pCliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", pCliente.getNome());
        cv.put("CPF", pCliente.getCpf());
        cv.put("data_nascimento", pCliente.getDataNascimento());
        cv.put("endereco", pCliente.getEndereco());
        cv.put("profissao", pCliente.getProfissao());
        cv.put("RG", pCliente.getRg());
        cv.put("nacionalidade", pCliente.getNacionalidade());

        long id = db.insert(TABELA_CLIENTE,null,cv);
        db.close();
        return id;
    }

    public long createFuncionario(Funcionario pFuncionario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", pFuncionario.getNome());
        cv.put("CPF", pFuncionario.getCpf());
        cv.put("data_nascimento", pFuncionario.getDataNascimento());
        cv.put("endereco", pFuncionario.getEndereco());
        cv.put("profissao", pFuncionario.getProfissao());
        cv.put("RG", pFuncionario.getRg());
        cv.put("nacionalidade", pFuncionario.getNacionalidade());

        long id = db.insert(TABELA_FUNCIONARIO,null,cv);
        db.close();
        return id;
    }

    public long updateCliente(Cliente pCliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", pCliente.getNome());
        cv.put("CPF", pCliente.getCpf());
        cv.put("data_nascimento", pCliente.getDataNascimento());
        cv.put("endereco", pCliente.getEndereco());
        cv.put("profissao", pCliente.getProfissao());
        cv.put("RG", pCliente.getRg());
        cv.put("nacionalidade", pCliente.getNacionalidade());

        long id = db.update(TABELA_CLIENTE,cv,"_id = ?", new String[]{String.valueOf(pCliente.getId())});
        db.close();

        return id;
    }

    public long updateFuncionario(Funcionario pFuncionario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", pFuncionario.getNome());
        cv.put("CPF", pFuncionario.getCpf());
        cv.put("data_nascimento", pFuncionario.getDataNascimento());
        cv.put("endereco", pFuncionario.getEndereco());
        cv.put("profissao", pFuncionario.getProfissao());
        cv.put("RG", pFuncionario.getRg());
        cv.put("nacionalidade", pFuncionario.getNacionalidade());

        long id = db.update(TABELA_FUNCIONARIO,cv,"_id = ?", new String[]{String.valueOf(pFuncionario.getId())});
        db.close();

        return id;
    }

    public long deleteCliente(Cliente pCliente){
        SQLiteDatabase db = this.getWritableDatabase();

        long id = db.delete(TABELA_CLIENTE,"_id = ?", new String[]{String.valueOf(pCliente.getId())});
        db.close();

        return id;
    }

    public long deleteFuncionario(Funcionario pFuncionario){
        SQLiteDatabase db = this.getWritableDatabase();

        long id = db.delete(TABELA_FUNCIONARIO,"_id = ?", new String[]{String.valueOf(pFuncionario.getId())});
        db.close();

        return id;
    }

    public void GetAllClientes(Context context, ListView lv){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","nome","CPF"};
                //"data_nascimento",
                //"endereco",
                //"profissao",
                //"RG",
                //"nacionalidade"
        //};
        Cursor data = db.query(TABELA_CLIENTE,columns,null,null,null,null,"nome");
        int[] to = {R.id.textViewIdListarCliente,R.id.textViewNomeListarCliente,R.id.textViewCpfListarCliente};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, R.layout.cliente_item_list_view,data,columns,to,0);
        lv.setAdapter(adapter);
        db.close();
    }

    public void GetAllFuncionarios(Context context, ListView lv){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","nome","CPF"};
        //"data_nascimento",
        //"endereco",
        //"profissao",
        //"RG",
        //"nacionalidade"
        //};
        Cursor data = db.query(TABELA_FUNCIONARIO,columns,null,null,null,null,"nome");
        int[] to = {R.id.textViewIdListarFuncionario,R.id.textViewNomeListarFuncionario,R.id.textViewCpfListarFuncionario};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, R.layout.funcionario_item_list,data,columns,to,0);
        lv.setAdapter(adapter);
        db.close();
    }

    public Cliente GetByIdCliente(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","nome","CPF"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABELA_CLIENTE,columns,"_id = ?",args,null,null,"nome");
        data.moveToFirst();
        Cliente oCliente = new Cliente();
        oCliente.setId(data.getInt(0));
        oCliente.setNome(data.getString(1));
        oCliente.setCpf(data.getString(2));
        data.close();
        db.close();
        return oCliente;
    }

    public Funcionario GetByIdFuncionario(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","nome","CPF"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABELA_FUNCIONARIO,columns,"_id = ?",args,null,null,"nome");
        data.moveToFirst();
        Funcionario oFuncionario = new Funcionario();
        oFuncionario.setId(data.getInt(0));
        oFuncionario.setNome(data.getString(1));
        oFuncionario.setCpf(data.getString(2));
        data.close();
        db.close();
        return oFuncionario;
    }
}
