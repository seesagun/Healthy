package com.example.lab203_26.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lab203_26.healthy.R;

import java.util.ArrayList;

public class WeightFragment extends Fragment {
    ArrayList<Weight> weights = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weights.add(new Weight("01 Jan 2013",63,"UP"));
        weights.add(new Weight("02 Jan 2013",64,"UP"));
        weights.add(new Weight("03 Jan 2013",62,"DOWN"));
        ListView _weightList = getView().findViewById(R.id.weight_list);
        WeightAdapter _weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_menu_item,
                weights
        );
        _weightList.setAdapter(_weightAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
}
