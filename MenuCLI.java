import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.time.*;
/**
 A menu for the property owners and department of environment.
*/
public class MenuCLI
{
     private Scanner in;
     
     /**
     Constructs a Menu object
     */
     public MenuCLI(){
         in = new Scanner(System.in);
     }
    
     /**
     Runs the property tax menu 
     @param menu  the menu
     */
     public void run(Owner menu, DeptEnvironment deptMenu)
     throws IOException{
         boolean more = true;
         while (more){
             System.out.println("P)roperty Owner   D)epartment of Enviroment    Q)uit");
             String userType = in.nextLine().toUpperCase();
             boolean propOwner = true;
             boolean depOfEnv = true;
             if(userType.equals("P")){ //Property Owner                  
                 while (propOwner){
                     System.out.println("R)egister Property V)iew Properties S)earch By Year P)ayments M)ake Payment B)alancing Statement Q)uit");
                     String command = in.nextLine().toUpperCase();
                     if (command.equals("R")){ //Register Properties
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
                     }else if (command.equals("V")){ //View properties
                         for(int i = 0; i < menu.viewProperties().size(); i++){
                           System.out.println(menu.viewProperties().get(i).toString());
                         }
                     } else if(command.equals("S")){ //Search By Year
                         System.out.println("Year: ");
                         int year = in.nextInt();
                         System.out.println(menu.viewPropertiesByYear(year));
                     } else if(command.equals("P")){ //Payments
                         
                     } else if(command.equals("M")){ //Make Payment   
                        try{
                            Property p = (Property)
                            getChoice(menu.viewProperties());                            
                            System.out.println(PropertyTax.calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), p.getYearRegistered()));  
                            System.out.println("Ammount: ");
                            double ammount = in.nextDouble();
                            
                            if(ammount > PropertyTax.calculatePropertyTax(p.getOwner(), p.getAddress(), p.getMarketValue(), p.getLocation(), p.isPPR(), p.getYearRegistered())){
                                System.out.println("Enter a smaller ammount");
                            }
                            
                            menu.payPropertyTax(p, ammount);
                            System.out.println("Payment Confirmed For: " + p.toString());
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                     } else if (command.equals("B")){ //Balancing Statement
                         menu.getBalancingStatement();
                     } else if (command.equals("Q")){ //Quit
                         propOwner = false;
                     }
                }
             } else if(userType.equals("D")){ //Department of Environment                 
                 while (depOfEnv){
                     System.out.println("P)roperty Tax Data T)ax Data for Owners O)verdue Tax S)tatistics By Area I)vestigate Rate Change Q)uit");
                     String command = in.nextLine().toUpperCase();
                     
                     if(command.equals("P")){ //Property Tax Data
                         System.out.println("Address: ");
                         String address = in.nextLine();
                         System.out.println(deptMenu.getTaxDataForProperty(address));
                     } else if(command.equals("T")){ //Tax Data For Owner
                         System.out.println("Property Owner Name: ");
                         String name = in.nextLine();
                         System.out.println(deptMenu.getTaxDataForOwner(name));
                     } else if(command.equals("O")){ //Overdue By Year
                         System.out.println("Year: ");
                         int year = in.nextInt();
                         System.out.println("Sort by Eircode?   Y)es   N)o");
                         String sort = in.nextLine().toUpperCase();
                         if(command.equals("Y")){ //Overdue By Year and Eircode
                             System.out.println("Eircode: ");
                             String eircode = in.nextLine();
                             System.out.println(deptMenu.getOverdueTaxForYear(year, eircode));
                         } else if(command.equals("N")){ //Overdue by Year
                             System.out.println(deptMenu.getOverdueTaxForYear(year));
                         }
                     } else if(command.equals("S")){ //Statistics
                         System.out.println("Eircode: ");
                         String eircode = in.nextLine();
                         System.out.println(deptMenu.getPropertyTaxStatistics(eircode));
                     } else if(command.equals("I")){ //Ivestigate Rate Changes
                         System.out.println("Rate Change: ");
                         int rateChange = in.nextInt();
                         System.out.println(deptMenu.investigateRateChange(rateChange));
                     } else if(command.equals("Q")){ //Quit
                         depOfEnv = false;
                     }
                 }
             } else if(userType.equals("Q")){
                 more = false;
             }
        }
     }
     
     /**
      * Runs the property tax menu 
      * @param choices  choices to be displayed on screen to user
      * @return choices choices displayed on screen to user
     */
     private Property getChoice(ArrayList<Property> choices){ //offer choice
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