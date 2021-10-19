/**
 * 
 */
package com.mercadolibre.mutant.domain.model;

/**
 * @author Usuario
 *
 */
public class Stat {

	private Integer count_mutant_dna;
	private Integer count_human_dna;
	private double ratio;

	public Integer getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(Integer count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public Integer getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(Integer count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

}
