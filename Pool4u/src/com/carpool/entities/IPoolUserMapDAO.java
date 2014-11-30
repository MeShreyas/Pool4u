package com.carpool.entities;

import java.util.List;

/**
 * Interface for PoolUserMapDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoolUserMapDAO {
	/**
	 * Perform an initial save of a previously unsaved PoolUserMap entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoolUserMapDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoolUserMap entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoolUserMap entity);

	/**
	 * Delete a persistent PoolUserMap entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoolUserMapDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoolUserMap entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoolUserMap entity);

	/**
	 * Persist a previously saved PoolUserMap entity and return it or a copy of
	 * it to the sender. A copy of the PoolUserMap entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoolUserMapDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoolUserMap entity to update
	 * @return PoolUserMap the persisted PoolUserMap entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoolUserMap update(PoolUserMap entity);

	public PoolUserMap findById(PoolUserMapId id);

	/**
	 * Find all PoolUserMap entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoolUserMap property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PoolUserMap> found by query
	 */
	public List<PoolUserMap> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * Find all PoolUserMap entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PoolUserMap> all PoolUserMap entities
	 */
	public List<PoolUserMap> findAll(int... rowStartIdxAndCount);
}