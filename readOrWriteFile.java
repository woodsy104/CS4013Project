import java.io.*;
import java.util.*;
public class readOrWriteFile
{
    public static ArrayList<Property> readProperties() {//fix reading off market value and ppr
            String file = "properties.csv";
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            String[] data = new String[256];            
            ArrayList<Property> csvProperties = new ArrayList<Property>();
            
            try {                
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {                
                    data = line.split(cvsSplitBy);
                    if(data[0].equals("Owner")){
                        continue;
                    } else {
                        boolean ppr = false;
                        //double marketValue = Double.valueOf(data[3]);                                       
                        if(data[5].equals("true") || data[5].equals("TRUE")){
                           ppr = true;
                        }
                            
                        csvProperties.add(new Property(data[0], data[1], data[2], 0, data[4], ppr));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return csvProperties;
     }
     
     public static ArrayList<Payment> readPayments() {//fix reading off market value
            String file = "payments.csv";
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            String[] data = new String[256];            
            ArrayList<Payment> csvPayments = new ArrayList<Payment>();
            
            try {                
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {                
                    data = line.split(cvsSplitBy);
                    if(data[0].equals("Owner")){
                        continue;
                    } else {                                                   
                        csvPayments.add(new Payment(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4])));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return csvPayments;
      }
}
