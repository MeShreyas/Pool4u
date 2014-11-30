package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for CityDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICityDAO {
	/**
	 * Perform an initial save of a previously unsaved City entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            City entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(City entity);

	/**
	 * Delete a persistent City entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            City entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(City entity);

	/**
	 * Persist a previously saved City entity and return it or a copy of it to
	 * the sender. A copy of the City entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            City entity to update
	 * @return City the persisted City entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public City update(City entity);

	public City findById(Integer id);

	/**
	 * Find all City entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the City property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<City> found by query
	 */
	public List<City> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<City> findByCityName(Object cityName,
			int... rowStartIdxAndCount);

	/**
	 * Find all City entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<City> all City entities
	 */
	public List<City> findAll(int... rowStartIdxAndCount);
}