import java.util.ArrayList;
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
    
    public void payment(double amount){
        this.toPay = amount - this.propTax;
       if(this.toPay == 0){
            this.status = "P";
        }
        else{
        this.status = "NP";
        
        
        
    }
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
    
    public double getPropValue(){
        return propValue;
    }
    
    public String getStatus(){
        return status;
    }
}
