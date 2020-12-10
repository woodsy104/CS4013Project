import java.io.*;
import java.util.*;
/**
 The Department of Environment
*/
public class DeptEnvironment{
    private ArrayList<Property> propOutput;
    
    /**
    Constructs a Department of Environment object
    */
    public DeptEnvironment(){           
        this.propOutput = new ArrayList<Property>();
    }
    
    /**
    Get Tax Data For Property
    @return propOutput  return Tax Data For Property 
    */
    public String getTaxDataForProperty(String address){
        propOutput = new ArrayList<Property>();
        propOutput = readOrWriteFile.readProperties();
        
        for(int i = 0; i < propOutput.size(); i++){
           if(address.equals((propOutput.get(i)).getAddress())){
               return (propOutput.get(i)).toString();
           }                          
        }    
        return "Try another address";
    }
    /**
    Get Tax Data For Property for an Owner
    @return propOutput  return Tax Data For Property for Owner 
    */
    public ArrayList<Property> getTaxDataForOwner(String name){
        ArrayList<Property> properties = new ArrayList<Property>();
        propOutput = new ArrayList<Property>();
        propOutput = readOrWriteFile.readProperties();
        
        for(int i = 0; i < propOutput.size(); i++){
           if(name.equals((propOutput.get(i)).getOwner())){
               properties.add(propOutput.get(i));
           }                          
        }    
        return  properties;
    }
    
    /**
    Get Overdue Tax For Year
    @return propOutput  return Overdue Tax For Year 
    */
    public String getOverdueTaxForYear(int year){
        
        return "";
    }
    /**
    Get Overdue Tax For Year for and area
    @return propOutput  return Overdue Tax For Year for an area 
    */
    public String getOverdueTaxForYear(int year, String eircode){
        
        return "";
    }
    
    /**
    Get Property Tax Statistics for and area
    @return propOutput  return Property Tax Statistics for and area 
    */
    public String getPropertyTaxStatistics(String eircode){
        
        return "";
    }
    
    /**
    Investigate Rate Change on Property Tax
    @return propOutput  return Impact of Rate Changes 
    */
    public String investigateRateChange(double propTax){
        
        return "";
    }    
}
