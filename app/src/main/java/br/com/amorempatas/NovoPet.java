package br.com.amorempatas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NovoPet extends Activity {
    Button btnSavePet , btnFoto ;
    EditText edNome , edSexo , edRaca , edIdade , edDescricao;
    ImageView imgFoto;
    final static int cameraData = 0;
    private PetDao datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pet);

        datasource = new PetDao(this);

        edNome = (EditText)findViewById(R.id.edNomePet);
        edSexo = (EditText)findViewById(R.id.edSexoPet);
        edRaca = (EditText)findViewById(R.id.edRaca);
        edIdade = (EditText)findViewById(R.id.edIdade);
        edDescricao = (EditText)findViewById(R.id.edDescricao);
        imgFoto = (ImageView)findViewById(R.id.imgFoto);


        btnFoto = (Button)findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,cameraData);
            }
        });


        btnSavePet = (Button)findViewById(R.id.btnSavePet);
        btnSavePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datasource.open();
                Pet p = datasource.insert(edNome.getText().toString() , edSexo.getText().toString() , edRaca.getText().toString() , Integer.parseInt(edIdade.getText().toString()) , edDescricao.getText().toString() , loadBitmapFromView(imgFoto) );
                datasource.close();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(NovoPet.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("O novo amiguinho " + p.getNome() + " foi cadatrado com sucesso!");
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

    private Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width , v.getLayoutParams().height , Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0,0,v.getLayoutParams().width,v.getLayoutParams().height);
        v.draw(c);
        return  b;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap)extras.get("data");
            imgFoto.setImageBitmap(bmp);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
