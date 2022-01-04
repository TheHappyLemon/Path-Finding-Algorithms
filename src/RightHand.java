import java.util.ArrayList;
import java.util.Scanner;

enum direction{
    UP,
    RIGHT,
    DOWN,
    LEFT
}

public class RightHand{

    static int maze[][];
    static ArrayList<Point> path = new ArrayList<>();

    static direction dir;
    static int[] right = new int[2];
    static int[] forward = new int[2];

    public static boolean WallIn(int y, int x){
        return y > maze.length-1 || y < 0 || x > maze[0].length - 1 || x < 0 || maze[y][x] == 1 ;
    }

    public static void SetDirections(int y, int x){
        if (dir==direction.UP) {
            right[0] = y; right[1] = x+1;
            forward[0] = y-1;forward[1] = x;
        } else if (dir == direction.RIGHT) {
            right[0] = y+1; right[1] = x;
            forward[0] = y;forward[1] = x+1;
        } else if (dir == direction.DOWN) {
            right[0] = y; right[1] = x-1;
            forward[0] = y+1;forward[1] = x;
        } else if (dir == direction.LEFT) {
            right[0] = y-1; right[1] = x;
            forward[0] = y;forward[1] = x-1;
        }
    }

    public static void FindExit() {
        int y = 0,x = 0;
        long max = maze.length * maze[0].length * 2;
        long iter = 0;
        while((y != maze.length - 1 || x != maze[0].length - 1) && iter < max){
            SetDirections(y, x);
            path.add(new Point(x, y));
            iter++;
            if(!WallIn(right[0],right[1])){
                //If there is no wall on the right we turn right
                if (dir==direction.UP) dir=direction.RIGHT;
                else if (dir==direction.RIGHT) dir=direction.DOWN;
                else if (dir==direction.LEFT) dir=direction.UP;
                else if (dir==direction.DOWN) dir=direction.LEFT;
                y = right[0]; x = right[1];
            }
            else if(WallIn(right[0],right[1]) && WallIn(forward[0],forward[1])){
                //If wall on the right and forward, we go left
                if (dir==direction.UP) dir=direction.LEFT;
                else if (dir==direction.RIGHT) dir=direction.UP;
                else if (dir==direction.LEFT) dir=direction.DOWN;
                else if (dir==direction.DOWN) dir=direction.RIGHT;
            }
            else if(WallIn(right[0],right[1]) && !WallIn(forward[0],forward[1])){
                //If wall on the right, we go forward, so dir remains the same
                y = forward[0]; x = forward[1];
            }
        }
        if (iter < max)
            path.add(new Point(maze.length-1, maze[0].length-1));
    }

    public static void main(int[][] arr){
        maze = arr;
        dir = direction.DOWN;
        FindExit();
        if (path.get(path.size() - 1).equals(new Point(maze.length - 1, maze[0].length - 1))) {
            System.out.println("Found exit with Right Hand method:");
            for (Point p : path) {
                System.out.print(p.toString());
            }
            System.out.println();
        } else {
            System.out.println("Found no exit with Right Hand this time :(");
        }
    }
}