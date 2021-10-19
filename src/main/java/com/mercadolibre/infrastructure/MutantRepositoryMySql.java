package com.mercadolibre.infrastructure;

import java.util.AbstractMap;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mercadolibre.domain.model.MutantRepository;
import com.mercadolibre.domain.model.Stat;
import com.mercadolibre.infrastructure.MutantRepositoryMySql;

@Repository
public class MutantRepositoryMySql extends JdbcDaoSupport implements MutantRepository {

	private final static String _CLASS = "[MutantRepositoryMySql]";
	private static final Logger logger = LogManager.getLogger(MutantRepositoryMySql.class);

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public boolean isRegistered(String idMutant) {
		boolean response = false;
		try {
			String sql = "SELECT id FROM mutants_dna WHERE id = ?";
			String resp = getJdbcTemplate().queryForObject(sql, new Object[] { idMutant }, String.class);
			if (idMutant.equals(resp)) {
				logger.info("Ya existe el registro en BD ");
				response = true;
			} else {
				response = false;
				logger.info("No existe el registro en BD ");
			}
		} catch (Exception e) {
			logger.info("Se ha presentado un error al consultar los ADN: " + e.getMessage());
			response = false;
		}
		return response;
	}

	@Override
	public void registerMutantDna(String idMutant, String[] mutantDna, boolean isMutant) {
		final String _METHOD = "[registerMutantDna]";
		logger.info("Class " + _CLASS + " registerMutantDna: " + _METHOD);
		try {

			String sql = "INSERT INTO mutants_dna (id, dna, isMutant) VALUES(?,?,?)";
			String str = "";
			for (String dna : mutantDna) {
				str += dna + ",";
			}
			getJdbcTemplate().update(sql, new Object[] { idMutant, str, isMutant });
		} catch (Exception e) {
			logger.info("Se ha presentado un error al registrar un nuevo ADN");
		}
	}

	@Override
	public AbstractMap<String, String[]> getMutantDna() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stat getStat() {
		final String _METHOD = "[getStat]";
		logger.info("Class " + _CLASS + " getStat: " + _METHOD);
		Stat stat_mutant = new Stat();
		try {
			String mutant = "SELECT COUNT(*) FROM mutants_dna WHERE isMutant = TRUE";
			int total_mutant = getJdbcTemplate().queryForObject(mutant, Integer.class);

			String human = "SELECT COUNT(*) FROM mutants_dna WHERE isMutant = FALSE";
			int total_human = getJdbcTemplate().queryForObject(human, Integer.class);

			stat_mutant.setCount_human_dna(total_human);
			stat_mutant.setCount_mutant_dna(total_mutant);
			float percentage_mutant = (float) (total_mutant * 100) / (total_human + total_mutant);
			stat_mutant.setRatio(percentage_mutant);

		} catch (Exception e) {
			logger.info("Se ha presentado un error al consultar los ADN");
		}
		return stat_mutant;
	}

}
