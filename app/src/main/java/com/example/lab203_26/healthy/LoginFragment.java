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

public class LoginFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        Button _loginBtn = getView().findViewById(R.id.login_button);
        _loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _useridString = _userId.getText().toString();
                String _passwordString = _password.getText().toString();
                if(_useridString.isEmpty() || _passwordString.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุ user or password",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER","USER OR PASSWORD IS EMPTY");
                }else if(_useridString.equals("admin") && _passwordString.equals("admin")){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
                    Log.d("USER","GOTO BMI");
                }else{
                    Toast.makeText(getActivity(),"user or password ไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    Log.d("USER","INVALID USER OR PASSWORD");
                }
            }
        });
        TextView _registerBtn = getView().findViewById(R.id.login_register);
        _registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).commit();
                Log.d("USER","GOTO REGISTER");
            }
        });
    }

}
