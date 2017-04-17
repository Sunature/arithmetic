package com.sunx.arithmetic.assemble.model;

/**
 * 方程解 - 单一解
 * 
 * @author Sun 2017年4月11日 下午7:39:53
 */
public class Solution {

	private Solution() {
	}

	private Long eflag;
	private Possolve[] solve;

	public Solution(Long eflag, Possolve[] solve) {
		this();
		this.eflag = eflag;
		this.solve = solve;
	}

	public Long getEflag() {
		return eflag;
	}

	public void setEflag(Long eflag) {
		this.eflag = eflag;
	}

	public Possolve[] getSolve() {
		return solve;
	}

	public void setSolve(Possolve[] solve) {
		this.solve = solve;
	}

}
