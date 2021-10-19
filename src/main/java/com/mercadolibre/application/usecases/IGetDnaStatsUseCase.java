package com.mercadolibre.application.usecases;

import org.springframework.http.ResponseEntity;

public interface IGetDnaStatsUseCase {

	public ResponseEntity<Object> getStats();

}
