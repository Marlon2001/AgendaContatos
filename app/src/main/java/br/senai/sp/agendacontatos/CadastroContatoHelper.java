package br.senai.sp.agendacontatos;

import android.media.Image;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;

import br.senai.sp.conversor.Imagem;
import br.senai.sp.modelo.Contato;
import br.senai.sp.utils.ValidaCaracteres;

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
    private ImageView imgFoto;
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
        imgFoto = activity.findViewById(R.id.img_foto);

        contato = new Contato();
    }

    public Contato getContato() {
        contato.setNome(txtNome.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());
        contato.setObs(txtObs.getText().toString());
        contato.setImgFoto(Imagem.ImageViewToArray(imgFoto));
        return contato;
    }

    public void preencherCampos(Contato contato){
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtEmail.setText(contato.getEmail());
        txtTelefone.setText(contato.getTelefone());
        txtLinkedin.setText(contato.getLinkedin());
        txtObs.setText(contato.getObs());
        if(contato.getImgFoto() != null)
            //Transforma um byte[] em um bitmap
            imgFoto.setImageBitmap(Imagem.arrayToBitmap(contato.getImgFoto()));

        this.contato = contato;
    }

    public boolean verificaCamposVazios(){
        boolean semErros = true;

        if(txtNome.getText().toString().isEmpty() || !ValidaCaracteres.isValidName(txtNome.getText().toString())){
            layoutTxtNome.setErrorEnabled(true);
            layoutTxtNome.setError("Por favor informe um nome válido.");
            semErros = false;
        }else{
            layoutTxtNome.setErrorEnabled(false);
        }
        if(txtEndereco.getText().toString().isEmpty() || !ValidaCaracteres.isValidEndereco(txtEndereco.getText().toString())){
            layoutTxtEndereco.setErrorEnabled(true);
            layoutTxtEndereco.setError("Por favor informe um endereço válido.");
            semErros = false;
        }else{
            layoutTxtEndereco.setErrorEnabled(false);
        }
        if(txtEmail.getText().toString().isEmpty() || !ValidaCaracteres.isValidEmail(txtEmail.getText().toString())){
            layoutTxtEmail.setErrorEnabled(true);
            layoutTxtEmail.setError("Por favor informe um email válido.");
            semErros = false;
        }else{
            layoutTxtEmail.setErrorEnabled(false);
        }
        if(txtTelefone.getText().toString().isEmpty() || !ValidaCaracteres.isValidFone(txtTelefone.getText().toString())){
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Por favor informe um telefone válido.");
            semErros = false;
        }else{
            layoutTxtTelefone.setErrorEnabled(false);
        }
        if(txtLinkedin.getText().toString().isEmpty()){
            layoutTxtLinkedin.setErrorEnabled(true);
            layoutTxtLinkedin.setError("Por favor informe o linkedin.");
            semErros = false;
        }else{
            layoutTxtLinkedin.setErrorEnabled(false);
        }
        return semErros;
    }


}
