package org.encog.ml.prg.train.mutate;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.encog.ml.ea.genome.Genome;
import org.encog.ml.ea.opp.EvolutionaryOperator;
import org.encog.ml.ea.opp.SubtreeMutation;
import org.encog.ml.prg.EncogProgram;
import org.encog.ml.prg.EncogProgramContext;
import org.encog.ml.prg.PrgCODEC;
import org.encog.ml.prg.extension.StandardExtensions;
import org.encog.ml.prg.train.PrgGenetic;
import org.encog.ml.prg.train.PrgPopulation;
import org.encog.ml.prg.train.fitness.MultiObjectiveFitness;
import org.encog.parse.expression.common.RenderCommonExpression;

public class TestSubtreeMutation extends TestCase {
	
	public void eval(int seed, String startExpression, String mutateExpression) {
		EncogProgramContext context = new EncogProgramContext();
		context.defineVariable("x");
		StandardExtensions.createNumericOperators(context.getFunctions());
		EncogProgram prg = new EncogProgram(context);
		prg.getVariables().setVariable("x", 1);
		EncogProgram[] offspring = prg.allocateOffspring(1);
		prg.compileExpression(startExpression);
		EvolutionaryOperator mutate = new SubtreeMutation(context,3);
		
		// create a trainer
		PrgPopulation pop = new PrgPopulation(context,1000);
		PrgGenetic genetic = new PrgGenetic(pop, new MultiObjectiveFitness());
		genetic.setCODEC(new PrgCODEC());
		genetic.addOperation(1.0, mutate);
		
		// test it
		Genome[] parents = {prg};
		mutate.performOperation(new Random(seed), parents, 0, offspring, 0);
		offspring[0].getVariables().setVariable("x", 1);
		RenderCommonExpression render = new RenderCommonExpression();
		Assert.assertEquals(mutateExpression,render.render(offspring[0]));
	}
	
	public void testAtomicMutate() {
		eval(2,"1","8");
	}
	
	public void testMutate() {
		eval(2,"((1+2)^2/(3+4-2))","(((1+8)^2)/((3+4)-2))");
	}
	
	public void testMutateFloat() {
		eval(7,"1.1+1.2","(1.1+(-9.807420865205021-(x-(-2*-4))))");
	}
	
	public void testMutateFloat2() {
		//eval(2,"((1.1+2.2)^2.2/(3.3+4.4-2,2))","(((1.1+-(-9.9))^2.2)/((3.3+4.4)-2.2))");
	}
}
