package com.example.lab203_26.healthy.Sleep;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab203_26.healthy.R;

import java.util.ArrayList;

public class SleepFragment extends Fragment {
    private ArrayList<Sleep> sleepArrayList = new ArrayList<>();
    private ListView sleepList;
    private SleepAdapter sleepAdapter;
    private SQLiteDatabase db;
    private Sleep sleep;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        sleepList = getView().findViewById(R.id.sleep_sleeplist);
        sleepAdapter = new SleepAdapter(getActivity(), R.layout.fragment_sleep_item, sleepArrayList);

        loadSleepData();
        initAddBtn();
        initEditBtn();
    }

    private void loadSleepData() {
        Cursor cursor = db.rawQuery("select * from sleep order by currentdate DESC", null);
        while(cursor.moveToNext()) {
            int primaryId = cursor.getInt(0);
            String currentDate = cursor.getString(1);
            String timetosleep = cursor.getString(2);
            String timetowakeup = cursor.getString(3);
            String counttime = cursor.getString(4);
            Sleep sleep = new Sleep(currentDate, timetosleep, timetowakeup, counttime, primaryId);
            sleepArrayList.add(sleep);
        }

        cursor.close();

        sleepAdapter.notifyDataSetChanged();
        sleepList.setAdapter(sleepAdapter);
    }

    private void initAddBtn() {
        Button _addBtn = getView().findViewById(R.id.sleep_addBtn);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sleep = Sleep.setSleepInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFormFragment()).commit();
            }
        });
    }

    private void initEditBtn() {
        ListView _editBtn = getView().findViewById(R.id.sleep_sleeplist);
        _editBtn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sleep = Sleep.getSleepInstance();
                Sleep sleepData = (Sleep) parent.getAdapter().getItem(position);

                sleep.setPrimaryId(sleepData.getPrimaryId());
                sleep.setCurrentDate(sleepData.getCurrentDate());
                sleep.setTimeToSleep(sleepData.getTimeToSleep());
                sleep.setTimeToGetUp(sleepData.getTimeToGetUp());
                sleep.setCountTime(sleepData.getCountTime());

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFormFragment()).commit();
            }
        });
    }
}
