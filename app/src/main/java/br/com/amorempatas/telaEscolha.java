package br.com.amorempatas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class telaEscolha extends AppCompatActivity {

    Button btnUsuario , btnPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tela_escolha);

        btnUsuario = (Button)findViewById(R.id.btnUsuario);
        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goUsuarioDetail();
            }
        });


        btnPet = (Button)findViewById(R.id.btnPet);
        btnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goListaPet();
            }
        });
    }

    private void goUsuarioDetail() {
        Intent intent = new Intent(this, UsuarioDetail.class);
        intent.putExtra("usuario_Id",0);
        startActivity(intent);
    }

    private void goListaPet() {
        Intent intent = new Intent(this, listaPets.class);
        intent.putExtra("idPet",0);
        startActivity(intent);
    }
}
