package cn.gmlee.demo.css.proxy;

public class Pr implements P{
    protected String yyy = "y";

    public String getYyy() {
        return yyy;
    }

    public void setYyy(String yyy) {
        this.yyy = yyy;
    }

    @Override
    public String toString() {
        return "Pr{" +
                "yyy='" + yyy + '\'' +
                '}';
    }
}
