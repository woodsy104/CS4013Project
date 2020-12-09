import java.util.ArrayList;
import java.time.*;
public class Payment{
    String owner;
    String eircode;
    String address;
    double propValue;
    double toPay;    
    int year; 
    
    public Payment(String owner, String eircode, String address, double propValue, double toPay, int year){
         this.owner = owner;
         this.eircode = eircode;
         this.address = address; 
         this.propValue = propValue;
         this.toPay = toPay;  
         this.year = Year.now().getValue();
    }
    
    public String getOwner(){
        return owner;
    }
    public String getEircode(){
        return eircode;
    }
    public String getAddress(){
        return address;
    }
    public double toPay(){
        return toPay;
    }
    public double getPropValue(){
        return propValue;
    }
    public int getYear(){
        return year;
    }
    
    public String toString(){
        return "Owner: " + owner + "\nAddress: " + address + "\nEircode: " + eircode + "\nMarketValue: " + propValue + "\nTo Pay: " + toPay;
    }
}