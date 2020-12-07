import java.util.*;
public class Owner
{
    private String name;
    private ArrayList<Property> properties;
    private ArrayList<Property> propertylist = new ArrayList();
    private double amountPaid;
    
    public Owner(){       
        propertylist = new ArrayList<Property>();
    }
    public Owner(String name){
        this.name = name;
        propertylist = new ArrayList<Property>();
    }
    
    public void setName(String name){
        this.name = name;      
    }
    public String getName(){
        return this.name;
    }
    
    public double toPay(){
        PropertyTax tax = new PropertyTax();
        double amount = tax.getPropertyTax();
        return amount;
    }
    
    public void payPropertyTax(double payment){
        amountPaid = payment;
        double tax = toPay();
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
    
    public void addProperty(Property p){
        propertylist.add(p);
    }
    public void addMultipleProperties(Property[] props){
        for(int i = 0; i < props.length; i++){
            propertylist.add(props[i]);
        }
    }
    
    public void removeProperty(Property p){
        propertylist.remove(p);
    }
    public void removeMultipleProperties(Property[] props){
        for(int i = 0; i < props.length; i++){
            for(int j = 0; j < propertylist.size(); j++){
                if(props[i] == propertylist.get(i)){
                    propertylist.remove(props[i]);
                    break;
                }
            }
        }
    }
    public void clearList(){
        propertylist.clear();
    }
    
    public ArrayList getPropertyList(){
        return propertylist;
    }
    
    public Property getPropertyByOwner(String name){
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getPropertyOwner().equalsIgnoreCase(name)){
                return propertylist.get(i);
            }
        }
        return null;
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
    }
    
    public static void writeToFile(ArrayList<Property> p) //writes the ArrayList to the CSV file
{
    try 
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter("payment.csv"));

        for (Property p1 : p) 
        {
            writer.write(p1.toString());
        }

        writer.close();
        }
     catch (IOException ex) 
    {
        System.out.println("Input/Output format error");
    }
}
}
