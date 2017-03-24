package cz.polankam.jmx.demo.mbeans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

public class Inspector {
    public static void main(String[] argv) throws Exception {
        Class<?> beanClass = DemoManager.class;
        System.out.println("MBean Name: " + beanClass.getName());

        BeanInfo bi = Introspector.getBeanInfo(beanClass);

        for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
            Class<?> pt = pd.getPropertyType();
            if (pt != null) {
                if (pd.getWriteMethod() == null) { System.out.print("readonly "); }
                if (pd.isBound()) { System.out.print("bound "); }
                if (pd.isConstrained()) { System.out.print("constrained "); }
                System.out.print("property ");
                System.out.print(pt.getName() + " ");
                System.out.println(pd.getName());
            }
        }

        for (MethodDescriptor md : bi.getMethodDescriptors()) {
            System.out.println("method " + md.getName());
        }

        System.out.println();
    }
}
