package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for NotificationEventsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INotificationEventsDAO {
	/**
	 * Perform an initial save of a previously unsaved NotificationEvents
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INotificationEventsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationEvents entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NotificationEvents entity);

	/**
	 * Delete a persistent NotificationEvents entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INotificationEventsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationEvents entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NotificationEvents entity);

	/**
	 * Persist a previously saved NotificationEvents entity and return it or a
	 * copy of it to the sender. A copy of the NotificationEvents entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = INotificationEventsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationEvents entity to update
	 * @return NotificationEvents the persisted NotificationEvents entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NotificationEvents update(NotificationEvents entity);

	public NotificationEvents findById(Integer id);

	/**
	 * Find all NotificationEvents entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NotificationEvents property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<NotificationEvents> found by query
	 */
	public List<NotificationEvents> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<NotificationEvents> findByEventDesc(Object eventDesc,
			int... rowStartIdxAndCount);

	public List<NotificationEvents> findByIsActive(Object isActive,
			int... rowStartIdxAndCount);

	/**
	 * Find all NotificationEvents entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<NotificationEvents> all NotificationEvents entities
	 */
	public List<NotificationEvents> findAll(int... rowStartIdxAndCount);
}