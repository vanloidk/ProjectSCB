package scb.com.vn.dbi.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author minhndb
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     *
     * @param id
     * @param lock
     * @return
     */
    T findById(ID id, boolean lock);

    /**
     *
     * @return
     */
    List<T> findAll();

    /**
     *
     * @param entity
     * @return
     */
    ID create(T entity);

    /**
     *
     * @param entity
     */
    public void update(T entity);

    /**
     *
     * @param entity
     */
    public void delete(T entity);
}
