package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for VendorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVendorDAO {
	/**
	 * Perform an initial save of a previously unsaved Vendor entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVendorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Vendor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Vendor entity);

	/**
	 * Delete a persistent Vendor entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVendorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Vendor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Vendor entity);

	/**
	 * Persist a previously saved Vendor entity and return it or a copy of it to
	 * the sender. A copy of the Vendor entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IVendorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Vendor entity to update
	 * @return Vendor the persisted Vendor entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Vendor update(Vendor entity);

	public Vendor findById(Integer id);

	/**
	 * Find all Vendor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Vendor property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Vendor> found by query
	 */
	public List<Vendor> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Vendor> findByVendorName(Object vendorName,
			int... rowStartIdxAndCount);

	public List<Vendor> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<Vendor> findByEmail(Object email, int... rowStartIdxAndCount);

	/**
	 * Find all Vendor entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Vendor> all Vendor entities
	 */
	public List<Vendor> findAll(int... rowStartIdxAndCount);
}