package cz.polankam.jmx.demo.mxbeans;

public class DemoManager implements DemoManagerMXBean {

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }

    @Override
    public String getMessage() {
        return "Have a great day!";
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

}
