import java.time.*;
import java.util.*;

/**
 Property Tax Calcuations
*/
public class PropertyTax {
    private static double[] propValue = {0, 150000, 400001, 650000};
    private static double[] rate = {0, 0.01, 0.02, 0.04};
    private static String[] locations = {"City","Large town","Small town","Village","Countryside"};
    private static int[] locationBase = {100, 80, 60, 50, 25};
    
    /**
    Get the rate of property tax paid
    @param value        property value
    @return marketRate  rate of property tax paid
    */
    private static double getRate(double value){
        double marketRate = 0;
        for(int i = 0; i < propValue.length - 1; i++){
            if((propValue[i] <= value) && (value < propValue[i + 1])){
                marketRate = rate[i];
            } else if(value >= 650000){
                marketRate = rate[3];
            }
        }
        return marketRate;
    }
    /**
    Get the rate of property tax paid for location
    @param location       location of property 
    @return locationRate  rate of property tax paid for location
    */
    private static int getLocationRate(String location){
        int locationRate = 0;
        for (int i = 0; i < locations.length - 1; i++){
            if(location.equals(locations[i])){
                locationRate = locationBase[i];
            }
        }
        return locationRate;
    }
    /**
    Get the rate of property tax paid for location
    @param location       location of property 
    @return locationRate  rate of property tax paid for location
    */
    private static int getLocationRateChange(String location, int[] locationRateChange){
        int locationRate = 0;
        
        for (int i = 0; i < locations.length - 1; i++){
            if(location.equals(locations[i])){
                locationRate = locationRateChange[i];
            }
        }
        return locationRate;
    }
    /**
    Calculate the ammount of property tax paid for overdue tax
    @param name             name of property owner
    @param yearRegistered   year the property was registered
    @return count           ammount of property tax paid for overdue tax
    */
    private static int calculateCompound(String name, int yearRegistered){
        int count = Year.now().getValue() - yearRegistered;
        ArrayList<Payment> payments = new ArrayList<Payment>();
        payments = readOrWriteFile.readPayments();
        if(count != 0){
            for(int year = yearRegistered; year < Year.now().getValue(); year++){
                for(int i = 0; i < payments.size(); i++){                
                    if(name.equals((payments.get(i)).getOwner()) && year == (payments.get(i)).getYear()){
                        count--;
                    }
                }
            }
        }
        
        return count;
    }
    /**
    Check if payment already made
    @param name     name of property owner
    @param address  address of property
    @return ammount left to pay if found
    */
    private static double checkToPay(String name, String address){
        ArrayList<Payment> payments = new ArrayList<Payment>();
        payments = readOrWriteFile.readPayments();
        
        for(int i = 0; i < payments.size(); i++){                
                if(name.equals((payments.get(i)).getOwner()) && address == (payments.get(i)).getAddress()){
                    return (payments.get(i)).toPay();
                }
        }
        return 0;
    }
    
    /**
    Calculate Property Tax for Property
    @param owner            name of property owner
    @param address          address of property
    @param value            market value of property
    @param location         location of property
    @param PPR              is principal private residence
    @ param yearRegistered  year property was registered
    @return ammount left to pay if found
    */
    public static double calculatePropertyTax(String owner, String address, double value, String location, boolean PPR, int yearRegistered) {
        if(checkToPay(owner, address) != 0){
            return checkToPay(owner, address);
        }
        
        double propTax = value * getRate(value);
        propTax += getLocationRate(location);
        propTax += 100;
        if(!PPR) {
            propTax += 100;
        }
        for(int i = 0; i < calculateCompound(owner, yearRegistered); i++){
            propTax += (propTax / 100) * 7;
        }        
       
        return propTax;
    }
    /**
    Calculate Property Tax for Property after location rate change
    @param owner            name of property owner
    @param address          address of property
    @param value            market value of property
    @param location         location of property
    @param PPR              is principal private residence
    @ param yearRegistered  year property was registered
    @return ammount left to pay if found
    */
    public static double calculatePropertyTaxChange(String owner, String address, double value, String location, boolean PPR, int yearRegistered, int[] locationRate) {
        if(checkToPay(owner, address) != 0){
            return checkToPay(owner, address);
        }
        
        double propTax = value * getRate(value);
        propTax += getLocationRateChange(location, locationRate);
        propTax += 100;
        if(!PPR) {
            propTax += 100;
        }
        for(int i = 0; i < calculateCompound(owner, yearRegistered); i++){
            propTax += (propTax / 100) * 7;
        }        
       
        return propTax;
    }
    
    
    /**
    Calulate overdue tax for a year
    @param year          get over due for this year 
    @return overDueTax   return list of overdue tax in this year
    */
    public static ArrayList<String> calculateOverdue(int year){
        ArrayList<Property> properties = new ArrayList<Property>();
        ArrayList<Payment> payments = new ArrayList<Payment>();
        ArrayList<String> overDueTax = new ArrayList<String>();
        properties = readOrWriteFile.readProperties();
        payments = readOrWriteFile.readPayments();
        double dueForYear;
        
        for(int i = 0; i < properties.size() - 1; i++){                               
                Property p = properties.get(i);
                 if(year > p.getYearRegistered()){
                    dueForYear = calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), year);
                    
                    for(int j = 0; j < payments.size(); j++){ 
                        if(p.getOwner() == (payments.get(j)).getOwner() && p.getAddress() == (payments.get(j)).getAddress()){
                            dueForYear = (payments.get(j)).toPay();
                        }
                    }
                    overDueTax.add("Name: " + p.getOwner() + ", Address: " + p.getAddress() + ", Ammount: " + dueForYear + "\n");
                }
        }   
        return overDueTax;
    }
    /**
    Calculate overdue tax for a year in a given area
    @param year          get over due for this year 
    @param eircode       area to sort by
    @return overDueTax   return list of overdue tax in this year
    */
    public static ArrayList<String> calculateOverdue(int year, String eircode){
        ArrayList<Property> properties = new ArrayList<Property>();
        ArrayList<Payment> payments = new ArrayList<Payment>();
        ArrayList<String> overDueTax = new ArrayList<String>();
        properties = readOrWriteFile.readProperties();
        payments = readOrWriteFile.readPayments();
        double dueForYear;
        
        
        for(int i = 0; i < properties.size() - 1; i++){ 
            Property p = properties.get(i);
            if(p.getEircode() == eircode && year > p.getYearRegistered()){               
                dueForYear = calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), year);
                
                for(int j = 0; j < payments.size(); j++){ 
                    if(p.getOwner() == (payments.get(j)).getOwner() && p.getAddress() == (payments.get(j)).getAddress()){
                        dueForYear = (payments.get(j)).toPay();
                    }
                }
                overDueTax.add("Name: " + p.getOwner() + ", Address: " + p.getAddress() + ", Ammount: " + dueForYear + "\n");
            }
        }
        return overDueTax;
        }
        /**
        Calculate overdue tax for a year in a given area
        @param p             get over due for this property 
        @return overDueTax   return total overdue tax for this property
        */
        public static double calculateOverdue(Property p){
            ArrayList<Payment> payments = new ArrayList<Payment>();
            payments = readOrWriteFile.readPayments();
            double overDueTax = 0;             
            double dueForYear;
            
            for(int year = p.getYearRegistered(); year < Year.now().getValue(); year++){
                dueForYear = calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), year);
                for(int j = 0; j < payments.size(); j++){ 
                        if(p.getOwner() == (payments.get(j)).getOwner() && p.getAddress() == (payments.get(j)).getAddress()){
                            dueForYear = (payments.get(j)).toPay();
                        }
                }
                overDueTax += dueForYear;
            }
            return overDueTax;       
        }
}