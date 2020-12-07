import java.io.*;
import java.util.*;

 public class paymentRecord{
      String file = "payment.csv";
    
      public ArrayList<String> readToArray(){
        
        String file = "payment.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> paymentList = new ArrayList<String>();
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
        for(int i=0; i<data.length; i++){
            paymentList.add(data[i]);
        }
        return paymentList;
     }
   
    public String getOwner(String address){
       ArrayList<String> data = readToArray();
       for(int i=0; i<data.size(); i++){
           if(data.get(i).equals(address)){
             return data.get(i-1);  
            }
        }
       return null;
}
    
   }


