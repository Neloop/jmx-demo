package cz.polankam.jmx.demo.platform;

import cz.polankam.jmx.demo.mbeans.DemoManager;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class PlatformServer {

    public static void main(String argv[]) throws Exception {
        
        // Create an MBeanServer
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            // Unique identification of MBeans
            DemoManager managerBean = new DemoManager();

            // Uniquely identify the MBeans and register them with the MBeanServer
            ObjectName managerName = new ObjectName("PlatformServer:type=DemoManager,name=Demo");
            mbs.registerMBean(managerBean, managerName);
        } catch(Exception e) {
            e.printStackTrace();
        }

        //
        System.out.println("Running...");

        // Wait forever
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
