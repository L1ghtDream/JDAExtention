package dev.lightdream.jdaextension;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Utils {
    public static double getRam() {
        return ((double) (Runtime.getRuntime()
                .totalMemory() / 1024) / 1024) - ((double) (Runtime.getRuntime()
                .freeMemory() / 1024) / 1024);
    }

    public static double getCpuLoad() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
            AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});

            if (list.isEmpty()) return Double.NaN;

            Attribute att = (Attribute) list.get(0);
            Double value = (Double) att.getValue();

            if (value == -1.0) return Double.NaN;
            return ((int) (value * 1000) / 10.0);
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    public static String getJava() {
        return String.format("Java Version: %s %s. \n", System.getProperty("java.vendor"), System.getProperty("java.version"));
    }

}
