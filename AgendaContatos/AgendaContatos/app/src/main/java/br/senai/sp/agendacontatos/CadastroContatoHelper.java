package br.senai.sp.agendacontatos;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.senai.sp.modelo.Contato;

public class CadastroContatoHelper {
    private TextInputLayout layoutTxtNome;
    private TextInputLayout layoutTxtEndereco;
    private TextInputLayout layoutTxtEmail;
    private TextInputLayout layoutTxtTelefone;
    private TextInputLayout layoutTxtLinkedin;
    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtEmail;
    private EditText txtTelefone;
    private EditText txtLinkedin;
    private EditText txtObs;
    private Contato contato;

    public CadastroContatoHelper(Cadastro activity){
        layoutTxtNome = activity.findViewById(R.id.layout_txt_nome);
        layoutTxtEndereco = activity.findViewById(R.id.layout_txt_endereco);
        layoutTxtEmail = activity.findViewById(R.id.layout_txt_email);
        layoutTxtTelefone = activity.findViewById(R.id.layout_txt_telefone);
        layoutTxtLinkedin = activity.findViewById(R.id.layout_txt_linkedin);

        txtNome = activity.findViewById(R.id.txt_nome);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtLinkedin = activity.findViewById(R.id.txt_linkedin);
        txtObs = activity.findViewById(R.id.txt_obs);
        contato = new Contato();
    }

    public boolean verificaCampos(){
        boolean semErros = true;
        if(txtNome.getText().toString().isEmpty()){
            layoutTxtNome.setError("Por favor informe o nome.");
            semErros = false;
        }
        if(txtEndereco.getText().toString().isEmpty()){
            layoutTxtEndereco.setErrorEnabled(true);
            layoutTxtEndereco.setError("Por favor informe o endereço.");
            semErros = false;
        }
        if(txtEmail.getText().toString().isEmpty()){
            layoutTxtEmail.setErrorEnabled(true);
            layoutTxtEmail.setError("Por favor informe o email.");
            semErros = false;
        }
        if(txtTelefone.getText().toString().isEmpty()){
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Por favor informe o telefone.");
            semErros = false;
        }
        if(txtLinkedin.getText().toString().isEmpty()){
            layoutTxtLinkedin.setErrorEnabled(true);
            layoutTxtLinkedin.setError("Por favor informe o linkedin.");
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
