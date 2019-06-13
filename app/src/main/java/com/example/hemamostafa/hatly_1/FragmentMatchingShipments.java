package com.example.hemamostafa.hatly_1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hemamostafa.hatly_1.Adapter.DealsAdapter;
import com.example.hemamostafa.hatly_1.Adapter.MatchingShipmentAdapter;
import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
import com.example.hemamostafa.hatly_1.Model.DummyClass;
import com.example.hemamostafa.hatly_1.Model.DummyClassDeals;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMatchingShipments extends Fragment {
    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    MatchingShipmentAdapter adapter;
    View view;
    ArrayList<DummyClass> arrayList;

    public FragmentMatchingShipments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_matching_shipments, container, false);


        recyclerview = view.findViewById(R.id.match_shipments_reecycler_view);
        layoutManager = new LinearLayoutManager(getContext());

        addDummyValue();
        adapter = new MatchingShipmentAdapter(arrayList);
        adapter.setOnButtonClickListener(new MatchingShipmentAdapter.OnItemClickListenerInterface() {
         @Override
        public void onClick(int position, DummyClass DummyClass) {

             startActivity(new Intent(getActivity(),SendRequest.class));
            }
        }
        );
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
        return view;
    }
    public  void  addDummyValue(){
        arrayList = new ArrayList<>();
        arrayList.add(new DummyClass("Minia","Cairo","car","10/2/2018",
                "hema_10","Mobile","2","no photo","HeMa Mostafa"
                ,"3"));

        arrayList.add(new DummyClass("Minia","Cairo","car","10/2/2018",
                "hema_10","Mobile","2","no photo","HeMa Mostafa"
                ,"3"));

    }
}
