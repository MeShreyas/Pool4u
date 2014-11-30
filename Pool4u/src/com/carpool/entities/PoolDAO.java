package com.carpool.entities;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.data.PoolDetails;
import com.carpool.data.PoolDetailsNew;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * A data access object (DAO) providing persistence and search support for Pool
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.carpool.entities.Pool
 * @author MyEclipse Persistence Tools
 */

public class PoolDAO implements IPoolDAO {
	// property constants
	public static final String SEATS = "seats";
	public static final String AUTO_APPROVE = "autoApprove";
	public static final String CONTRIBUTION = "contribution";
	public static final String DESCRIPTION = "description";
	public static final String POOL_TITLE = "poolTitle";
	public static final String SMOKING = "smoking";
	public static final String WOMEN_ONLY = "womenOnly";
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * PoolDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pool entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Pool entity) {
		EntityManagerHelper.log("saving Pool instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Pool entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoolDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Pool entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Pool entity) {
		EntityManagerHelper.log("deleting Pool instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Pool.class,
					entity.getPoolId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = PoolDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pool entity to update
	 * @return Pool the persisted Pool entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Pool update(Pool entity) {
		EntityManagerHelper.log("updating Pool instance", Level.INFO, null);
		try {
			Pool result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Pool findById(Integer id) {
		EntityManagerHelper.log("finding Pool instance with id: " + id,
				Level.INFO, null);
		try {
			Pool instance = getEntityManager().find(Pool.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<PoolDetails> searchPools(List<Integer> routeIds,HashMap<String,String> params, HashMap<String, String> startTimeRange, boolean strictMode){
		System.out.println("time map : "+startTimeRange);
		Set<String> paramSet = params.keySet();
		boolean whereAttached = false;
		int whereLocation = 0;
		StringBuffer sqlBuffer = new StringBuffer("SELECT pool.pool_id," +
				"       route_id," +
				"       start_time," +
				"       return_time," +
				"       seats," +
				"       contribution," +
				"       auto_approve," +
				"       pool_title," +
				"       smoking," +
				"       women_only," +
				"   users.first_name," +
				"  users.last_name," +
				"  companies.company_name" +
				"  FROM pool" +
				"  join (" +
				"	select" +
				"		user_id," +
				"								first_name," +
				"					      last_name," +
				"					      company_id" +
				"							from" +
				"							user_data" +
				"					)users on users.user_id=pool.user_id" +
				"	join (" +
				"			select" +
				"				company_id," +
				"				company_name" +
				"			from" +
				"			company" +
				"		)companies on companies.company_id=users.company_id ");
		if(routeIds!=null && routeIds.size()>0){
			if(!whereAttached){
				sqlBuffer = sqlBuffer.append(" WHERE ");
				whereAttached = true;
				whereLocation= 1;
			}
			
		sqlBuffer = sqlBuffer.append("route_id IN " +
				"(");
				for(int index=0;index<routeIds.size();index++){
					sqlBuffer = sqlBuffer.append(routeIds.get(index));
					if(index!=(routeIds.size()-1)){
						sqlBuffer = sqlBuffer.append(",");
					}
				}
				sqlBuffer = sqlBuffer.append(")" ); 
		}
		else if(strictMode){
			return new ArrayList<PoolDetails>();
		}
				Collection<String> paramValues = params.values();
				List<String> paramValueList = new ArrayList<String>(paramValues);
				int index=0;
				boolean enter = false;
				for(index=0; index<paramValueList.size();index++){
					if(paramValueList.get(index)!=null && paramValueList.get(index).trim().length()>0){
						enter = true;
						break;
					}
				}
				if(paramSet!=null && paramSet.size()>0 && enter){
					String key="";
					Iterator<String> iterator = paramSet.iterator();
					if(!whereAttached){
						sqlBuffer = sqlBuffer.append(" WHERE ");
						whereAttached = true;
						whereLocation = 2;
					}
					int i = 0;
					while(iterator.hasNext()){
						i++;
						key= iterator.next();
						if(params.get(key)!=null && params.get(key).trim().length()>0){
							if(whereLocation!=2){
								sqlBuffer = sqlBuffer.append("  AND "+ key+" = '"+params.get(key)+"' ");
							}
							else{
								sqlBuffer = sqlBuffer.append( key+" = '"+params.get(key)+"' ");
								if(i!=params.size()){
									sqlBuffer = sqlBuffer.append("  AND ");
								}
							}
						}
					}
				}	
				if(startTimeRange!=null && startTimeRange.size()==2){
					if(!whereAttached){
						sqlBuffer = sqlBuffer.append(" WHERE ");
						whereAttached = true;
						whereLocation = 3;
					}
					if(whereLocation!=3){
						sqlBuffer = sqlBuffer.append("  AND ");
					}
					
						sqlBuffer = sqlBuffer.append("      start_time BETWEEN '"+startTimeRange.get("startTime1")+"'" +//2011-02-10 01:00:00
							 	 "   AND '"+startTimeRange.get("startTime2")+"'");
					
					
				}
				System.out.println(sqlBuffer.toString());
		Query q = getEntityManager().createNativeQuery(sqlBuffer.toString(), "getPools");
		List<PoolDetails> poolDetailsList = q.getResultList();

		
		return poolDetailsList;
	}
	
	public List<PoolDetailsNew> searchNewPools(int userId, List<Integer> routeIds,HashMap<String,String> params, HashMap<String, String> startTimeRange, boolean strictMode){
		System.out.println("time map : "+startTimeRange);
		Set<String> paramSet = params.keySet();
		boolean whereAttached = false;
		int whereLocation = 0;
		StringBuffer sqlBuffer = new StringBuffer("SELECT pool.pool_id," +
				"       route_id," +
				"       start_time," +
				"       return_time," +
				"       seats," +
				"       contribution," +
				"       auto_approve," +
				"       pool_title," +
				"       smoking," +
				"       women_only," +
				"  user_data.first_name," +
				"  user_data.last_name," +
				"  user_data.company_id," +
				"  company.company_name," +
				"  uacf_post.company_id as posting_user_companies,"+
			    "  uacf_search.company_id as searching_user_companies"+
				"  FROM pool" +
				"  LEFT OUTER JOIN user_data " +
				" ON user_data.user_id = pool.user_id " +
				" LEFT OUTER JOIN company " +
				" ON user_data.company_id = company.company_id "+
				"   LEFT OUTER JOIN " +
				" user_allowed_companies_formatted uacf_post ON uacf_post.user_id = pool.user_id" +
				"   LEFT OUTER JOIN " +
				" user_allowed_companies_formatted uacf_search ON uacf_search.user_id = "+userId);
		if(routeIds!=null && routeIds.size()>0){
			if(!whereAttached){
				sqlBuffer = sqlBuffer.append(" WHERE ");
				whereAttached = true;
				whereLocation= 1;
			}
			
		sqlBuffer = sqlBuffer.append("route_id IN " +
				"(");
				for(int index=0;index<routeIds.size();index++){
					sqlBuffer = sqlBuffer.append(routeIds.get(index));
					if(index!=(routeIds.size()-1)){
						sqlBuffer = sqlBuffer.append(",");
					}
				}
				sqlBuffer = sqlBuffer.append(")" ); 
		}
		else if(strictMode){
			return new ArrayList<PoolDetailsNew>();
		}
				Collection<String> paramValues = params.values();
				List<String> paramValueList = new ArrayList<String>(paramValues);
				int index=0;
				boolean enter = false;
				for(index=0; index<paramValueList.size();index++){
					if(paramValueList.get(index)!=null && paramValueList.get(index).trim().length()>0){
						enter = true;
						break;
					}
				}
				if(paramSet!=null && paramSet.size()>0 && enter){
					String key="";
					Iterator<String> iterator = paramSet.iterator();
					if(!whereAttached){
						sqlBuffer = sqlBuffer.append(" WHERE ");
						whereAttached = true;
						whereLocation = 2;
					}
					int i = 0;
					while(iterator.hasNext()){
						i++;
						key= iterator.next();
						if(params.get(key)!=null && params.get(key).trim().length()>0){
							if(whereLocation!=2){
								sqlBuffer = sqlBuffer.append("  AND "+ key+" = '"+params.get(key)+"' ");
							}
							else{
								sqlBuffer = sqlBuffer.append( key+" = '"+params.get(key)+"' ");
								if(i!=params.size()){
									sqlBuffer = sqlBuffer.append("  AND ");
								}
							}
						}
					}
				}	
				if(startTimeRange!=null && startTimeRange.size()==2){
					if(!whereAttached){
						sqlBuffer = sqlBuffer.append(" WHERE ");
						whereAttached = true;
						whereLocation = 3;
					}
					if(whereLocation!=3){
						sqlBuffer = sqlBuffer.append("  AND ");
					}
					
						sqlBuffer = sqlBuffer.append("      start_time BETWEEN '"+startTimeRange.get("startTime1")+"'" +//2011-02-10 01:00:00
							 	 "   AND '"+startTimeRange.get("startTime2")+"'");
					
					
				}
				/*if(!whereAttached){
					sqlBuffer = sqlBuffer.append(" WHERE ");
					whereAttached = true;
					whereLocation = 4;
				}
				if(whereLocation!=4){
					sqlBuffer = sqlBuffer.append("  AND ");
				}
				sqlBuffer = sqlBuffer.append("   user_data.user_id <> "+userId);*/
				System.out.println(sqlBuffer.toString());
		Query q = getEntityManager().createNativeQuery(sqlBuffer.toString(), "getPoolsNew");
		List<PoolDetailsNew> poolDetailsList = q.getResultList();

		
		return poolDetailsList;
	}
	
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
	 *            number of results to return.
	 * @return List<Pool> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Pool> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Pool instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Pool model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Pool> findBySeats(Object seats, int... rowStartIdxAndCount) {
		return findByProperty(SEATS, seats, rowStartIdxAndCount);
	}

	public List<Pool> findByAutoApprove(Object autoApprove,
			int... rowStartIdxAndCount) {
		return findByProperty(AUTO_APPROVE, autoApprove, rowStartIdxAndCount);
	}

	public List<Pool> findByContribution(Object contribution,
			int... rowStartIdxAndCount) {
		return findByProperty(CONTRIBUTION, contribution, rowStartIdxAndCount);
	}

	public List<Pool> findByDescription(Object description,
			int... rowStartIdxAndCount) {
		return findByProperty(DESCRIPTION, description, rowStartIdxAndCount);
	}

	public List<Pool> findByPoolTitle(Object poolTitle
	, int...rowStartIdxAndCount
	) {
		return findByProperty(POOL_TITLE, poolTitle
	, rowStartIdxAndCount
		);
	}
	
	public List<Pool> findBySmoking(Object smoking
	, int...rowStartIdxAndCount
	) {
		return findByProperty(SMOKING, smoking
	, rowStartIdxAndCount
		);
	}
	
	public List<Pool> findByWomenOnly(Object womenOnly
	, int...rowStartIdxAndCount
	) {
		return findByProperty(WOMEN_ONLY, womenOnly
	, rowStartIdxAndCount
		);
	}
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
	@SuppressWarnings("unchecked")
	public List<Pool> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Pool instances", Level.INFO, null);
		try {
			final String queryString = "select model from Pool model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}