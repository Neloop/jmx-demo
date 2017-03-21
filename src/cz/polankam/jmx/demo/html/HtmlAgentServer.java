package cz.polankam.jmx.demo.html;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import com.sun.jdmk.comm.HtmlAdaptorServer;
import cz.polankam.jmx.demo.mbeans.DemoManager;

public class HtmlAgentServer {
    private MBeanServer mbs = null;

    public HtmlAgentServer() {

        // Create an MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();

        // Create and setup HTML adaptor
        HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
        adaptor.setPort(8000);

        try {
            // Unique identification of MBeans
            DemoManager managerBean = new DemoManager();

            // Uniquely identify the MBeans and register them with the MBeanServer
            ObjectName managerName = new ObjectName("HtmlAgentServer:name=demomanager");
            mbs.registerMBean(managerBean, managerName);

            // Register and start the HTML adaptor
            ObjectName adaptorName = new ObjectName("HtmlAgentServer:name=htmladaptor,port=8000");
            mbs.registerMBean(adaptor, adaptorName);
            adaptor.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        HtmlAgentServer agent = new HtmlAgentServer();
        System.out.println("HtmlAdaptorServer is running...");
    }
}