package com.example.pokehelper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokehelper.DB.DB;
import com.example.pokehelper.DB.DB_Keys;
import com.example.pokehelper.DB.Pokemon;
import com.example.pokehelper.DB.SQLines;
import com.example.pokehelper.DB.Types;
import com.example.pokehelper.R;
import com.example.pokehelper.utils.AdaptadorPokemon;
import com.example.pokehelper.utils.AdaptadorTipos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class PokeList extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLines sqlRepo;

    private RecyclerView recView;
    private AdaptadorPokemon adaptador;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_list);

        Intent intent = getIntent();
        String typeToSearch = intent.getStringExtra(DB_Keys.COL_NAME);

        db = DB.getDB(null);
        sqlRepo = new SQLines(db);
        pokemonList =  new ArrayList<>();

        Log.i("MyApp", "letra0" + typeToSearch.charAt(0));

        if(typeToSearch.charAt(0)=='0'){ //un 0 implica que buscamos por nombre
            Cursor c = sqlRepo.searchPokeByName(typeToSearch.substring(1));
            Log.i("MyApp", "Pokemon Recuperados: " + c.getCount());
            while (c.moveToNext()) {
                pokemonList.add(new Pokemon(c.getInt(0), c.getString(1), c.getString(2), c.getBlob(3), c.getInt(4)));
            }
        } else if(typeToSearch.charAt(0)=='1'){ //un 1 implica que buscamos por favorito
            Cursor c = sqlRepo.searchPokeByFavorite();
            Log.i("MyApp", "Pokemon Recuperados: " + c.getCount());
            while (c.moveToNext()) {
                pokemonList.add(new Pokemon(c.getInt(0), c.getString(1), c.getString(2), c.getBlob(3), c.getInt(4)));
            }
        } else { //lo demas es que buscamos por typo
            Cursor c = sqlRepo.searchPokeByType(typeToSearch);
            Log.i("MyApp", "Pokemon Recuperados: " + c.getCount());
            while (c.moveToNext()) {
                pokemonList.add(new Pokemon(c.getInt(0), c.getString(1), c.getString(2), c.getBlob(3), c.getInt(4)));
            }
        }

        fillRecView();
    }

    private void fillRecView() {
        recView = findViewById(R.id.recView);
        adaptador = new AdaptadorPokemon(pokemonList);

        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adaptador);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recView.addItemDecoration(divider);

        adaptador.setOnClickListener(view -> {
            int pos = recView.getChildAdapterPosition(view);
            openPokedexData(pos);
        });
    }

    private void openPokedexData(int pos){
        Intent intent = new Intent(this, PokedexData.class);
        intent.putExtra(DB_Keys.COL_NAME, pokemonList.get(pos).getName());
        startActivity(intent);
    }

    @Override //fill appBar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu); //fill the appbar

        //configuring the search
        MenuItem menuItem = menu.findItem(R.id.Item_Search); //get the search item
        SearchView searchView = (SearchView) menuItem.getActionView(); //get the searchView from the searchItem
        searchView.setQueryHint("Bulbasaur"); //the hint
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //what we do with the infor
            @Override
            public boolean onQueryTextSubmit(String query) { //when intro clicked
                openPokeListActivity("0"+query); //send a 0 so the program search for names instead of types
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //when typing
                //future update
                return false;
            }
        });

        return true;
    }

    @Override //give functions to the appBar
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Item_Search:
                break;
            case R.id.Item_ShowPokedex:
                openPokeListActivity("0"); //sending "" so it search for all
                break;
            case R.id.Item_ShowTypes:
                PokeList.super.onBackPressed();
                break;
            case R.id.Item_ShowFavoritos:
                openPokeListActivity("1"); //send a 1 so the program search for favorites
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void openPokeListActivity(String str){
        Intent intent = new Intent(this, PokeList.class);
        intent.putExtra(DB_Keys.COL_NAME, str);
        startActivity(intent);
    }
}