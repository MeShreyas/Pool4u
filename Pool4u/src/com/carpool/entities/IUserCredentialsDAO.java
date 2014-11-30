package com.carpool.entities;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for UserCredentialsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserCredentialsDAO {
	/**
	 * Perform an initial save of a previously unsaved UserCredentials entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserCredentialsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserCredentials entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserCredentials entity);

	/**
	 * Delete a persistent UserCredentials entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserCredentialsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserCredentials entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserCredentials entity);

	/**
	 * Persist a previously saved UserCredentials entity and return it or a copy
	 * of it to the sender. A copy of the UserCredentials entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserCredentialsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserCredentials entity to update
	 * @return UserCredentials the persisted UserCredentials entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserCredentials update(UserCredentials entity);

	public UserCredentials findById(Integer id);

	/**
	 * Find all UserCredentials entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserCredentials property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserCredentials> found by query
	 */
	public List<UserCredentials> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<UserCredentials> findByUserName(Object userName,
			int... rowStartIdxAndCount);

	public List<UserCredentials> findByPassword(Object password,
			int... rowStartIdxAndCount);

	public List<UserCredentials> findByIsAuthorized(Object isAuthorized,
			int... rowStartIdxAndCount);

	public List<UserCredentials> findByLoginAttemptCount(
			Object loginAttemptCount, int... rowStartIdxAndCount);

	public List<UserCredentials> findByIsActive(Object isActive,
			int... rowStartIdxAndCount);

	public List<UserCredentials> findByUserCredits(Object userCredits,
			int... rowStartIdxAndCount);
	/**
	 * Find all UserCredentials entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserCredentials> all UserCredentials entities
	 */
	public List<UserCredentials> findAll(int... rowStartIdxAndCount);
}