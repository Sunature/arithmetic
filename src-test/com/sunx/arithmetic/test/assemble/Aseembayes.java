package com.sunx.arithmetic.test.assemble;

import java.util.ArrayList;
import java.util.List;

import com.sunx.arithmetic.assemble.model.Equation;
import com.sunx.arithmetic.assemble.model.Possolve;
import com.sunx.arithmetic.assemble.model.Solution;
import com.sunx.arithmetic.assemble.solve.InverseMatch;
import com.sunx.arithmetic.assemble.solve.PreferentialIndexCalculation;
import com.sunx.arithmetic.assemble.solve.SolvesPreset;
import com.sunx.arithmetic.util.AssembleUtil;

public class Aseembayes {

	public void preset(Equation e) {
		switch (e.getFlag().intValue()) {
		case 1001:
			e.setAllowSolves(new Long[] { 900L, 903L });
			break;
		case 1002:
			e.setAllowSolves(new Long[] { 902L, 903L, 904L });
			break;
		case 1003:
			e.setAllowSolves(new Long[] { 900L, 901L, 903L });
			break;
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		final Aseembayes a = new Aseembayes();
		// 准备数据

		// 方程组
		List<Equation> es = new ArrayList<Equation>();
		// 方程
		es.add(new Equation(1001L, 3));
		es.add(new Equation(1002L, 1));
		es.add(new Equation(1003L, 2));

		// 待解参数集
		List<Possolve> ps = new ArrayList<Possolve>();
		ps.add(new Possolve(900L, 5));
		ps.add(new Possolve(901L, 3));
		ps.add(new Possolve(902L, 3));
		ps.add(new Possolve(903L, 2));
		ps.add(new Possolve(904L, 2));

		// 方式一：初始化 - 装配参数
		AssembleUtil assemble1 = new AssembleUtil(new SolvesPreset() {

			@Override
			public void presetAllowSolves(Equation e) {
				// 查询数据库填入 - 这里是模拟所以有逻辑判断
				a.preset(e);
			}

		});

		// 方式二：初始化 - 自定义匹配
		AssembleUtil assemble2 = new AssembleUtil(new InverseMatch() {

			@Override
			public boolean match(Long pno, Equation e) {
				return false;
			}

		});

		// 计算结果
		long start = System.currentTimeMillis();
		List<Solution[]> result = assemble1.untie(es, ps);
		// result = untie2.assemble(es, ps);
		System.out.println("\n 计算耗时：" + (System.currentTimeMillis() - start)
				+ "ms" + " ; 解的個數：" + result.size());

		// 取最优解
		Solution[] optimal = assemble1.preferential(result,
				new PreferentialIndexCalculation() {

					@Override
					public Double calculation(Solution[] solutions) {
						// 这里计算该解的择优指数
						return 0D;
					}
				});

		// 打印
		for (Solution[] sa : result) {
			System.out.println("\n solution: ================== ");
			for (Solution s : sa) {
				System.out.print("ES:" + s.getEflag() + " >> PS: {");
				for (Possolve p : s.getSolve())
					System.out.print(p.getPno() + "[" + p.getNum() + "],");
				System.out.println("}");
			}
		}
	}
}
