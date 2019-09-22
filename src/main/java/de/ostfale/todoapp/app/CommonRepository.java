package de.ostfale.todoapp.app;

import java.util.Collection;

/**
 * Defines some standard CRUD services
 * Created :  19.09.2019
 *
 * @author : Uwe Sauerbrei
 */
public interface CommonRepository<T> {

    public T save(T domain);

    public Iterable<T> save(Collection<T> domains);

    public void delete(T domain);

    public T findById(String id);

    public Iterable<T> findAll();
}
