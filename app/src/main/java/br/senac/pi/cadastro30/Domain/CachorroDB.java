package br.senac.pi.cadastro30.Domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 23/11/2015.
 */
public class CachorroDB extends SQLiteOpenHelper {


    private static final String TAG = "sql";
    //Nome do banco
    private static final String NOME_BANCO = "cursoandroid.sqlite";
    private static final int VERSAO_BANCO = 1;

    public CachorroDB(Context context) {
        //context, nome do banco, factory, versão
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela cachorro...");
        db.execSQL("CREATE TABLE IF NOT EXISTS cachorro (_id integer primary key autoincrement," +
                "nome text, raça text);");
        Log.d(TAG, "Tabela cachorro criada com sucesso!");
    }
    @Override
  public  void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion ){

    }

public  long save (Cachorro cachorro){

    long id = cachorro.getId();
    SQLiteDatabase db = getWritableDatabase();
    try {

        ContentValues values = new ContentValues();
        values.put("nome",cachorro.getNome());
        values.put("raça",cachorro.getRaça());
        if (id != 0){
           String _id = String.valueOf(cachorro.getId());
            String[] wereArgs = new String[]{_id};
            int count = db.update("cachorro", values, "_id=?", wereArgs);
            return count;
        }else {

            id = db.insert("cachorro", "", values);
            return id;
        }

     }finally {
        db.close();
    }


}
public int delete(Cachorro cachorro){
    SQLiteDatabase db = getWritableDatabase();
try {


    int count = db.delete("cachorro","_id=?",new  String[]{String.valueOf(cachorro.getId())});
    Log.i(TAG, "deletou [" + count + "] registro ");
    return  count;


}finally {
    db.close();
}

}
public List<Cachorro>findAll(){
    SQLiteDatabase db = getReadableDatabase();
    try {

        Cursor cursor = db.query("cachorro", null, null, null, null, null, null, null);
               return toList(cursor);

    }finally {
        db.close();
    }
}

public  List<Cachorro> toList(Cursor cursor){

    List<Cachorro> cachorros = new ArrayList<Cachorro>();
    if (cursor.moveToFirst()){
do {
    Cachorro cachorro = new Cachorro();
    cachorros.add(cachorro);
    cachorro.setId(cursor.getLong(cursor.getColumnIndex("_id")));
    cachorro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
    cachorro.setNome(cursor.getString(cursor.getColumnIndex("raça")));
}while (cursor.moveToNext());

}
return cachorros;

}



}