package com.mercadolibre.application.usecases;

import org.springframework.http.ResponseEntity;

import com.mercadolibre.domain.model.Mutant;

public interface IValidateDnaUseCase {

	public ResponseEntity<Object> isMutant(Mutant mutant);

	public void validarHorizontales(String[] dnaChain, String[][] matrix);

	public void validarVerticales(String[][] matrix);

	public void validarDiagonales(String matrix[][]);

	public boolean validateDna(String dna);

}
