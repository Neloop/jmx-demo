package cz.polankam.jmx.demo.notifications;

public interface DemoManagerMBean {
    public void sayHello();
    public String getMessage();
    public void setMessage(String message);
    public long factorial(long number);
    public void killMe();
    public long getUpTime();
}
