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

    private static final String DROP_TABLE_CLIENTE = "DROP TABLE  IF EXISTS " + TABELA_CLIENTE;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_CLIENTE);
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
        db.close();
        long id = db.insert(TABELA_CLIENTE,null,cv);

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

    public long deleteCliente(Cliente pCliente){
        SQLiteDatabase db = this.getWritableDatabase();

        long id = db.delete(TABELA_CLIENTE,"_id = ?", new String[]{String.valueOf(pCliente.getId())});
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
        int[] to = {R.id.txtID,R.id.txtNome,R.id.txtCpf};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, R.layout.layout_clientes,data,columns,to,0);
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
}
