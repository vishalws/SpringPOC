package com.nt.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class SPutil {
private static Logger log=LoggerFactory.getLogger(SPutil.class);
public static final String get_Student="getAllStudent1";
	public static Map<String, SimpleJdbcCall> spCalls = new HashMap<String, SimpleJdbcCall>();

	public static Map<String, Integer> getEmptyOutputParams() {
		Map<String, Integer> outputParams = new HashMap<String, Integer>();

		return outputParams;
	}

	public static MapSqlParameterSource spInputParameters() {
		MapSqlParameterSource in = new MapSqlParameterSource();
		return in;

	}

	public static SimpleJdbcCall getSPCall(String key, DataSource ds, String spName, String packageName,
			Map<String, RowMapper<?>> cursorMappers, Map<String, Integer> outputParams) {
		SimpleJdbcCall spCall = spCalls.get(key);
		if (spCall == null) {
			spCall = new SimpleJdbcCall(ds).withProcedureName(spName);
			// Set SP package Name to be executed
			if (packageName != null && packageName.length() > 0)
				spCall.withCatalogName(packageName);

			// spCall.withSchemaName("inctrl");
			spCall.getJdbcTemplate().setQueryTimeout(240);
			// TODO: Need more flexibility to support multiple cursors
			for (String cursorName : cursorMappers.keySet()) {
				RowMapper<?> mapper = cursorMappers.get(cursorName);
				spCall.returningResultSet(cursorName, mapper);
			}

			// Declare Output Parameters
			for (Map.Entry<String, Integer> entry : outputParams.entrySet())
				spCall.declareParameters(new SqlOutParameter(entry.getKey(), entry.getValue()));

			// This SP is now compiled and looked up for metadata, add to cahce to be reused
			// later
			spCalls.put(key, spCall);
		}

		return spCall;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List execute(DataSource ds, String packageName, String spName, MapSqlParameterSource inputParams,
			Map<String, Integer> outputParams, String cursorName, RowMapper<?> rowMapper) {
		Map<String, RowMapper<?>> cursorMappers = new HashMap<String, RowMapper<?>>();
		if (rowMapper != null)
			cursorMappers.put(cursorName, rowMapper);

		Map<String, Object> m = executeMultiple(ds, packageName, spName, inputParams, outputParams, cursorMappers);

		List<Object> results = new LinkedList<Object>();
		results = (List<Object>) m.get(cursorName);
		return results;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> executeMultiple(DataSource ds, String packageName, String spName,
			MapSqlParameterSource inputParams, Map<String, Integer> outputParams,
			/* expects a map with cursorname --> rowmapper */Map<String, RowMapper<?>> cursorMappers) {
		System.out.println("execute multi=" + inputParams.getValues());
		String key = packageName + "." + spName;// +rowMapper.getClass().getName();
		for (String cursorName : cursorMappers.keySet()) {
			RowMapper<?> mapper = cursorMappers.get(cursorName);
			key += mapper.getClass().getName();
		}

		// Get the compiled SP
		SimpleJdbcCall spCall = getSPCall(key, ds, spName, packageName, cursorMappers, outputParams);
		// spCall.getJdbcTemplate().setFetchSize(0);
		System.out.println("sp call "+spCall);

		logSPParameters(inputParams, outputParams, key);
		long startTime = 0;
		long endTime = 0;
		if (log.isDebugEnabled())
			startTime = System.currentTimeMillis();

		Map<String, Object> m = null;

if (inputParams != null) {
	System.out.println("in " + inputParams.getValues());
	m = (Map<String, Object>) spCall.execute(inputParams);
			System.out.println("in " + inputParams);
			System.out.println("m  " + m.get(0));
		} else
			m = (Map<String, Object>) spCall.execute(new HashMap<String, Object>());

		if (log.isDebugEnabled()) {
			endTime = System.currentTimeMillis();
			log.info("Time for execution of SP " + key + " is " + (endTime - startTime) / 1000 + " seconds.");
		}

		return m;
	}

	public static void logSPParameters(MapSqlParameterSource inputParams, Map<String, Integer> outputParams,
			String spName) {
		if (log.isDebugEnabled()) {
			log.debug("Executing SP: " + spName + " with the below Parameters.");

			log.debug("Input:");
			if (inputParams != null) {
				Map<String, Object> inpMap = inputParams.getValues();
				for (Map.Entry<String, Object> entry : inpMap.entrySet()) {
					log.debug(entry.getKey() + " = " + entry.getValue());
				}
			}

			log.debug("Output:");
			if (outputParams != null) {
				for (Map.Entry<String, Integer> entry : outputParams.entrySet()) {
					log.debug(entry.getKey() + " = " + entry.getValue());
				}
			}
		}
	}
}
