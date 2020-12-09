import java.time.*;
import java.util.*;
public class PropertyTax {
    private static double[] propValue = {0, 150000, 400001, 650000};
    private static double[] rate = {0, 0.01, 0.02, 0.04};
    private static String[] locations = {"City","Large town","Small town","Village","Countryside"};
    private static int[] locationBase = {100, 80, 60, 50, 25};
    
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
    private static int getLocationRate(String location){
        int locationRate = 0;
        for (int i = 0; i < locations.length - 1; i++){
            if(location.equalsIgnoreCase(locations[i])){
                locationRate = locationBase[i];
            }
        }
        return locationRate;
    }
    private static int calculateCompound(String name, int yearRegistered){
        int count = Year.now().getValue() - yearRegistered;
        ArrayList<Payment> payments = new ArrayList<Payment>();
        payments = readOrWriteFile.readPayments();
        
        for(int year = yearRegistered; year < Year.now().getValue(); year++){
            for(int i = 0; i < payments.size(); i++){                
                if(name.equals((payments.get(i)).getOwner()) && year == (payments.get(i)).getYear()){
                    count--;
                }
            }
        }
        
        return count;
    }
    
    public static double calculatePropertyTax(String owner, double value, String location, boolean PPR, double ammount, int yearRegistered) {
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
}
