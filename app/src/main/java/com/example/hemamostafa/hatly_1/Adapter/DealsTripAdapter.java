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

import com.example.hemamostafa.hatly_1.Model.Deal;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.R;

import java.util.ArrayList;

public class DealsTripAdapter extends RecyclerView.Adapter<DealsTripAdapter.ViewHolder>{



    ArrayList<Deal> arrayList;
    OnItemClickListenerInterface onButtonClickListener ;

    public DealsTripAdapter(ArrayList<Deal> arrayList) {
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
                        .inflate(R.layout.deals_trips_cardview, viewGroup, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Deal dealItem = arrayList.get(position);

        viewHolder.fromTextView.setText(dealItem.getSource());
        viewHolder.toTextView.setText(dealItem.getDistination());
        viewHolder.dateStringTextView.setText("Data :");
        viewHolder.dateNumberTextView.setText(dealItem.getDate());

        viewHolder.shipperNameTextView.setText("Shipper Name "); // Data From User Model
        viewHolder.shipmentNameTextView.setText(dealItem.getShipmentName());
        viewHolder.shipmentWeightsTextView.setText(dealItem.getWeight() +" KG only"); // go to Shipment Class and modify constructor
        viewHolder.transportaionImg.setImageResource(R.drawable.ic_car_img);
        viewHolder.shipperImage.setImageResource(R.drawable.shipper_image);
        viewHolder.boxImage.setImageResource(R.drawable.box_icon);

        if(onButtonClickListener != null){
            viewHolder.requesPriceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClickListener.onClick(position,dealItem);
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
       private TextView  shipperNameTextView,shipmentNameTextView,shipmentWeightsTextView ;
       private   AppCompatRatingBar ratingBar;
       private ImageView transportaionImg,shipperImage, boxImage ;
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
           this.shipmentNameTextView=parent.findViewById(R.id.shipment_name_tr_d);
           this.shipmentWeightsTextView=parent.findViewById(R.id.shipment_weight_d_tr);
           this.transportaionImg=parent.findViewById(R.id.transportionTypeImg_d);
           this.ratingBar=parent.findViewById(R.id.traveller_ratingbar_d);
            this.requesPriceBtn=parent.findViewById(R.id.request_price_btn);
            this.boxImage=parent.findViewById(R.id.box_d_tr);

            this.cardView = parent.findViewById(R.id.deals_trips_cardview);

        }
    }
    public static  interface OnItemClickListenerInterface{
        void onClick(int position, Deal deal);
    }



}
