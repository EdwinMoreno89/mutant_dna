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

import com.mercadolibre.domain.model.Mutant;
import com.mercadolibre.domain.model.MutantRepository;

/**
 * @author Edwin Moreno Cristancho Caso de Uso para validar ADN de Mutantes
 */

@Service
public final class ValidateDnaUseCase {

	private static int countChaingMutant = 0;
	private final static String _CLASS = "[ValidateDnaUseCase]";
	private static final Logger logger = LogManager.getLogger(ValidateDnaUseCase.class);

	private static MutantRepository mutantRepository;

	@SuppressWarnings("static-access")
	@Autowired
	public ValidateDnaUseCase(MutantRepository mutantRepository) {
		super();
		this.mutantRepository = mutantRepository;
	}

	/**
	 * operación isMutant para validar ADN de Mutantes
	 */
	public static ResponseEntity<Object> isMutant(Mutant mutant) throws Exception {

		final String _METHOD = "[isMutant]";
		logger.info("Class " + _CLASS + " Method: " + _METHOD);

		/** Validamos la cadena de ADN para que solo contenga una estructura válida */
		if (!mutant.validateChainDna(mutant.getDna())) {
			logger.error("Class " + _CLASS + " Method: " + _METHOD + HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(mutant, HttpStatus.BAD_REQUEST);
		}

		String[] dnaChain = mutant.getDna();
		String mutantId = "";
		/** Creamos un Id unico para persistir */
		for (String mutantDna : dnaChain) {
			mutantId = mutantId + mutantDna;
		}
		logger.info("Class " + _CLASS + " Method: " + _METHOD + "Creamos un ID Unico: " + mutantId);

		/** Creamos una matriz bidimensional con la estructura recibida */
		String[][] matrix = new String[dnaChain[0].length()][dnaChain.length];

		/** Validamos las Filas de la matriz para identificar ADN Mutante */
		validarHorizontales(dnaChain, matrix);

		/** Validamos las Columnas de la matriz para identificar ADN Mutante */
		validarVerticales(matrix);

		/** Validamos las Diagonales de la matriz para identificar ADN Mutante */
		validarDiagonales(matrix);

		/**
		 * Si existen más de una secuencia de cadena de 4 letras iguales retornamos HTTP
		 * 200-OK en caso contrario 403-Forbidden
		 */
		if (countChaingMutant > 1) {
			/** Persistimos la cadena de ADN */
			mutant.setId(mutantId);
			mutant.setMutant(true);
			if (!mutantRepository.isRegistered(mutantId)) {
				mutantRepository.registerMutantDna(mutantId, mutant.getDna(), true);
			}
			countChaingMutant = 0;
			logger.info("Class " + _CLASS + " Method: " + _METHOD + " - " + HttpStatus.OK + " Es un Mutante!");
			return new ResponseEntity<Object>(mutant, HttpStatus.OK);
		} else {
			mutant.setId(mutantId);
			mutant.setMutant(false);
			countChaingMutant = 0;
			logger.info(
					"Class " + _CLASS + " Method: " + _METHOD + " - " + HttpStatus.FORBIDDEN + " No Es un Mutante!");
			/** Persistimos la cadena de ADN */
			if (!mutantRepository.isRegistered(mutantId)) {
				mutantRepository.registerMutantDna(mutantId, dnaChain, false);
			}
			return new ResponseEntity<Object>(mutant, HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * operación validarHorizontales para validar ADN de Mutantes en la cadena
	 * String [] (Filas) recibida. luego de validar las filas, el metodo construye
	 * una matriz que nos servirá para validar las columnas y diagonales
	 * posteriormente
	 */
	private static void validarHorizontales(String[] dnaChain, String[][] matrix) {

		final String _METHOD = "[validarHorizontales]";
		logger.info("Class " + _CLASS + " Method: " + _METHOD);

		/**
		 * recorremos la cadena enviada, 1 para validar las cadenas horizontales y 2
		 * para llenar la matriz
		 */
		for (int i = 0; i < dnaChain.length; i++) {
			/** enviamos a validar las filas que ya vienen en el array de string[] */
			validateDna(dnaChain[i]);

			/** Construimos Matriz que nos va a servir para validar columnas y diagonales */
			char[] dnaCharArray = dnaChain[i].toCharArray();
			for (int j = 0; j < dnaChain[0].length(); j++) {
				char x = dnaCharArray[j];
				matrix[i][j] = String.valueOf(x);
			}
		}

	}

	/**
	 * operación validarVerticales para validar ADN de Mutantes en las columnas de
	 * la matriz construida.
	 */
	private static void validarVerticales(String[][] matrix) {

		final String _METHOD = "[validarVerticales]";
		logger.info("Class " + _CLASS + " Method: " + _METHOD);

		/** FOR para recorrer la matriz y realizar validación de las verticales */
		for (int h = 0; h < matrix.length; h++) {
			String vertical = "";
			/**
			 * En el String vertical: concatenamos las posiciones de las columnas de la
			 * matriz para enviarlas a validar
			 */
			for (int row = 0; row < matrix.length; row++) {
				vertical = vertical + matrix[row][h];
			}
			/** Enviamos a validar las Columnas ó Verticales */
			validateDna(vertical);
		}

	}

	/**
	 * operación validarVerticales para validar ADN de Mutantes en las diagonales de
	 * la matriz construida.
	 */
	public static void validarDiagonales(String matrix[][]) {

		final String _METHOD = "[validarDiagonales]";
		logger.info("Class " + _CLASS + " Method: " + _METHOD);

		/** Validación de diagonales de izq a derecha */

		/** Calculamos la altura y la anchura de la matriz */
		Integer altura = matrix.length, anchura = matrix[0].length;
		/** Recorremos los inicios de cada diagonal en los bordes de la matriz */
		/** Mientras no llegue a la ultima Diagonal */
		/** Avanza hasta el comienzo de la siguiente Diagonal */
		for (Integer diagonal = 1 - anchura; diagonal <= altura - 1; diagonal += 1) {
			String valDiagonal = "";

			/**
			 * Recorre cada una de las diagonales a partir del extremo superior izquierdo.
			 */
			/** Mientras no exceda los limites */
			/** Avanza en diagonal incrementando ambos ejes. */
			for (Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal); vertical < altura
					&& horizontal < anchura; vertical += 1, horizontal += 1) {

				valDiagonal = valDiagonal + matrix[vertical][horizontal];

			}
			/** Enviamos a validar las diagonales. */
			validateDna(valDiagonal);
		}

	}

	/**
	 * operación validateDna para validar ADN de Mutantes en las cadenas de String.
	 */
	public static boolean validateDna(String dna) {

		final String _METHOD = "[validateDna]";
		logger.info("Class " + _CLASS + " Method: " + _METHOD);

		/**
		 * Se convierte la cadena String en un array de caracteres para validar la
		 * secuencia
		 */
		char[] dnaCharArray = dna.toCharArray();
		for (int i = 0; i < dnaCharArray.length; i++) {
			char x = dnaCharArray[i];
			/**
			 * Validamos si la cadena enviada contiene 4 veces la misma letra consecutiva
			 */
			String cadena = x + "" + x + "" + x + "" + x;
			if (dna.contains(cadena)) {
				logger.warn("Class " + _CLASS + " Method: " + _METHOD + "Se encontró cadena mutante en " + dna);
				countChaingMutant++;
				break;
			}
		}
		return true;
	}
}
