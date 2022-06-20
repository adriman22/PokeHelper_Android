package com.example.pokehelper.DB;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Pokemon {
    private int pokedex;
    private String name;
    private String tipos;
    private byte[] image;
    private int favorito;

    public Pokemon (){
    }

    public Pokemon(int pokedex, String name, String tipos, byte[] image, int favorito) {
        this.pokedex = pokedex;
        this.name = name;
        this.tipos = tipos;
        this.image = image;
        this.favorito = favorito;
    }

    public int getPokedex() {
        return pokedex;
    }

    public void setPokedex(int pokedex) {
        this.pokedex = pokedex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipos() {
        return tipos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokedex=" + pokedex +
                ", name='" + name + '\'' +
                ", tipos='" + tipos + '\'' +
                ", image=" + Arrays.toString(image) +
                ", favorito=" + favorito +
                '}';
    }
}
