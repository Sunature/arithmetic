package com.sunx.arithmetic.assemble.solve;

import java.util.ArrayList;
import java.util.List;

import com.sunx.arithmetic.assemble.model.Equation;
import com.sunx.arithmetic.assemble.model.Possolve;

/**
 * 解方程 - 一维一元
 * 
 * @author Sun 2017年4月11日 下午8:32:48
 */
public class EquationSolve {

	public void solve(Equation e, List<Possolve> ps) {
		// 所有可能的参数项
		List<Possolve> temp = new ArrayList<Possolve>();
		for (Possolve p : ps) {
			if (p.getEflag().contains(e.getFlag()))
				temp.add(p);
		}
		// 列举所有解的组合
		Assemble ass = new Assemble(temp, e.getCoefficient());
		ass.run();
		e.setSolves(ass.solves);
	}

	class Assemble {

		public Assemble(List<Possolve> ps, int max) {
			this.ps = ps;
			this.max = max;
			this.deap = this.ps.size();
		}

		List<Possolve> ps;
		int max = 0;
		int deap = 0;
		List<Possolve[]> solves = new ArrayList<Possolve[]>();

		public void run() {
			this.deal(0, 0);
		}

		Possolve[] psolve;

		private void deal(int cur, int sum) {
			int i = 0;
			while (i <= this.ps.get(cur).getNum()) {
				if (cur == 0)
					this.psolve = new Possolve[this.deap];
				if (i > 0)
					this.psolve[cur] = new Possolve(this.ps.get(cur).getPno(),
							i);
				if (sum + i == this.max) {
					this.solves.add(this.cleanPossolve(this.psolve));
					i++;
					this.psolve[cur] = null;
					continue;
				} else if (cur < this.deap - 1 && sum + i < this.max)
					this.deal(cur + 1, sum + i);
				i++;
				this.psolve[cur] = null;
			}
		}

		private Possolve[] cleanPossolve(Possolve[] psolve) {
			List<Possolve> pls = new ArrayList<Possolve>();
			for (Possolve p : psolve) {
				if (p != null)
					pls.add(p);
			}
			return pls.toArray(new Possolve[pls.size()]);
		}
	}

}
