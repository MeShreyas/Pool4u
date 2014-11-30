package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for ActivityDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IActivityDAO {
	/**
	 * Perform an initial save of a previously unsaved Activity entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActivityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Activity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Activity entity);

	/**
	 * Delete a persistent Activity entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActivityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Activity entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Activity entity);

	/**
	 * Persist a previously saved Activity entity and return it or a copy of it
	 * to the sender. A copy of the Activity entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IActivityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Activity entity to update
	 * @return Activity the persisted Activity entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Activity update(Activity entity);

	public Activity findById(Integer id);

	/**
	 * Find all Activity entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Activity property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Activity> found by query
	 */
	public List<Activity> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Activity> findByActivityDesc(Object activityDesc,
			int... rowStartIdxAndCount);

	/**
	 * Find all Activity entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Activity> all Activity entities
	 */
	public List<Activity> findAll(int... rowStartIdxAndCount);
}