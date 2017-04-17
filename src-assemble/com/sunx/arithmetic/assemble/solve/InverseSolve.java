package com.sunx.arithmetic.assemble.solve;

import com.sunx.arithmetic.assemble.model.Equation;

/**
 * 匹配参数项是否符合方程式允许解的范围
 * 
 * @author Sun 2017年4月11日 下午8:17:42
 */
public class InverseSolve implements InverseMatch {

	@Override
	public boolean match(Long pno, Equation e) {
		if (e.getAllowSolves() != null && e.getAllowSolves().length > 0) {
			for (Long no : e.getAllowSolves()) {
				if (pno.compareTo(no) == 0)
					return true;
			}
		}
		return false;
	}

}
