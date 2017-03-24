package cz.polankam.jmx.demo.platform;

import cz.polankam.jmx.demo.notifications.DemoManager;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class PlatformNotificationServer {
    private MBeanServer mbs = null;

    public PlatformNotificationServer() {
    }

    public void init() {
        // Create an MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            // Unique identification of MBeans
            DemoManager managerBean = new DemoManager();

            // Uniquely identify the MBeans and register them with the MBeanServer
            ObjectName managerName = new ObjectName("PlatformNotificationServer:type=DemoManager");
            mbs.registerMBean(managerBean, managerName);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) throws Exception {
        PlatformNotificationServer agent = new PlatformNotificationServer();
        agent.init();
        System.out.println("Running...");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
