import java.io.*;
import java.util.*;
public class Owner
{
    private String name;
    private ArrayList<Property> properties;
    private ArrayList<Payment> payments;
    private double amountPaid;
    
    public Owner(){       
        properties = new ArrayList<Property>();
        payments = new ArrayList<Payment>();
    }
    public Owner(String name){
        this.name = name;
        properties = new ArrayList<Property>();
        payments = new ArrayList<Payment>();
    }
    
    public void setName(String name){
        this.name = name;      
    }
    public String getName(){
        return this.name;
    }
    
    public void registerProperty(Property property) throws FileNotFoundException{
        properties.add(property);
        readOrWriteFile.writeProperty(name, property.getAddress(), property.getEircode(), property.getMarketValue(), property.getLocationCategory(), property.isPPR());
    }
    public void registerMultipleProperties(Property[] props) throws FileNotFoundException{
        for(int i = 0; i < props.length; i++){
            properties.add(props[i]);
            readOrWriteFile.writeProperty(name, props[i].getAddress(), props[i].getEircode(), props[i].getMarketValue(), props[i].getLocationCategory(), props[i].isPPR());
        }
    }
    
    public ArrayList<Property> viewProperties(){ 
        ArrayList<Property> propOutput = new ArrayList<Property>();
        properties = new ArrayList<Property>();
        properties= readOrWriteFile.readProperties();
        
        for(int i = 0; i < properties.size(); i++){
           if(name.equals((properties.get(i)).getPropertyOwner())){
               propOutput.add(properties.get(i));
           }                          
        }
        return propOutput;
    }
    public ArrayList<Property> viewPropertiesByYear(int year){ 
        ArrayList<Property> propOutput = new ArrayList<Property>();
        properties = new ArrayList<Property>();
        properties= readOrWriteFile.readProperties();
        
        for(int i = 0; i < properties.size(); i++){
           if(name.equals((properties.get(i)).getPropertyOwner()) && year == (properties.get(i)).getYearRegistered()){
               propOutput.add(properties.get(i));
           }                          
        }
        return propOutput;
    }
    public ArrayList<Payment> viewPayments(){ 
        ArrayList<Payment> paymentOutput = new ArrayList<Payment>();
        payments = new ArrayList<Payment>();
        payments = readOrWriteFile.readPayments();
        
        for(int i = 0; i < payments.size(); i++){
           if(name.equals((payments.get(i)).getOwner())){
               paymentOutput.add(payments.get(i));
           }                          
        }
        return paymentOutput;
    }
    
    public void payPropertyTax(Property property){
        double taxDue = PropertyTax.calculatePropertyTax(property.getMarketValue(), property.getLocationCategory(), property.isPPR(), property.getYearRegistered());
        
    }
    
    /**public double toPay(){
        PropertyTax tax = new PropertyTax();
        double amount = tax.getPropertyTax();
        return amount;
    }    
        
    public double amountPaid(){
        return amountPaid; 
    }    
    public boolean paidPropTax(){
        boolean paid;
        if(toPay() - amountPaid() > 0){
            return false;
        }
        return true;
    }        
    
    public void removeProperty(Property p){
        properties.remove(p);
    }
    public void removeMultipleProperties(Property[] props){
        for(int i = 0; i < props.length; i++){
            for(int j = 0; j < properties.size(); j++){
                if(props[i] == properties.get(i)){
                    properties.remove(props[i]);
                    break;
                }
            }
        }
    }
    public void clearList(){
        properties.clear();
    }
    
    public Property getPropertyByEircode(String eircode){
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getEircode().equalsIgnoreCase(eircode)){
                return propertylist.get(i);
            }
        }
        return null;
    }
    public Property getPropertyByAddress(String address){
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getAddress().equalsIgnoreCase(address)){
                return propertylist.get(i);
            }
        }
        return null;
    }
    
    public ArrayList getLocationProperties(String location){
        properties = new ArrayList();
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getLocationCategory().equalsIgnoreCase(location)){
                properties.add(propertylist.get(i));
            }
        }
        return properties;
    }
    
    //This is for Department
    public ArrayList getOwnersProperties(String owner){
        properties = new ArrayList();
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getPropertyOwner().equalsIgnoreCase(owner)){
                properties.add(propertylist.get(i));
            }
        }
        return properties;
    }
    
    public double marketValueByAddress(String address){
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getAddress().equalsIgnoreCase(address)){
                return propertylist.get(i).getMarketValue();
            }
        }
        return 0;
    }
    public double marketValueByEircode(String eircode){
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getEircode().equalsIgnoreCase(eircode)){
                return propertylist.get(i).getMarketValue();
            }
        }
        return 0;
    }*/
}
