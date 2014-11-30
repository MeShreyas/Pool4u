package com.carpool.entities;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for AlertDeliveryDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAlertDeliveryDAO {
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
	 * IAlertDeliveryDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AlertDelivery entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AlertDelivery entity);

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
	 * IAlertDeliveryDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AlertDelivery entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AlertDelivery entity);

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
	 * entity = IAlertDeliveryDAO.update(entity);
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
	public AlertDelivery update(AlertDelivery entity);

	public AlertDelivery findById(Integer id);

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
	 *            count of results to return.
	 * @return List<AlertDelivery> found by query
	 */
	public List<AlertDelivery> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<AlertDelivery> findBySubject(Object subject,
			int... rowStartIdxAndCount);

	public List<AlertDelivery> findByMessage(Object message,
			int... rowStartIdxAndCount);

	public List<AlertDelivery> findByStatus(Object status,
			int... rowStartIdxAndCount);

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
	public List<AlertDelivery> findAll(int... rowStartIdxAndCount);
}