package com.example.hemamostafa.hatly_1.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.hemamostafa.hatly_1.Model.DummyClassDeals;
import com.example.hemamostafa.hatly_1.R;


import java.util.ArrayList;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder>{



    ArrayList<DummyClassDeals> arrayList;
    OnItemClickListenerInterface onButtonClickListener ;

    public DealsAdapter(ArrayList<DummyClassDeals> arrayList) {
        this.arrayList = arrayList;
    }

    public void setOnButtonClickListener(OnItemClickListenerInterface onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.deals_shipments_cardview, viewGroup, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final DummyClassDeals dummyClassDeals = arrayList.get(position);

        viewHolder.fromTextView.setText(dummyClassDeals.getFrom());
        viewHolder.toTextView.setText(dummyClassDeals.getTo());
        viewHolder.dateStringTextView.setText("Data :");
        viewHolder.dateNumberTextView.setText(dummyClassDeals.getDate());

        viewHolder.shipperNameTextView.setText(dummyClassDeals.getShipper_name());

        viewHolder.transportaionImg.setImageResource(R.drawable.ic_car_img);
        viewHolder.shipperImage.setImageResource(R.drawable.shipper_image);

        if(onButtonClickListener != null){
            viewHolder.requesPriceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClickListener.onClick(position,dummyClassDeals);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if(arrayList != null)
            return  arrayList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View parent;
       private TextView fromTextView , toTextView,dateStringTextView,dateNumberTextView;
       private TextView  shipperNameTextView;
       private   AppCompatRatingBar ratingBar;
       private ImageView transportaionImg,shipperImage ;
       private   CardView cardView;
       private Button requesPriceBtn;

        //ViewHolder Constructor to make refrence
        public ViewHolder(View parent){
            super(parent);
            this.parent=parent;

           this.fromTextView = parent.findViewById(R.id.from_txtView_d);
           this.toTextView = parent.findViewById(R.id.to_txtView_d);
           this.dateStringTextView = parent.findViewById(R.id.dateString_d);
           this.dateNumberTextView= parent.findViewById(R.id.dateNumber_d);

           this.shipperNameTextView=parent.findViewById(R.id.shipper_name_d_tr);
           this.shipperImage= parent.findViewById(R.id.shipper_img_d_tr);

           this.transportaionImg=parent.findViewById(R.id.transportionTypeImg_d);
         //  this.ratingBar=parent.findViewById(R.id.shipper_ratingbar_d);
            this.requesPriceBtn=parent.findViewById(R.id.request_price_btn);


            this.cardView = parent.findViewById(R.id.deals_cardview);

        }
    }
    public static  interface OnItemClickListenerInterface{
        void onClick(int position, DummyClassDeals dummyClassDeals);
    }



}
