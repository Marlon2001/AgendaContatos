package br.senai.sp.agendacontatos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class Cadastro extends AppCompatActivity {
    private CadastroContatoHelper helper;
    private String caminhoFoto;
    private Button btnOpenCamera;
    private Button btnOpenGalery;
    private ImageView imageViewFoto;

    private static final int GET_PHOTO_CAMERA = 5341;
    private static final int GET_PHOTO_GALERY = 3412;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        helper = new CadastroContatoHelper(this);
        btnOpenGalery = findViewById(R.id.btn_galery);
        btnOpenCamera = findViewById(R.id.btn_camera);
        imageViewFoto = findViewById(R.id.img_foto);

        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");
        if (contato != null)
            helper.preencherCampos(contato);

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent abrirCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            String nomeArquivo = "/IMG_" + System.currentTimeMillis() + ".jpg";
            caminhoFoto = getExternalFilesDir(null) + nomeArquivo;

            File arquivoFoto = new File(caminhoFoto);

            Uri fotoUri = FileProvider.getUriForFile(
                    Cadastro.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    arquivoFoto
            );

            abrirCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(abrirCamera, GET_PHOTO_CAMERA);
            }
        });

        btnOpenGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirGaleria = new Intent(Intent.ACTION_GET_CONTENT);
                abrirGaleria.setType("image/*");
                startActivityForResult(abrirGaleria, GET_PHOTO_GALERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            try {
                switch (requestCode) {
                    case GET_PHOTO_CAMERA:
                        Bitmap bitmapFactory = BitmapFactory.decodeFile(caminhoFoto);
                        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmapFactory, 700, 400, true);
                        imageViewFoto.setImageBitmap(bitmapReduzido);
                        break;
                    case GET_PHOTO_GALERY:
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageViewFoto.setImageBitmap(bitmap);
                        break;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
            case R.id.btn_deletar:
                final Contato c = helper.getContato();
                final ContatoDAO d = new ContatoDAO(this);

                if(c.getId() != 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Deletar contato");
                    builder.setMessage("Tem certeza que deseja excluir o contato "+c.getNome()+"?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            d.excluir(c);
                            Toast.makeText(Cadastro.this, "Contato "+c.getNome()+" excluido com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                            d.close();
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                }else {
                    Toast.makeText(this, "Contato não cadastrado no banco!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
