package com.example.hemamostafa.hatly_1;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDealUser extends Fragment {

Button changePrice,viewProfile,accept_deal_price__btn;
ImageView img_1 , img_2 , imageViewAccept;
TextView textView_1 , textView_2;
View view;
    public FragmentDealUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_deal_user, container, false);
        changePrice=view.findViewById(R.id.change__btn);
        viewProfile=view.findViewById(R.id.view_profile_btn);

        changePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ChangeDealRequest.class));
            }
        });

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Profile.class));
            }
        });







        return view;

    }

}
