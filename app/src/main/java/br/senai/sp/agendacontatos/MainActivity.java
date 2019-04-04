package br.senai.sp.agendacontatos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.adapter.ContatosAdapter;
import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class MainActivity extends AppCompatActivity {
    private ListView listaContato;
    private Button btNovoContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContato = findViewById(R.id.lista_contatos);
        btNovoContato = findViewById(R.id.btn_novo_contato);

        btNovoContato.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastro = new Intent(MainActivity.this, Cadastro.class);
                startActivity(abrirCadastro);
            }
        });

        registerForContextMenu(listaContato);
    }

    public boolean onContextItemSelected(MenuItem item) {
        final ContatoDAO dao = new ContatoDAO(this);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Contato contato = (Contato) listaContato.getItemAtPosition(info.position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar contato");
        builder.setMessage("Tem certeza que deseja excluir o contato "+contato.getNome()+"?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                dao.excluir(contato);
                Toast.makeText(MainActivity.this, "Contato "+contato.getNome()+" excluido com sucesso", Toast.LENGTH_SHORT).show();
                carregarLista();
                dao.close();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_lista_contatos, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        carregarLista();
        super.onResume();
    }

    private void carregarLista(){
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.getContatos();
        dao.close();

        ContatosAdapter contatosAdapter = new ContatosAdapter(this, contatos);
        listaContato.setAdapter(contatosAdapter);
    }
}
