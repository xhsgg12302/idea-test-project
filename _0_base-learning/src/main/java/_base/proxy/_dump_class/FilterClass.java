package _base.proxy._dump_class;

import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

public class FilterClass implements ClassFilter {

    @Override
    public boolean canInclude(InstanceKlass instanceKlass) {
        String klassName = instanceKlass.getName().asString();
        return klassName.startsWith("sun/reflect/GeneratedMethodAccessor");
    }

}
