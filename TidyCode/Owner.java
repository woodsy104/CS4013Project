import java.io.*;
import java.util.*;
import java.time.*;
/**
 A property Owner
*/
public class Owner
{
    private String name;
    private ArrayList<Property> properties;
    private ArrayList<Payment> payments;
    private double amountPaid;
    
    /**
    Constructs an Owner object
    @param name  property owners name
    */
    public Owner(String name){
        this.name = name;
        properties = new ArrayList<Property>();
        payments = new ArrayList<Payment>();
    }
    
    /**
     * Get property owners name
     * @param name  name of property owner
    */
    public void setName(String name){
        this.name = name;      
    }
    /**
     * Get property owners name
     * @return name  name of property owner
    */
    public String getName(){
        return this.name;
    }
    
    /**
    Register a property into the system
    @param property  property to register
    */
    public void registerProperty(Property property) throws FileNotFoundException{
        properties.add(property);
        readOrWriteFile.writeProperty(name, property.getAddress(), property.getEircode(), property.getMarketValue(), property.getLocation(), property.isPPR(), property.getYearRegistered());
    }
    
    /**
    View properties in the system
    @return propOutput  properties in the system
    */
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
    /**
    View properties in the system by year they were registered
    @param year         year to sort by
    @return propOutput  properties registered in the system for given year
    */
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
    /**
    View payments in the system
    @return paymentOutput  payments in the system
    */
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
    
    /**
    View balancing statement for property
    @return balancingStatement  Balancing Statement for a property
    */
    public String getBalancingStatement(String address) throws FileNotFoundException{
        ArrayList<Payment> payments = new ArrayList<Payment>();
        payments = readOrWriteFile.readPayments();        
        double taxDue = 0;
        double taxPaid = 0;
        double taxOwed = 0;
        
        for(int i = 0; i < (viewProperties().size()); i++){
            Property p = viewProperties().get(i);
            if(p.getAddress().equals(address)){
                double tax = (Property.getPropertyTax(p));
                taxDue += tax;
                for(int j = 0; j < payments.size(); j++){
                    if(address.equals((payments.get(j)).getAddress())){
                        System.out.println("entered");
                        tax -= (payments.get(j)).toPay();
                    }            
                }
                taxPaid = tax;
                taxOwed += PropertyTax.calculateOverdue(p);
            }
        }
        return "Total Tax Due: " + String.format( "%.2f", taxDue) + "\nTotal Tax Paid: " + String.format( "%.2f", taxPaid) + "\nOverdue Tax: " + String.format( "%.2f", taxOwed);
    }
    /**
    View balancing statement for year
    @return balancingStatement  Balancing Statement for year
    */
    public String getBalancingStatement(int year) throws FileNotFoundException{
        ArrayList<Payment> payments = new ArrayList<Payment>();
        payments = readOrWriteFile.readPayments();
        double taxDue = 0;
        double taxPaid = 0;
        double taxOwed = 0;
        
         for(int i = 0; i < (viewProperties().size()); i++){            
            Property p = viewProperties().get(i);
            taxOwed += PropertyTax.calculateOverdue(p);
            if(p.getOwner().equals(name) && p.getYearRegistered() <= year){
                taxDue += (PropertyTax.calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), year));   
            }
        }
        for(int i = 0; i < payments.size(); i++){
            if((payments.get(i).getOwner().equals(name)) && (payments.get(i).getYear() == (year))){
                taxPaid = taxDue - (payments.get(i)).toPay();
            }
        }
        return "Total Tax Due: " +  String.format( "%.2f", taxDue) + "\nTotal Tax Paid: " + String.format( "%.2f", taxPaid) + "\nOverdue Tax: " + String.format( "%.2f", taxOwed);
    }
    
    /**
    Pay property tax for a property
    @param property  Property to pay property tax for
    @param ammount   Ammount to pay
    */
    public void payPropertyTax(Property property, double ammount) throws FileNotFoundException{
        property.payPropertyTax(ammount, property.getAddress());
    }   
}
