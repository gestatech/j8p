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
                Class<?> clazz = f.getType();
                System.out.println("Setting "+name+" to new "+clazz.getName());
                f.set(obj, clazz.newInstance());
            }
        }
    }
}
