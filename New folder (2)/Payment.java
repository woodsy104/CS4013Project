import java.time.*;

/**
 A Payment
*/
public class Payment{
    String owner;
    String eircode;
    String address;
    double propValue;
    double toPay;    
    int year; 
    
    /**
    Constructs a Payment object
    @param owner      name of property owner
    @param eircode    eircode of property
    @param address    address of property
    @param propValue  value of the property
    @param toPay      tax left to pay
    @param year       year of payment
    */
    public Payment(String owner, String eircode, String address, double propValue, double toPay, int year){
         this.owner = owner;
         this.eircode = eircode;
         this.address = address; 
         this.propValue = propValue;
         this.toPay = toPay;  
         this.year = Year.now().getValue();
    }
    
    /**
    Get property owner name
    @return owner  name of property owner 
    */
    public String getOwner(){
        return owner;
    }
    /**
    Get property eircode
    @return eircode  eircode of property
    */
    public String getEircode(){
        return eircode;
    }
    /**
    Get property address
    @return address  address of property
    */
    public String getAddress(){
        return address;
    }
    /**
    Get value of property
    @return propValue  value of the property
    */
    public double getPropValue(){
        return propValue;
    }
    /**
    Get tax left to pay
    @return toPay  tax left to pay  
    */
    public double toPay(){
        return toPay;
    }
    /**
    Get year of payment
    @return year  year of payment
    */
    public int getYear(){
        return year;
    }
    
    /**
    Format payment toString()
    @return payment as string 
    */
    @Override
	public String toString(){
        return "Owner: " + owner + "\nAddress: " + address + "\nEircode: " + eircode + "\nMarketValue: " + propValue + "\nTo Pay: " + toPay + "\n";
    }
}