package br.senai.sp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.agendacontatos.Cadastro;
import br.senai.sp.agendacontatos.MainActivity;
import br.senai.sp.agendacontatos.R;
import br.senai.sp.conversor.Imagem;
import br.senai.sp.modelo.Contato;

public class ContatosAdapter extends BaseAdapter{

    private List<Contato> contatos;
    private Context context;

    public ContatosAdapter(Context context, List<Contato> contatos){
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
        Contato contato = this.contatos.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_lista_contatos, null);

        ImageView imgContato = view.findViewById(R.id.img_contatos);
        TextView txtNome = view.findViewById(R.id.txt_nome_contato);
        TextView txtTelefone = view.findViewById(R.id.txt_telefone);

        if(contato.getImgFoto() != null)
            imgContato.setImageBitmap(Imagem.arrayToBitmap(contato.getImgFoto()));
        txtNome.setText(contato.getNome());
        txtTelefone.setText(contato.getTelefone());
        return view;
    }
}
