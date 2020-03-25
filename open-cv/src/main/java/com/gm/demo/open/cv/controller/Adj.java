package com.gm.demo.open.cv.controller;

/**
 * Adj.
 *
 * @author : Jason.lee
 * @version : 1.0
 */
public class Adj {

    private Double ratio;
    private Integer left;
    private Integer top;

    public Double getRatio() {
        return ratio == null ? 0.07 : ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Integer getLeft() {
        return left == null ? 10 : left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return top == null ? 5 : top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }


    @Override
    public String toString() {
        return "Adj{" +
                "ratio=" + ratio +
                ", left=" + left +
                ", top=" + top +
                '}';
    }
}
