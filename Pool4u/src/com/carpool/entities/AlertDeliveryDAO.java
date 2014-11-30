package com.carpool.entities;

import com.carpool.dao.helper.EntityManagerHelper;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AlertDelivery entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.carpool.entities.AlertDelivery
 * @author MyEclipse Persistence Tools
 */

public class AlertDeliveryDAO implements IAlertDeliveryDAO {
	// property constants
	public static final String SUBJECT = "subject";
	public static final String MESSAGE = "message";
	public static final String STATUS = "status";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AlertDelivery entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AlertDeliveryDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AlertDelivery entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AlertDelivery entity) {
		EntityManagerHelper.log("saving AlertDelivery instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AlertDelivery entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AlertDeliveryDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AlertDelivery entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AlertDelivery entity) {
		EntityManagerHelper.log("deleting AlertDelivery instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(AlertDelivery.class,
					entity.getAlertId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AlertDelivery entity and return it or a copy
	 * of it to the sender. A copy of the AlertDelivery entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AlertDeliveryDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AlertDelivery entity to update
	 * @return AlertDelivery the persisted AlertDelivery entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AlertDelivery update(AlertDelivery entity) {
		EntityManagerHelper.log("updating AlertDelivery instance", Level.INFO,
				null);
		try {
			AlertDelivery result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AlertDelivery findById(Integer id) {
		EntityManagerHelper.log(
				"finding AlertDelivery instance with id: " + id, Level.INFO,
				null);
		try {
			AlertDelivery instance = getEntityManager().find(
					AlertDelivery.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AlertDelivery entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AlertDelivery property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<AlertDelivery> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AlertDelivery> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding AlertDelivery instance with property: " + propertyName
						+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AlertDelivery model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<AlertDelivery> findBySubject(Object subject,
			int... rowStartIdxAndCount) {
		return findByProperty(SUBJECT, subject, rowStartIdxAndCount);
	}

	public List<AlertDelivery> findByMessage(Object message,
			int... rowStartIdxAndCount) {
		return findByProperty(MESSAGE, message, rowStartIdxAndCount);
	}

	public List<AlertDelivery> findByStatus(Object status,
			int... rowStartIdxAndCount) {
		return findByProperty(STATUS, status, rowStartIdxAndCount);
	}

	/**
	 * Find all AlertDelivery entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AlertDelivery> all AlertDelivery entities
	 */
	@SuppressWarnings("unchecked")
	public List<AlertDelivery> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all AlertDelivery instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from AlertDelivery model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}