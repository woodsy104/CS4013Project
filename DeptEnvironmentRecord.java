package guiClasses;

import java.io.*;
import java.util.*;
public class DeptEnvironmentRecord{
    private ArrayList<Property> propOutput;
    public DeptEnvironmentRecord(){           
        this.propOutput = new ArrayList<Property>();
    }
    
    public String getTaxDataForProperty(String address){
        propOutput = new ArrayList<Property>();
        propOutput = readOrWriteFile.readProperties();
        
        for(int i = 0; i < propOutput.size(); i++){
           if(address.equalsIgnoreCase((propOutput.get(i)).getAddress())){
               return (propOutput.get(i)).toString();
           }                          
        }    
        return "Try another address";
    }
    public String getTaxDataForOwner(String name){
        propOutput = new ArrayList<Property>();
        propOutput = readOrWriteFile.readProperties();
        
        for(int i = 0; i < propOutput.size(); i++){
           if(name.equalsIgnoreCase((propOutput.get(i)).getOwner())){
               return (propOutput.get(i)).toString();
           }                          
        }    
        return "Try another name";
    }
    
    public String getOverdueTaxForYear(int year){
        
        return "";
    }
    public String getOverdueTaxForYear(int year, String eircode){
        
        return "";
    }
    
    public String getPropertyTaxStatistics(String eircode){
        
        return "";
    }
    
    public String investigateRateChange(double propTax){
        
        return "";
    }
    
    
}
