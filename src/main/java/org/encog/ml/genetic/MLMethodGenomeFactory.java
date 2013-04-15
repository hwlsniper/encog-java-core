/*
 * Encog(tm) Core v3.2 - Java Version
 * http://www.heatonresearch.com/encog/
 * https://github.com/encog/encog-java-core
 
 * Copyright 2008-2013 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package org.encog.ml.genetic;

import org.encog.ml.MLEncodable;
import org.encog.ml.MethodFactory;
import org.encog.ml.ea.genome.Genome;
import org.encog.ml.ea.genome.GenomeFactory;
import org.encog.ml.ea.population.Population;

public class MLMethodGenomeFactory implements GenomeFactory {

	private MethodFactory factory;
	private Population population;
	
	public MLMethodGenomeFactory(MethodFactory theFactory, Population thePopulation) {
		this.factory = theFactory;
		this.population = thePopulation;
	}
	
	@Override
	public Genome factor() {
		Genome result = new MLMethodGenome((MLEncodable)this.factory.factor());
		result.setPopulation(this.population);
		return result;
	}

	@Override
	public Genome factor(Genome other) {
		MLMethodGenome result = (MLMethodGenome)factor();
		result.copy((MLMethodGenome)other);
		result.setPopulation(this.population);
		return result;
	}
}
