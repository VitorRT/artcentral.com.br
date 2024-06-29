package br.com.artcentral.mvc.system.generics;

import java.lang.reflect.Field;

public class GenericValidator {
    public static <T> void validaValoresNulos(T object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ValidatesNull.class)) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value == null) {
                    throw new IllegalAccessException(
                            "O campo " + field.getName() + " na classe " + clazz.getSimpleName() + " não pode ser nulo."
                    );
                } else {
                    if(value instanceof String) {
                        if(((String)value).trim().equals("")) {
                            throw new IllegalAccessException(
                                    "O campo " + field.getName() + " na classe " + clazz.getSimpleName() + " não pode ser vazio."
                            );
                        }
                    }
                }
            }
        }
    }
}
