package ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class ClassGeneration {

    public Object createObj(Class tClass, ApplicationContext applicationContext, Class... constructorParams) throws Exception {

        if (constructorParams.length ==0 ){
            return createObjWitNoArgsConstr(tClass);
        }else {
            return  createObjWithArgsConstr(tClass, applicationContext, constructorParams);
        }

    }

    private Object createObjWitNoArgsConstr(Class tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }

    private Object createObjWithArgsConstr(Class tClass, ApplicationContext applicationContext, Class[] constructorParams) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = tClass.getConstructor(constructorParams);
        Object[] params = Arrays.stream(constructorParams).map(applicationContext::getBean).toArray();

        return constructor.newInstance(params);
    }

    public Constructor getConstructorWithAnnotation(Class clazz, Class<Autowired> autowiredClass) {
        List<Constructor>
    }
}
