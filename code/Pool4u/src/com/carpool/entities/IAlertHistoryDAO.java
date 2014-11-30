package com.carpool.entities;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for AlertHistoryDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAlertHistoryDAO {
	/**
	 * Perform an initial save of a previously unsaved AlertHistory entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAlertHistoryDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AlertHistory entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AlertHistory entity);

	/**
	 * Delete a persistent AlertHistory entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAlertHistoryDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AlertHistory entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AlertHistory entity);

	/**
	 * Persist a previously saved AlertHistory entity and return it or a copy of
	 * it to the sender. A copy of the AlertHistory entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAlertHistoryDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AlertHistory entity to update
	 * @return AlertHistory the persisted AlertHistory entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AlertHistory update(AlertHistory entity);

	public AlertHistory findById(Integer id);

	/**
	 * Find all AlertHistory entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AlertHistory property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AlertHistory> found by query
	 */
	public List<AlertHistory> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<AlertHistory> findBySubject(Object subject,
			int... rowStartIdxAndCount);

	public List<AlertHistory> findByMessage(Object message,
			int... rowStartIdxAndCount);

	public List<AlertHistory> findByStatus(Object status,
			int... rowStartIdxAndCount);

	/**
	 * Find all AlertHistory entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AlertHistory> all AlertHistory entities
	 */
	public List<AlertHistory> findAll(int... rowStartIdxAndCount);
}