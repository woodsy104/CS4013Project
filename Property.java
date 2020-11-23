
public class Property
{
    private String propertyOwner;
    private String address;
    private String eircode;
    private double estimatedMarketValue;
    private String locationCategory;
    private boolean principalPrivateResidence;
    
    public Property(String propertyOwner, String adress, String eircode, double estimatedMarketValue){
        
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
}
