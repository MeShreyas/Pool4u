package com.carpool.data;

import java.sql.Timestamp;

public class PoolDetailsForUI {
	private int pId;
	private int rId;
	private Timestamp sTm;
	private Timestamp rTm;
	private int sts;
	private String atApr;
	private String smkng;
	private String wmn;
	private String pT;
	private String uN;
	private String cN;
	private float cTB;
	public PoolDetailsForUI() {
		
		// TODO Auto-generated constructor stub
	}
	public PoolDetailsForUI(PoolDetails poolDetails) {
		this.setpId(poolDetails.getPoolId());
		this.setrId(poolDetails.getRouteId());
		this.setsTm(poolDetails.getStartTime());
		this.setrTm(poolDetails.getReturnTime());
		this.setSts(poolDetails.getSeats());
		this.setAtApr(poolDetails.getAutoApprove());
		this.setSmkng(poolDetails.getSmoking());
		this.setWmn(poolDetails.getWomenOnly());
		this.setpT(poolDetails.getPoolTitle());
		
		this.setcTB(poolDetails.getContribution());
		this.setuN(poolDetails.getFirstName());
		if(poolDetails.getLastName()!=null && poolDetails.getLastName().trim().length()>0){
			uN = uN +" "+ poolDetails.getLastName();
		}
		this.setcN(poolDetails.getCompanyName());
		
	}
	
	/**
	 * @return the pId
	 */
	public int getpId() {
		return pId;
	}
	/**
	 * @param pId the pId to set
	 */
	public void setpId(int pId) {
		this.pId = pId;
	}
	
	public void setpId(String pId) {
		this.pId = Integer.parseInt(pId);
	}
	/**
	 * @return the rId
	 */
	public int getrId() {
		return rId;
	}
	/**
	 * @param rId the rId to set
	 */
	public void setrId(int rId) {
		this.rId = rId;
	}
	public void setrId(String rId) {
		this.rId = Integer.parseInt(rId);
	}
	/**
	 * @return the sTm
	 */
	public Timestamp getsTm() {
		return sTm;
	}
	/**
	 * @param sTm the sTm to set
	 */
	public void setsTm(Timestamp sTm) {
		this.sTm = sTm;
	}
	/**
	 * @return the rTm
	 */
	public Timestamp getrTm() {
		return rTm;
	}
	/**
	 * @param rTm the rTm to set
	 */
	public void setrTm(Timestamp rTm) {
		this.rTm = rTm;
	}
	/**
	 * @return the sts
	 */
	public int getSts() {
		return sts;
	}
	/**
	 * @param sts the sts to set
	 */
	public void setSts(int sts) {
		this.sts = sts;
	}
	/**
	 * @return the atApr
	 */
	public String getAtApr() {
		return atApr;
	}
	/**
	 * @param atApr the atApr to set
	 */
	public void setAtApr(String atApr) {
		this.atApr = atApr;
	}
	/**
	 * @return the smkng
	 */
	public String getSmkng() {
		return smkng;
	}
	/**
	 * @param smkng the smkng to set
	 */
	public void setSmkng(String smkng) {
		this.smkng = smkng;
	}
	/**
	 * @return the wmn
	 */
	public String getWmn() {
		return wmn;
	}
	/**
	 * @param wmn the wmn to set
	 */
	public void setWmn(String wmn) {
		this.wmn = wmn;
	}
	/**
	 * @return the pT
	 */
	public String getpT() {
		return pT;
	}
	/**
	 * @param pT the pT to set
	 */
	public void setpT(String pT) {
		this.pT = pT;
	}
	/**
	 * @return the uN
	 */
	public String getuN() {
		return uN;
	}
	/**
	 * @param uN the uN to set
	 */
	public void setuN(String uN) {
		this.uN = uN;
	}
	/**
	 * @return the cN
	 */
	public String getcN() {
		return cN;
	}
	/**
	 * @param cN the cN to set
	 */
	public void setcN(String cN) {
		this.cN = cN;
	}
	/**
	 * @return the cTB
	 */
	public float getcTB() {
		return cTB;
	}
	/**
	 * @param cTB the cTB to set
	 */
	public void setcTB(float cTB) {
		this.cTB = cTB;
	}
	public void setcTB(String contribution) {
		// TODO Auto-generated method stub
		cTB = Float.parseFloat(contribution);
	}

	
	
	
}
