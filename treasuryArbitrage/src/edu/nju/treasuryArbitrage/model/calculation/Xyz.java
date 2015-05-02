package edu.nju.treasuryArbitrage.model.calculation;

import java.util.Date;

public class Xyz {
    private int group;
    private Date time;
    private double x;
    private double y;
    private double z;

    public Xyz(int group, double x, double y, double z) {
        this.group = group;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Xyz(int group, Date time, double x, double y, double z) {
        this.group = group;
        this.time = time;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Xyz(int group) {
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        String string = "group: " + group + "\n" +
                "time:" + time + "\n" +
                "x:" + x + "\n" +
                "y:" + y + "\n" +
                "z:" + z;
        return string;
    }

}
