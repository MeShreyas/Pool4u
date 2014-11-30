package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for IndustrySectorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IIndustrySectorDAO {
	/**
	 * Perform an initial save of a previously unsaved IndustrySector entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IIndustrySectorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            IndustrySector entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(IndustrySector entity);

	/**
	 * Delete a persistent IndustrySector entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IIndustrySectorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            IndustrySector entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(IndustrySector entity);

	/**
	 * Persist a previously saved IndustrySector entity and return it or a copy
	 * of it to the sender. A copy of the IndustrySector entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IIndustrySectorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            IndustrySector entity to update
	 * @return IndustrySector the persisted IndustrySector entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public IndustrySector update(IndustrySector entity);

	public IndustrySector findById(Integer id);

	/**
	 * Find all IndustrySector entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the IndustrySector property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<IndustrySector> found by query
	 */
	public List<IndustrySector> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<IndustrySector> findBySectorName(Object sectorName,
			int... rowStartIdxAndCount);

	/**
	 * Find all IndustrySector entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<IndustrySector> all IndustrySector entities
	 */
	public List<IndustrySector> findAll(int... rowStartIdxAndCount);
}