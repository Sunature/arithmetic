package com.sunx.arithmetic.assemble.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 待解参数集 - 参数项类
 * 
 * @author Sun 2017年4月11日 下午7:00:49
 */
public class Possolve {

	private Possolve() {
	}

	/**
	 * 定义参数项
	 * 
	 * @param pno
	 * @param num
	 */
	public Possolve(Long pno, int num) {
		this();
		this.pno = pno;
		this.num = num;
	}

	private Long pno; // 参数项编号
	private int num; // 参数项数量
	private List<Long> eflag = new ArrayList<Long>();// 对应的方程标识数组

	public Long getPno() {
		return pno;
	}

	public void setPno(Long pno) {
		this.pno = pno;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<Long> getEflag() {
		return eflag;
	}

	public void setEflag(List<Long> eflag) {
		this.eflag = eflag;
	}

}
