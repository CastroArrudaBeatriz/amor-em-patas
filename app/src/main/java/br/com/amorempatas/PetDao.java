package br.com.amorempatas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by beatriz on 14/11/2017.
 */

public class PetDao {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allcolumns = { Pet.KEY_ID , Pet.KEY_NOME , Pet.KEY_SEXO , Pet.KEY_RACA , Pet.KEY_IDADE , Pet.KEY_DESCRICAO , Pet.KEY_FOTO};

    public  PetDao(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Pet insert(String nome , String sexo , String raca , int idade , String descricao , Bitmap foto){

        ContentValues values = new ContentValues();
        values.put(Pet.KEY_NOME , nome);
        values.put(Pet.KEY_SEXO , sexo);
        values.put(Pet.KEY_RACA , raca);
        values.put(Pet.KEY_IDADE , idade);
        values.put(Pet.KEY_DESCRICAO , descricao);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        foto.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] photo = baos.toByteArray();
        values.put(Pet.KEY_FOTO,photo);
        long insertId = database.insert(Pet.PET_TABLE,null,values);

        Cursor cursor = database.query(Pet.PET_TABLE,allcolumns,Pet.KEY_ID + " = " + insertId, null , null , null , null);
        cursor.moveToFirst();
        return cursorToPet(cursor);

    }


    public void delete (int idPet){

        database.delete(Pet.PET_TABLE, Pet.KEY_ID + " = " + idPet , null);
    }

    private Pet cursorToPet(Cursor cursor) {
        byte[] blob = cursor.getBlob(cursor.getColumnIndex(Pet.KEY_FOTO));
        Bitmap bmp = BitmapFactory.decodeByteArray(blob,0,blob.length);
        Pet pet = new Pet(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5),bmp);
        return pet;
    }

    public Cursor getPets(){
        Cursor cursor = database.rawQuery("select _id , nome , sexo , raca ,idade , descricao , foto from pet ",null);
        return cursor;
    }

    public Pet getPet(int idPet){
        Cursor cursor = database.query( Pet.PET_TABLE , allcolumns , Pet.KEY_ID + " = " + idPet , null ,null ,null ,null);
        cursor.moveToFirst();
        return cursorToPet(cursor);
    }

    public void editar (int idPet , ContentValues contentValues){
        database.update(Pet.PET_TABLE, contentValues , Pet.KEY_ID + " = " + idPet , null);
    }


}
