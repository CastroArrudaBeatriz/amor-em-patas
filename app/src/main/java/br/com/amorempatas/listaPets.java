package br.com.amorempatas;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class listaPets extends ListActivity {
    Button btnNovoPet;
    ListAdapter adapter;
    PetDao datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pets);

        datasource = new PetDao(this);
        datasource.open();
        Cursor cursor = datasource.getPets();

        String[] columns = new String[]{"nome","sexo"};
        int[] to = new int[]{R.id.nomePet , R.id.sexoPet};

        adapter = new SimpleCursorAdapter(this,R.layout.pet_list_item,cursor,columns,to);
        this.setListAdapter(adapter);
        datasource.close();



        btnNovoPet = (Button)findViewById(R.id.btnNovoPet);
        btnNovoPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNovoPet();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        datasource.open();
        Cursor cursor = datasource.getPets();
        String[] columns = new String[]{"nome","sexo"};
        int[] to = new int[]{R.id.nomePet , R.id.sexoPet};
        adapter = new SimpleCursorAdapter(this,R.layout.pet_list_item,cursor,columns,to);
        this.setListAdapter(adapter);
        datasource.close();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(this, PetDetail.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("idPet",cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }

    private void goNovoPet() {
        Intent intent = new Intent(this, NovoPet.class);
        startActivity(intent);
    }
}
