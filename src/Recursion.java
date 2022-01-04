import java.util.ArrayList;
import java.util.Scanner;

public class Recursion {

    static int[][] arr;

    static boolean foundExit = false;
    static ArrayList<Point> path = new ArrayList<>();

    static void FindRoute(Point cPoint, Point pPoint, int iter) {
        if (!foundExit) {
            if (cPoint.x == arr[0].length - 1 && cPoint.y == arr.length - 1) {
                foundExit = true;
                path.add(new Point(cPoint.x,cPoint.y));
            } else {
                path.add(new Point(cPoint.x,cPoint.y));
                if (cPoint.x < arr[0].length - 1 && arr[cPoint.y][cPoint.x + 1] == 0
                        && !new Point(cPoint.x + 1, cPoint.y).equals(pPoint))
                    FindRoute(new Point(cPoint.x + 1, cPoint.y), cPoint, iter+1);
                if (cPoint.y < arr.length - 1 && arr[cPoint.y + 1][cPoint.x] == 0
                        && !new Point(cPoint.x, cPoint.y + 1).equals(pPoint))
                    FindRoute(new Point(cPoint.x, cPoint.y + 1), cPoint, iter+1);
                if (cPoint.x > 0 && arr[cPoint.y][cPoint.x - 1] == 0
                        && !new Point(cPoint.x - 1, cPoint.y).equals(pPoint))
                    FindRoute(new Point(cPoint.x - 1, cPoint.y), cPoint, iter+1);

                if (cPoint.y > 0 && arr[cPoint.y - 1][cPoint.x] == 0
                        && !new Point(cPoint.x, cPoint.y - 1).equals(pPoint))
                    FindRoute(new Point(cPoint.x, cPoint.y - 1), cPoint, iter+1);
            }
        }
    }

    public static void main(int[][] maze) {
        arr = maze;
        try {
            FindRoute(new Point(0, 0), new Point(0, 0), 0);
            if (foundExit) {
                System.out.println("Found exit with Recursion method:");
                for (Point p: path) {
                    System.out.print(p.toString());
                }
                System.out.println();
            }
            else
                System.out.println("Found no exit with Recursion method this time :(");
        } catch (StackOverflowError e) {
            System.out.println("Maze was too huge for Recursion method :(");
        }
    }

    private static void Test() {
        for (int i = 0; i < 100; i++) {
            foundExit = false;
            long start = System.nanoTime();
            FindRoute(new Point(0, 0), new Point(0, 0), 0);
            long end = System.nanoTime();
            System.out.println(end - start);
        }
    }
}