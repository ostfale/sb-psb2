package de.ostfale.todojdbc.todo;

import java.util.Collection;

/**
 * JDBC based implementation of the repository
 * Created :  25.09.2019
 *
 * @author : Uwe Sauerbrei
 */
public class ToDoRepository implements CommonRepository<ToDo> {
    @Override
    public ToDo save(ToDo domain) {
        return null;
    }

    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {
        return null;
    }

    @Override
    public void delete(ToDo domain) {

    }

    @Override
    public ToDo findById(String id) {
        return null;
    }

    @Override
    public Iterable<ToDo> findAll() {
        return null;
    }
}
