/**
 * 
 */
package com.mercadolibre.mutant.domain.model;

import java.io.Serializable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Usuario
 *
 */

public class Mutant implements Serializable {

	private static final Logger logger = LogManager.getLogger(Mutant.class);
	private final static String _CLASS = "[Mutant]";

	private String id;

	private String[] dna;
	
	private boolean isMutant;

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

	public Mutant(String id, String[] dna) {
		this.id = id;
		this.dna = dna;
	}


	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

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

	public boolean validateChainDna(String[] dna) {
		final String _METHOD = "[validateChainDna]";
		for (int i = 0; i < dna.length; i++) {
			Pattern pat = Pattern.compile("(A|T|C|G)+");
			Matcher mat = pat.matcher(dna[i]);
			if (!mat.matches()) {
				logger.error("Class " + _CLASS + " Method: " + _METHOD + " - " + dna[i]
						+ "No cumple con la estructura Valida de cadena de ADN[A|T|C|G]");
				return false;
			}
		}
		return true;
	}

}
