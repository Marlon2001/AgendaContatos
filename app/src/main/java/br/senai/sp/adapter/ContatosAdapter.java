package br.senai.sp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import br.senai.sp.agendacontatos.Cadastro;
import br.senai.sp.agendacontatos.MainActivity;
import br.senai.sp.agendacontatos.R;
import br.senai.sp.conversor.Imagem;
import br.senai.sp.modelo.Contato;

public class ContatosAdapter extends BaseAdapter{

    private List<Contato> contatos;
    private MainActivity context;

    public ContatosAdapter(MainActivity context, List<Contato> contatos){
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Contato contato = this.contatos.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_lista_contatos, null);

        LinearLayout linearLayout = view.findViewById(R.id.btn_telefone);
        ImageView imgContato = view.findViewById(R.id.img_contatos);
        ImageView btnEdit = view.findViewById(R.id.btn_edit);
        TextView txtNome = view.findViewById(R.id.txt_nome_contato);
        TextView txtTelefone = view.findViewById(R.id.txt_telefone);
        TextView txtPosition = view.findViewById(R.id.txt_position);

        if(contato.getImgFoto() != null)
            imgContato.setImageBitmap(Imagem.arrayToBitmap(contato.getImgFoto()));
        txtNome.setText(contato.getNome());
        txtTelefone.setText(contato.getTelefone());
        txtPosition.setText(String.valueOf(position));

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+contato.getTelefone());
                Intent intentChamada = new Intent(Intent.ACTION_DIAL,uri);
                context.startActivity(intentChamada);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastro = new Intent(context, Cadastro.class);
                abrirCadastro.putExtra("contato", contato);
                context.startActivity(abrirCadastro);
            }
        });

        return view;
    }
}
