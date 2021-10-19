/**
 * 
 */
package com.mercadolibre.mutant.infrastructure.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutant.application.usecases.ValidateDnaUseCase;
import com.mercadolibre.mutant.domain.model.Mutant;

/**
 * @author Edwin Moreno Cristancho
 * Controlador que permite recibir peticiones POST para validar ADN de Mutantes
 */

@RestController
public final class MutantDnaValidateController {

	private final static String _CLASS = "[MutantDnaValidateController]";
	private static final Logger logger = LogManager.getLogger(MutantDnaValidateController.class);

	@Autowired
	private ValidateDnaUseCase validate;

	/**
	 * EndPoint para recibir peticiones POST y validar si existe ADN Mutante.
	 */
	@RequestMapping(value = "/mutant/", method = RequestMethod.POST)
	public ResponseEntity<Object> isMutant(@RequestBody Mutant mutant) throws Exception {
		final String _METHOD = "[isMutant]";
		logger.info("Class "+_CLASS +" Method: "+_METHOD);
		return validate.isMutant(mutant);
	}

}
