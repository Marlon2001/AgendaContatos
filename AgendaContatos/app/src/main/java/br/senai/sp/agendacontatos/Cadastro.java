package br.senai.sp.agendacontatos;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class Cadastro extends AppCompatActivity {
    private CadastroContatoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão de voltar
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Agenda de Contatos");

        helper = new CadastroContatoHelper(this);

        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");
        if (contato != null)
            helper.preencherCampos(contato);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_salvar:
                ContatoDAO dao = new ContatoDAO(this);
                Contato contato = helper.getContato();
                if(helper.verificaCamposVazios()) {
                    if (contato.getId() > 0) {
                        dao.atualizar(contato);
                        Toast.makeText(this, "Contato atualizado com sucesso!", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        dao.salvar(contato);
                        Toast.makeText(this, "Contato salvo com sucesso!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    dao.close();
                }else{
                    Toast.makeText(this, "Preencha os campos para cadastrar!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_cancelar:
                Toast.makeText(this, "Operação cancelada!", Toast.LENGTH_LONG).show();
                finish();
                break;
            case android.R.id.home:
                Intent intent = new Intent(Cadastro.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
