import java.util.*;
public class Owner
{
    private String name;
    private ArrayList<Property> properties;
    
    public Owner(){       
        properties = new ArrayList<Property>();
    }
    public Owner(String name){
        this.name = name;
        properties = new ArrayList<Property>();
    }
    
    public void setName(String name){
        this.name = name;      
    }
    public String getName(){
        return this.name;
    }
    
    public void registerProperty(Property property){
        properties.add(property);
    }    
    public Property[] viewProperties(){ 
        Property[] output = new Property[properties.size()]; 
        output = properties.toArray(output); 
      
        return output;
    }
    
    public void payPropertyTax(double payment){
        
    }
}
