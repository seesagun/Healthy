package com.example.lab203_26.healthy;

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
import android.widget.TextView;
import android.widget.Toast;

public class BMIFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        Button calBtn = getView().findViewById(R.id.bmi_cal);
        final TextView bmi_show = getView().findViewById(R.id.bmi_show);
        calBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText _height = (EditText) getView().findViewById(R.id.bmi_h);
                EditText _weight = (EditText) getView().findViewById(R.id.bmi_w);
                String _heightStr = _height.getText().toString();
                String _weightStr = _weight.getText().toString();
                float _weightInt = Integer.parseInt(_weightStr);
                float _heightInt = Integer.parseInt(_heightStr);
                double bmi;
                if(_heightStr.isEmpty() || _weightStr.isEmpty()){
                    Toast.makeText(getActivity(),"กรุณาระบุข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                    Log.d("USER","FIELD NAME IS EMPTY");
                }
                else{
                    bmi = 60 / ((_heightInt*_heightInt)/10000);
                    bmi_show.setText(String.format("%.2f", bmi));
                    Log.d("USER","BMI IS VALUE");
                }
            }
        });
    }
}
