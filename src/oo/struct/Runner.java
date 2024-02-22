package oo.struct;

import org.junit.Test;

public class Runner {

    public static class Point3D {
        public int x;
        public int y;
        public int z;

        public Point3D(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    @Test
    public void coordinatesAsArrays() {

        int[][] trianglePoints = {{1, 1, 0}, {5, 1, 0}, {3, 7, 1}};

        for (int[] each : trianglePoints) {
            System.out.println(each[0]);
        }

    }

    @Test
    public void coordinatesAsObjects() {
        Point3D[] points = new Point3D[3];
        points[0] = new Point3D(1, 1, 1);
        points[1] = new Point3D(2, 2, 2);
        points[2] = new Point3D(3, 3, 3);

        for(Point3D p : points)
        {
            System.out.println(p.x);
        }
    }
}
