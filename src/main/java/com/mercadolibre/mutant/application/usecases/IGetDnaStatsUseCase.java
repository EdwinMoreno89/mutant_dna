package com.mercadolibre.mutant.application.usecases;

import org.springframework.http.ResponseEntity;

public interface IGetDnaStatsUseCase {

	public ResponseEntity<Object> getStats();

}
