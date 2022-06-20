package com.example.pokehelper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokehelper.DB.DB;
import com.example.pokehelper.DB.SQLines;
import com.example.pokehelper.DB.Pokemon;
import com.example.pokehelper.DB.Types;
import com.example.pokehelper.R;
import com.example.pokehelper.utils.WebUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LoadingScreen extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLines sqlRepo;

    private int lastPoke=809; //809 Pokemon existen
    private int pokesLeft=0;

    private List<Pokemon> pokeList;
    private List<Types> typesList;

    private ProgressBar progressBar;
    private TextView progressText;
    private Double percentageLoaded = 0.0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        progressBar = findViewById(R.id.progress_horizontal);
        progressText = findViewById(R.id.progressText);

        pokeList = new ArrayList<>();
        typesList = new ArrayList<>();

        db = DB.getDB(this);
        sqlRepo = new SQLines(db);

        Cursor c = sqlRepo.showDB();
        if(c.getCount() == 0){//comprobar que la base de datos tenga o no registros
            fillDB();
        }else{//si ya tiene datos nos saltamos la carga
            openTypeListActivity();
            //fillDB();
        }
    }

    private void fillDB(){
        showToast();
        getPokesData();
    }

    private void getPokesData(){
        for(int i = 1; i<=lastPoke; i++){
            progressBar.setMax(lastPoke);
            obtenerWeb(i);
        }
    }

    private void obtenerWeb(int numeroPokedex){ //con esto recupero el documento que luego procesare en otra clase
        new Thread(new Runnable() {
            @Override
            public void run() {
                String URL = "https://www.pokexperto.net/index2.php?seccion=nds/nationaldex/stats&pk=" + numeroPokedex;
                try {
                    Document document = Jsoup.connect(URL).get();
                    sqlRepo.insertPoke(WebUtils.getPokemonFromWeb(document));
                    sqlRepo.insertStats(WebUtils.getStatsPokemon(document));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> { //cuando un hilo acaba resto 1 al total hasta llegar a 0
                    pokesLeft++;
                    progressBar.setProgress(pokesLeft); //con esto controlo la barra de carga
                    percentageLoaded += (double)100/lastPoke;
                    progressText.setText("Loading Pokemon: "+df.format(percentageLoaded)+"%");
                    if (pokesLeft == lastPoke) { //cuando acabo con los pokemon empiezo con los tipos
                        progressText.setText("Loading Types: "+67.8+"%");
                        progressBar.setMax(100);
                        progressBar.setProgress(68);
                        obtenerWebTypes();
                    }
                });
            }
        }).start();
    }

    private void obtenerWebTypes(){ //con esto recupero el documento que luego procesare en otra clase
        new Thread(new Runnable() {
            @Override
            public void run() {
                String URL = "https://www.pokexperto.net/index2.php?seccion=general/efec";
                try {
                    Document document = Jsoup.connect(URL).get();
                    typesList = WebUtils.getTypesFromWeb(document);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    insertTypes();
                });
            }
        }).start();
    }

    private void insertTypes(){ //ordeno la lista y luego la inserto
        for(Types type : typesList){
            sqlRepo.insertType(type);
        }
        openTypeListActivity();
    }
    private void showToast(){
        Toast toast1 = Toast.makeText(getApplicationContext(),"Cargando datos de los Pokemon\nSolo sera necesario la 1º vez",Toast.LENGTH_LONG);
        toast1.show();
    }

    private void openTypeListActivity(){
        startActivity(new Intent(this, TypeList.class));
        finish();
    }
}