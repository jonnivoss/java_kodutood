package oo.hide;

import java.util.Arrays;

public class PointSet {

    private Point[] points;
    private int pointsInSet;
    private int capacity;
    private static Point[] enlargenArray(Point[] pointArray){
        int len = pointArray.length;
        Point[] newArray = Arrays.copyOf(pointArray, pointArray.length * 2);
        for (int i = len; i < newArray.length; i++){
            newArray[i] = null;
        }
        return newArray;
    }
    private Point[] removeElement(Point[] rec, int index){
        Point[] temp = Arrays.copyOf(rec, rec.length);
        for (int i = index; i < rec.length - 1; i++) {
            temp[i] = temp[i+1];
        }
        temp[rec.length - 1] = null;
        return temp;
    }


    public PointSet(int capacity) {
        this.capacity = capacity;
        this.points = new Point[capacity];
        this.pointsInSet = 0;
    }

    public PointSet() {
        this(10);
    }

    public void add(Point point) {
        if (contains(point)){
            return;
        }
        if(pointsInSet >= capacity){
            points = enlargenArray(points);
        }

        points[pointsInSet] = point;

        pointsInSet++;
    }

    public int size() {
        return pointsInSet;
    }

    public boolean contains(Point point) {
        for (Point p : points){
            if(p == null){
                continue;
            }
            if(p.equals(point)){
                return true;
            }
        }
        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet temp = new PointSet();
        for (Point p : points){
            if(p != null && !other.contains(p)){
                temp.add(p);
            }
        }
        return temp;
    }

    public PointSet intersect(PointSet other) {
        PointSet temp = new PointSet();
        for (Point p : points){
            if(p != null && other.contains(p)){
                temp.add(p);
            }
        }
        return temp;
    }

    public void remove(Point point) {
        for (int i = 0; i < points.length; i++){
            if(point.equals(points[i])){
                points = removeElement(points, i);
            }
        }
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof PointSet))
        {
            return true;
        }
        PointSet set = (PointSet) obj;
        if (size() != set.size()){
            return false;
        }
        if(set.size() == 0 && this.size() == 0){
            return true;
        }
        for (Point p : points){
            if(!set.contains(p)){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString(){
        if(pointsInSet == 0){
            return "No elements in set";
        }
        StringBuilder s = new StringBuilder(new String() + "(%s, %s)".formatted(points[0].getX(), points[0].getY()));
        for (int i  = 1; i < pointsInSet; i++){
            if(points[i] == null){
                s.append(", ");
                s.append("null");
            }else {
                s.append(", ");
                s.append("(%s, %s)".formatted(points[i].getX(), points[i].getY()));
            }

        }
        return s.toString();
    }
}
