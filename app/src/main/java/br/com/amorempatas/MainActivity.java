package br.com.amorempatas;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd , btnGetAll , btnVoltar;
    TextView usuario_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button)findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        btnVoltar = (Button)findViewById(R.id.btnVoltarMain);
        btnVoltar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == findViewById(R.id.btnadd)){
            Intent intent = new Intent(this, UsuarioDetail.class);
            intent.putExtra("usuario_Id",0);
            startActivity(intent);
        }else if(view == findViewById(R.id.btnVoltarMain)){

            Intent intent = new Intent(this, UsuarioDetail.class);
            startActivity(intent);

        }else {

            UsuarioDao usuarioDao = new UsuarioDao(this);

            ArrayList<HashMap<String,String>> usuarioList = usuarioDao.getUsuarioList();

            if (usuarioList.size() != 0 ){
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        usuario_Id = (TextView) view.findViewById(R.id.usuario_Id);
                        String usuarioId = usuario_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),UsuarioDetail.class);
                        objIndent.putExtra("usuario_Id" , Integer.parseInt(usuarioId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( MainActivity.this,usuarioList,R.layout.view_usuario_entry,new String[]{"id_usuario","nome"},new int[]{R.id.usuario_Id,R.id.usuario_name});
                setListAdapter(adapter);
            }else {
                Toast.makeText(this,"Não existem usuarios cadastrados!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
