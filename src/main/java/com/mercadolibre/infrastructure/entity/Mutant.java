package com.mercadolibre.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mutants_dna")
public class Mutant {

    @Column(name = "id")
	private String id;
    
    @Column(name = "dna")
	
    private String[] dna;
    @Column(name = "isMutant")
	private boolean isMutant;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dna
	 */
	public String[] getDna() {
		return dna;
	}

	/**
	 * @param dna the dna to set
	 */
	public void setDna(String[] dna) {
		this.dna = dna;
	}

	/**
	 * @return the isMutant
	 */
	public boolean isMutant() {
		return isMutant;
	}

	/**
	 * @param isMutant the isMutant to set
	 */
	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	
}
