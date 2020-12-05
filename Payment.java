import java.util.ArrayList;
public class Payment{
    String owner;
    String eircode;
    String address;
    double Balance;
    double propTax;
    char status;
    double propValue;
    
    public Payment(String owner, String eircode, String address, double propValue){
     this.propValue = propValue;
     PropertyTax val = new PropertyTax();
     this.propTax = val.getRate(propValue);
     this.owner = owner;
     this.eircode = eircode;
     this.address = address;
        
    }
    
    public void payment(double amount){
        this.Balance = amount - this.propTax;
       if(this.Balance == 0){
            this.status = 'P';
        }
        else{
        
        /* Not finised yet.
        Not entirely sure on how were donig the paymethod. 
        Will look at it more later 
        */
        
        
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
    
    public char getStatus(){
        return status;
    }
}
