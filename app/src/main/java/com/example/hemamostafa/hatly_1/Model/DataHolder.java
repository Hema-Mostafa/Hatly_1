package com.example.hemamostafa.hatly_1.Model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DataHolder {

  public ArrayList<String> governmentNames;
  private int hatlyFees;
  private int travellerFees;
  final static private int COMMISION_STATIC_NUM =5;

  public int computeHatlyFees(String source,String distination){

      int sourcePos=getGovernmentNames().indexOf(source);
      int distinationPos=getGovernmentNames().indexOf(distination);

      int distance= Math.abs(sourcePos - distinationPos);
      hatlyFees=distance * COMMISION_STATIC_NUM;

      if(hatlyFees > 40 ){
          hatlyFees = 40; // maximum Value for hatlyFees
      }
      if(hatlyFees < 15){
          hatlyFees = 15;  // minimun valuew for hatlyFees
      }
      if( hatlyFees ==0 ){
          hatlyFees = 0;
      }

    return hatlyFees;

  }

  public int computeTravellerFees(){
      travellerFees = hatlyFees * 3 ;
      return travellerFees;
  }




    public DataHolder() {
        governmentNames=new ArrayList<>();
        hatlyFees=0;
        travellerFees=0;
        governmentNames.add("alexandria");
        governmentNames.add("cairo");
        governmentNames.add("giza");
        governmentNames.add("faiyum");
        governmentNames.add("beni suef");
        governmentNames.add("minya");
        governmentNames.add("asyut");
        governmentNames.add("sohag");
        governmentNames.add("qena");
        governmentNames.add("luxor");
        governmentNames.add("aswan");

    }

    public ArrayList<String> getGovernmentNames() {
        return governmentNames;
    }
}
