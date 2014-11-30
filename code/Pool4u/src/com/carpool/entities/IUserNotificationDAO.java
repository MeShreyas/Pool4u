package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for UserNotificationDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserNotificationDAO {
	/**
	 * Perform an initial save of a previously unsaved UserNotification entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserNotificationDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserNotification entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserNotification entity);

	/**
	 * Delete a persistent UserNotification entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserNotificationDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserNotification entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserNotification entity);

	/**
	 * Persist a previously saved UserNotification entity and return it or a
	 * copy of it to the sender. A copy of the UserNotification entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserNotificationDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserNotification entity to update
	 * @return UserNotification the persisted UserNotification entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserNotification update(UserNotification entity);

	public UserNotification findById(Integer id);

	/**
	 * Find all UserNotification entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserNotification property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserNotification> found by query
	 */
	public List<UserNotification> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	/**
	 * Find all UserNotification entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserNotification> all UserNotification entities
	 */
	public List<UserNotification> findAll(int... rowStartIdxAndCount);
}