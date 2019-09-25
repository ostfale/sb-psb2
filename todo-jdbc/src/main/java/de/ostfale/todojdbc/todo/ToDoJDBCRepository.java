package de.ostfale.todojdbc.todo;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC based implementation of the repository
 * Created :  25.09.2019
 *
 * @author : Uwe Sauerbrei
 */
@Repository
public class ToDoJDBCRepository implements CommonRepository<ToDoJDBC> {

    private static final String SQL_INSERT = "insert into todo (id, description, created, modified, completed) values (:id, :description, :created, :modified, :completed)";
    private static final String SQL_QUERY_FIND_ALL = "select id, description, created, modified, completed from todo";
    private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " where id = :id";
    private static final String SQL_UPDATE = "update todo set description = :description, modified = :modified, completed = :completed where id = :id";
    private static final String SQL_DELETE = "delete  from todo where id = :id";

    // use :id instead of ?
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ToDoJDBCRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ToDoJDBC save(ToDoJDBC domain) {
        ToDoJDBC result = findById(domain.getId());
        if (result != null) {
            result.setDescription(domain.getDescription());
            result.setCompleted(domain.isCompleted());
            result.setModified(domain.getModified());
            return upsert(result, SQL_UPDATE);
        }
        return upsert(domain, SQL_INSERT);
    }

    @Override
    public Iterable<ToDoJDBC> save(Collection<ToDoJDBC> domains) {
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDoJDBC domain) {
        Map<String, String> namedParameters = Collections.singletonMap("id", domain.getId());
        this.jdbcTemplate.update(SQL_DELETE, namedParameters);
    }

    @Override
    public ToDoJDBC findById(String id) {
        try {
            Map<String, String> namedParameters = Collections.singletonMap("id", id);
            return this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, namedParameters, toDoJDBCRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Iterable<ToDoJDBC> findAll() {
        return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, toDoJDBCRowMapper);
    }

    private ToDoJDBC upsert(final ToDoJDBC toDo, final String sql) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", toDo.getId());
        namedParameters.put("description", toDo.getDescription());
        namedParameters.put("created", java.sql.Timestamp.valueOf(toDo.getCreated()));
        namedParameters.put("modified", java.sql.Timestamp.valueOf(toDo.getModified()));
        namedParameters.put("completed", toDo.isCompleted());
        this.jdbcTemplate.update(sql, namedParameters);
        return findById(toDo.getId());
    }

    private RowMapper<ToDoJDBC> toDoJDBCRowMapper = (ResultSet rs, int rowNum) -> {
        ToDoJDBC toDoJDBC = new ToDoJDBC();
        toDoJDBC.setId(rs.getString("id"));
        toDoJDBC.setDescription(rs.getString("description"));
        toDoJDBC.setModified(rs.getTimestamp("modified").toLocalDateTime());
        toDoJDBC.setCreated(rs.getTimestamp("created").toLocalDateTime());
        toDoJDBC.setCompleted(rs.getBoolean("completed"));
        return toDoJDBC;
    };
}
