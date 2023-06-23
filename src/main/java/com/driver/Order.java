package com.driver;
import java.util.List;

import  java.util.Arrays;
public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) { //Constructor

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM

        //**Above thing is already given


        this.deliveryTime= convertDeliveryTime(deliveryTime);
        this.id=id;
//       List<String> list= Arrays.asList(deliveryTime.split(":"));
//       //Convert them into integer
//        int HH = Integer.parseInt(list.get(0));//convert string into integer
//        int MM = Integer.parseInt(list.get(1));
//        this.deliveryTime= HH * 60 + MM; //Converting in minutes

    }

    public String getId() {

        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }


    private int convertDeliveryTime(String deliveryTime){
        //Convert an array to a list
        //Arrays.asList(deliveryTime.split()
        //List<String> list= Arrays.asList(deliveryTime.split(":"));
        //Convert them into integer
        //int HH = Integer.parseInt(list.get(0));//convert string into integer
        //int MM = Integer.parseInt(list.get(1));
        //return HH * 60 + MM; //Converting in minutes
        String[]time= deliveryTime.split(":");
        //1st part                                2nd part
        return Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

    }
    public String convertDeliveryTime(int deliveryTime){
        int hh= deliveryTime/60;
        int mm= deliveryTime% 60;
        String HH= String.valueOf(hh);
        String MM= String.valueOf(mm);
        return String.format("%s:%s", HH, MM);
    }

    public String getDeliveryTimeString(){
     return convertDeliveryTime(this.deliveryTime);
    }

    //Make Setters also

    public void setId(String id) {

        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {

        this.deliveryTime = deliveryTime;
    }

    //giving string in the constructor
    public void setDeliveryTime(String deliveryTime){

        this.deliveryTime = convertDeliveryTime(deliveryTime);
    }
    //to not repeat the above thing

}
