package com.carpool.entities;

import java.util.List;

/**
 * Interface for ConfingParamDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IConfingParamDAO {
	/**
	 * Perform an initial save of a previously unsaved ConfingParam entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IConfingParamDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ConfingParam entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ConfingParam entity);

	/**
	 * Delete a persistent ConfingParam entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IConfingParamDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ConfingParam entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ConfingParam entity);

	/**
	 * Persist a previously saved ConfingParam entity and return it or a copy of
	 * it to the sender. A copy of the ConfingParam entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IConfingParamDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ConfingParam entity to update
	 * @return ConfingParam the persisted ConfingParam entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ConfingParam update(ConfingParam entity);

	public ConfingParam findById(String id);

	/**
	 * Find all ConfingParam entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ConfingParam property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ConfingParam> found by query
	 */
	public List<ConfingParam> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<ConfingParam> findByParamValue(Object paramValue,
			int... rowStartIdxAndCount);

	/**
	 * Find all ConfingParam entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ConfingParam> all ConfingParam entities
	 */
	public List<ConfingParam> findAll(int... rowStartIdxAndCount);
}