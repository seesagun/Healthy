package com.example.lab203_26.healthy.Sleep;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lab203_26.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class SleepAdapter extends ArrayAdapter<Sleep> {
    List<Sleep> sleepList = new ArrayList<Sleep>();
    Context context;
    public SleepAdapter(Context context, int resource, List<Sleep> objects) {
        super(context, resource, objects);
        this.sleepList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _sleepItem = LayoutInflater.from(context).inflate(R.layout.fragment_sleep_item, parent, false);
        TextView currentDate = _sleepItem.findViewById(R.id.sleep_item_date);
        TextView time = _sleepItem.findViewById(R.id.sleep_item_time);
        TextView timeCalculate = _sleepItem.findViewById(R.id.sleep_item_calculate_time);
        Sleep _row = sleepList.get(position);
        currentDate.setText(_row.getCurrentDate());
        time.setText(_row.getTimeToSleep() + " - " + _row.getTimeToGetUp());
        timeCalculate.setText(_row.getCountTime());
        return _sleepItem;
    }
}
