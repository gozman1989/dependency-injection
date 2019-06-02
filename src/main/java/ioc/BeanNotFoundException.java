package ioc;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(String className) {
       super(className);
    }
}
