package com.example.pokehelper.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokehelper.DB.Pokemon;
import com.example.pokehelper.DB.Types;
import com.example.pokehelper.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorTipos extends RecyclerView.Adapter<AdaptadorTipos.ViewHolderPokes> implements View.OnClickListener{
    private List<Types> datos;
    private View.OnClickListener listener;

    public AdaptadorTipos(List<Types> datos){
        this.datos = datos;
    }


    @NonNull
    @Override
    public ViewHolderPokes onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_schema, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolderPokes(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPokes holder, int position) {
        Types items = datos.get(position);
        holder.bindTitular(items);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderPokes extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;

        public ViewHolderPokes(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.Image);
            name = itemView.findViewById(R.id.name);
        }

        public void bindTitular(Types p){
            img.setImageBitmap(getBitmap(p.getImage()));
            name.setText(" - "+p.getName());
        }

        private Bitmap getBitmap(byte[] image){
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }

    }
}
