package br.com.amorempatas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioDetail extends ActionBarActivity implements View.OnClickListener {

    Button btnSave , btnDelete ;
    Button btnClose;
    Button btnVisualiarUsuarios;
    EditText editTextNome;
    EditText editTextCpf;
    EditText editTextTelefone;
    EditText editTextEmail;

    private int _Usuario_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detail);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnClose = (Button)findViewById(R.id.btnClose);
        btnVisualiarUsuarios = (Button)findViewById(R.id.btnVisualizarUsu);

        editTextNome = (EditText)findViewById(R.id.editTextNome);
        editTextCpf = (EditText)findViewById(R.id.editTextCpf);
        editTextTelefone = (EditText)findViewById(R.id.editTextPhone);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnVisualiarUsuarios.setOnClickListener(this);

        _Usuario_Id=0;
        Intent intent = getIntent();
        _Usuario_Id = intent.getIntExtra("usuario_Id" , 0);
        UsuarioDao usuarioDao = new UsuarioDao(this);
        Usuario usuario = new Usuario();
        usuario = usuarioDao.getUsuarioById(_Usuario_Id);

        if (editTextNome.getText() != null && editTextCpf.getText() != null && editTextTelefone.getText() != null && editTextEmail.getText() != null){

            editTextNome.setText(String.valueOf(usuario.nome));
            editTextCpf.setText(String.valueOf(usuario.cpf));
            editTextTelefone.setText(String.valueOf(usuario.telefone));
            editTextEmail.setText(String.valueOf(usuario.email));


        }





    }



    public void onClick(View view){

        if (view == findViewById(R.id.btnSave)){
            UsuarioDao usuarioDao = new UsuarioDao(this);
            Usuario usuario = new Usuario();
            usuario.nome = editTextNome.getText().toString();
            usuario.cpf = editTextCpf.getText().toString();
            usuario.telefone = editTextTelefone.getText().toString();
            usuario.email = editTextEmail.getText().toString();
            usuario.usuario_ID = _Usuario_Id;

            if (_Usuario_Id==0){

                _Usuario_Id = usuarioDao.insert(usuario);
                Toast.makeText(this,"Usuario cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
            }else {
                usuarioDao.update(usuario);
                Toast.makeText(this,"Suas alterações foram salvas!",Toast.LENGTH_SHORT).show();
            }


        }else if (view == findViewById(R.id.btnDelete)){

            UsuarioDao usuarioDao = new UsuarioDao(this);
            usuarioDao.delete(_Usuario_Id);
            Toast.makeText(this,"Usuario deletado!",Toast.LENGTH_SHORT).show();
            finish();

        }else if( view == findViewById(R.id.btnClose)){
            goTelaEscolha();
            finish();
        }else if(view == findViewById(R.id.btnVisualizarUsu)){
            goMainActivityListaUsuarios();
        }

    }

    private void goTelaEscolha() {

        Intent intent = new Intent(this, telaEscolha.class);
        startActivity(intent);
    }

    private void goMainActivityListaUsuarios(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
