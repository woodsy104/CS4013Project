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
    @return properties  return Tax Data For Property 
    */
    public ArrayList<Property> getTaxDataForProperty(String address){
        ArrayList<Property> properties = new ArrayList<Property>();
        propOutput = new ArrayList<Property>();
        propOutput = readOrWriteFile.readProperties();
        
        for(int i = 0; i < propOutput.size(); i++){
           if(address.equals((propOutput.get(i)).getAddress())){
               properties.add(propOutput.get(i));
           }                          
        }    
        return properties;
    }
    /**
    Get Tax Data For Property for an Owner
    @return properties  return Tax Data For Property for Owner 
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
    public ArrayList<String> getOverdueTaxForYear(int year){
        return PropertyTax.calculateOverdue(year);
    }
    /**
    Get Overdue Tax For Year for and area
    @return propOutput  return Overdue Tax For Year for an area 
    */
    public ArrayList<String> getOverdueTaxForYear(int year, String eircode){
        return PropertyTax.calculateOverdue(year, eircode);
    }
    
    /**
    Get Property Tax Statistics for and area
    @return propOutput  return Property Tax Statistics for and area 
    */
    public String getPropertyTaxStatistics(String eircode){
        double totalTaxPaid = 0;
        double numProps = 1;
        
        ArrayList<Property> properties = new ArrayList<Property>();
        ArrayList<Payment> payments = new ArrayList<Payment>();
        properties = readOrWriteFile.readProperties();
        payments = readOrWriteFile.readPayments();
        
        for(int i = 0; i < properties.size(); i++){
            Property p = properties.get(i);
            if((p.getEircode()).equals(eircode)){
                numProps++;                
                double propTax = PropertyTax.calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), p.getYearRegistered());
            
                for(int j = 0; j < payments.size(); j++){
                    if((payments.get(i)).getEircode() == eircode && p.getAddress() == (payments.get(i)).getAddress()){
                        propTax -= (payments.get(i)).toPay();
                    }            
                }
                totalTaxPaid += propTax;
            }
        }
        
        return "Eircode: " + eircode + "\nTotal Tax Paid: " + totalTaxPaid + "\nAverage Tax Paid: " + (totalTaxPaid / numProps);
    }
    
    /**
    Investigate Rate Change on Property Tax
    @return propOutput  return Impact of Rate Changes 
    */
    public String investigateRateChange(int city, int largeTown, int smallTown, int village, int countryside){
        ArrayList<Property> properties = new ArrayList<Property>();
        properties = readOrWriteFile.readProperties();
        double currentTotalTax = 0;
        double totalTaxAfterChange = 0;
        int[] locationBaseChange = {city, largeTown, smallTown, village, countryside};
                        
        for(int i = 0; i < properties.size(); i++){
            Property p = (properties.get(i));
            currentTotalTax += PropertyTax.calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), p.getYearRegistered());
            totalTaxAfterChange += PropertyTax.calculatePropertyTaxChange(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), p.getYearRegistered(), locationBaseChange);
        }
        return "Current Total Tax: " + currentTotalTax + "\nTotal Tax After Change: " + totalTaxAfterChange + "\nIncrease: " + (totalTaxAfterChange - currentTotalTax);
    }    
}
