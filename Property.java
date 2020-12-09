import java.time.*;
import java.io.*;
public class Property
{
    private String owner;
    private String address;
    private String eircode;
    private double marketValue;
    private String location;
    private boolean PPR;
    private int yearRegistered;
    
    public Property(String owner, String address, String eircode, double marketValue, String location, boolean PPR, int yearRegitered){
        this.owner = owner;
        this.address = address;
        this.eircode = eircode;
        this.marketValue = marketValue;
        this.location = location;
        this.PPR = PPR;
        this.yearRegistered = yearRegistered;
    }
    
    public void setowner(String owner){
        this.owner = owner;
    }
    public String getOwner(){
        return this.owner;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    
    public void setEircode(String eircode){
        this.eircode = eircode;
    }
    public String getEircode(){
        return this.eircode;
    }
    
    public void setMarketValue(double marketValue){
        this.marketValue = marketValue;
    }
    public double getMarketValue(){
        return this.marketValue;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    
    public void setPPR(boolean PPR){
        this.PPR = PPR;
    }
    public boolean isPPR(){
        return this.PPR;
    }
    
    public void setYearRegistered(int year){
        this.yearRegistered = year;
    }
    public int getYearRegistered(){
        return this.yearRegistered;
    }
    
    public void payPropertyTax(double ammount) throws FileNotFoundException{
        double taxDue = PropertyTax.calculatePropertyTax(owner, marketValue, location, PPR, ammount, yearRegistered);
        readOrWriteFile.writePayment(owner, eircode, address, marketValue, (taxDue - ammount), Year.now().getValue());
    }
    
    public String toString(){
        return "Owner: " + owner + "\nAddress: " + address + "\nEircode: " + eircode + "\nMarketValue: " + marketValue + "\nLocation Category: " + location + "\nPPR: " + PPR + "\nYear Registered: " + yearRegistered + "\n";
    }
}
