package org.flowable.demo.codemotion18.riffdecisiontable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiffDecisionTableApplicationTests {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	Function<Map<String,Object>,Map<String,Object>> loanGuidance;

	@Test
	public void contextLoads() {
	}

	@Test
	public void loanguidance_withAnGenXPersonAndADateObject_returnsGenX () throws ParseException {
		//Given
		Date birthDate = sdf.parse("1976-01-01");

		//When
		Map<String,Object> outputVariables = loanGuidance.apply(ImmutableMap.of("birthDate",birthDate));

		//Then
		Assert.assertEquals("Gen X or older", outputVariables.get("guidance"));
	}

	@Test
	public void loanguidance_withAGenYPersonAndADateObject_returnsGenX () throws ParseException {
		//Given
		Date birthDate = sdf.parse("1994-01-01");

		//When
		Map<String,Object> outputVariables = loanGuidance.apply(ImmutableMap.of("birthDate",birthDate));

		//Then
		Assert.assertEquals("Gen Y", outputVariables.get("guidance"));
	}

	@Test
	public void loanguidance_withAGenZPersonAndADateObject_returnsGenX () throws ParseException {
		//Given
		Date birthDate = sdf.parse("1996-01-01");

		//When
		Map<String,Object> outputVariables = loanGuidance.apply(ImmutableMap.of("birthDate",birthDate));

		//Then
		Assert.assertEquals("Gen Z or younger", outputVariables.get("guidance"));
	}


	@Test
	public void loanguidance_withAGenXPersonAndAStringObject_returnsGenX () throws ParseException {
		//Given
		String birthDate = "1976-01-01";

		//When
		Map<String,Object> outputVariables = loanGuidance.apply(ImmutableMap.of("birthDate",birthDate));

		//Then
		Assert.assertEquals("Gen X or older", outputVariables.get("guidance"));
	}

	@Test
	public void loanguidance_withAGenYPersonAndAStringObject_returnsGenX () throws ParseException {
		//Given
		String birthDate = "1994-01-01";

		//When
		Map<String,Object> outputVariables = loanGuidance.apply(ImmutableMap.of("birthDate",birthDate));

		//Then
		Assert.assertEquals("Gen Y", outputVariables.get("guidance"));
	}

	@Test
	public void loanguidance_withAGenZPersonAndAStringObject_returnsGenX () throws ParseException {
		//Given
		String birthDate = "1996-01-01";

		//When
		Map<String,Object> outputVariables = loanGuidance.apply(ImmutableMap.of("birthDate",birthDate));

		//Then
		Assert.assertEquals("Gen Z or younger", outputVariables.get("guidance"));
	}

}
