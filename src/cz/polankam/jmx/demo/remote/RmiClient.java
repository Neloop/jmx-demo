package cz.polankam.jmx.demo.remote;

import cz.polankam.jmx.demo.mbeans.DemoManagerMBean;
import java.util.Set;
import javax.management.Attribute;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class RmiClient {
    public static void main(String[] args) {
        try {
            // Create and RMI connector client and connect to remote server
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

            // Retrieve remote MBeanServer
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

            // Obtain DemoManagement MBean proxy object
            ObjectName beanName = new ObjectName("RemoteServer:type=DemoManager,name=Demo");
            DemoManagerMBean proxy = JMX.newMBeanProxy(mbsc, beanName, DemoManagerMBean.class, true);

            // Get message from remote proxy
            System.out.println("Old message: \"" + proxy.getMessage() + "\"");

            // Set message and print it again
            proxy.setMessage("Set using proxy client object!");
            System.out.println("New message: \"" + proxy.getMessage() + "\"");

            // Wait
            System.out.println("Press <Enter> to continue...");
            System.in.read();

            /*
             *
             */

            // Go through all MBeans on remote server
            System.out.println("Obtain all MBeans on server");
            Set names = mbsc.queryNames(null, null);
            for (Object oName : names) {
                System.out.println("\t" + (ObjectName) oName);
            }
            System.out.println();

            // Get value of attribute Message
            System.out.println("Old message: \"" + mbsc.getAttribute(beanName, "Message") + "\"");

            // Set value of attribute Message and print it
            mbsc.setAttribute(beanName, new Attribute("Message", "Set using only ObjectName!"));
            System.out.println("New message: \"" + mbsc.getAttribute(beanName, "Message") + "\"");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
