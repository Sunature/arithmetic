package com.sunx.arithmetic.assemble.solve;

import java.util.ArrayList;
import java.util.List;

import com.sunx.arithmetic.assemble.model.Equation;
import com.sunx.arithmetic.assemble.model.Possolve;
import com.sunx.arithmetic.assemble.model.Solution;

/**
 * 多维一元方程组解的组合
 * 
 * @author Sun 2017年4月11日 下午9:31:56
 */
public class NativeBayesolves {

	int dimension = 0;// 方程组维度
	List<Equation> es;
	List<Possolve> ps;

	private NativeBayesolves() {
	}

	public NativeBayesolves(List<Equation> es, List<Possolve> ps) {
		this();
		this.es = es;
		this.ps = ps;
		this.dimension = es.size();
	}

	public List<Solution[]> compute() {
		this.deal(0);
		return this.result;
	}

	List<Solution[]> result = new ArrayList<Solution[]>();
	Solution[] solution;

	private void deal(int cur) {
		for (Possolve[] p : this.es.get(cur).getSolves()) {
			if (cur == 0)
				this.solution = new Solution[this.dimension];
			// clear
			this.clearSolution(cur);
			// valid
			if (this.valid(p)) {
				this.solution[cur] = new Solution(this.es.get(cur).getFlag(), p);
				if (cur == this.dimension - 1)
					this.result.add(this.solution.clone());
			} else
				continue;
			// recursion
			if (cur < this.dimension - 1)
				this.deal(cur + 1);
		}
	}

	private boolean valid(Possolve[] p) {
		for (Possolve ep : ps) {
			int sum = 0;
			for (Solution s : this.solution) {
				if (s != null)
					for (Possolve sp : s.getSolve()) {
						if (sp != null
								&& sp.getPno().compareTo(ep.getPno()) == 0) {
							sum += sp.getNum();
						}
					}
			}
			for (Possolve sp : p) {
				if (sp != null && sp.getPno().compareTo(ep.getPno()) == 0) {
					sum += sp.getNum();
				}
			}
			if (sum > ep.getNum())
				return false;
		}
		return true;
	}

	private void clearSolution(int cur) {
		while (cur < this.dimension)
			this.solution[cur++] = null;
	}

}
