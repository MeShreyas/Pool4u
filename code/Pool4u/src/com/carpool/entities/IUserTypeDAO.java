package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for UserTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved UserType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserType entity);

	/**
	 * Delete a persistent UserType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserType entity);

	/**
	 * Persist a previously saved UserType entity and return it or a copy of it
	 * to the sender. A copy of the UserType entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserType entity to update
	 * @return UserType the persisted UserType entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserType update(UserType entity);

	public UserType findById(Integer id);

	/**
	 * Find all UserType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserType property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserType> found by query
	 */
	public List<UserType> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<UserType> findByUserTypeDesc(Object userTypeDesc,
			int... rowStartIdxAndCount);

	/**
	 * Find all UserType entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserType> all UserType entities
	 */
	public List<UserType> findAll(int... rowStartIdxAndCount);
}