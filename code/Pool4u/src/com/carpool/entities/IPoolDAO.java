package com.carpool.entities;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.carpool.data.PoolDetails;

/**
 * Interface for PoolDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoolDAO {
	/**
	 * Perform an initial save of a previously unsaved Pool entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoolDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pool entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Pool entity);

	/**
	 * Delete a persistent Pool entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoolDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Pool entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Pool entity);

	/**
	 * Persist a previously saved Pool entity and return it or a copy of it to
	 * the sender. A copy of the Pool entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoolDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pool entity to update
	 * @return Pool the persisted Pool entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Pool update(Pool entity);

	public Pool findById(Integer id);

	/**
	 * Find all Pool entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Pool property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Pool> found by query
	 */
	public List<Pool> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);
	public List<PoolDetails> searchPools(List<Integer> routeIds,HashMap<String,String> params, HashMap<String, String> startTimeRange, boolean strictMode);
	public List<Pool> findBySeats(Object seats, int... rowStartIdxAndCount);

	public List<Pool> findByAutoApprove(Object autoApprove,
			int... rowStartIdxAndCount);

	public List<Pool> findByContribution(Object contribution,
			int... rowStartIdxAndCount);

	public List<Pool> findByDescription(Object description,
			int... rowStartIdxAndCount);
	
	public List<Pool> findByPoolTitle(Object poolTitle
			, int...rowStartIdxAndCount
		);
	public List<Pool> findBySmoking(Object smoking
			, int...rowStartIdxAndCount
		);
	public List<Pool> findByWomenOnly(Object womenOnly
			, int...rowStartIdxAndCount
		);
	/**
	 * Find all Pool entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Pool> all Pool entities
	 */
	public List<Pool> findAll(int... rowStartIdxAndCount);
}