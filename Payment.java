import java.util.ArrayList;
import java.io.*;
public class Payment{
    String owner;
    String eircode;
    String address;
    double toPay;
    double propTax;
    String status;
    double propValue;
    
    public Payment(String owner, String eircode, String address, double propValue, double toPay){
     this.propValue = propValue;
     PropertyTax val = new PropertyTax();
     this.propTax = val.getRate(propValue);
     this.owner = owner;
     this.eircode = eircode;
     this.address = address;
     this.toPay = this.propTax;   
    }
    
     public void payment(double amount) throws FileNotFoundException{
        this.toPay = amount - this.propTax;
        double tPay = 0;
        double remaining = 0;
        ArrayList<Payment> pay = readOrWriteFile.readPayments();
       for(int i=0; i<pay.size(); i++){
           if(pay.get(i).equals(this.owner)){
               Payment p = pay.get(i+3);
               tPay = p.toPay();
               remaining = amount - tPay;
            }
        }
       
       readOrWriteFile.writePayment(this.owner, this.eircode, this.address,this.propValue,remaining); 
    
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
    
    public double getRate(){
        return propTax;
    }
    
    public double toPay(){
        return toPay;
    }
    
    public double getPropValue(){
        return propValue;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String toString(){
        return "Owner: " + owner + "\nAddress: " + address + "\nEircode: " + eircode + "\nMarketValue: " + propValue + "\nTo Pay: " + toPay;
    }
}
