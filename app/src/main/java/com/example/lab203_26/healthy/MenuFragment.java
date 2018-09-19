package com.example.lab203_26.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab203_26.healthy.weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuFragment extends Fragment{

    ArrayList<String> _menu = new ArrayList<>();
    private FirebaseAuth _mAuth;

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        _mAuth = FirebaseAuth.getInstance();
        _menu.clear();
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Setup");
        _menu.add("Sign Out");

        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                _menu
        );
        ListView _menuList = getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU","Click on menu = "+_menu.get(i));
                _menuAdapter.notifyDataSetChanged();
                if (_menu.get(i).equals("BMI")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null)
                            .commit();
                }else if(_menu.get(i).equals("Weight")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFragment())
                            .addToBackStack(null)
                            .commit();
                }else if (_menu.get(i).equals("Sign Out")){
                    _mAuth.signOut();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .commit();
                    Log.d("USER","LOG OUT COMPLETE");
                }
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_menu,container,false);
    }

}
