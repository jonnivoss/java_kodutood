package oo.hide;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PointSet {

    private Point[] points;
    private int pointsInSet;
    private int capacity;
    private Point[] enlargenArray(Point[] pointArray){
        int len = pointArray.length;
        Point[] newArray = Arrays.copyOf(pointArray, pointArray.length * 2);
        for (int i = len; i < newArray.length; i++){
            newArray[i] = null;
        }
        return newArray;
    }
    private Point[] removeElement(Point[] rec, int index){
        Point[] temp = Arrays.copyOf(rec, rec.length);
        for (int i = index; i < pointsInSet - 1; i++) {
            temp[i] = temp[i+1];
        }
        pointsInSet--;
        temp = Arrays.copyOf(temp, pointsInSet);
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
        if(pointsInSet == capacity){
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
        if(!this.contains(point)){ return;}
        for (int i = 0; i < points.length; i++){
            if(point.equals(points[i])){
                points = removeElement(points, i);
            }
        }
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof PointSet set))
        {
            return true;
        }
        if(set.size() == 0 && this.size() == 0){
            return true;
        }
        for (int i = 0; i < this.size(); i++){
            if(!set.contains(points[i])){
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
        String[] s = new String[pointsInSet];
        for (int i  = 0; i < pointsInSet; i++){
            if(points[i] == null){
                s[i] = "null";
            }else {
                s[i] = "(%s, %s)".formatted(points[i].getX(), points[i].getY());
            }
        }
        String temp = String.join(", ",s);
        return temp;
    }
}
