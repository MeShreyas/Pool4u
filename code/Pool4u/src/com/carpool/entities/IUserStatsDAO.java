package com.carpool.entities;

import java.util.List;

/**
 * Interface for UserStatsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserStatsDAO {
	/**
	 * Perform an initial save of a previously unsaved UserStats entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserStatsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserStats entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserStats entity);

	/**
	 * Delete a persistent UserStats entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserStatsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserStats entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserStats entity);

	/**
	 * Persist a previously saved UserStats entity and return it or a copy of it
	 * to the sender. A copy of the UserStats entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserStatsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserStats entity to update
	 * @return UserStats the persisted UserStats entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserStats update(UserStats entity);

	public UserStats findById(Integer id);

	/**
	 * Find all UserStats entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserStats property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserStats> found by query
	 */
	public List<UserStats> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<UserStats> findByReliableCount(Object reliableCount,
			int... rowStartIdxAndCount);

	/**
	 * Find all UserStats entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserStats> all UserStats entities
	 */
	public List<UserStats> findAll(int... rowStartIdxAndCount);
}