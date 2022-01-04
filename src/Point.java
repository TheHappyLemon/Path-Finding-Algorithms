import java.util.ArrayList;

class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ArrayList<Point> GetNeighbours(int[][] maze){
        ArrayList<Point> res = new ArrayList<>();
        if(y - 1 > - 1 && maze[y - 1][x] == 0) res.add(new Point(x,y - 1));
        if(y + 1 < maze.length && maze[y + 1][x] == 0) res.add(new Point(x,y + 1));
        if(x - 1 > - 1 && maze[y][x - 1] == 0) res.add(new Point(x - 1,y));
        if(x + 1 < maze[0].length && maze[y][x + 1] == 0) res.add(new Point(x + 1,y));
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point))
            return false;
        Point c = (Point) o;
        return this.x == c.x && this.y == c.y;
    }

    @Override
    public String toString() {
        return ("(" + x + ";" + y + ") ");
    }
}