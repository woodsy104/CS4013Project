
public class PropertyList
{
    private ArrayList<Property> propertylist = new ArrayList();
    private ArrayList<Property> propertiesInLocation;
    public void addProperty(Property p){
        propertylist.add(p);
    }
    
    public void removeProperty(Property p){
        propertylist.remove(p);
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
        propertiesInLocation = new ArrayList();
        for(int i = 0; i < propertylist.size(); i++){
            if(propertylist.get(i).getLocationCategory().equalsIgnoreCase(location)){
                propertiesInLocation.add(propertylist.get(i));
            }
        }
        return propertiesInLocation;
    }
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
}
