package dbEntities;

import java.util.List;

public interface InterfaceDAO<T> {
    public void add(T item);

    public void delete(T item);

    public List<T> seekAll();

    public void update(T item);
}
