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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        mAuth = FirebaseAuth.getInstance();
        checkCurrentUser();
        Button _loginBtn = getView().findViewById(R.id.login_button);
        _loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText _email = (EditText) getView().findViewById(R.id.login_email);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _emailString = _email.getText().toString();
                String _passwordString = _password.getText().toString();
                if(_emailString.isEmpty() || _passwordString.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุ user or password",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER","USER OR PASSWORD IS EMPTY");
                }else{
                    mAuth.signInWithEmailAndPassword(_emailString,_passwordString).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if(mAuth.getCurrentUser().isEmailVerified()) {
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
                                Log.d("LOGIN", "LOGIN SUCCESS AND GO TO MENU");
                            } else {
                                Toast.makeText(getActivity(), "กรุณายืนยัน email", Toast.LENGTH_LONG).show();
                                Log.d("LOGIN", "Login unsucess and unconfirm email");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "ERROR - " + e.getMessage(), Toast.LENGTH_LONG).show();
                            Log.d("USER","ERROR - " + e.getMessage());
                        }
                    });
                    //Toast.makeText(getActivity(),"user or password ไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    //Log.d("USER","INVALID USER OR PASSWORD");
                }
            }
        });
        TextView _registerBtn = getView().findViewById(R.id.login_register);
        _registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
                Log.d("USER","GOTO REGISTER");
            }
        });
    }

    void checkCurrentUser() {
        if(mAuth.getCurrentUser() != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
            Log.d("USER", "SILL LOGIN, GO TO MENU");
        }
    }

}
