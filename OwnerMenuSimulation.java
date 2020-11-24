import java.io.IOException;
/**
 This program simulates an owner menu.
*/
public class OwnerMenuSimulation
{
    public static void main(String[] args)
    throws IOException{
         Owner owner = new Owner();
         OwnerMenu menu = new OwnerMenu();
         menu.run(owner);
    }
} 