package com.sunx.arithmetic.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sunx.arithmetic.assemble.model.Equation;
import com.sunx.arithmetic.assemble.model.Possolve;
import com.sunx.arithmetic.assemble.model.Solution;
import com.sunx.arithmetic.assemble.model.Solutions;
import com.sunx.arithmetic.assemble.solve.EquationSolve;
import com.sunx.arithmetic.assemble.solve.InverseMatch;
import com.sunx.arithmetic.assemble.solve.InverseSolve;
import com.sunx.arithmetic.assemble.solve.NativeBayesolves;
import com.sunx.arithmetic.assemble.solve.PreferentialIndexCalculation;
import com.sunx.arithmetic.assemble.solve.SolvesPreset;

public class AssembleUtil {

	private SolvesPreset solvesPreset;
	private InverseMatch inverseMatch;
	private EquationSolve equationSolve = new EquationSolve();

	private AssembleUtil() {
	}

	public AssembleUtil(SolvesPreset solvesPreset) {
		this();
		this.solvesPreset = solvesPreset;
		this.inverseMatch = new InverseSolve();
	}

	public AssembleUtil(InverseMatch inverseMatch) {
		this();
		this.inverseMatch = inverseMatch;
	}

	/**
	 * 求解 - 所有有效解
	 * 
	 * @param es
	 * @param ps
	 * @return
	 * @throws ArithmeticException
	 */
	public List<Solution[]> untie(List<Equation> es, List<Possolve> ps)
			throws ArithmeticException {
		// step1:验证参数
		if (es == null || es.size() == 0 || ps == null || ps.size() == 0)
			throw new ArithmeticException("params error.");
		// step2:预置方程解
		if (this.solvesPreset != null)
			for (Equation e : es) {
				this.solvesPreset.presetAllowSolves(e);
			}
		// step3:反向解
		for (Possolve p : ps) {
			for (Equation e : es) {
				if (this.inverseMatch.match(p.getPno(), e))
					p.getEflag().add(e.getFlag());
			}
		}
		// step4:一维一元方程求全解
		for (Equation e : es) {
			this.equationSolve.solve(e, ps);
		}
		// step5:排序
		Collections.sort(es, new Comparator<Equation>() {

			@Override
			public int compare(Equation o1, Equation o2) {
				return o1.getSolves().size() - o2.getSolves().size();
			}

		});
		// step6:求多维一元组合解
		return new NativeBayesolves(es, ps).compute();
	}

	/**
	 * 择优 - 取择优指数最高的解
	 * 
	 * @param solutions
	 * @return
	 */
	public Solution[] preferential(List<Solution[]> solutionsls,
			PreferentialIndexCalculation pic) {
		List<Solutions> sls = new ArrayList<Solutions>();
		for (Solution[] ss : solutionsls) {
			Solutions sarr = new Solutions();
			sarr.setSolution(ss);
			sarr.setIndex(pic.calculation(ss));
			sls.add(sarr);
		}
		Collections.sort(sls, new Comparator<Solutions>() {

			@Override
			public int compare(Solutions o1, Solutions o2) {
				return o1.getIndex().compareTo(o2.getIndex());
			}

		});
		return sls.get(0).getSolution();
	}

}
