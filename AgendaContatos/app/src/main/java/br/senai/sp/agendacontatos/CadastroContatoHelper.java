package br.senai.sp.agendacontatos;

import android.widget.EditText;

import br.senai.sp.modelo.Contato;

public class CadastroContatoHelper {
    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtEmail;
    private EditText txtTelefone;
    private EditText txtLinkedin;
    private EditText txtObs;
    private Contato contato;

    public CadastroContatoHelper(Cadastro activiry){
        txtNome = activiry.findViewById(R.id.txt_nome);
        txtEndereco = activiry.findViewById(R.id.txt_endereco);
        txtEmail = activiry.findViewById(R.id.txt_email);
        txtTelefone = activiry.findViewById(R.id.txt_telefone);
        txtLinkedin = activiry.findViewById(R.id.txt_linkedin);
        txtObs = activiry.findViewById(R.id.txt_obs);
        contato = new Contato();
    }

    public boolean verificaCampos(){
        boolean semErros = true;
        if(txtNome.getText().toString().isEmpty()){
            semErros = false;
        }
        if(txtEndereco.getText().toString().isEmpty()){
            semErros = false;
        }
        if(txtEmail.getText().toString().isEmpty()){
            semErros = false;
        }
        if(txtTelefone.getText().toString().isEmpty()){
            semErros = false;
        }
        if(txtLinkedin.getText().toString().isEmpty()){
            semErros = false;
        }
        return semErros;
    }

    public Contato getContato() {
        contato.setNome(txtNome.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());
        contato.setObs(txtObs.getText().toString());
        return contato;
    }

    public void preencherCampos(Contato contato){
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtEmail.setText(contato.getEmail());
        txtTelefone.setText(contato.getTelefone());
        txtLinkedin.setText(contato.getLinkedin());
        txtObs.setText(contato.getObs());
        this.contato = contato;
    }
}
