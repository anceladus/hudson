package br.senac.pi.cadastro30;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.senac.pi.cadastro30.Domain.Cachorro;
import br.senac.pi.cadastro30.Domain.CachorroDB;

public class activityl_list_cachorro extends AppCompatActivity {

    private CursorAdapter dataSource;
    private SQLiteDatabase database;
    private static final String campos[] = {"nome", "raça", "_id"};
    ListView listView;
    CachorroDB cachorroDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityl_list_cachorro);



        listView = (ListView) findViewById(R.id.listView);
        cachorroDB = new CachorroDB(this);
        database = cachorroDB.getWritableDatabase();
        findViewById(R.id.btnListaCachorro).setOnClickListener(listarCachorros());
        //Chama Listener de delete
        listView.setOnItemClickListener(deletarItem());
    }
    private View.OnClickListener listarCachorros() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cachorros = database.query("raça", campos, null, null, null, null, null);
                if (cachorros.getCount() > 0) {
                    dataSource = new SimpleCursorAdapter(activityl_list_cachorro.this, R.layout.row, cachorros, campos, new int[] {R.id.txtNome, R.id.txtRaça});
                    listView.setAdapter(dataSource);
                } else {
                    Toast.makeText(activityl_list_cachorro.this, getString(R.string.zero_registros), Toast.LENGTH_LONG).show();
                }
            }
        };
    }
    //Responsável por recuperar o item do banco pelo ID e fazer o delete
    private AdapterView.OnItemClickListener deletarItem() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final long itemSelecionado = id;
                final int posicao = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityl_list_cachorro.this);
                builder.setTitle("Pergunta");
                builder.setMessage("O deseja fazer? :");
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int id) {
                        String codigo;
                        Cachorro c = new Cachorro();
                        Cursor cachorro = database.query("cachorro",campos,null,null,null,null,null);
                        cachorro.moveToPosition(posicao);
                        codigo = cachorro.getString(cachorro.getColumnIndexOrThrow("_id"));
                        Intent intent = new Intent(getApplicationContext(),activityl_list_cachorro.class);
                        intent.putExtra("id", codigo);
                        startActivity(intent);
                        finish();

                        ;

                    }
                });
                builder.setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("cachorro", "ID do item Selecionado: " + itemSelecionado);
                        Cachorro cachorro = new Cachorro();
                        cachorro.setId(itemSelecionado);
                        cachorroDB.delete(cachorro);
                    }
                });
                AlertDialog dialog =  builder.create();
                dialog.show();

            }
        };












    }

}
