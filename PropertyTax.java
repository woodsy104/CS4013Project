import java.time.*;
public class PropertyTax {
    //private double propTax;
    private static double[] propValue = {0, 150000, 400001, 650000};
    private static double[] rate = {0, 0.01, 0.02, 0.04};
    private static String[] locations = {"City","Large town","Small town","Village","Countryside"};
    private static int[] locationBase = {100, 80, 60, 50, 25};

    /**public void setPropertyTax(double propTax){
        this.propTax = propTax;
    }
    public double getPropertyTax() {
        return propTax;
    }*/

    public static double getRate(double value){
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
    public static int getLocationRate(String location){
        int locationRate = 0;
        for (int i = 0; i < locations.length - 1; i++){
            if(location.equals(locations[i])){
                locationRate = locationBase[i];
            }
        }
        return locationRate;
    }
    
    public static double calculatePropertyTax(double value, String location, boolean PPR, int yearRegistered) {
        double propTax = value * getRate(value);
        propTax += getLocationRate(location);
        propTax += 100;
        if(!PPR) {
            propTax += 100;
        }
        if(Year.now().getValue() - yearRegistered > 0){
            for(int i = 0; i < Year.now().getValue() - yearRegistered; i++){
                propTax += (propTax / 100) * 7;
            }
        }
        return propTax;
    }
}