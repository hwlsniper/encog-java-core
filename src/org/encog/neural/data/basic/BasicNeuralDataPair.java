package org.encog.neural.data.basic;

import org.encog.neural.data.NeuralData;
import org.encog.neural.data.NeuralDataPair;

public class BasicNeuralDataPair implements NeuralDataPair {

	private NeuralData ideal;
	private NeuralData input;
	
	public BasicNeuralDataPair(NeuralData input,NeuralData ideal)
	{
		this.input = input;
		this.ideal = ideal;
	}
	
	public BasicNeuralDataPair(NeuralData input) {
		this.input = input;
	}

	public NeuralData getIdeal() {
		return this.ideal;
	}

	public NeuralData getInput() {
		return this.input;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder("[NeuralDataPair:");
		builder.append("Input:");
		builder.append(this.getInput());
		builder.append("Ideal:");
		builder.append(this.getIdeal());
		builder.append("]");
		return builder.toString();
	}

}
