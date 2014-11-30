package com.carpool.entities;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for UserLoginDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserLoginDAO {
	/**
	 * Perform an initial save of a previously unsaved UserLogin entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserLoginDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserLogin entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserLogin entity);

	/**
	 * Delete a persistent UserLogin entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserLoginDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserLogin entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserLogin entity);

	/**
	 * Persist a previously saved UserLogin entity and return it or a copy of it
	 * to the sender. A copy of the UserLogin entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserLoginDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserLogin entity to update
	 * @return UserLogin the persisted UserLogin entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserLogin update(UserLogin entity);

	public UserLogin findById(Integer id);

	/**
	 * Find all UserLogin entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserLogin property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserLogin> found by query
	 */
	public List<UserLogin> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<UserLogin> findByLoginStatus(Object loginStatus,
			int... rowStartIdxAndCount);

	public List<UserLogin> findByStatusComments(Object statusComments,
			int... rowStartIdxAndCount);

	/**
	 * Find all UserLogin entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserLogin> all UserLogin entities
	 */
	public List<UserLogin> findAll(int... rowStartIdxAndCount);
}