package com.carpool.entities;

import java.util.List;

/**
 * Interface for UserActivityDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserActivityDAO {
	/**
	 * Perform an initial save of a previously unsaved UserActivity entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserActivityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserActivity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserActivity entity);

	/**
	 * Delete a persistent UserActivity entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserActivityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserActivity entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserActivity entity);

	/**
	 * Persist a previously saved UserActivity entity and return it or a copy of
	 * it to the sender. A copy of the UserActivity entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserActivityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserActivity entity to update
	 * @return UserActivity the persisted UserActivity entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserActivity update(UserActivity entity);

	public UserActivity findById(UserActivityId id);

	/**
	 * Find all UserActivity entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserActivity property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserActivity> found by query
	 */
	public List<UserActivity> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * Find all UserActivity entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserActivity> all UserActivity entities
	 */
	public List<UserActivity> findAll(int... rowStartIdxAndCount);
}