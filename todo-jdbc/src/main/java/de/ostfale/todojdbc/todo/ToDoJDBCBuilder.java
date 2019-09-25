package de.ostfale.todojdbc.todo;

/**
 * Fluent API class to create a {@link ToDoJDBC} instance
 * Created :  19.09.2019
 *
 * @author : Uwe Sauerbrei
 */
public class ToDoJDBCBuilder {

    private static ToDoJDBCBuilder instance = new ToDoJDBCBuilder();
    private String id = null;
    private String description = "";

    private ToDoJDBCBuilder() {
    }

    public static ToDoJDBCBuilder create() {
        return instance;
    }

    public ToDoJDBCBuilder withDescription(String description) {
        this.description = description;
        return instance;
    }

    public ToDoJDBCBuilder withId(String id) {
        this.id = id;
        return instance;
    }

    public ToDoJDBC build() {
        ToDoJDBC result = new ToDoJDBC(this.description);
        if (id != null) {
            result.setId(id);
        }
        return result;
    }
}
