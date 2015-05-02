package edu.nju.treasuryArbitrage.model.calculation;

import java.util.Date;

public class OptimalKT {
    private int group;
    private Date time;
    private double optimalK;
    private double optimalT;

    public OptimalKT(int group, Date time, double OptimalK, double OptimalT) {
        this.group = group;
        this.time = time;
        this.optimalK = OptimalK;
        this.optimalT = OptimalT;
    }

    public OptimalKT(int group, double OptimalK, double OptimalT) {
        this.setGroup(group);
        this.setOptimalK(OptimalK);
        this.setOptimalT(OptimalT);
    }

    public OptimalKT(int group) {
        this.setGroup(group);
        this.setOptimalK(0);
        this.setOptimalT(0);
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getOptimalK() {
        return optimalK;
    }

    public void setOptimalK(double optimalK) {
        this.optimalK = optimalK;
    }

    public double getOptimalT() {
        return optimalT;
    }

    public void setOptimalT(double optimalT) {
        this.optimalT = optimalT;
    }

    @Override
    public String toString() {
        String string = "group: " + group + "\n" +
                "time:" + time + "\n" +
                "k:" + optimalK + "\n" +
                "t:" + optimalT;
        return string;
    }
}
