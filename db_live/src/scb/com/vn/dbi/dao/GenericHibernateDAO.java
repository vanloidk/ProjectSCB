package scb.com.vn.dbi.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 *
 * @author minhndb
 * @param <T>
 * @param <ID>
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;
    private Session session;

    /**
     * Create a new instance of GenericHibernateDAO
     */
    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *
     * @param s
     */
    @SuppressWarnings("unchecked")
    public void setSession(Session s) {
        this.session = s;
    }

    /**
     *
     * @return
     */
    protected Session getSession() {
        if (session == null) {
            throw new IllegalStateException("Session has not been set on DAO before usage");
        }
        return session;
    }

    /**
     * begin a transaction
     */
    public void beginTransaction() {
        this.session.beginTransaction();
    }

    /**
     * commit a transaction
     */
    public void commitTransaction() {
        this.session.getTransaction().commit();
    }

    /**
     *
     * @return
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     *
     * @param id
     * @param lock
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public T findById(ID id, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistentClass(), id);
        }
        return entity;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    /**
     *
     * @param exampleInstance
     * @param excludeProperty
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public ID create(T entity) {
        return (ID) getSession().save(entity);
    }

    /**
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    /**
     *
     * @param entity
     */
    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    /**
     * flush a session
     */
    public void flush() {
        getSession().flush();
    }

    /**
     * clear a session
     */
    public void clear() {
        getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
}
