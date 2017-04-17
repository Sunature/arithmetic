package com.sunx.arithmetic.assemble.model;

import java.util.List;

/**
 * 定义多维一元方程组 - 方程类
 * 
 * @author Sun 2017年4月11日 下午7:00:30
 */
public class Equation {

	private Equation() {
	}

	/**
	 * 定义方程式
	 * 
	 * @param flag
	 * @param coefficient
	 */
	public Equation(Long flag, int coefficient) {
		this();
		this.flag = flag;
		this.coefficient = coefficient;
	}

	private Long flag; // 方程标记
	private int coefficient = 1; // 默认系数为1
	private Long[] allowSolves; // 一维一元方程式允许解的范围 ，例如 ： [1,2,6,7]
	private List<Possolve[]> solves; // 一维一元方程式所有可能组合解；Possovle[]：一个组合解

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public Long[] getAllowSolves() {
		return allowSolves;
	}

	public void setAllowSolves(Long[] allowSolves) {
		this.allowSolves = allowSolves;
	}

	public List<Possolve[]> getSolves() {
		return solves;
	}

	public void setSolves(List<Possolve[]> solves) {
		this.solves = solves;
	}

}
