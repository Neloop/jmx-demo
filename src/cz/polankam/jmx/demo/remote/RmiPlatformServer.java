package cz.polankam.jmx.demo.remote;

import cz.polankam.jmx.demo.mbeans.DemoManager;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class RmiPlatformServer {
    public static void main(String[] args) {
        try {
            // Retrieve platform MBean server
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

            // Uniquely identify the MBeans and register them with the MBeanServer
            DemoManager managerBean = new DemoManager();
            ObjectName managerName = new ObjectName("RmiPlatformServer:type=DemoManager,name=Demo");
            mbs.registerMBean(managerBean, managerName);

            // Create connector server for requests
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/remote-platform-server");
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);

            // Start the connector server
            cs.start();
            System.out.println("Running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
