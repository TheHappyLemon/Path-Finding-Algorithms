import java.util.Random;
import java.util.Scanner;

public class Maze {

    static int[][] maze;
    static Random r = new Random();

    static void FillMaze(int dirx, int oy, int ox) {
        int y = oy, x = ox, boundx = dirx == 1 ? maze[0].length - 1 : 0;
        // 0 - down, 1 - dirx
        maze[y][x] = 0;
        while (y != maze.length - 1 || x != boundx) {
            if (r.nextInt(2) == 0){
                if (y + 1 <= maze.length - 1) y += 1;
                else if (x != boundx) x += dirx;

            }else{
                if (x != boundx) x += dirx;
                else if (y + 1 <= maze.length - 1) y += 1;
            }
            maze[y][x] = 0;
        }
    }

    static void GenerateFullRandom() {
        for (int i =0;i<maze.length;i++)
            for (int j=0;j<maze[0].length;j++)
                if (Math.random()<0.5f)
                    maze[i][j]=0;
                else
                    maze[i][j]=1;
        maze[0][0] = 0;
        maze[maze.length-1][maze[0].length-1] = 0;
    }

    static void EnterMaze(int width, int height, Scanner in){
        main: do {
            kek: for (int i = 0; i < height; i++) {
                String ch = in.nextLine();
                if (ch.length() == width) {
                    for (int j = 0; j < width; j++) {
                        if (ch.charAt(j) == '1' || ch.charAt(j) == '0') {
                            int n = Integer.parseInt(String.valueOf(ch.charAt(j)));
                            if (n == 1 || n == 0) {
                                maze[i][j] = n;
                            } else {
                                System.out.println("Maze can only contain 1 and 0");
                                break kek;
                            }
                        } else {
                            System.out.println("Maze cannot contain letters");
                            break kek;
                        }
                    }
                    if (i == height - 1) {
                        break main;
                    }
                } else {
                    System.out.println("Width of line must be the same as digit uou have entered");
                    break kek;
                }
            }
        } while (true);
        maze[0][0] = 0;
        maze[maze.length-1][maze[0].length-1] = 0;
    }

    static void OnlyFill(){
        FillMaze(-1,0,maze[0].length - 1);
        FillMaze(1,0,0);
        FillMaze(-1,(maze.length - 1)/2,(maze[0].length - 1));
    }

    static void Print(){
        for(int i = 0;i<maze.length;i++){
            for(int j = 0;j<maze[0].length;j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] GetMaze(int width, int height, int mode, Scanner scanner) {
        // 1 - by hands; 2 = Full Random; 3 - Only Fill; 4 - Random and Fill
        maze = new int[height][width];
        for (int i =0;i<maze.length;i++)
            for (int j=0;j<maze[0].length;j++)
                maze[i][j] = 1;
        if (mode == 1){
            EnterMaze(width, height, scanner);
        }
        else if (mode == 2){
            GenerateFullRandom();
        }
        else if (mode == 3){
            OnlyFill();
        }
        else if (mode == 4){
            GenerateFullRandom();
            FillMaze(1,0,0);
            FillMaze(-1,0,maze[0].length - 1);
        }
        return maze;
    }
}


