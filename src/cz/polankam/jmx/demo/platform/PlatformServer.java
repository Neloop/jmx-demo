package cz.polankam.jmx.demo.platform;

import cz.polankam.jmx.demo.mbeans.DemoManager;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class PlatformServer {
    private MBeanServer mbs = null;

    public PlatformServer() {
    }

    public void init() {
        // Create an MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            // Unique identification of MBeans
            DemoManager managerBean = new DemoManager();

            // Uniquely identify the MBeans and register them with the MBeanServer
            ObjectName managerName = new ObjectName("PlatformServer:name=demomanager");
            mbs.registerMBean(managerBean, managerName);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) throws Exception {
        PlatformServer agent = new PlatformServer();
        agent.init();
        System.out.println("Running...");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
