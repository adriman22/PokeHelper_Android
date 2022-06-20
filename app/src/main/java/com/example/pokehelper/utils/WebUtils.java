package com.example.pokehelper.utils;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pokehelper.DB.DB;
import com.example.pokehelper.DB.Pokemon;
import com.example.pokehelper.DB.SQLines;
import com.example.pokehelper.DB.StatsPokemon;
import com.example.pokehelper.DB.Types;
import com.example.pokehelper.activities.TypeList;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WebUtils {

    public static Pokemon getPokemonFromWeb(Document document){
        Pokemon p1 = new Pokemon();
        StringBuilder tiposStr = new StringBuilder();

        //usa la clase amarillo para dar estilo al numero de pokedex
        Element pokedexNumber = document.getElementsByClass("amarillo").get(0);
        //usar la clase mini para dar estilo al nombre del pokemon
        Element nombrePokemon = document.getElementsByClass("mini").get(0);

        //desde aqui sacare todos los demas datos que necesite
        Element body = document.getElementsByClass("pkmain").get(0);

        //imagen del pokemon
        Element imagenPokemon = body.getElementsByClass("bordedcho").get(0).getElementsByTag("img").get(0);

        //los 3 contenedores con datos
        Elements datos = body.getElementsByClass("bordeambos");

        //el contenedor "datos basicos"
        Element datosBasicos = datos.get(0);

        //busco las imagenes dentro de "datos basicos" ya que estas son las imagenes de los tipos
        Elements imagenesTipos = datosBasicos.getElementsByTag("img");

        //para cada imagen que haya le saco el alt que corresponde al nombre del tipo
        for (Element imagen : imagenesTipos){
            tiposStr.append(imagen.attr("alt")).append("\n");
        }

        //guardo todos los datos para poder mostrarlos
        p1.setTipos(tiposStr.toString());
        p1.setPokedex(Integer.parseInt(pokedexNumber.text()));
        p1.setName(nombrePokemon.text());
        p1.setImage(urlToByte(imagenPokemon.attr("src")));

        return p1;
    }

    public static StatsPokemon getStatsPokemon(Document document){
        Element pokedexNumber = document.getElementsByClass("amarillo").get(0);

        Element body = document.getElementsByClass("pkmain").get(3);
        Elements stats = body.getElementsByClass("right");

        List<Integer> statsList = new ArrayList<>();
        statsList.add(Integer.parseInt(pokedexNumber.text()));
        for(int i = 0; i <= 10; i+=2){
            statsList.add(Integer.parseInt(stats.get(i).text()));
        }

        return new StatsPokemon(statsList.get(0),statsList.get(1),statsList.get(2),statsList.get(3),statsList.get(4),statsList.get(5),statsList.get(6));

    }

    private static byte[] urlToByte(String url){
        byte[] bytes = null;
        url = "https://www.pokexperto.net/"+url;

        try {
            InputStream is= new java.net.URL(url).openStream();
            bytes = IOUtils.toByteArray(is);
        } catch (Exception e) {
            Log.e("MyApp", e.getMessage());
        }

        return bytes;
    }

    public static List<Types> getTypesFromWeb(Document document) {
        List<Types> typesList = new ArrayList<>();

        Element tablaTipos = document.getElementsByClass("pkmain").get(0);
        Elements imagenesTipos = tablaTipos.getElementsByTag("img");
        for (Element imagen : imagenesTipos){
            String url = imagen.attr("src");
            if(url.indexOf("-")==-1){
                String nombreTipo = url.substring(18, url.length()-4);
                typesList.add(new Types(nombreTipo, urlToByte(url)));
            }
        }
        return typesList;
    }
}
