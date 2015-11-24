package br.senac.pi.cadastro30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senac.pi.cadastro30.Domain.Cachorro;
import br.senac.pi.cadastro30.Domain.CachorroDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrarCarro = (Button) findViewById(R.id.btnCadastrarCachorro);
        btnCadastrarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtNomeCachorro = (EditText) findViewById(R.id.edtNomeCachorro);
                EditText edtRaçaCachorro = (EditText) findViewById(R.id.edtRaçaCachorro);
                String nomeCachorro = edtNomeCachorro.getText().toString();
                String raçaCachorro = edtRaçaCachorro.getText().toString();
                Cachorro cachorro = new Cachorro();
                cachorro.setNome(nomeCachorro);
                cachorro.setRaça(raçaCachorro);
                CachorroDB cachorroDB = new CachorroDB(MainActivity.this);
                if (cachorroDB.save(cachorro) != -1) {
                    Toast.makeText(MainActivity.this, getString(R.string.sucesso), Toast.LENGTH_LONG).show();
                    edtNomeCachorro.setText("");
                    edtRaçaCachorro.setText("");
                    edtNomeCachorro.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.btnListaCachorro).setOnClickListener(listagemcachorros());
    }

    private View.OnClickListener listagemcachorros() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activityl_list_cachorro.class);
                startActivity(intent);
            }
        };
    }
}






























