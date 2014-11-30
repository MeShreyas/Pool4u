package com.carpool.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.carpool.data.RouteCount;

/**
 * Interface for RoutesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRoutesDAO {
	/**
	 * Perform an initial save of a previously unsaved Routes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRoutesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Routes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Routes entity);
	
	/**
	 * Delete a persistent Routes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRoutesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Routes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Routes entity);

	/**
	 * Persist a previously saved Routes entity and return it or a copy of it to
	 * the sender. A copy of the Routes entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRoutesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Routes entity to update
	 * @return Routes the persisted Routes entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Routes update(Routes entity);
	

	public Routes findById(Integer id);

	/**
	 * Find all Routes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Routes property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Routes> found by query
	 */
	public List<Routes> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);
	
	
	public List<Routes> findByRouteCount(Object routeCount,
			int... rowStartIdxAndCount);

	/**
	 * Find all Routes entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Routes> all Routes entities
	 */
	public List<Routes> findAll(int... rowStartIdxAndCount);
}