import java.util.Scanner;

public class Console {

     public static void main(String[] args) {

       String inputString;
       Scanner in = new Scanner(System.in);
       boolean goingon =true;
       Test mytest = new Test();
       
while (goingon){       // Reads a single line from the console 
       // and stores into name variable
       inputString = in.nextLine();

       // Reads a integer from the console
       // and stores into age variable
System.out.println(inputString);
       
       if (inputString.contains("create")) mytest.createMaze();
       if (inputString.contains("solve")) mytest.solveMaze();
       if (inputString.contains("quit")) goingon=false;
       
       

       
       
}

in.close();            




    }
}