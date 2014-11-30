package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for AreaDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAreaDAO {
	/**
	 * Perform an initial save of a previously unsaved Area entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAreaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Area entity);

	/**
	 * Delete a persistent Area entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAreaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Area entity);

	/**
	 * Persist a previously saved Area entity and return it or a copy of it to
	 * the sender. A copy of the Area entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAreaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to update
	 * @return Area the persisted Area entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Area update(Area entity);

	public Area findById(Integer id);

	/**
	 * Find all Area entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Area property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Area> found by query
	 */
	public List<Area> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Area> findByAreaName(Object areaName,
			int... rowStartIdxAndCount);

	/**
	 * Find all Area entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Area> all Area entities
	 */
	public List<Area> findAll(int... rowStartIdxAndCount);
}