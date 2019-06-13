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


import com.example.hemamostafa.hatly_1.Model.DummyClass;
import com.example.hemamostafa.hatly_1.R;

import java.util.ArrayList;

public class MatchingShipmentAdapter extends RecyclerView.Adapter<MatchingShipmentAdapter.ViewHolder>{



    ArrayList<DummyClass> arrayList;
    OnItemClickListenerInterface onButtonClickListener ;


    public MatchingShipmentAdapter(ArrayList<DummyClass> arrayList) {
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
                        .inflate(R.layout.matching_shipments_cardview, viewGroup, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final DummyClass dummyClass = arrayList.get(position);

        viewHolder.fromTextView.setText(dummyClass.getFrom());
        viewHolder.toTextView.setText(dummyClass.getTo());
        viewHolder.dateStringTextView.setText("Data :");
        viewHolder.beforDateTextView.setText(dummyClass.getDate());
        viewHolder.shipmentWeightTextView.setText("Weight :"+ dummyClass.getTotal_weight()+" KG ");
        viewHolder.shipmentNameTextView.setText(dummyClass.getShipment_name());
        viewHolder.shipperNameTextView.setText(dummyClass.getShipper_name());

        viewHolder.transportaionImg.setImageResource(R.drawable.ic_car_img);
        viewHolder.shipperImage.setImageResource(R.drawable.shipper_image);
        viewHolder.shippmentImage.setImageResource(R.drawable.mobile_box);
        //viewHolder.ratingBar.se


        if(onButtonClickListener != null){
            viewHolder.sendRequestBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClickListener.onClick(position,dummyClass);
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
        TextView fromTextView , toTextView,dateStringTextView,beforDateTextView,shipmentNameTextView,shipmentWeightTextView;
        TextView  shipperNameTextView;
        AppCompatRatingBar ratingBar;
        ImageView transportaionImg,shippmentImage,shipperImage ;
        CardView cardView;
        Button sendRequestBtn;

        //ViewHolder Constructor to make refrence
        public ViewHolder(View parent){
            super(parent);
            this.parent=parent;

           this.fromTextView = parent.findViewById(R.id.from_txtView_tr);
           this.toTextView = parent.findViewById(R.id.to_txtView_tr);
           this.dateStringTextView = parent.findViewById(R.id.date_tr);
           this.beforDateTextView= parent.findViewById(R.id.dateNumber_sh);
           this.shipmentWeightTextView = parent.findViewById(R.id.shipment_weight_tr);
           this.shipmentNameTextView = parent.findViewById(R.id.shipment_name_sh);
           this.shipperNameTextView=parent.findViewById(R.id.shipper_name_tr);
           this.shipperImage= parent.findViewById(R.id.shipper_img_1);
           this.shippmentImage= parent.findViewById(R.id.item_img_sh);
           this.transportaionImg=parent.findViewById(R.id.transportionTypeImg_tr);
          // this.ratingBar=parent.findViewById(R.id.shipper_ratingbar);
            this.sendRequestBtn=parent.findViewById(R.id.send_request_btn);


            this.cardView = parent.findViewById(R.id.match_shipments_cardview);

        }
    }
    public static  interface OnItemClickListenerInterface{
        void onClick(int position, DummyClass DummyClass);
    }



}
