package com.example.lab203_26.healthy.Sleep;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab203_26.healthy.R;

import java.util.regex.Pattern;

public class SleepFormFragment extends Fragment {
    private SQLiteDatabase db;
    private Sleep sleep;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        sleep = Sleep.getSleepInstance();

        initSaveBtn();
        initBackBtn();
        if(sleep != null) {
            loadData();
        }
    }

    private void initSaveBtn() {
        Button _saveBtn = getView().findViewById(R.id.frg_sleep_form_saveBtn);
        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
    }

    private void initBackBtn() {
        Button _backBtn = getView().findViewById(R.id.sleep_form_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment()).commit();
            }
        });
    }

    private void setData() {
        EditText _dateTxt = getView().findViewById(R.id.frg_sleep_form_date);
        EditText _timetosleepTxt = getView().findViewById(R.id.frg_sleep_form_timetosleep);
        EditText _timetowakeupTxt = getView().findViewById(R.id.frg_sleep_form_timetowakeup);

        String _dateStr = _dateTxt.getText().toString();
        String _timetosleepStr = _timetosleepTxt.getText().toString();
        String _timetowakeupStr = _timetowakeupTxt.getText().toString();

        if(_dateStr.isEmpty() || _timetosleepStr.isEmpty() || _timetowakeupStr.isEmpty()) {
            Toast.makeText(getActivity(), "กรุณาใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
            Log.d("FIELDEMPTY_SLEEPFORMFRAGMENT", "field is empty");
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("currentdate", _dateStr);
            contentValues.put("timetosleep", _timetosleepStr);
            contentValues.put("timetowakeup", _timetowakeupStr);

            String[] _timetosleepSplit;
            String[] _timetowakeupSplit;
            if(_timetosleepStr.contains(":") && _timetowakeupStr.contains(":")){
                _timetosleepSplit = _timetosleepStr.split(":");
                _timetowakeupSplit = _timetowakeupStr.split(":");
            }else{
                _timetosleepSplit = _timetosleepStr.split(Pattern.quote("."));
                _timetowakeupSplit = _timetowakeupStr.split(Pattern.quote("."));
            }

            int _timetosleepHourInt = Integer.parseInt(_timetosleepSplit[0]);
            int _timetosleepMinInt = Integer.parseInt(_timetosleepSplit[1]);
            int _timetowakeupHourInt = Integer.parseInt(_timetowakeupSplit[0]);
            int _timetowakeupMinInt = Integer.parseInt(_timetowakeupSplit[1]);

            int calculateHour;
            int calculateMin;
            int resultHour = 0;
            int resultMin = 0;
            if(_timetosleepHourInt < _timetowakeupHourInt) {
                calculateHour = Math.abs(_timetosleepHourInt - _timetowakeupHourInt);
                if(_timetosleepMinInt > _timetowakeupMinInt) {
                    calculateMin = (60 - _timetosleepMinInt)+_timetowakeupMinInt;
                    calculateHour-=1;
                } else {
                    calculateMin = Math.abs(_timetosleepMinInt - _timetowakeupMinInt);
                }
                resultHour = calculateHour;
                resultMin = calculateMin;
            } else {
                calculateHour = Math.abs(24 - _timetosleepHourInt);

                if(_timetosleepMinInt > _timetowakeupMinInt) {
                    calculateMin = (60 - _timetosleepMinInt)+_timetowakeupMinInt;
                    calculateHour-=1;
                } else {
                    calculateMin = Math.abs(_timetosleepMinInt - _timetowakeupMinInt);
                }
                resultHour = calculateHour + _timetowakeupHourInt;
                resultMin = calculateMin;
            }

            String resultMinStr;
            if(resultMin == 0) {
                resultMinStr = "00";
                contentValues.put("counttime", resultHour + ":" + resultMinStr);
            } else {
                contentValues.put("counttime", resultHour + ":" + resultMin);
            }

            if(sleep.getPrimaryId() == 0) {
                db.insert("sleep", null, contentValues);
            } else {
                db.update("sleep", contentValues, "_id=" + sleep.getPrimaryId(), null);
            }

            Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show();
            Log.d("ADDTODATABASE_SLEEPFORMFRAGMENT", "add to database");
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment()).commit();
        }
    }

    private void loadData() {
        EditText _dateTxt = getView().findViewById(R.id.frg_sleep_form_date);
        EditText _timetosleepTxt = getView().findViewById(R.id.frg_sleep_form_timetosleep);
        EditText _timetowakeupTxt = getView().findViewById(R.id.frg_sleep_form_timetowakeup);

        _dateTxt.setText(sleep.getCurrentDate());
        _timetosleepTxt.setText(sleep.getTimeToSleep());
        _timetowakeupTxt.setText(sleep.getTimeToGetUp());
    }
}
