package com.carpool.entities;

import java.util.List;

/**
 * Interface for PoolQueriesDAO.
 * @author MyEclipse Persistence Tools
 */

public interface IPoolQueryDAO {
		/**
	 Perform an initial save of a previously unsaved PoolQueries entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   IPoolQueriesDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity PoolQueries entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(PoolQuery entity);
    /**
	 Delete a persistent PoolQueries entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   IPoolQueriesDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity PoolQueries entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(PoolQuery entity);
   /**
	 Persist a previously saved PoolQueries entity and return it or a copy of it to the sender. 
	 A copy of the PoolQueries entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = IPoolQueriesDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity PoolQueries entity to update
	 @return PoolQueries the persisted PoolQueries entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
	public PoolQuery update(PoolQuery entity);
	public PoolQuery findById( Integer id);
	 /**
	 * Find all PoolQueries entities with a specific property value.  
	 
	  @param propertyName the name of the PoolQueries property to query
	  @param value the property value to match
	  	  @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the  the row index in the query result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.  
	  	  @return List<PoolQueries> found by query
	 */
	public List<PoolQuery> findByProperty(String propertyName, Object value
			, int...rowStartIdxAndCount
		);
	public List<PoolQuery> findByQuery(Object query
			, int...rowStartIdxAndCount
		);
	public List<PoolQuery> findByResponse(Object response
			, int...rowStartIdxAndCount
		);
	/**
	 * Find all PoolQueries entities.
	  	  @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the  the row index in the query result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.  
	  	  @return List<PoolQueries> all PoolQueries entities
	 */
	public List<PoolQuery> findAll(
			int...rowStartIdxAndCount
		);	
}