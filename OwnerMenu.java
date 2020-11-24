import java.util.Scanner;
import java.io.IOException;
/**
 A menu for the property owners.
*/
public class OwnerMenu
{
     private Scanner in;
     
     /**
     Constructs a OwnerMenu object
     */
     public OwnerMenu(){
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
             System.out.println("V)iew Properties S)elect Year G)et Balancing Statement P)ay Property Tax R)egister Property Q)uit");
             String command = in.nextLine().toUpperCase();
             
             if (command.equals("V")){ //view properties
                 for (Property p : menu.viewProperties()){
                    System.out.println(p);
                 }                
             } else if (command.equals("S")) { //select by year
                 //machine.addCoin((Coin) getChoice(coins));
             } else if (command.equals("G")){ //balancing statement
                 //System.out.println("Removed: " + machine.removeMoney());
             } else if (command.equals("P")){ //pay property tax
                 try{
                    Property p = (Property)
                    getChoice(menu.viewProperties());
                    System.out.println("Amount: ");
                    double ammount = in.nextDouble();
                    menu.payPropertyTax(ammount);
                    System.out.println("Payment Confirmed For: " + p);
                 } catch (Exception ex){
                    System.out.println(ex.getMessage());
                 }
               } else if (command.equals("R")){
                 System.out.println("Name: ");
                 String name = in.nextLine();
                 System.out.println("Address: ");
                 String address = in.nextLine();
                 System.out.println("Eircode: ");
                 String eircode = in.nextLine();
                 System.out.println("Market Value: ");
                 double estimatedMarketValue = in.nextDouble();
                 System.out.println("Location Category: ");
                 String locationCategory = in.nextLine();
                 System.out.println("PPR: ");
                 boolean ppr = in.nextBoolean();
                      
                 in.nextLine(); // read the new-line character
                 menu.registerProperty(new Property(name, address, eircode, estimatedMarketValue, locationCategory, ppr));
             } else if (command.equals("Q")){
                 more = false;
             }
          }
     }
     
     private Object getChoice(Object[] choices){
         while (true){
              char c = 'A';
              for (Object choice : choices){
                  System.out.println(c + ") " + choice);
                  c++;
              }
              String input = in.nextLine();
              int n = input.toUpperCase().charAt(0) - 'A';
              if (0 <= n && n < choices.length){
                  return choices[n];
                }
         }
     }
} 

