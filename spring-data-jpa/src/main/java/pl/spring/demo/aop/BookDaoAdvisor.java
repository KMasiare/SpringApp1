package pl.spring.demo.aop;


import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NewIdAnnotation;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.IdAware;
import pl.spring.demo.common.Sequence;

import java.lang.reflect.Method;

@Component
public class BookDaoAdvisor implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects /*argumenty metody*/, Object o /* to jest obiekt, na ktorym wszystko jest wywolywane*/) 
    		throws Throwable {

        if (hasAnnotation(method, o, NullableId.class)) {
            checkNotNullId(objects[0]);
        }
        
        if (hasAnnotation(method, o, NewIdAnnotation.class)) {
        	setNewId(objects[0], o);
        }
    }

    private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }
    
    private void setNewId(Object book, Object clazz) {
    	if(clazz instanceof IdAware && ((IdAware) clazz).getId() == null) {
    		Sequence seq = new Sequence();
    		long id = seq.nextValue(((BookDaoImpl) clazz).findAll());
    		((BookTo) book).setId(id);
    	}
    }

    private boolean hasAnnotation (Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

        if (!hasAnnotation && o != null) {
            hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
        }
        return hasAnnotation;
    }
}
