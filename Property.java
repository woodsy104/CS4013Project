import java.time.*;
import java.io.*;
import java.util.*;
/**
 A Property
*/
public class Property
{
    private String owner;
    private String address;
    private String eircode;
    private double marketValue;
    private String location;
    private boolean PPR;
    private int yearRegistered;
    
    /**
    Constructs a Payment object
    @param owner            name of property owner
    @param address          address of property
    @param eircode          eircode of property    
    @param marketValue      value of the property
    @param location         tax left to pay
    @param PPR              year of payment
    @param yearRegistered   year of payment
    */
    public Property(String owner, String address, String eircode, double marketValue, String location, boolean PPR, int yearRegistered){
        this.owner = owner;
        this.address = address;
        this.eircode = eircode;
        this.marketValue = marketValue;
        this.location = location;
        this.PPR = PPR;
        this.yearRegistered = yearRegistered;
    }
    
    /**
    Get property owner name
    @return owner  name of property owner 
    */
    public String getOwner(){
        return this.owner;
    }
    /**
    Get property address
    @return address  address of property
    */
    public String getAddress(){
        return this.address;
    }
    /**
    Get property eircode
    @return eircode  eircode of property
    */
    public String getEircode(){
        return this.eircode;
    }
    /**
    Get value of property
    @return marketValue  value of the property
    */
    public double getMarketValue(){
        return this.marketValue;
    }
    /**
    Get location category of property i.e. City, Large town, Small town, Village, Countryside
    @return location  location category of the property
    */
    public String getLocation(){
        return this.location;
    }
    /**
    Check whether property is principal private residence
    @return PPR  returns true if preoperty is principal privat residence
    */
    public boolean isPPR(){
        return this.PPR;
    }
    /**
    Get year property was registered
    @return yearRegistered  returns year property was registered
    */
    public int getYearRegistered(){
        return this.yearRegistered;
    }
    
    /**
    Pay property tax for a property
    @param ammount  ammount of property tax to pay
    */
    public void payPropertyTax(double ammount, String address) throws FileNotFoundException{
        double taxDue = PropertyTax.calculatePropertyTax(owner, address, marketValue, location, PPR, yearRegistered);
        readOrWriteFile.writePayment(owner, eircode, address, marketValue, (taxDue - ammount), Year.now().getValue());
    }
    /**
    Get property tax for a property
    @param Property  property to get property tax for
    */
    public static double getPropertyTax(Property p) throws FileNotFoundException{
        double taxDue = PropertyTax.calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), p.getYearRegistered());
        ArrayList<Payment> payments = readOrWriteFile.readPayments();
        
        for(int i = 0; i < payments.size(); i++){
            if((payments.get(i)).getOwner().equals(p.getOwner()) && (payments.get(i)).getAddress().equals(p.getAddress())){
                taxDue = (payments.get(i)).toPay();
            }
        }
        
        return taxDue;
    }
    
    /**
    Format property toString()
    @return property as string 
    */
    public String toString(){
        return "Owner: " + owner + "\nAddress: " + address + "\nEircode: " + eircode + "\nMarketValue: " + marketValue + "\nLocation Category: " + location + "\nPPR: " + PPR + "\nYear Registered: " + yearRegistered + "\n";
    }
}
