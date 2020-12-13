import java.io.*;
import java.util.*;
/**
Read or Write payments/property to/from a csv file
*/
public class readOrWriteFile
{
     /**
     Read registered properties from csv file
     */
     public static ArrayList<Property> readProperties() {
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
                        if(data[5].equals("true") || data[5].equals("TRUE")){
                           ppr = true;
                        }                            
                        csvProperties.add(new Property(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4], ppr, Integer.parseInt(data[6])));                        
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
     /**
     Read payments made from csv file
     */
     public static ArrayList<Payment> readPayments() {
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
                        csvPayments.add(new Payment(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]), Integer.parseInt(data[5])));
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
     
     /**
    Write property to csv file
    */    
    public static void writeProperty(String owner, String address, String eircode, double marketValue, String location, boolean PPR, int yearRegistered) throws FileNotFoundException{
            FileOutputStream fos = new FileOutputStream("properties.csv", true);
            PrintWriter pw = new PrintWriter(fos);
            
            pw.println(owner + "," + address + "," + eircode + "," + marketValue + "," + location  + ","  + PPR + "," + yearRegistered);
            pw.flush();
            pw.close();
     }     
    /**
    Write payment made to csv file
    */ 
    public static void writePayment(String owner, String eircode, String address, double propValue, double toPay, int yearPaid) throws FileNotFoundException{
            FileOutputStream fos = new FileOutputStream("payments.csv", true);
            PrintWriter pw = new PrintWriter(fos);
            
            pw.println(owner + "," + eircode + "," + address + "," + propValue + "," + toPay + "," + yearPaid);
            pw.flush();
            pw.close();
    }
}
