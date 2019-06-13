package com.example.hemamostafa.hatly_1;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.Model.Item;
import com.example.hemamostafa.hatly_1.Model.ItemDimensions;
import com.example.hemamostafa.hatly_1.Model.Shipment;

public class AddShippingItemDetails_new extends MyBaseActivity implements View.OnClickListener {

    private Button reviewBtn,plusBtn,minusBtn;
    private ImageView itemImage;
    private EditText link,price,name,weight,height,width,lenght;
    private TextView quantity;
    private Spinner mySpinner;
    private String stringLink,stringPrice,stringName,stringQuantity,stringPackageWeight,stringPackageSize;
    private String stringHeight, stringWidth,stringLenght,stringCategory,stringItemPhoto;
    private boolean checkBox,imageFlag;
    private CheckBox smallBox,mediumBox,largeBox;
    private Item myItem;
    private Shipment myReceivedShipment;
    private boolean receivedItemFlag;

    private int position;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipping_item_details_new);

        inititView();


        //////////////////////////////////////////////
/*
   myReceivedShipment=(Shipment) getIntent().getSerializableExtra("myReceivedShipment");
        Intent intent = this.getIntent();
        if(intent !=null){
            String stringData = intent.getExtras().getString("shipmentDetails");
            if(stringData.equals("recyclerViewClicked"))
            {

                myReceivedShipment=(Shipment) getIntent().getSerializableExtra("myReceivedShipment");
                position=intent.getIntExtra("pos",0);
                receivedItemFlag=true;
                fillFields();





                //Do Something here...
            }
            if(stringData.equals("AddNewItem"))
            {
                myReceivedShipment=(Shipment) getIntent().getSerializableExtra("myShipment");

                Toast.makeText(activity,"The Activity start From MyNewHome Activity",Toast.LENGTH_SHORT).show();
                // Hide REcycler View
                //Hide Button
            }



        }
        else {
            Toast.makeText(activity,"Intent IS Null",Toast.LENGTH_SHORT).show();

        }
        */
        /////////////////////////////////////////

    }

    private void fillFields() {
        link.setText(myReceivedShipment.getItemList().get(position).getItem_link());
        price.setText(myReceivedShipment.getItemList().get(position).getItem_price());
        name.setText(myReceivedShipment.getItemList().get(position).getItem_name());
        quantity.setText(myReceivedShipment.getItemList().get(position).getQuantity());
        weight.setText(myReceivedShipment.getItemList().get(position).getItem_weight());
        height.setText(myReceivedShipment.getItemList().get(position).getItemDimensions().getHeight());
        width.setText(myReceivedShipment.getItemList().get(position).getItemDimensions().getWidht());
        lenght.setText(myReceivedShipment.getItemList().get(position).getItemDimensions().getLenght());
        Uri mUri = Uri.parse(myReceivedShipment.getItemList().get(position).getItem_photo());
        itemImage.setImageURI(mUri);

       if(myReceivedShipment.getItemList().get(position).getItem_size().equals("small")){
           smallBox.setChecked(true);
           mediumBox.setChecked(false);
           largeBox.setChecked(false);


       }
        else if(myReceivedShipment.getItemList().get(position).getItem_size().equals("medium")){
            mediumBox.setChecked(true);
            largeBox.setChecked(false);
            smallBox.setChecked(false);

        }
       else if(myReceivedShipment.getItemList().get(position).getItem_size().equals("large")){
           largeBox.setChecked(true);
           mediumBox.setChecked(false);
           smallBox.setChecked(false);

       }


        //mySpinner.set


    }


    private void inititView(){
        myReceivedShipment=(Shipment) getIntent().getSerializableExtra("myShipment");


        receivedItemFlag=false;
        checkBox=false;
        imageFlag=false;
        itemImage=findViewById(R.id.item_image);
        smallBox=findViewById(R.id.small_checkbox);
        mediumBox=findViewById(R.id.medium_checkbox);
        largeBox=findViewById(R.id.large_checkbox);
        reviewBtn=findViewById(R.id.review_btn);
        plusBtn=findViewById(R.id.plus);
        minusBtn=findViewById(R.id.minus);
        quantity=findViewById(R.id.quantity);
        mySpinner = findViewById(R.id.spinner);
        link=findViewById(R.id.item_link);
        price= findViewById(R.id.item_price);
        name=findViewById(R.id.item_name);
        weight=findViewById(R.id.package_weight);
        height= findViewById(R.id.item_height);
        width=findViewById(R.id.item_width);
        lenght=findViewById(R.id.item_lenght);
        reviewBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        itemImage.setOnClickListener(this);


        smallBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(smallBox.isChecked()){
                    mediumBox.setChecked(false);
                    largeBox.setChecked(false);
                    stringPackageSize="small";
                    checkBox=true;
                }
            }
        });
        mediumBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mediumBox.isChecked()){
                    smallBox.setChecked(false);
                    largeBox.setChecked(false);
                    stringPackageSize="medium";
                    checkBox=true;
                }
            }
        });

        largeBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(largeBox.isChecked()){
                    smallBox.setChecked(false);
                    mediumBox.setChecked(false);
                    stringPackageSize="large";
                    checkBox=true;
                }
            }
        });






    }
    private boolean validateData() {

        stringLink=link.getText().toString().trim();
        stringPrice=price.getText().toString().trim();
        stringName=name.getText().toString().trim();

        stringQuantity=quantity.getText().toString().trim();
        stringPackageWeight=weight.getText().toString().trim();
        stringCategory=mySpinner.getSelectedItem().toString();
        stringHeight=height.getText().toString().trim();
        stringWidth=width.getText().toString().trim();
        stringLenght=lenght.getText().toString().trim();

        if (stringName.isEmpty()) {
            name.setError(getString(R.string.required_field));
            name.requestFocus();
            return false;
        }
        if (stringPackageWeight.isEmpty()) {
            weight.setError(getString(R.string.required_field));
            weight.requestFocus();
            return false;
        }
        if (stringHeight.isEmpty() ) {
            height.setError(getString(R.string.required_field));
            height.requestFocus();
            return false;
        }
        if (stringWidth.isEmpty() ) {
            width.setError(getString(R.string.required_field));
            width.requestFocus();
            return false;
        }
        if (stringLenght.isEmpty() ) {
            lenght.setError(getString(R.string.required_field));
            lenght.requestFocus();
            return false;
        }

        if (checkBox==false){
            return false;
        }
        if(stringCategory.isEmpty()){
            stringCategory="Not Specified";

        }


        if(!imageFlag){

            Toast.makeText(activity,"Select Item Image ", Toast.LENGTH_SHORT).show();
            return false;
        }


        return  true;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            itemImage.setImageURI(uri);
            imageFlag=true;
            stringItemPhoto=uri.toString();
        }
    }

    @Override
    public void onClick(View v) {

        if (v==plusBtn){
            int x = Integer.parseInt((String) quantity.getText())+1;
            quantity.setText(x+"");

        }
        if (v== minusBtn){
            int x = Integer.parseInt((String) quantity.getText());
            if(x==1){
                quantity.setText(1+"");
            }
            else {
                x=x-1;
                quantity.setText(x+"");
            }
        }
        if(v==itemImage){
            Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,1);

        }

        if (v==reviewBtn){

                if(validateData()){
                    ItemDimensions ItemDimensions=new ItemDimensions(stringHeight,stringWidth,stringLenght);
                    myItem=new Item(stringLink,stringPrice,stringPackageWeight,stringPackageSize,stringName,stringCategory
                            ,stringItemPhoto,stringQuantity,ItemDimensions);

                    myReceivedShipment.setItemOnList(myItem);
                    Toast.makeText(activity,myReceivedShipment.getFrom() + "/"+ myReceivedShipment.getItemList().size(),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddShippingItemDetails_new.this, ReviewPrices.class);
                    intent.putExtra("myShipment",myReceivedShipment);
                    startActivity(intent);

                }else {
                    Toast.makeText(activity,"in-validate Data",Toast.LENGTH_SHORT).show();
                }




                /*
                if(validateData()){

                    ItemDimensions ItemDimensions=new ItemDimensions(stringHeight,stringWidth,stringLenght);
                    myReceivedShipment.getItemList().get(position).setItem_link(stringLink);
                    myReceivedShipment.getItemList().get(position).setItem_price(stringPrice);
                    myReceivedShipment.getItemList().get(position).setItem_name(stringName);
                    myReceivedShipment.getItemList().get(position).setQuantity(stringQuantity);
                    myReceivedShipment.getItemList().get(position).setItem_size(stringPackageSize);
                    myReceivedShipment.getItemList().get(position).setItem_category(stringCategory);
                    myReceivedShipment.getItemList().get(position).setItem_photo(stringItemPhoto);
                    myReceivedShipment.getItemList().get(position).setItemDimensions(ItemDimensions);
                    Intent intent = new Intent(activity, ReviewPrices.class);
                    intent.putExtra("myShipment",myReceivedShipment);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(activity,"in-validate Data",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                if(validateData()){
                    ItemDimensions ItemDimensions=new ItemDimensions(stringHeight,stringWidth,stringLenght);
                    myItem=new Item(stringLink,stringPrice,stringPackageWeight,stringPackageSize,stringName,stringCategory
                            ,stringItemPhoto,stringQuantity,ItemDimensions);

                    myReceivedShipment.setItemOnList(myItem);
                    Toast.makeText(activity,myReceivedShipment.getFrom() + "/"+ myReceivedShipment.getItemList().size(),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity, ReviewPrices.class);
                    intent.putExtra("myShipment",myReceivedShipment);
                    startActivity(intent);

                }else {
                    Toast.makeText(activity,"in-validate Data",Toast.LENGTH_SHORT).show();
                }

            }
*/

        }

    }
}
