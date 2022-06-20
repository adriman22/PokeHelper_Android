package com.example.pokehelper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokehelper.DB.DB;
import com.example.pokehelper.DB.DB_Keys;
import com.example.pokehelper.DB.SQLines;
import com.example.pokehelper.DB.Pokemon;
import com.example.pokehelper.DB.Types;
import com.example.pokehelper.R;
import com.example.pokehelper.utils.AdaptadorTipos;

import java.util.ArrayList;
import java.util.List;

public class TypeList extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLines sqlRepo;

    private RecyclerView recView;
    private AdaptadorTipos adaptador;
    private List<Types> typesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);

        db = DB.getDB(this);
        sqlRepo = new SQLines(db);

        Cursor c = sqlRepo.showDBTypes();
        typesList = new ArrayList<>();
        Log.i("MyApp", "Tipos Recuperados: "+c.getCount());
        while(c.moveToNext()){
            typesList.add(new Types(c.getString(0),c.getBlob(1)));
        }

        fillRecView();
    }

    private void fillRecView() {
        recView = findViewById(R.id.recView);
        adaptador = new AdaptadorTipos(typesList);

        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adaptador);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recView.addItemDecoration(divider);

        adaptador.setOnClickListener(view -> {
            int pos = recView.getChildAdapterPosition(view);
            openPokeListActivity(typesList.get(pos).getName());
        });
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