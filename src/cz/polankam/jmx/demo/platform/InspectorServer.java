package cz.polankam.jmx.demo.platform;

import cz.polankam.jmx.demo.notifications.DemoManager;
import java.lang.management.ManagementFactory;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class InspectorServer {
    private MBeanServer mbs = null;

    public InspectorServer() {
    }

    public void run() {
        // Create an MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            // Unique identification of MBeans
            DemoManager managerBean = new DemoManager();

            // Uniquely identify the MBeans and register them with the MBeanServer
            ObjectName managerName = new ObjectName("InspectorServer:name=DemoManager");
            mbs.registerMBean(managerBean, managerName);

            // Print MBeanInfo
            printMBeanInfo(mbs, managerName, "DemoManager");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void printMBeanInfo(MBeanServer mbs,
            ObjectName mbeanObjectName, String mbeanClassName) {
        System.out.println("\n>>> Retrieve the management information for the " +
             mbeanClassName);
        System.out.println("    MBean using the getMBeanInfo() method of the MBeanServer");
        MBeanInfo info = null;
        try {
            info = mbs.getMBeanInfo(mbeanObjectName);
        } catch (Exception e) {
            System.out.println("\t!!! Could not get MBeanInfo object for " +
                 mbeanClassName +" !!!");
            e.printStackTrace();
            return;
        }
        System.out.println("\nCLASSNAME: \t" + info.getClassName());
        System.out.println("\nDESCRIPTION: \t" + info.getDescription());
        System.out.println("\nATTRIBUTES");
        MBeanAttributeInfo[] attrInfo = info.getAttributes();
        if (attrInfo.length > 0) {
            for (int i = 0; i < attrInfo.length; i++) {
                System.out.println(" ** NAME: \t" + attrInfo[i].getName());
                System.out.println("    DESCR: \t" + attrInfo[i].getDescription());
                System.out.println("    TYPE: \t" + attrInfo[i].getType() +
                     "\tREAD: "+ attrInfo[i].isReadable() +
                     "\tWRITE: "+ attrInfo[i].isWritable());
            }
        } else System.out.println(" ** No attributes **");
        System.out.println("\nCONSTRUCTORS");
        MBeanConstructorInfo[] constrInfo = info.getConstructors();
        for (int i=0; i<constrInfo.length; i++) {
            System.out.println(" ** NAME: \t" + constrInfo[i].getName());
            System.out.println("    DESCR: \t" + constrInfo[i].getDescription());
            System.out.println("    PARAM: \t" + constrInfo[i].getSignature().length +
                 " parameter(s)");
        }
        System.out.println("\nOPERATIONS");
        MBeanOperationInfo[] opInfo = info.getOperations();
        if (opInfo.length > 0) {
            for (int i = 0; i < opInfo.length; i++) {
                System.out.println(" ** NAME: \t" + opInfo[i].getName());
                System.out.println("    DESCR: \t" + opInfo[i].getDescription());
                System.out.println("    PARAM: \t" + opInfo[i].getSignature().length +
                     " parameter(s)");
            }
        } else System.out.println(" ** No operations ** ");
        System.out.println("\nNOTIFICATIONS");
        MBeanNotificationInfo[] notifInfo = info.getNotifications();
        if (notifInfo.length > 0) {
            for (int i = 0; i < notifInfo.length; i++) {
                System.out.println(" ** NAME: \t" + notifInfo[i].getName());
                System.out.println("    DESCR: \t" + notifInfo[i].getDescription());
                String notifTypes[] = notifInfo[i].getNotifTypes();
                for (int j = 0; j < notifTypes.length; j++) {
                    System.out.println("    TYPE: \t" + notifTypes[j]);
                }
            }
        } else System.out.println(" ** No notifications **");
    }

    public static void main(String argv[]) throws Exception {
        InspectorServer server = new InspectorServer();
        server.run();
    }
}
