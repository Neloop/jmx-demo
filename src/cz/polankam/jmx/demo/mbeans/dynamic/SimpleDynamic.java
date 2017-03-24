package cz.polankam.jmx.demo.mbeans.dynamic;

import java.util.ArrayList;
import java.util.List;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;

public class SimpleDynamic implements DynamicMBean {

    private final List<MBeanAttributeInfo> attributes;
    private final List<MBeanConstructorInfo> constructors;
    private final List<MBeanOperationInfo> operations;
    private final List<MBeanNotificationInfo> notifications;
    private final MBeanInfo mBeanInfo;

    public SimpleDynamic() {
        attributes = new ArrayList<>();
        constructors = new ArrayList<>();
        operations = new ArrayList<>();
        notifications = new ArrayList<>();

        attributes.add(new MBeanAttributeInfo(
                "State",                // name
                "java.lang.String",     // type
                "State: state string.", // description
                true,                   // readable
                true,                   // writable
                true)                   // is
        );

        mBeanInfo = new MBeanInfo("SimpleDynamic", "Simple dynamic MBean",
                attributes.toArray(new MBeanAttributeInfo[0]),
                constructors.toArray(new MBeanConstructorInfo[0]),
                operations.toArray(new MBeanOperationInfo[0]),
                notifications.toArray(new MBeanNotificationInfo[0]));
    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
