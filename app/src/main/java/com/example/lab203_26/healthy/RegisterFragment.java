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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        mAuth = FirebaseAuth.getInstance();
        InitRegisterBtn();
    }
    void InitRegisterBtn() {
        Button _reBtn = getView().findViewById(R.id.re_button);
        _reBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                registerNewUser();

            }
        });
    }
    void registerNewUser() {
        EditText _email = (EditText) getView().findViewById(R.id.re_email);
        EditText _repass = (EditText) getView().findViewById(R.id.re_repss);
        EditText _password = (EditText) getView().findViewById(R.id.re_pass);

        String _emailStr = _email.getText().toString();
        String _repassStr = _repass.getText().toString();
        String _passwordStr = _password.getText().toString();
        if(_emailStr.isEmpty() || _passwordStr.isEmpty() || _repassStr.isEmpty()){
            Toast.makeText(getActivity(),"กรุณาระบุข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
            Log.d("USER","FIELD NAME IS EMPTY");
        }else if(!_passwordStr.equals(_repassStr)){
            Toast.makeText(getActivity(),"กรุณากรอกยืนยัน Password ให้ถูกต้อง",Toast.LENGTH_SHORT).show();
            Log.d("USER","RE-PASSWORD INCORRECT");
        }else if(_passwordStr.length() < 6){
            Toast.makeText(getActivity(),"Password ต้องมีอย่างน้อย 6 ตัวอักษร",Toast.LENGTH_SHORT).show();
            Log.d("USER","PASSWORD NEED LENGTH AT LEAST 6 LONG");
        }else if(_emailStr.equals("admin")){
            Toast.makeText(getActivity(),"user นี้มีอยู่ในระบบอยู่แล้ว",Toast.LENGTH_SHORT).show();
            Log.d("USER","USER ALREADY EXIST");
        }else{
            mAuth.createUserWithEmailAndPassword(_emailStr,_passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    sendVertificationEmail(authResult.getUser());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),"ERROR -"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).commit();
            Log.d("USER", "REGISTER SUCCESS PLEASE CHECK YOUR EMAIL INBOX TO VERIFICATION YOUR ACCOUNT");
        }
    }
    private void sendVertificationEmail(FirebaseUser _user){
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
