/**
 * 
 */
package com.mercadolibre.application.usecases;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mercadolibre.application.usecases.GetDnaStatsUseCase;
import com.mercadolibre.domain.model.MutantRepository;

/**
 * @author Usuario
 *
 */

@Service
public final class GetDnaStatsUseCase implements IGetDnaStatsUseCase{

	private final static String _CLASS = "[GetDnaStatsUseCase]";
	private static final Logger logger = LogManager.getLogger(GetDnaStatsUseCase.class);

	private static MutantRepository mutantRepository;

	@SuppressWarnings("static-access")
	@Autowired(required=true)
	public GetDnaStatsUseCase(MutantRepository mutantRepository) {
		super();
		this.mutantRepository = mutantRepository;
	}

	/**
	 * operación getStats para consultar las estadísticas de las verificaciones de
	 * ADN:
	 */
	public ResponseEntity<Object> getStats() {
		final String _METHOD = "[getStats]";
		logger.info("Class " + _CLASS + " Method: " + _METHOD);
		return new ResponseEntity<Object>(mutantRepository.getStat(), HttpStatus.OK);
	}


	
}
