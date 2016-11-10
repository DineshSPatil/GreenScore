package com.ptc.greenscore;


public class MyData {
	
    public int sensorData;
    public String licenseNumber;

    MyData(int data, String licenseNumber) {
        this.sensorData = data;
        this.licenseNumber = licenseNumber;
    }
    /*public void setData(int data, Timestamp timestamp){
    	this.sensorData = data;
        this.myTime = timestamp;
    }*/

}
