/**
 * 
 */
package com.mercadolibre.infrastructure.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.application.usecases.GetDnaStatsUseCase;

/**
 * @author Edwin Moreno Cristancho
 *
 */
@RestController
public class MutantDnaStatsController {

	private final static String _CLASS = "[MutantDnaStatsController]";
	private static final Logger logger = LogManager.getLogger(MutantDnaStatsController.class);
	
	@Autowired
	private GetDnaStatsUseCase retrieveStats;

	/**
	 * EndPoint para recibir peticiones GET y consultar las cadenas de ADN validadas.
	 */
	@GetMapping("/stats")
	public ResponseEntity<Object> getStats() {
		final String _METHOD = "[getStats]";
		logger.info("Class "+_CLASS +" Method: "+_METHOD);
		return retrieveStats.getStats();
	}
}
