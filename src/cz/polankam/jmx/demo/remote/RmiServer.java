package cz.polankam.jmx.demo.remote;

import cz.polankam.jmx.demo.mbeans.DemoManager;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class RmiServer {
    public static void main(String[] args) {
        try {
            // Create brand new MBean server
            MBeanServer mbs = MBeanServerFactory.createMBeanServer();

            // Uniquely identify the MBeans and register them with the MBeanServer
            DemoManager managerBean = new DemoManager();
            ObjectName managerName = new ObjectName("RemoteServer:type=DemoManager,name=Demo");
            mbs.registerMBean(managerBean, managerName);

            // Create connector server for requests
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server");
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);

            // Start the connector server
            cs.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
