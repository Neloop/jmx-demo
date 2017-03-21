package cz.polankam.jmx.demo.mbeans;

public interface DemoManagerMBean {
    public void sayHello();
    public String getMessage();
    public long factorial(long number);
    public void killMe();
}
