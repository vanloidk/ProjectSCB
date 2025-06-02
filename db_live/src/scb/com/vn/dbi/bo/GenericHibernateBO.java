package scb.com.vn.dbi.bo;

import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.ultility.Common;

/**
 *
 * @author minhndb
 * @param <T>
 * @param <ID>
 */
public class GenericHibernateBO<T, ID extends Serializable> {

    private static final Logger LOGGER = Logger.getLogger(GenericHibernateBO.class);

    /**
     *
     */
    public static final PropertySelector NOT_NULL_OR_EMPTY = new NotNullOrEmptyPropertySelector();

    private static final class NotNullOrEmptyPropertySelector implements PropertySelector {

        private static final long serialVersionUID = 1L;

        @Override
        public boolean include(Object object, String propertyName, Type type) {
            if (object == null) {
                return false;
            }
            try {
                Object property = object;
                if ((property instanceof String) && Common.isEmpty((String) property)) {
                    return false;
                }
                if ((property instanceof Character) && (Character.toString((Character) property).trim().equals(""))) {
                    return false;
                }
                if ((property instanceof Number) && ((Number) property).longValue() == 0) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                LOGGER.error(e);
                return false;
            }
        }

        private Object readResolve() {
            return NOT_NULL_OR_EMPTY;
        }
    }
    private Class<T> persistentClass;
    private Session session;
    private SessionFactory sessionfactory;

    /**
     * Create a new instance of GenericHibernateBO
     */
    public GenericHibernateBO() {
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
     *
     * @return
     */
    public SessionFactory getSessionfactory() {
        return sessionfactory;
    }

    /**
     *
     * @param sessionfactory
     */
    public void setSessionfactory(SessionFactory sessionfactory) {
        this.sessionfactory = sessionfactory;
    }

    /**
     * Create a new transaction
     */
    public void beginTransaction() {
        try {
            setSession(sessionfactory.openSession());
            getSession().beginTransaction();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Commit a transaction
     */
    public void commitTransaction() {
        try {
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("commitTransaction - error: " + ex);
        } finally {
            if (getSession().isConnected()) {
                getSession().clear();
                getSession().close();
            }
            if (getSession().isOpen()) {
                getSession().clear();
                getSession().close();
            }
        }

    }

    /**
     *
     * @return
     */
    public int commitTransaction2() {
        int result = 0;
        try {
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error(ex);
            result = -1;

        } finally {
            if (getSession().isConnected()) {
                getSession().clear();
                getSession().close();
            }
            if (getSession().isOpen()) {
                getSession().clear();
                getSession().close();
            }
        }
        return result;
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
     * @param persistentClass
     */
    public void setPersistentClass(Class persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     *
     * @param entity
     * @return
     */
    public ID create(T entity) {
        try {
            beginTransaction();
            ID id = (ID) getSession().save(entity);
            commitTransaction();
            return id;
        } catch (Exception Ex) {
            LOGGER.error(Ex);
            try {
                if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                    getSession().close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            return null;
        } finally {
            if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                getSession().close();
            }
        }
    }

    /**
     *
     * @param entity
     */
    public void update(T entity) {
        try {
            beginTransaction();
            getSession().update(entity);
            commitTransaction();
        } catch (Exception Ex) {
            LOGGER.error(Ex);
            try {
                if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                    getSession().close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
        } finally {
            if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                getSession().close();
            }
        }
    }

    /**
     *
     * @param entity
     */
    public void saveOrUpdate(T entity) {
        try {
            beginTransaction();
            getSession().saveOrUpdate(entity);
            commitTransaction();
        } catch (Exception Ex) {
            LOGGER.error(Ex);
            try {
                if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                    getSession().close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
        } finally {
            if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                getSession().close();
            }
        }
    }

    /**
     *
     * @param entity
     */
    public void delete(T entity) {
        try {
            beginTransaction();
            getSession().delete(entity);
            commitTransaction();
        } catch (Exception Ex) {
            LOGGER.error(Ex);
            try {
                if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                    getSession().close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
        } finally {
            if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                getSession().close();
            }
        }
    }

    /**
     *
     * @param criterion
     * @return
     */
    protected List<T> findByCriteria(Criterion... criterion) {
        beginTransaction();
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        List l = crit.list();
        commitTransaction();
        return l;
    }

    /**
     *
     * @param id
     * @return
     */
    public T findById(ID id) {
        return findById(id, false);
    }

    /**
     *
     * @param id
     * @param lock
     * @return
     */
    public T findById(ID id, boolean lock) {
        beginTransaction();
        T entity;
        if (lock) {
            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistentClass(), id);
        }
        T rentity = (T) HibernateUtil.getMapper().map(entity, entity.getClass());
        commitTransaction();
        return rentity;
    }

    /**
     *
     * @param clz
     * @param id
     * @return
     */
    public T findById(Class clz, ID id) {
        beginTransaction();
        T e = (T) getSession().get(clz, id);
        if (e == null) {
            return null;
        }
        T rentity = (T) HibernateUtil.getMapper().map(e, e.getClass());
        commitTransaction();
        return rentity;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List l = findByCriteria();
        if (l == null) {
            return null;
        }
        List<T> r = (List<T>) HibernateUtil.getMapper().map(l, l.getClass());
        return r;
    }

    /**
     *
     * @param exampleInstance
     * @param excludeProperty
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        beginTransaction();
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        List l = crit.list();
        commitTransaction();
        return l;
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
     * close a session
     */
    public void close() {
        getSession().close();
    }

    /**
     *
     * @param entity
     * @return
     */
    public int updateHasReturn(T entity) {
        int result = 0;
        try {
            beginTransaction();
            getSession().update(entity);
            commitTransaction();
        } catch (Exception Ex) {
            LOGGER.error(Ex);
            try {
                if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                    getSession().close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            result = -1;
        } finally {
            if (getSession() != null && (getSession().isConnected() || getSession().isOpen())) {
                getSession().close();
            }
        }
        return result;
    }
}
