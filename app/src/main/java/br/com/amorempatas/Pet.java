package br.com.amorempatas;

import android.graphics.Bitmap;

/**
 * Created by beatriz on 11/11/2017.
 */

public class Pet {

    public static final String PET_TABLE = "pet";

    public static final String KEY_ID = "_id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_SEXO = "sexo";
    public static final String KEY_RACA = "raca";
    public static final String KEY_IDADE = "idade";
    public static final String KEY_DESCRICAO = "descricao";
    public static final String KEY_FOTO = "foto";

    public long _id;
    public String nome;
    public String sexo;
    public String raca;
    public int idade;
    public String descricao;
    public Bitmap foto;



    public Pet(long pet_ID , String nome , String sexo , String raca , int idade , String descricao){
        this._id = pet_ID;
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.idade = idade;
        this.descricao = descricao;
    }

    public Pet(long pet_ID , String nome , String sexo , String raca , int idade , String descricao , Bitmap foto){

        this._id = pet_ID;
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.idade = idade;
        this.descricao = descricao;
        this.foto = foto;
    }

    public long getPet_ID() {
        return _id;
    }

    public void setPet_ID(long pet_ID) {
        this._id = pet_ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
