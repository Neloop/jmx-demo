package cz.polankam.jmx.demo.notifications;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class DemoManager extends NotificationBroadcasterSupport
        implements DemoManagerMBean {

    private final long startTime;
    private String message = "Have a great day!";
    private long sequenceNumber = 1;

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
        String oldValue = this.message;
        this.message = message;

        Notification n = new AttributeChangeNotification(this,
                sequenceNumber++,
                System.currentTimeMillis(),
                "Message changed",
                "Message",
                "String",
                oldValue,
                this.message);

        sendNotification(n);
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
