package com.company.task7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */

    public static void assign(Object to, Object from) {

        List<Method> fromMethods = Arrays.stream(from.getClass().getMethods()).filter(method -> method.getName().startsWith("get"))
                .filter(method -> Modifier.isPublic(method.getModifiers())).collect(Collectors.toList());

        Arrays.stream(to.getClass().getMethods()).filter(method -> method.getName().startsWith("set"))
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(toMethod -> fromMethods.stream().anyMatch(fromMethod -> fromMethod.getName().substring(3).equals(toMethod.getName().substring(3))
                        && fromMethod.getReturnType().isAssignableFrom(toMethod.getParameterTypes()[0])))
                .forEach(toMethod -> {

                    Optional<Method> fromOptional = fromMethods.stream().filter(fromMethod -> fromMethod.getName().substring(3).equals(toMethod.getName().substring(3))
                            && fromMethod.getReturnType().isAssignableFrom(toMethod.getParameterTypes()[0])).findFirst();

                    if (fromOptional.isPresent()) {
                        try {
                            toMethod.invoke(to, fromOptional.get().invoke(from));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
