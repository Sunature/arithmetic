package com.sunx.arithmetic.assemble.model;

/**
 * 方程组的解 - 一组
 * 
 * @author Sun 2017年4月12日 下午7:09:26
 */
public class Solutions {

	private Solution[] solutions;
	private Double index;// 择优指数

	public Solution[] getSolution() {
		return solutions;
	}

	public void setSolution(Solution[] solutions) {
		this.solutions = solutions;
	}

	public Double getIndex() {
		return index;
	}

	public void setIndex(Double index) {
		this.index = index;
	}

}
