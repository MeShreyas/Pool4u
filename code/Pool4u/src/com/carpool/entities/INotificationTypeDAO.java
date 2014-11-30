package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for NotificationTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INotificationTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved NotificationType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INotificationTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NotificationType entity);

	/**
	 * Delete a persistent NotificationType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INotificationTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NotificationType entity);

	/**
	 * Persist a previously saved NotificationType entity and return it or a
	 * copy of it to the sender. A copy of the NotificationType entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = INotificationTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationType entity to update
	 * @return NotificationType the persisted NotificationType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NotificationType update(NotificationType entity);

	public NotificationType findById(Integer id);

	/**
	 * Find all NotificationType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NotificationType property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<NotificationType> found by query
	 */
	public List<NotificationType> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<NotificationType> findByNotificationDesc(
			Object notificationDesc, int... rowStartIdxAndCount);

	public List<NotificationType> findByIsActive(Object isActive,
			int... rowStartIdxAndCount);

	/**
	 * Find all NotificationType entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<NotificationType> all NotificationType entities
	 */
	public List<NotificationType> findAll(int... rowStartIdxAndCount);
}