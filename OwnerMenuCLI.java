import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.time.*;
/**
 A menu for the property owners.
*/
public class OwnerMenuCLI
{
     private Scanner in;
     
     /**
     Constructs a OwnerMenu object
     */
     public OwnerMenuCLI(){
         in = new Scanner(System.in);
     }
    
     /**
     Runs the vending machine system. 
     @param machine the vending machine
     */
     public void run(Owner menu)
     throws IOException{
         boolean more = true;
         while (more){
             System.out.println("P)roperty Owner   D)epartment of Enviroment Q)uit");
             String userType = in.nextLine().toUpperCase();
             if(userType.equals("P")){             
                 System.out.println("R)egister Property V)iew Properties S)earch By Year P)ayments M)ake Payment B)alancing Statement Q)uit");
                 String command = in.nextLine().toUpperCase();
                 if (command.equals("R")){ //view properties
                     System.out.println("Name: ");
                     String name = in.nextLine();
                     System.out.println("Address: ");
                     String address = in.nextLine();
                     System.out.println("Eircode: ");
                     String eircode = in.nextLine();
                     System.out.println("Location Category: " + "(City,Large town,Small town,Village,Countryside)");
                     String locationCategory = in.nextLine();
                     System.out.println("Market Value: ");
                     double estimatedMarketValue = in.nextDouble();
                     System.out.println("PPR: ");
                     boolean ppr = in.nextBoolean();
                     
                     in.nextLine(); // read the new-line character
                     menu.registerProperty(new Property(name, address, eircode, estimatedMarketValue, locationCategory, ppr, Year.now().getValue()));                
                 }else if (command.equals("V")){ 
                     for(int i = 0; i < menu.viewProperties().size(); i++){
                       System.out.println(menu.viewProperties().get(i).toString());
                     }
                 } else if(command.equals("S")){
                     System.out.println("Year: ");
                     int year = in.nextInt();
                     System.out.println(menu.viewPropertiesByYear(year));
                 } else if(command.equals("P")){
                     
                 } else if(command.equals("M")){    
                    try{
                        Property p = (Property)
                        getChoice(menu.viewProperties());
                        System.out.println("Amount: ");
                        double ammount = in.nextDouble();
                        menu.payPropertyTax(p, ammount);
                        System.out.println("Payment Confirmed For: " + p.toString());
                    } catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                 } else if (command.equals("B")){ //pay property tax
                     menu.getBalancingStatement();
                 } else if (command.equals("Q")){
                     more = false;
                 }
             } else if(userType.equals("D")){
                 DeptEnvironmentRecord depMenu = new DeptEnvironmentRecord();
                 System.out.println("P)roperty Tax Data T)ax Data for Owners O)verdue Tax S)tatistics By Area I)vestigate Rate Change Q)uit");
                 String command = in.nextLine().toUpperCase();
                 
                 if(command.equals("P")){
                     System.out.println("Address: ");
                     String address = in.nextLine();
                     System.out.println(depMenu.getTaxDataForProperty(address));
                 } else if(command.equals("T")){
                     System.out.println("Property Owner Name: ");
                     String name = in.nextLine();
                     System.out.println(depMenu.getTaxDataForOwner(name));
                 } else if(command.equals("O")){
                     System.out.println("Year: ");
                     int year = in.nextInt();
                     System.out.println("Sort by Eircode?   Y)es   N)o");
                     String sort = in.nextLine().toUpperCase();
                     if(command.equals("Y")){
                         System.out.println("Eircode: ");
                         String eircode = in.nextLine();
                         System.out.println(depMenu.getOverdueTaxForYear(year, eircode));
                     } else if(command.equals("N")){
                         System.out.println(depMenu.getOverdueTaxForYear(year));
                     }
                 } else if(command.equals("S")){
                     System.out.println("Eircode: ");
                     String eircode = in.nextLine();
                     System.out.println(depMenu.getPropertyTaxStatistics(eircode));
                 } else if(command.equals("I")){
                     System.out.println("Rate Change: ");
                     int rateChange = in.nextInt();
                     System.out.println(depMenu.investigateRateChange(rateChange));
                 } else if(command.equals("Q")){
                     more = false;
                 }
             } else if(userType.equals("Q")){
                 more = false;
             }
        }
     }
     
     private Property getChoice(ArrayList<Property> choices){
         while (true){
              char c = 'A';
              for (Property choice : choices){
                  System.out.println(c + ") " + choice);
                  c++;
              }
              String input = in.nextLine();
              int n = input.toUpperCase().charAt(0) - 'A';
              if (0 <= n && n < choices.size()){
                  return choices.get(n);
                }
         }
     }
} 