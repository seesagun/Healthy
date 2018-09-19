package com.example.lab203_26.healthy.weight;

import android.os.Bundle;
import android.widget.Toast;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.lab203_26.healthy.MenuFragment;
import com.example.lab203_26.healthy.R;
import com.example.lab203_26.healthy.RegisterFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class WeightFragment extends Fragment {
    ArrayList<Weight> weights = new ArrayList<>();
    FirebaseAuth _mAuth;
    FirebaseFirestore _mStore;
    ListView _weightList;
    WeightAdapter _weightAdapter;
    String _uid;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _mStore = FirebaseFirestore.getInstance();
        _mAuth = FirebaseAuth.getInstance();
        /*Button _btn = getView().findViewById(R.id.add_weight);
        weights.add(new Weight("01 Jan 2013",63,"UP"));
        weights.add(new Weight("02 Jan 2013",64,"UP"));
        weights.add(new Weight("03 Jan 2013",62,"DOWN"));*/
        _weightList = getView().findViewById(R.id.weight_list);
        _uid = _mAuth.getCurrentUser().getUid();
        _weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_menu_item,
                weights
        );
        loadWeight(_uid);
        initAddWeightBtn();
        /*_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFormFraggment()).commit();
                Log.d("USER","ADD WEIGHT");
            }
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    void loadWeight(String uid){
        _mStore.collection("myfitness").document(uid).collection("weight").orderBy("date", Query.Direction.DESCENDING)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                _weightAdapter.clear();

                if(!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> listWeightData = queryDocumentSnapshots.getDocuments();
                    ArrayList<Weight> _tempWeight_1 = new ArrayList<>();
                    ArrayList<Weight> _tempWeight_2 = new ArrayList<>();

                    // Get data from firestore
                    for(DocumentSnapshot _doc : listWeightData) {
                        Weight _weightData = _doc.toObject(Weight.class);
                        Log.d("FIRESTORE", "Form Firestore : " + _weightData.getDate() + " : " + _weightData.getWeight());
                        _tempWeight_1.add(_weightData);
                        _tempWeight_2.add(_weightData);
                        Log.d("TEMP", "Add to tempWeight");
                    }

                    // Compare Weight and set Status
                    if(_tempWeight_1.size() == 1) {
                        weights.add(_tempWeight_1.get(0));
                    } else {
                        for (int i = 0; i < _tempWeight_1.size(); i++) {
                            Log.d("TEMP", "I : " + i + " tempWeight : " + _tempWeight_1.get(i).getWeight() + " : " + _tempWeight_1.get(i).getStatus());
                            for (int j = 1; j < _tempWeight_2.size(); j++) {
                                Log.d("TEMP", "J : " + j + " tempWeight 2 : " + _tempWeight_2.get(i).getWeight() + " : " + _tempWeight_2.get(i).getStatus());
                                if (_tempWeight_1.get(i).getWeight() > _tempWeight_2.get(j).getWeight()) {
                                    _tempWeight_1.get(i).setStatus("ขึ้น");
                                    Log.d("TEMP", "tempWeight : " + _tempWeight_1.get(i).getWeight() + " : " + _tempWeight_1.get(i).getStatus());
                                    weights.add(_tempWeight_1.get(i));
                                    updateStatusWeight(_tempWeight_1.get(i));
                                } else if (_tempWeight_1.get(i).getWeight() < _tempWeight_2.get(j).getWeight()) {
                                    _tempWeight_1.get(i).setStatus("ลง");
                                    Log.d("TEMP", "tempWeight : " + _tempWeight_1.get(i).getWeight() + " : " + _tempWeight_1.get(i).getStatus());
                                    weights.add(_tempWeight_1.get(i));
                                    updateStatusWeight(_tempWeight_1.get(i));
                                } else {
                                    _tempWeight_1.get(i).setStatus("คงที่");
                                    Log.d("TEMP", "tempWeight : " + _tempWeight_1.get(i).getWeight() + " : " + _tempWeight_1.get(i).getStatus());
                                    weights.add(_tempWeight_1.get(i));
                                    updateStatusWeight(_tempWeight_1.get(i));
                                }
                                i++;
                            }
                            weights.add(_tempWeight_1.get(i));
                        }
                    }

                    _weightAdapter.notifyDataSetChanged();
                    _weightList.setAdapter(_weightAdapter);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "ERROR - " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    void updateStatusWeight(Weight weight) {
        _mStore.collection("myfitness").document(_uid).collection("weight").document(weight.getDate())
                .update("status", weight.getStatus());
    }
    void initAddWeightBtn(){
        Button _btn = getView().findViewById(R.id.add_weight);
        _btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFormFraggment()).commit();
                Log.d("USER","ADD WEIGHT");
            }
        });
    }

}
