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
            if(location.equals(locations[i])){
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
    public static void calculateOverdue(){
        ArrayList<Property> properties = new ArrayList<Property>();
        ArrayList<Payment> payments = new ArrayList<Payment>();
        properties = readOrWriteFile.readProperties();
        payments = readOrWriteFile.readPayments();
        //double taxDue[][] = new double[][];
        double dueForYear;
        
        for(int i = 0; i < properties.size(); i++){                
            for(int j = 0; j <= (Year.now().getValue() - (properties.get(i)).getYearRegistered()); j++){                
                Property p = properties.get(i);
                dueForYear = calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), p.getYearRegistered());
                
                for(int k = 0; k < payments.size(); k++){ 
                    if(p.getOwner() == (payments.get(i)).getOwner() && p.getAddress() == (payments.get(i)).getAddress()){
                        dueForYear = (payments.get(i)).toPay();
                    }
                }
                
                //taxDue[i][j] = dueForYear;
            }
        }
    }
}