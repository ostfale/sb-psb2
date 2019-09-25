package de.ostfale.todojdbc.todo;

import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * JDBC based implementation of the repository
 * Created :  25.09.2019
 *
 * @author : Uwe Sauerbrei
 */
@Repository
public class ToDoJDBCRepository implements CommonRepository<ToDoJDBC> {
    @Override
    public ToDoJDBC save(ToDoJDBC domain) {
        return null;
    }

    @Override
    public Iterable<ToDoJDBC> save(Collection<ToDoJDBC> domains) {
        return null;
    }

    @Override
    public void delete(ToDoJDBC domain) {

    }

    @Override
    public ToDoJDBC findById(String id) {
        return null;
    }

    @Override
    public Iterable<ToDoJDBC> findAll() {
        return null;
    }
}
