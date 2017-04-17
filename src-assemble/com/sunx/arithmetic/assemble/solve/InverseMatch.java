package com.sunx.arithmetic.assemble.solve;

import com.sunx.arithmetic.assemble.model.Equation;

/**
 * 反向匹配 - 校验参数项是否在方程式的允许解的范围内
 * 
 * @author Sun 2017年4月11日 下午7:59:43
 */
public interface InverseMatch {

	public boolean match(Long pno, Equation e);

}
