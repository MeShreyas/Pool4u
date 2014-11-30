package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for UserDataDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserDataDAO {
	/**
	 * Perform an initial save of a previously unsaved UserData entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserData entity);

	/**
	 * Delete a persistent UserData entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserData entity);

	/**
	 * Persist a previously saved UserData entity and return it or a copy of it
	 * to the sender. A copy of the UserData entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserData entity to update
	 * @return UserData the persisted UserData entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserData update(UserData entity);

	public UserData findById(Integer id);

	/**
	 * Find all UserData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserData> found by query
	 */
	public List<UserData> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<UserData> findByFirstName(Object firstName,
			int... rowStartIdxAndCount);

	public List<UserData> findByLastName(Object lastName,
			int... rowStartIdxAndCount);

	public List<UserData> findByDisplayName(Object displayName,
			int... rowStartIdxAndCount);

	public List<UserData> findByGender(Object gender,
			int... rowStartIdxAndCount);

	public List<UserData> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<UserData> findByEmail(Object email, int... rowStartIdxAndCount);

	/**
	 * Find all UserData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserData> all UserData entities
	 */
	public List<UserData> findAll(int... rowStartIdxAndCount);
}