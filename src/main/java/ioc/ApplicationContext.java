package ioc;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<String, Object> beans = new HashMap<>();

    private ClassGeneration classGeneration = new ClassGeneration();

    public void registerClasses(Class... classes){
        for (Class clazz : classes){
            Constructor constructor = classGeneration.getConstructorWithAnnotation(clazz, Autowired.class);
        }
    }

    public <T> Object getBean(Class<T> clazz) throws BeanNotFoundException{
        String className = getBeanKey(clazz);
        if (beans.containsKey(className)){
            return beans.get(className);
        }else {
            throw  new BeanNotFoundException(className);
        }
    }

    private <T> String getBeanKey(Class<T> clazz) {
        return clazz.getSimpleName();
    }
}
