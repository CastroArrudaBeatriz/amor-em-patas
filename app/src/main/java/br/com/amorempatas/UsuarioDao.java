package br.com.amorempatas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by beatriz on 11/11/2017.
 */

public class UsuarioDao {

    private DBHelper dbHelper;

    public UsuarioDao(Context context){
        dbHelper = new DBHelper(context);
    }

    public int insert(Usuario usuario){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Usuario.KEY_NOME , usuario.nome);
        values.put(Usuario.KEY_CPF , usuario.cpf);
        values.put(Usuario.KEY_TELEFONE , usuario.telefone);
        values.put(Usuario.KEY_EMAIL , usuario.email);

        long usuario_Id = db.insert(Usuario.USUARIO_TABLE, null, values);
        db.close();
        return (int)usuario_Id;
    }



    public void delete(int usuario_Id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Usuario.USUARIO_TABLE, Usuario.KEY_ID + "= ?", new String[]{String.valueOf(usuario_Id)});
        db.close();
    }


    public void update(Usuario usuario){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Usuario.KEY_NOME , usuario.nome);
        values.put(Usuario.KEY_CPF , usuario.cpf);
        values.put(Usuario.KEY_TELEFONE , usuario.telefone);
        values.put(Usuario.KEY_EMAIL , usuario.email);

        db.update(Usuario.USUARIO_TABLE, values, Usuario.KEY_ID + "= ?", new  String[]{String.valueOf(usuario.usuario_ID)});
        db.close();
    }

    public ArrayList<HashMap<String,String>> getUsuarioList(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT " +
                Usuario.KEY_ID + "," +
                Usuario.KEY_NOME + "," +
                Usuario.KEY_CPF + "," +
                Usuario.KEY_TELEFONE + "," +
                Usuario.KEY_EMAIL +
                " FROM " + Usuario.USUARIO_TABLE;

        ArrayList<HashMap<String,String>> usuarioList = new ArrayList<HashMap<String,String>>();

        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){

            do {
                HashMap<String,String> usuario = new HashMap<String,String>();
                usuario.put("id_usuario" , cursor.getString(cursor.getColumnIndex(Usuario.KEY_ID)));
                usuario.put("nome", cursor.getString(cursor.getColumnIndex(Usuario.KEY_NOME)));
                usuarioList.add(usuario);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return usuarioList;
    }



    public Usuario getUsuarioById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Usuario.KEY_ID + "," +
                Usuario.KEY_NOME + "," +
                Usuario.KEY_CPF + "," +
                Usuario.KEY_TELEFONE + "," +
                Usuario.KEY_EMAIL +
                " FROM " + Usuario.USUARIO_TABLE
                + " WHERE " +
                Usuario.KEY_ID + "= ?";

        int iCount =0;
        Usuario usuario = new Usuario();

        Cursor  cursor = db.rawQuery(selectQuery, new String[]{ String.valueOf(Id)});

        if (cursor.moveToFirst()){
            do {
                usuario.usuario_ID = cursor.getInt(cursor.getColumnIndex(Usuario.KEY_ID));
                usuario.nome =cursor.getString(cursor.getColumnIndex(Usuario.KEY_NOME));
                usuario.cpf =cursor.getString(cursor.getColumnIndex(Usuario.KEY_CPF));
                usuario.telefone =cursor.getString(cursor.getColumnIndex(Usuario.KEY_TELEFONE));
                usuario.email =cursor.getString(cursor.getColumnIndex(Usuario.KEY_EMAIL));

            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return usuario;

    }




}
