package com.example.hemamostafa.hatly_1.Adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemamostafa.hatly_1.Model.Item;
import com.example.hemamostafa.hatly_1.R;

import java.net.URI;
import java.util.ArrayList;

public class ItemShipmentAdapter extends RecyclerView.Adapter<ItemShipmentAdapter.ViewHolder>{




    ArrayList<Item> itemArrayList;


    OnItemClickListenerInterface onCardviewClickListener, oncloseIconClickListener;


    public ItemShipmentAdapter(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public void setOnCardviewClickListener(OnItemClickListenerInterface onCardviewClickListener) {
        this.onCardviewClickListener = onCardviewClickListener;
    }

    public void setOncloseIconClickListener(OnItemClickListenerInterface oncloseIconClickListener) {
        this.oncloseIconClickListener = oncloseIconClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_cardview, viewGroup, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Item myItem = itemArrayList.get(position);
        viewHolder.weightText.setText("Weight :");
        viewHolder.weightNumber.setText(myItem.getItem_weight()+" Kg");
        viewHolder.closeIconImg.setImageResource(R.drawable.ic_close_icon);
        Uri uri=Uri.parse( myItem.getItem_photo());
        viewHolder.itemImage.setImageURI(uri);



        if(onCardviewClickListener != null){
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardviewClickListener.onClick(position,myItem);
                }
            });
        }
        if(oncloseIconClickListener !=null){
            viewHolder.closeIconImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oncloseIconClickListener.onClick(position,myItem);
                }
            });
        }





    }

    @Override
    public int getItemCount() {
        if(itemArrayList != null)
            return  itemArrayList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View parent;
        CardView cardView;
        TextView  weightText,weightNumber;
        ImageView itemImage, closeIconImg;


        //ViewHolder Constructor to make refrence
        public ViewHolder(View parent){
            super(parent);
            this.parent=parent;
            this.cardView=parent.findViewById(R.id.item_shipments_cardview);
            this.weightText=parent.findViewById(R.id.shipment_weight_text);
            this.weightNumber=parent.findViewById(R.id.shipment_weight_number);
            itemImage=parent.findViewById(R.id.item_img_sh);
            closeIconImg =parent.findViewById(R.id.close_icons);




        }
    }
    public static  interface OnItemClickListenerInterface{
        void onClick(int position, Item item);
    }



}
