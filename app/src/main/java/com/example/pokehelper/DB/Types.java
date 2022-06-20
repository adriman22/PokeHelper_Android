package com.example.pokehelper.DB;

import java.util.Arrays;

public class Types {
    private String name;
    private byte[] image;

    public Types() {
    }

    public Types(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Types{" +
                "name='" + name + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
