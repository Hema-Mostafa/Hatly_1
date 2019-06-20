package com.example.hemamostafa.hatly_1.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.R;

import java.util.ArrayList;

public class MyShipmentAdpter extends RecyclerView.Adapter<MyShipmentAdpter.ViewHolder>{

    ArrayList<Shipment> shipmentArrayList;

    // number of Click = number of OnItemClickListenerInterface objects

    OnItemClickListenerInterface onCardViewClickListener ;
    OnItemClickListenerInterface onArrowClickListener ;

    public MyShipmentAdpter(ArrayList<Shipment> tripArrayList) {
        this.shipmentArrayList = tripArrayList;
    }

    public void setOnCardViewClickListener(OnItemClickListenerInterface onCardViewClickListener) {
        this.onCardViewClickListener = onCardViewClickListener;
    }

    public void setOnArrowClickListener(OnItemClickListenerInterface onArrowClickListener) {
        this.onArrowClickListener = onArrowClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.shipment_list_file, viewGroup, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Shipment item= shipmentArrayList.get(position);

        viewHolder.fromTextView.setText(item.getFrom());
        viewHolder.toTextView.setText(item.getTo());
        viewHolder.dateString.setText("Data :");
        viewHolder.dateNumber.setText(item.getBeforeDate());
        viewHolder.weight.setText(item.getShipmentWeight()+" Kg");
        viewHolder.arrow.setImageResource(R.drawable.ic_arrow_drop_down_img);
        viewHolder.transportaionImg.setImageResource(R.drawable.ic_car_img);
        viewHolder.shipmentName.setText(item.getShipmentName());

        viewHolder.shipmentPhoto.setImageResource(R.drawable.mobile_box);


        if(onCardViewClickListener != null){
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardViewClickListener.onClick(position,item);
                }
            });
        }
        if(onArrowClickListener != null){

            viewHolder.arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onArrowClickListener.onClick(position,item);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if(shipmentArrayList != null)
            return  shipmentArrayList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View parent;
        TextView fromTextView , toTextView,dateString,dateNumber, weight,weightNumber ;
        TextView shipmentName;
        ImageView transportaionImg , arrow,shipmentPhoto;
        CardView cardView;

        //ViewHolder Constructor to make refrence
        public ViewHolder(View parent){
            super(parent);
            this.parent=parent;
           this.fromTextView = parent.findViewById(R.id.from_txtView_tr);
           this.toTextView = parent.findViewById(R.id.to_txtView_tr);
           this.dateString = parent.findViewById(R.id.date_tr);
           this.dateNumber= parent.findViewById(R.id.dateNumber_sh);
           this.weight = parent.findViewById(R.id.shipment_weight_tr);
           this.shipmentName=parent.findViewById(R.id.shipment_name_sh);
           this.transportaionImg= parent.findViewById(R.id.transportionTypeImg_tr);
           this.arrow = parent.findViewById(R.id.arrow_sh);
           this.cardView = parent.findViewById(R.id.shipments_cardview);
            this.shipmentPhoto=parent.findViewById(R.id.item_img_sh);
        }
    }
    public static  interface OnItemClickListenerInterface{
        void onClick(int position, Shipment shipment);
    }

}
