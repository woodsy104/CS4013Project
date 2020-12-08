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
        int tPay = 0;
       if(this.toPay == 0){
            this.status = "P";
       } else {
            this.status = "NP";
            ArrayList<Payment> pay = readOrWriteFile.readPayments();
            
            for(int i = 0; i < pay.size(); i++){
                if(this.owner.equals(pay.get(i).getOwner())){
                    this.toPay -= (pay.get(i).toPay());
                }
            }

            if(this.toPay == 0){
                this.status = "P";
            }
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