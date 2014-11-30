package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for DeviceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDeviceDAO {
	/**
	 * Perform an initial save of a previously unsaved Device entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDeviceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Device entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Device entity);

	/**
	 * Delete a persistent Device entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDeviceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Device entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Device entity);

	/**
	 * Persist a previously saved Device entity and return it or a copy of it to
	 * the sender. A copy of the Device entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDeviceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Device entity to update
	 * @return Device the persisted Device entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Device update(Device entity);

	public Device findById(Integer id);

	/**
	 * Find all Device entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Device property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Device> found by query
	 */
	public List<Device> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Device> findByDeviceDesc(Object deviceDesc,
			int... rowStartIdxAndCount);

	/**
	 * Find all Device entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Device> all Device entities
	 */
	public List<Device> findAll(int... rowStartIdxAndCount);
}