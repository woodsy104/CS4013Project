import java.io.*;
import java.util.*;

 public class paymentRecord{
      static String file = "payment.csv";
    
      public static String[] readToArray(String csvFile) {
        
        String file = "payment.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> paymentRecord = new ArrayList<String>();
        String[] data = new String[256];
        try {
            
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {                
                data = line.split(cvsSplitBy);

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
        return data;
     }
   
    public static String getOwner(String owner){
       String[] data = readToArray(file);
       for(int i=0; i<data.length; i++){
           if(data[i].equals(owner)){
             return data[i];  
            }
        }
       return null;
}

    public static void main(String[] args){
        System.out.println(Arrays.toString(readToArray(file)));
        
    }
   }


