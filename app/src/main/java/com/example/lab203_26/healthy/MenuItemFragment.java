package com.example.lab203_26.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class MenuItemFragment extends ArrayAdapter {
    public MenuItemFragment(@NonNull Context context,int resourse,@NonNull List<MenuItemFragment> objects){
        super(context, resourse, objects);
    }
    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return LayoutInflater.from(context)
                .inflate(R.layout.fragment_menu_item,parent,false);
    }*/
}
