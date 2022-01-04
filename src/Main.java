import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static int Input(String msg){
        boolean wrongInput = true;
        int value = -1;
        while (value < 1){
            try{
                System.out.println(msg);
                value = scanner.nextInt();
                if (value < 1){
                    System.out.println("Should be positive number!");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Wrong input");
                scanner.next();
            }
        }
        return value;
    }

    public static void main(String[] args) {
        int width = 0, height = 0, mode = -1;
        width = Input("Enter maze width");
        height = Input("Enter maze height");
        boolean wrongInput = true;
        while (wrongInput){
            // 0 - by hands; 1 = Full Random; 2 - Only Fill; 3 - Random and Fill
            mode = Input("Enter maze generation variant (1, 2, 3, 4)");
            if (mode != 1 && mode != 2 && mode != 3 && mode != 4){
                System.out.println("Options are: 1, 2, 3, 4");
            }
            else {
                wrongInput = false;
            }
        }
        // https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input
        int[][] maze =  Maze.GetMaze(width, height, mode, scanner);
        Maze.Print();
        wrongInput = true;
        while (wrongInput){
            // 1 - RightHand; 2 - Full Recursion; 3 - Depth First search
            mode = Input("Enter path search variant (1, 2, 3)");
            if (mode != 1 && mode != 2 && mode != 3){
                System.out.println("Options are: 1, 2, 3");
            }
            else {
                wrongInput = false;
            }
        }
        //scanner.close();
        //OutOfMemory Exception may occur in RightHand method if maze will be too large
        //For instance 10 000 x 10 000 maze
        //Recursion method also will not find the exit in large mazes, for example 5000x5000 due to stack overflow
        if (mode == 1){
            RightHand.main(maze);
        }
        else if (mode == 2){
            Recursion.main(maze);
        }
        else if (mode == 3){
            DFS.main(maze);
        }
    }
}
