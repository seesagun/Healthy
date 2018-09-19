package com.example.lab203_26.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_26.healthy.R;
import com.example.lab203_26.healthy.weight.Weight;

import java.util.ArrayList;
import java.util.List;

public class WeightAdapter extends ArrayAdapter {
    List<Weight> weight = new ArrayList<Weight>();
    Context context;
    public WeightAdapter(@NonNull Context context, int resourse, @NonNull List<Weight> objects){
        super(context, resourse, objects);
        this.weight = objects;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View _weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_menu_item,parent,false
        );
        TextView _date = _weightItem.findViewById(R.id.menu_item_date);
        TextView _weight = _weightItem.findViewById(R.id.menu_item_weight);
        TextView _show = _weightItem.findViewById(R.id.weight_item_show);
        Weight _row = weight.get(position);
        _date.setText(_row.getDate());
        _weight.setText(String.valueOf(_row.getWeight()));
        _show.setText(_row.getStatus());
        return _weightItem;
    }
}
