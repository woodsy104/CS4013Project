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
            paymentRecord list = new paymentRecord();
            String[] pay = list.readToArray("payment.csv");
            String sPay = "";
            for(int i=0; i<pay.length; i++){
                if(pay[i].equals(this.owner)){
                    sPay = pay[i+4];
                    tPay = Integer.parseInt(sPay);
                }
            }
            this.toPay -= tPay;
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