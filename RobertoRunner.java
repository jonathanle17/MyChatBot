import java.util.Scanner;
/**
 * Write a description of class RobertoRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RobertoRunner
{
    // instance variables - replace the example below with your own
   public static void main(String[] args)
   {
       Roberto roberto = new Roberto();
       
       System.out.println (roberto.getGreeting());
       Scanner in = new Scanner (System.in);
       String statement = in.nextLine();
       
       while (!statement.equals("Bye"))
       {
           System.out.println (roberto.getResponse(statement));
           statement = in.nextLine();
       }
   }
}
