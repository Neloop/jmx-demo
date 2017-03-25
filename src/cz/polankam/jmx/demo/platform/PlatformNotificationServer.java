package cz.polankam.jmx.demo.platform;

import cz.polankam.jmx.demo.notifications.DemoManager;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class PlatformNotificationServer {

    public static void main(String argv[]) throws Exception {

        // Create an MBeanServer
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            // Unique identification of MBeans
            DemoManager managerBean = new DemoManager();

            // Uniquely identify the MBeans and register them with the MBeanServer
            ObjectName managerName = new ObjectName("PlatformNotificationServer:type=DemoManager");
            mbs.registerMBean(managerBean, managerName);

            //
            System.out.println("Running...");
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Wait forever
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
