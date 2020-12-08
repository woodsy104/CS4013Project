
public class PropertyTax {
    private double propTax;
    private double[] propValue = {0, 150000, 400001, 650000};
    private double[] rate = {0, 0.01, 0.02, 0.04};
    private String[] locations = {"City","Large town","Small town","Village","Countryside"};
    private int[] locationBase = {100, 80, 60, 50, 25};

    public void setPropertyTax(double propTax){
        this.propTax = propTax;
    }

    public double getPropertyTax(Property p) {
        this.p = p;
        calculatePropertyTax(p.getMarketValue(), p.getLocationCategory(), p.isPPR());
        return propTax;
    }

    public double getRate(double value){
        double marketRate = 0;
        for(int i = 0; i < propValue.length; i++){
            if((propValue[i] <= value) && (value < propValue[i+1])){
                marketRate = rate[i];
            } else if(value >= 650000){
                marketRate = rate[3];
            }
        }
        return marketRate;
    }

    public int getLocationRate(String location){
        int locationRate = 0;
        for (int i = 0; i < locations.length; i++){
            if(location.equals(locations[i])){
                locationRate = locationBase[i];
            }
        }
        return locationRate;
    }
    public void calculatePropertyTax(double value, String location, boolean PPR) {
        propTax = value * getRate(value);
        propTax += getLocationRate(location);
        propTax += 100;
        if (!PPR) {
            propTax += 100;
        }
        setPropertyTax(propTax);
    }
}
