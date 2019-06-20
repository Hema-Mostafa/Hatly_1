package com.example.hemamostafa.hatly_1.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemamostafa.hatly_1.Model.Trip;
import com.example.hemamostafa.hatly_1.R;

import java.util.ArrayList;

public class MyTripsAdpter extends RecyclerView.Adapter<MyTripsAdpter.ViewHolder>{

    ArrayList<Trip> tripArrayList;

    // number of Click = number of OnItemClickListenerInterface objects

    OnItemClickListenerInterface onCardViewClickListener ;
    OnItemClickListenerInterface onArrowClickListener ;

    public MyTripsAdpter(ArrayList<Trip> tripArrayList) {
        this.tripArrayList = tripArrayList;
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
                        .inflate(R.layout.trip_list_file, viewGroup, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Trip item=tripArrayList.get(position);

        viewHolder.fromTextView.setText(item.getFrom());
        viewHolder.toTextView.setText(item.getTo());
        viewHolder.dateString.setText("Data : ");
        viewHolder.dateNumber.setText(item.getDate());
        viewHolder.weightString.setText("Weight:  ");
        viewHolder.weightNumber.setText(item.getWeight());
        viewHolder.arrow.setImageResource(R.drawable.ic_arrow_drop_down_img);

        if(item.getTransportationType().equals("car")) {
            viewHolder.transportaionImg.setImageResource(R.drawable.ic_car_img);
        }
        else if (item.getTransportationType().equals("bus") ){
            viewHolder.transportaionImg.setImageResource(R.drawable.ic_bus);
        }
        else if(item.getTransportationType().equals("train")){
            viewHolder.transportaionImg.setImageResource(R.drawable.ic_train);
        }


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
        if(tripArrayList != null)
            return  tripArrayList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View parent;
        TextView fromTextView , toTextView,dateString,dateNumber, weightString,weightNumber ;
        ImageView transportaionImg , arrow;
        CardView cardView;

        //ViewHolder Constructor to make refrence
        public ViewHolder(View parent){
            super(parent);
            this.parent=parent;
           this.fromTextView = parent.findViewById(R.id.from_txtView_tr);
           this.toTextView = parent.findViewById(R.id.to_txtView_tr);
           this.dateString = parent.findViewById(R.id.date_tr);
           this.dateNumber= parent.findViewById(R.id.dateNumber_sh);
           this.weightString = parent.findViewById(R.id.weighString);
           this.weightNumber = parent.findViewById(R.id.weightNumbeer);
           this.transportaionImg= parent.findViewById(R.id.transportionTypeImg_tr);
           this.arrow = parent.findViewById(R.id.close_icons);
           this.cardView = parent.findViewById(R.id.tripCardView);

        }
    }
    public static  interface OnItemClickListenerInterface{
        void onClick(int position,Trip trip);
    }

}
