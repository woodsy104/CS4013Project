import java.io.*;
import java.util.*;
import java.time.*;
public class Owner
{
    private String name;
    private ArrayList<Property> properties;
    private ArrayList<Payment> payments;
    private double amountPaid;
    
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
        readOrWriteFile.writeProperty(name, property.getAddress(), property.getEircode(), property.getMarketValue(), property.getLocation(), property.isPPR(), property.getYearRegistered());
    }
    public void registerMultipleProperties(Property[] props) throws FileNotFoundException{
        for(int i = 0; i < props.length; i++){
            properties.add(props[i]);
            readOrWriteFile.writeProperty(name, props[i].getAddress(), props[i].getEircode(), props[i].getMarketValue(), props[i].getLocation(), props[i].isPPR());
        }
    }
    
    public ArrayList<Property> viewProperties(){ 
        ArrayList<Property> propOutput = new ArrayList<Property>();
        properties = new ArrayList<Property>();
        properties= readOrWriteFile.readProperties();
        
        for(int i = 0; i < properties.size(); i++){
           if(name.equals((properties.get(i)).getOwner())){
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
           if(name.equals((properties.get(i)).getOwner()) && year == (properties.get(i)).getYearRegistered()){
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
    
    public void getBalancingStatement(){
        System.out.println("howaya now");
    }
    
    public void payPropertyTax(Property property, double ammount) throws FileNotFoundException{
        property.payPropertyTax(ammount);
        readOrWriteFile.writePayment(name, property.getEircode(), property.getAddress(), property.getMarketValue(), (PropertyTax.calculatePropertyTax(name, property.getMarketValue(), property.getLocation(), property.isPPR(), ammount, property.getYearRegistered())), Year.now().getValue());
    }
   
}
