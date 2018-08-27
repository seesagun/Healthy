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
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        Button _reBtn = getView().findViewById(R.id.re_button);
        _reBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText _userId = (EditText) getView().findViewById(R.id.re_user);
                EditText _name = (EditText) getView().findViewById(R.id.re_name);
                EditText _age = (EditText) getView().findViewById(R.id.re_age);
                EditText _password = (EditText) getView().findViewById(R.id.re_pss);
                String _userIdStr = _userId.getText().toString();
                String _nameStr = _name.getText().toString();
                String _ageStr = _age.getText().toString();
                String _passwordStr = _password.getText().toString();
                if(_userIdStr.isEmpty() || _passwordStr.isEmpty() || _ageStr.isEmpty() || _nameStr.isEmpty()){
                    Toast.makeText(getActivity(),"กรุณาระบุข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                    Log.d("USER","FIELD NAME IS EMPTY");
                }else if(_userIdStr.equals("admin")){
                    Toast.makeText(getActivity(),"user นี้มีอยู่ในระบบอยู่แล้ว",Toast.LENGTH_SHORT).show();
                    Log.d("USER","USER ALREADY EXIST");
                }else{
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).commit();
                    Log.d("USER","GOTO BMI");
                }
            }
        });
    }
}
