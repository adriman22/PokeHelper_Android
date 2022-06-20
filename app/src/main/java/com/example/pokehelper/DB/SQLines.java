package com.example.pokehelper.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Locale;

public class SQLines {

    private static SQLiteDatabase sqLiteDatabase;

    public SQLines(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void insertPoke(Pokemon p1){
        ContentValues cv = new  ContentValues();
        cv.put(DB_Keys.COL_POKEDEX, p1.getPokedex());
        cv.put(DB_Keys.COL_NAME, firstLetterToUpperCase(p1.getName()));
        cv.put(DB_Keys.COL_TYPES, p1.getTipos());
        cv.put(DB_Keys.COL_IMG, p1.getImage());
        cv.put(DB_Keys.COL_FAVORITO, 0);
        sqLiteDatabase.insert(DB_Keys.TABLE_POKEDEX, null, cv);
    }

    public void insertType(Types t1){
        ContentValues cv = new  ContentValues();
        cv.put(DB_Keys.COL_NAME, firstLetterToUpperCase(t1.getName()));
        cv.put(DB_Keys.COL_IMG, t1.getImage());
        sqLiteDatabase.insert(DB_Keys.TABLE_TYPES, null, cv);
    }

    public void insertStats(StatsPokemon s1){
        ContentValues cv = new  ContentValues();
        cv.put(DB_Keys.COL_POKEDEX, s1.getPokemonId());
        cv.put(DB_Keys.COL_PS, s1.getPs());
        cv.put(DB_Keys.COL_ATQ, s1.getAtq());
        cv.put(DB_Keys.COL_DEF, s1.getDef());
        cv.put(DB_Keys.COL_SP_ATQ, s1.getSp_atq());
        cv.put(DB_Keys.COL_SP_DEF, s1.getSp_def());
        cv.put(DB_Keys.COL_VEL, s1.getVel());
        cv.put(DB_Keys.COL_TOTAL, s1.getTotal());
        sqLiteDatabase.insert(DB_Keys.TABLE_STATS, null, cv);
    }

    public Cursor showDB(){
        String[] campos = new String[]{DB_Keys.COL_POKEDEX, DB_Keys.COL_NAME, DB_Keys.COL_TYPES, DB_Keys.COL_IMG};
        String[] args = new String[]{"%"+"%"};
        String where = DB_Keys.COL_NAME;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_POKEDEX,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                DB_Keys.COL_POKEDEX                        //Clausula ORDER BY
        );

        return c;
    }

    public Cursor showDBTypes(){
        String[] campos = new String[]{DB_Keys.COL_NAME, DB_Keys.COL_IMG};
        String[] args = new String[]{"%"+"%"};
        String where = DB_Keys.COL_NAME;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_TYPES,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                null                      //Clausula ORDER BY
        );

        return c;
    }

    public Cursor searchPokeByType(String type){
        String[] campos = new String[]{DB_Keys.COL_POKEDEX, DB_Keys.COL_NAME, DB_Keys.COL_TYPES, DB_Keys.COL_IMG, DB_Keys.COL_FAVORITO};
        String[] args = new String[]{"%"+type+"%"};
        String where = DB_Keys.COL_TYPES;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_POKEDEX,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                DB_Keys.COL_POKEDEX                 //Clausula ORDER BY
        );

        return c;
    }

    public Cursor searchPokeByName(String name){
        String[] campos = new String[]{DB_Keys.COL_POKEDEX, DB_Keys.COL_NAME, DB_Keys.COL_TYPES, DB_Keys.COL_IMG, DB_Keys.COL_FAVORITO};
        String[] args = new String[]{"%"+name+"%"};
        String where = DB_Keys.COL_NAME;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_POKEDEX,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                DB_Keys.COL_POKEDEX                 //Clausula ORDER BY
        );

        return c;
    }

    public Cursor searchPokeByFavorite(){
        String[] campos = new String[]{DB_Keys.COL_POKEDEX, DB_Keys.COL_NAME, DB_Keys.COL_TYPES, DB_Keys.COL_IMG, DB_Keys.COL_FAVORITO};
        String[] args = new String[]{"%"+1+"%"};
        String where = DB_Keys.COL_FAVORITO;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_POKEDEX,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                DB_Keys.COL_POKEDEX                 //Clausula ORDER BY
        );

        return c;
    }

    public Cursor searchTypesByName(String name){
        String[] campos = new String[]{DB_Keys.COL_NAME, DB_Keys.COL_IMG};
        String[] args = new String[]{"%"+name+"%"};
        String where = DB_Keys.COL_NAME;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_TYPES,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                null               //Clausula ORDER BY
        );

        return c;
    }

    public Cursor searchStatsById(int pokedex){
        String[] campos = new String[]{DB_Keys.COL_POKEDEX, DB_Keys.COL_PS, DB_Keys.COL_ATQ, DB_Keys.COL_DEF, DB_Keys.COL_SP_ATQ, DB_Keys.COL_SP_DEF, DB_Keys.COL_VEL, DB_Keys.COL_TOTAL};
        String[] args = new String[]{""+pokedex};
        String where = DB_Keys.COL_POKEDEX;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_STATS,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                null               //Clausula ORDER BY
        );

        return c;
    }

    private String firstLetterToUpperCase(String str){
        StringBuilder result = new StringBuilder();
        result.append((""+str.charAt(0)).toUpperCase());
        result.append(str.substring(1));

        return result.toString();
    }

    public int isNameFavorito(String name){
        String[] campos = new String[]{DB_Keys.COL_FAVORITO};
        String[] args = new String[]{"%"+name+"%"};
        String where = DB_Keys.COL_NAME;

        Cursor c = sqLiteDatabase.query(
                DB_Keys.TABLE_POKEDEX,              //Nombre de la tabla
                campos,                             //Lista de columnas a consultar (las que devuelve)
                where+" LIKE ?",            //Columna donde busca
                args,                               //Valor que busca
                null,                       //Como se agrupan (GROUP BY)
                null,                        //Condicion HAVING para el GROUP BY
                null               //Clausula ORDER BY
        );

        while(c.moveToNext()){
            if(c.getInt(0)==1){
                takeOutNameFavorito(name);
                return 0;
            }else{
                makeNameFavorito(name);
                return 1;
            }
        }
        return 0;
    }

    public void makeNameFavorito(String name){
        String[] args = new String []{ name};
        ContentValues cv = new  ContentValues();
        cv.put(DB_Keys.COL_FAVORITO, 1);
        sqLiteDatabase.update(DB_Keys.TABLE_POKEDEX, cv, DB_Keys.COL_NAME+"=?", args);
    }

    public void takeOutNameFavorito(String name){
        String[] args = new String []{ name};
        ContentValues cv = new  ContentValues();
        cv.put(DB_Keys.COL_FAVORITO, 0);
        sqLiteDatabase.update(DB_Keys.TABLE_POKEDEX, cv, DB_Keys.COL_NAME+"=?", args);
    }
}
