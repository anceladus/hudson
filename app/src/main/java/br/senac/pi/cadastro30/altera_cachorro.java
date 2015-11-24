package br.senac.pi.cadastro30;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.senac.pi.cadastro30.Domain.CachorroDB;

public class altera_cachorro extends AppCompatActivity {


    private CachorroDB cachorroDB ;
    private SQLiteDatabase db;
    private EditText edtAlteraNome ,edtAlteraRaça;
    private TextView txtIdCachorro;
    private String id;
    private Cursor cursor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_cachorro);
        id = getIntent().getStringExtra("id");
        cachorroDB = new CachorroDB(this);

        txtIdCachorro = (TextView) findViewById(R.id.txtIdCachorro);
        edtAlteraNome = (EditText) findViewById(R.id.edtAlteraNome);
        edtAlteraRaça = (EditText) findViewById(R.id.edtAlteraRaça);
        cursor = carregaCachorro(Integer.parseInt(id));
        txtIdCachorro.setText(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
        edtAlteraNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        edtAlteraRaça.setText(cursor.getString(cursor.getColumnIndexOrThrow("raça")));
        findViewById(R.id.btnAlterarCachorro).setOnClickListener(alterarCachorro());


    }






    private Cursor carregaCachorro(int id){

        db = cachorroDB.getWritableDatabase();
        String[] compos = {"_id","nome","raça"};
        String wereArgs = String.valueOf(id);
        cursor = db.query("cachorro",compos,wereArgs,null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();

        }
        db.close();
        return cursor;


    }
    private View.OnClickListener alterarCachorro(){

        return new View.OnClickListener(){
            @Override
            public void onClick (View view){

                db = cachorroDB.getWritableDatabase();
                ContentValues values = new ContentValues();
                String wereArgs = id;
                Log.i("curso", "ID capiturado: " + id);
                values.put("nome",edtAlteraNome.getText().toString());
                values.put("raça",edtAlteraRaça.getText().toString());
                db.update("cachorro",values,"_id = " + wereArgs,null);
                db.close();
            }

        };

    }




}
