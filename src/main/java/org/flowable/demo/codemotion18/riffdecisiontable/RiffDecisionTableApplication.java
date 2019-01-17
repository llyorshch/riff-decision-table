package org.flowable.demo.codemotion18.riffdecisiontable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;

import org.flowable.dmn.api.DmnRuleService;
import org.flowable.dmn.engine.DmnEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RiffDecisionTableApplication {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final DmnRuleService dmnRuleService;

	public RiffDecisionTableApplication(DmnEngine dmnEngine) {
		this.dmnRuleService = dmnEngine.getDmnRuleService();
	}

	@Bean
	Function<Map<String, Object>, Map<String, Object>> loanGuidance() {
		return inputVariables -> {
			Map<String, Object> parsedInputVariables = parseInputVariables(inputVariables);
			return dmnRuleService.createExecuteDecisionBuilder().decisionKey("loanGuidance")
					.variables(parsedInputVariables).executeWithSingleResult();
		};
	}

	private static Map<String, Object> parseInputVariables(Map<String, Object> inputVariables) {
		try {
			String BIRTH_DATE = "birthDate";
			return (inputVariables.containsKey(BIRTH_DATE) && inputVariables.get(BIRTH_DATE) instanceof String
					? ImmutableMap.of(BIRTH_DATE, sdf.parse((String) inputVariables.get(BIRTH_DATE)))
					: inputVariables);
		} catch (ParseException e) {
			throw new RuntimeException(
					"The input variables includes an invalid Date or String in the 'birthDate' field.");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RiffDecisionTableApplication.class, args);
	}

}
