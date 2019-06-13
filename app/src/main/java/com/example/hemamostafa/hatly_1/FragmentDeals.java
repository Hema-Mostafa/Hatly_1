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
import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
import com.example.hemamostafa.hatly_1.Model.DummyClassDeals;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDeals extends Fragment {
    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;

    DealsAdapter adapter;
    View view;
    ArrayList<DummyClassDeals> arrayList;

    public FragmentDeals() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_deals, container, false);
        recyclerview = view.findViewById(R.id.deals_recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        addDummyValue();
        adapter = new DealsAdapter(arrayList);
        adapter.setOnButtonClickListener(new DealsAdapter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, DummyClassDeals dummyClassDeals) {
                startActivity(new Intent(getContext(),DealInfo_1.class));
            }
        });

        recyclerview.setAdapter(adapter);


        return view;
    }

    public void addDummyValue() {
        arrayList = new ArrayList<>();
        arrayList.add(new DummyClassDeals("Minia", "Cairo", "car", "10/2/2018", "hema_10",
                "no photo", "HeMa Mostafa ", "3"));


        arrayList.add(new DummyClassDeals("Minia", "Cairo", "car", "10/2/2018", "hema_10",
                "no photo", "HeMa Mostafa ", "3"));

    }
}
