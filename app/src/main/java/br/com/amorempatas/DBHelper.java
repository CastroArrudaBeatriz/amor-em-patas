package br.com.amorempatas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by beatriz on 11/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "pet.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_USUARIO = "CREATE TABLE " + Usuario.USUARIO_TABLE +"("
                                        + Usuario.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                                        + Usuario.KEY_NOME + " TEXT ,"
                                        + Usuario.KEY_CPF + " TEXT ,"
                                        + Usuario.KEY_TELEFONE + " TEXT ,"
                                        + Usuario.KEY_EMAIL + " TEXT)";


        String CREATE_TABLE_PET = "CREATE TABLE " + Pet.PET_TABLE +"("
                + Pet.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Pet.KEY_NOME + " TEXT ,"
                + Pet.KEY_SEXO + " TEXT ,"
                + Pet.KEY_RACA + " TEXT ,"
                + Pet.KEY_IDADE + " INTEGER ,"
                + Pet.KEY_DESCRICAO + " TEXT ,"
                + Pet.KEY_FOTO + " BLOB )";




        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_PET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Usuario.USUARIO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Pet.PET_TABLE);

        onCreate(db);
    }
}
