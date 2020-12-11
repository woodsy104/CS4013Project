import java.io.IOException;
/**
 This program simulates a menu.
*/
public class MenuSimulation
{
    /**
    This program simulates a menu.
    */
    public static void main(String[] args)
    throws IOException{
         Owner owner = new Owner("Kevin Mulry");
         DeptEnvironment deptOfEnvi = new DeptEnvironment();
         MenuCLI menu = new MenuCLI();
         menu.run(owner, deptOfEnvi);
    }
} 