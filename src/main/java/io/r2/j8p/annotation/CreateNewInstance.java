package io.r2.j8p.annotation;

import java.lang.reflect.Field;

/**
 * CreateNewInstance
 */
public class CreateNewInstance {

    /**
     * Recreate special fields (new instance)
     * @param obj
     */
    public static void recreateSpecial(Object obj) throws Exception {
        for (Field f : obj.getClass().getFields()) {
            if (f.getAnnotation(SpecialField.class) != null) {
                String name = f.getName();
                Class<?> iface = f.getType();
                Class<?> clazz = f.get(obj).getClass();
                System.out.println("."+name+" = ("+iface.getName()+") new "+clazz.getName()+"()");
                f.set(obj, clazz.newInstance());
            }
        }
    }
}
