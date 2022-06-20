package com.example.pokehelper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pokehelper.DB.DB;
import com.example.pokehelper.DB.DB_Keys;
import com.example.pokehelper.DB.Pokemon;
import com.example.pokehelper.DB.SQLines;
import com.example.pokehelper.utils.getTypeData;
import com.example.pokehelper.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokedexData extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLines sqlRepo;

    private Pokemon poke;

    private ImageView pokeImg;
    private TextView pokeName;
    private ImageView tipo1;
    private ImageView tipo2;
    private ImageButton showTypes;
    private ImageButton showStats;
    private View pokedexTypes;
    private View pokedexStats;
    private ImageButton favorito;

    private TextView ps;
    private TextView atq;
    private TextView def;
    private TextView sp_atq;
    private TextView sp_def;
    private TextView vel;
    private TextView total;
    private LinearLayout holder;

    private List<String> weak;
    private List<String> resist;
    private List<String> strong;
    private List<String> immune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_data);

        pokeImg = findViewById(R.id.PokeImg);
        tipo1 = findViewById(R.id.Tipo1);
        tipo2 = findViewById(R.id.Tipo2);
        pokeName = findViewById(R.id.PokeName);
        showTypes = findViewById(R.id.showTypes);
        showStats = findViewById(R.id.showStats);
        pokedexTypes = findViewById(R.id.pokedexTypes);
        pokedexStats = findViewById(R.id.pokedexStats);
        favorito = findViewById(R.id.favorito);

        ps = findViewById(R.id.ps);
        atq = findViewById(R.id.atq);
        def = findViewById(R.id.def);
        sp_atq =findViewById(R.id.sp_atq);
        sp_def = findViewById(R.id.sp_def);
        vel = findViewById(R.id.vel);
        total = findViewById(R.id.total);
        holder = findViewById(R.id.PokeTypes);

        weak = new ArrayList<>();
        resist = new ArrayList<>();
        strong = new ArrayList<>();
        immune = new ArrayList<>();
        poke = new Pokemon();

        Intent intent = getIntent();
        String nameToSearch = intent.getStringExtra(DB_Keys.COL_NAME);

        db = DB.getDB(null);
        sqlRepo = new SQLines(db);

        Cursor c = sqlRepo.searchPokeByName(nameToSearch);
        while(c.moveToNext()){
            poke = new Pokemon(c.getInt(0),c.getString(1),c.getString(2),c.getBlob(3), c.getInt(4));
        }

        pokeImg.setImageBitmap(getBitmap(poke.getImage()));
        pokeName.setText(String.format("%03d" , poke.getPokedex())+"-"+poke.getName());

        if(poke.getFavorito()==1){
            favorito.setImageResource(R.drawable.corazonfull);
        } else {
            favorito.setImageResource(R.drawable.corazonempty);
        }

        printPokemonTypes(poke.getTipos().split("\n" ));

        printTypeAdvantages();

        printStatsPokemon(poke.getPokedex());

        showTypes.setOnClickListener(view -> {
            pokedexTypes.setVisibility(View.VISIBLE);
            pokedexStats.setVisibility(View.GONE);
        });
        showStats.setOnClickListener(view -> {
            pokedexTypes.setVisibility(View.GONE);
            pokedexStats.setVisibility(View.VISIBLE);
        });
        favorito.setOnClickListener(view -> {
            if(sqlRepo.isNameFavorito(poke.getName())==1){
                favorito.setImageResource(R.drawable.corazonfull);
            } else {
                favorito.setImageResource(R.drawable.corazonempty);
            }
        });
    }

    private void printPokemonTypes(String[] typesList){
        Cursor c = sqlRepo.searchTypesByName(typesList[0]);
        while(c.moveToNext()){
            tipo1.setImageBitmap(getBitmap(c.getBlob(1)));
            getTypeData(c.getString(0));
        }
        if(typesList.length > 1){
            c = sqlRepo.searchTypesByName(typesList[1]);
            while(c.moveToNext()){
                tipo2.setImageBitmap(getBitmap(c.getBlob(1)));
                getTypeData(c.getString(0));
            }
        }
    }

    private Bitmap getBitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private void getTypeData(String str){
        weak.addAll(getTypeData.getWeak(str));
        resist.addAll(getTypeData.getResist(str));
        strong.addAll(getTypeData.getStrong(str));
        immune.addAll(getTypeData.getImmune(str));

        removeIncoherence();
    }

    private void removeIncoherence(){
        List<String> toRemove = typesToRemove(weak,resist);
        for (String typeToRemove : toRemove){
            weak.remove(typeToRemove);
            resist.remove(typeToRemove);
        }
        toRemove = typesToRemove(weak,immune);
        for (String typeToRemove : toRemove){
            weak.remove(typeToRemove);
        }
        toRemove = typesToRemove(resist,immune);
        for (String typeToRemove : toRemove){
            resist.remove(typeToRemove);
        }
    }

    private List<String> typesToRemove(List<String> list1, List<String> list2){
        List<String> toRemove = new ArrayList<>();
        for (String typeToCheck : list1){
            if(list2.contains(typeToCheck)){
                toRemove.add(typeToCheck);
            }
        }
        return toRemove;
    }

    private void printTypeAdvantages(){
        //weak
        holder = findViewById(R.id.weakLayout);
        weak = cleanDuplicates(weak);
        for(String str : weak) {
            printType(str);
            if(holder.getChildCount()>6){
                holder = findViewById(R.id.weakLayout2);
            }
        }

        //resist
        holder = findViewById(R.id.resistLayout);
        resist = cleanDuplicates(resist);
        for(String str : resist) {
            printType(str);
            if(holder.getChildCount()>6){
                holder = findViewById(R.id.resistLayout2);
            }
        }

        //strong
        holder = findViewById(R.id.strongLayout);
        strong = cleanDuplicates(strong);
        for(String str : strong) {
            printType(str);
            if(holder.getChildCount()>6){
                holder = findViewById(R.id.strongLayout2);
            }
        }

        //immune
        holder = findViewById(R.id.immuneLayout);
        for(String str : immune) printType(str);
    }

    private List<String> cleanDuplicates(List<String> toClean){
        for (int i = 0; i < (toClean.size()-1); i++){
            for (int y = (i+1); y < toClean.size(); y++){
                if(toClean.get(i).equals(toClean.get(y))){
                    printx2(toClean.get(i));
                    toClean.remove(y);
                    toClean.remove(i);
                    i=-1;
                    break;
                }
            }

        }
        return toClean;
    }

    private void printx2(String str){
        TextView x2 = new TextView(this); //create a new TextView
        x2.setText(R.string.doble); //the text

        //Resize the text
        x2.setTextSize(getResources().getDimension(R.dimen.x2Text)); //the useless dimen again
        x2.setTypeface(null, Typeface.BOLD);

        holder.addView(x2); //print x2 and img
        printType(str);
    }

    private void printType(String typeStr){
        Bitmap bMap = null;
        Cursor c = sqlRepo.searchTypesByName(typeStr);
        while(c.moveToNext()){
            bMap = getBitmap(c.getBlob(1));
            bMap = Bitmap.createScaledBitmap(bMap, 147, 56, true); //resize the image
        }
        ImageView imageNew = new ImageView(this); //creating a new imageView
        imageNew.setImageBitmap(bMap); //set the resized img
        holder.addView(imageNew);//put the new imgView in the layout
    }

    private void printStatsPokemon(int pokedexId){
        Cursor c = sqlRepo.searchStatsById(pokedexId);
        while(c.moveToNext()){
            Log.i("MyApp", "printStatsPokemon: "+c.getInt(0));
            ps.setText("|\t\t"+c.getInt(1));
            atq.setText("|\t\t"+c.getInt(2));
            def.setText("|\t\t"+c.getInt(3));
            sp_atq.setText("|\t\t"+c.getInt(4));
            sp_def.setText("|\t\t"+c.getInt(5));
            vel.setText("|\t\t"+c.getInt(6));
            total.setText("|\t\t"+c.getInt(7));
        }
    }
}