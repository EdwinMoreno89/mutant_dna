/**
 * 
 */
package com.mercadolibre.mutant.domain.model;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Usuario
 *
 */

@Repository
public interface MutantRepository {

	public Stat getStat();

	public boolean isRegistered(String idMutant);
	
	public void registerMutantDna(String idMutant, String[] mutantDna, boolean isMutant);

	public AbstractMap<String, String []> getMutantDna();

}
