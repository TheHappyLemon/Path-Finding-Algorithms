import java.util.*;


public class DFS {
    static int[][] maze;

    static ArrayList<Point> path = new ArrayList<>();

    public static void FindExit(Point p, Point exit){
        Stack<Point> points = new Stack<>();
        ArrayList<Point> visited = new ArrayList<>();
        points.push(p);
        Point curP = null;
        while (!points.empty()){
            curP = points.pop();
            path.add(curP);
            if(!visited.contains(curP)){
                visited.add(curP);
                //path.add(curP);
                if(!curP.equals(exit)) {
                    var neighbours = curP.GetNeighbours(maze);
                    if (neighbours.size() != 0)
                        for (Point n: neighbours) {
                            points.push(n);
                        }
                }
                else points.clear();
            }
        }
    }
    public static void main(int[][] arr) {
        maze = arr;
        FindExit(new Point(0, 0), new Point(maze.length - 1, maze[0].length - 1));
        if (path.get(path.size()-1).equals(new Point(maze.length - 1, maze[0].length - 1))){
            System.out.println("Found exit with Depth First Search method:");
            for (Point p: path) {
                System.out.print(p.toString());
            }
            System.out.println();
        }
        else{
            System.out.println("Found no exit with  Depth First Search this time :(");
        }
    }
}
