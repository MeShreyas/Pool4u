package com.carpool.entities;

import java.util.List;

/**
 * Interface for UserAllowedCompaniesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserAllowedCompaniesDAO {
	/**
	 * Perform an initial save of a previously unsaved UserAllowedCompanies
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserAllowedCompaniesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserAllowedCompanies entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserAllowedCompanies entity);

	/**
	 * Delete a persistent UserAllowedCompanies entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserAllowedCompaniesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserAllowedCompanies entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserAllowedCompanies entity);

	/**
	 * Persist a previously saved UserAllowedCompanies entity and return it or a
	 * copy of it to the sender. A copy of the UserAllowedCompanies entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserAllowedCompaniesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserAllowedCompanies entity to update
	 * @return UserAllowedCompanies the persisted UserAllowedCompanies entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserAllowedCompanies update(UserAllowedCompanies entity);

	public UserAllowedCompanies findById(UserAllowedCompaniesId id);

	/**
	 * Find all UserAllowedCompanies entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserAllowedCompanies property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserAllowedCompanies> found by query
	 */
	public List<UserAllowedCompanies> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	/**
	 * Find all UserAllowedCompanies entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserAllowedCompanies> all UserAllowedCompanies entities
	 */
	public List<UserAllowedCompanies> findAll(int... rowStartIdxAndCount);
}