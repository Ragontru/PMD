package com.example.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.db.R;
import com.example.fruteria.db.room.model.Fruta;

import java.util.List;

public class MiArrayAdapter extends ArrayAdapter {

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Fruta> frutaList;

    public MiArrayAdapter(@NonNull Context context, int resource, List<Fruta> frutaList) {
        super(context, resource);
        this.frutaList = frutaList;
        this.layoutResource=resource;
        this.layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return frutaList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= layoutInflater.inflate(layoutResource,parent,false);

        TextView idTextView = (TextView)view.findViewById(R.id.textViewId);
        TextView nombreTextView = (TextView)view.findViewById(R.id.textViewNombre);
        TextView variedadTextView = (TextView)view.findViewById(R.id.textViewVariedad);

        Fruta fruta=frutaList.get(position);

        idTextView.setText(String.valueOf(fruta.getIdFruta()));
        nombreTextView.append(fruta.getNombre());
        variedadTextView.append(fruta.getVariedad());


        return view;
    }

}
