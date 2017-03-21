package cz.polankam.jmx.demo.mxbeans;

public interface DemoManagerMXBean {
    public void sayHello();
    public String getMessage();
    public long factorial(long number);
    public void killMe();
}
