package cz.polankam.jmx.demo.remote;

import java.util.Set;
import javax.management.Attribute;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import cz.polankam.jmx.demo.mbeans.DemoManagerMBean;

public class JmxmpClient {
    public static void main(String[] args) {
        try {
            // Create and RMI connector client and connect to remote server
            JMXServiceURL url = new JMXServiceURL("service:jmx:jmxmp://localhost:10009");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

            // Retrieve remote MBeanServer
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

            // Obtain DemoManagement MBean proxy object
            ObjectName beanName = new ObjectName("JmxmpPlatformServer:type=DemoManager,name=Demo");
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

            // Close connection at the end
            jmxc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
