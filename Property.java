import java.time.*;
public class Property
{
    private String propertyOwner;
    private String address;
    private String eircode;
    private double estimatedMarketValue;
    private String locationCategory;
    private boolean principalPrivateResidence;
    private int yearRegistered = 0;
    
    public Property(String propertyOwner, String address, String eircode, double estimatedMarketValue, String locationCategory, boolean principalPrivateResidence){
        this.propertyOwner = propertyOwner;
        this.address = address;
        this.eircode = eircode;
        this.estimatedMarketValue = estimatedMarketValue;
        this.locationCategory = locationCategory;
        this.principalPrivateResidence = principalPrivateResidence;
        this.yearRegistered = Year.now().getValue();
    }
    public Property(String propertyOwner, String address, String eircode, double estimatedMarketValue, String locationCategory, boolean principalPrivateResidence, int yearRegitered){
        this.propertyOwner = propertyOwner;
        this.address = address;
        this.eircode = eircode;
        this.estimatedMarketValue = estimatedMarketValue;
        this.locationCategory = locationCategory;
        this.principalPrivateResidence = principalPrivateResidence;
        this.yearRegistered = yearRegistered;
    }
    
    public void setPropertyOwner(String owner){
        this.propertyOwner = owner;
    }
    public String getPropertyOwner(){
        return this.propertyOwner;
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
        this.estimatedMarketValue = marketValue;
    }
    public double getMarketValue(){
        return this.estimatedMarketValue;
    }
    
    public void setLocationCategory(String location){
        this.locationCategory = location;
    }
    public String getLocationCategory(){
        return this.locationCategory;
    }
    
    public void setPPR(boolean PPR){
        this.principalPrivateResidence = PPR;
    }
    public boolean isPPR(){
        return this.principalPrivateResidence;
    }
    
    public void setYearRegistered(int year){
        this.yearRegistered = year;
    }
    public int getYearRegistered(){
        return this.yearRegistered;
    }
    
    public void calculatePropertyTax(double value, String location, boolean PPR){
        //System.out.println("" + calculatePropertyTax(value, location, PPR));
    }
    
    public String toString(){
        return "Owner: " + propertyOwner + "\nAddress: " + address + "\nEircode: " + eircode + "\nMarketValue: " + estimatedMarketValue + "\nLocation Category: " + locationCategory + "\nPPR: " + principalPrivateResidence + "\nYear Registered: " + yearRegistered;
    }
}
