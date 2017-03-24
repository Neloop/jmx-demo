package cz.polankam.jmx.demo.mxbeans;

public class DemoManager implements DemoManagerMXBean {

    private final long startTime;
    private String message = "Have a great day!";

    public DemoManager() {
        startTime = System.nanoTime();
    }

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long factorial(long number) {
        if (number < 1) {
            return 0;
        } else if (number == 1) {
            return 1;
        }

        return number * factorial(number - 1);
    }

    @Override
    public void killMe() {
        System.exit(0);
    }

    @Override
    public long getUpTime() {
        return System.nanoTime() - startTime;
    }

}
