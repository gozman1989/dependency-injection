package ioc;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ApplicationContext {

    private Map<String, Object> beans = new HashMap<>();

    private ClassGeneration classGeneration = new ClassGeneration();

    public void registerClasses(Class... classes) throws Exception{
        for (Class clazz : classes){
            Optional<Constructor> constructorOpt = classGeneration.getConstructorWithAnnotation(clazz, Autowired.class);
            if (constructorOpt.isPresent()) {
            	Constructor constructor = constructorOpt.get();
            	registerBean(clazz, constructor);
            }else {
            	Constructor constructor = classGeneration.getNoArgsCons(clazz);
            	registerBean(clazz, constructor);
            }
        }
    }

	private void registerBean(Class clazz, Constructor constructor) throws Exception {
		Class[] constructorParams = constructor.getParameterTypes();
		Object beanObject = classGeneration.createObj(clazz, this, constructorParams);
		String beanKeyString = getBeanKey(clazz);
		beans.put(beanKeyString, beanObject);
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
