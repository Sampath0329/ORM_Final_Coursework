package lk.ijse.dao;

import java.io.IOException;
import java.util.List;

public interface CrudDao<T> extends SuperDao{
    public boolean add(T entity) throws IOException;

    public String generateNewID() throws IOException;

    public List<T> getAll() throws IOException;

    public boolean update(T entity) throws IOException;

    public boolean delete(String id) throws IOException;

    T search(String userName) throws IOException;
}
