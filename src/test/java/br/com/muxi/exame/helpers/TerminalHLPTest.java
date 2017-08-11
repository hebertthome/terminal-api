package br.com.muxi.exame.helpers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.muxi.exame.exceptions.JsonException;
import br.com.muxi.exame.exceptions.SchemaValidationException;
import br.com.muxi.exame.exceptions.TerminalInputException;
import br.com.muxi.exame.exceptions.TerminalSchemaValidatorException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TerminalHLPTest {

	private TerminalHLP helper;
	
	@Value(value = "classpath:terminal-schema-validator.json") 
	private Resource schemaValidator;

	@Before
	public void setUp() throws IOException {
		helper = spy(new TerminalHLP(schemaValidator));
	}

	@Test
	public void bindTerminalSuccessful() throws TerminalSchemaValidatorException {
		doReturn(getValidJson()).when(helper).mountTerminalJson(any(String.class));
		doNothing().when(helper).validateTerminal(any(String.class));

		helper.bindTerminal(any(String.class));
		verify(helper, times(1)).bindTerminal(any(String.class));
	}
	
	@Test(expected=TerminalInputException.class)
	public void bindTerminalFailed_mountTerminalJsonThrowTerminalInputException() throws TerminalSchemaValidatorException {
		doThrow(new TerminalInputException()).when(helper).mountTerminalJson(any(String.class));

		helper.bindTerminal(any(String.class));
	}
	
	@Test(expected=TerminalSchemaValidatorException.class)
	public void bindTerminalFailed_validateTerminalThrowTerminalSchemaValidatorException() throws TerminalSchemaValidatorException {
		doReturn(getValidJson()).when(helper).mountTerminalJson(any(String.class));
		doThrow(new TerminalSchemaValidatorException()).when(helper).validateTerminal(any(String.class));
		
		helper.bindTerminal(any(String.class));
	}
	
	@Test(expected=JsonException.class)
	public void bindTerminalFailed_wrongTerminalFormatJsonThrowJsonException() throws TerminalSchemaValidatorException {
		doReturn("anyone_value").when(helper).mountTerminalJson(any(String.class));
		doNothing().when(helper).validateTerminal(any(String.class));

		helper.bindTerminal(any(String.class));
		verify(helper, times(1)).bindTerminal(any(String.class));
	}
	
	@Test
	public void mountTerminalJsonSuccessful() {
		helper.mountTerminalJson("44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN");
		verify(helper, times(1)).mountTerminalJson(any(String.class));
	}
	
	@Test(expected=TerminalInputException.class)
	public void mountTerminalJsonFailed_invalidInputthrowTerminalInputException() {
		helper.mountTerminalJson("44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN;more");
	}
	
	@Test
	public void validateTerminalSuccessful() throws TerminalSchemaValidatorException {
		helper.validateTerminal(getValidJson());
		verify(helper, times(1)).validateTerminal(any(String.class));
	}
	
	@Test(expected=SchemaValidationException.class)
	public void validateTerminalFailed_loadSchemaThrowSchemaValidationException() throws TerminalSchemaValidatorException {
		doThrow(new SchemaValidationException()).when(helper).loadSchema();
		helper.validateTerminal(any(String.class));
	}
	
	@Test(expected=JsonException.class)
	public void validateTerminalFailed_loadSchemaThrowJsonException() throws TerminalSchemaValidatorException {
		doThrow(new JsonException()).when(helper).loadSchema();
		helper.validateTerminal(any(String.class));
	}
	
	@Test(expected=TerminalSchemaValidatorException.class)
	public void validateTerminalFailed_invalidInputThowTerminalSchemaValidatorException() throws TerminalSchemaValidatorException {
		helper.validateTerminal(getInvalidJson());
	}
	
	@Test(expected=JsonException.class)
	public void validateTerminalFailed_wrongTerminalFormatJsonThowJsonException() throws TerminalSchemaValidatorException {
		helper.validateTerminal("anyone_value");
	}
	
	@Test
	public void loadSchemaSuccessful() {
		helper.loadSchema();
		verify(helper, times(1)).loadSchema();
	}
	
	@Test(expected=SchemaValidationException.class)
	public void loadSchemaFailed_throwSchemaValidationException() {
		doThrow(new SchemaValidationException()).when(helper).loadSchema();
		helper.loadSchema();
	}
	
	@Test(expected=JsonException.class)
	public void loadSchemaFailed_throwJsonException() {
		doThrow(new JsonException()).when(helper).loadSchema();
		helper.loadSchema();
	}

	private String getValidJson() {
		return "{\"logic\": 44332211, \"serial\": \"123\","
				+ "\"model\": \"PWWIN\",\"sam\": 0,\"ptid\": \"F04A2E4088B\","
				+ "\"plat\": 4,\"version\": \"8.00b3\","
				+ "\"mxr\": 0,\"mxf\": 16777216,\"VERFM\": \"PWWIN\"}";
	}
	
	private String getInvalidJson() {
		return "{\"logic\": \"443a32211\", \"serial\": \"123\","
				+ "\"model\": \"PWWIN\",\"sam\": 0,\"ptid\": \"F04A2E4088B\","
				+ "\"plat\": 4,\"version\": \"8.00b3\","
				+ "\"mxr\": 0,\"mxf\": 16777216,\"VERFM\": \"PWWIN\"}";
	}

}
