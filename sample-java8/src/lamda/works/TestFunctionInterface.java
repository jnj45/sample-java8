package lamda.works;

/**
 *  
 * @author Administrator
 *
 * @param <T>
 */
@FunctionalInterface
public interface TestFunctionInterface<T> {
	public T doSomething(T t1, T t2);
}
