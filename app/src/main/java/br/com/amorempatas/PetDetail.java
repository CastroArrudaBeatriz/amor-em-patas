package br.com.amorempatas;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PetDetail extends AppCompatActivity {
    int idPet ;
    PetDao datasource;
    Pet pet;
    TextView nomePet , sexo , raca , idade , descricao;
    Button btnVoltarLista , btnExcluir;
    ImageView fotoPet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);


        nomePet = (TextView)findViewById(R.id.txtNomePetDetail);
        sexo = (TextView)findViewById(R.id.txtSexoPetDetail);
        raca = (TextView)findViewById(R.id.txtRacaPetDetail);
        idade = (TextView)findViewById(R.id.txtIdadePetDetail);
        descricao = (TextView)findViewById(R.id.txtDescricaoPetDetail);
        fotoPet = (ImageView)findViewById(R.id.ivFotoDetail);

        carregaDetalhesPet();

        btnVoltarLista = (Button)findViewById(R.id.btnVoltarListaPet);
        btnVoltarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnExcluir = (Button)findViewById(R.id.btnExcluiPet);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datasource.open();
                datasource.delete(idPet);
                datasource.close();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(PetDetail.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("O amiguinho foi deletado de nosso cadastro!");
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialogo.show();
            }
        });


    }

    private void carregaDetalhesPet(){
        idPet = getIntent().getIntExtra("idPet",0);
        datasource = new PetDao(this);
        datasource.open();

        pet = datasource.getPet(idPet);

        datasource.close();
        fotoPet.setImageBitmap(pet.getFoto());
        nomePet.setText(pet.getNome());
        sexo.setText(pet.getSexo());
        raca.setText(pet.getRaca());
        idade.setText(Integer.toString(pet.getIdade()));
        descricao.setText(pet.getDescricao());

    }
}
