package br.com.muxi.exame.helpers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.exceptions.JsonException;
import br.com.muxi.exame.exceptions.SchemaValidationException;
import br.com.muxi.exame.exceptions.TerminalInputBuildException;
import br.com.muxi.exame.exceptions.TerminalInputException;
import br.com.muxi.exame.exceptions.TerminalSchemaValidatorException;
import br.com.muxi.exame.vo.TerminalInputVO;
import br.com.muxi.exame.vo.errors.FailedValidationErrorsVO;
import br.com.muxi.exame.vo.errors.FailedValidationTerminalOutputVO;

@Component
public class TerminalHLP {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Resource schemaValidator;

    private JsonSchema schema;

    private final Pattern pattern = Pattern.compile(";");

    public TerminalHLP(@Value(value = "classpath:terminal-schema-validator.json") final Resource schemaValidator) {
        super();
        this.schemaValidator = schemaValidator;
    }

    public Terminal bindTerminal(final String terminalStr) throws TerminalSchemaValidatorException {
        final String terminalCompoused = mountTerminalJson(terminalStr);
        validateTerminal(terminalCompoused);
        try {
            return new ObjectMapper().readValue(terminalCompoused, Terminal.class);
        } catch (IOException e) {
            log.error("Fail on bind the json to Terminal object", e);
            throw new JsonException("Occured a problem on json bind");
        }
    }

    protected String mountTerminalJson(final String terminal) {
        final String[] fieldValues = pattern.split(terminal);
        try {
            final TerminalInputVO vo = TerminalInputVO.build(fieldValues);
            return new ObjectMapper().writeValueAsString(vo);
        } catch (TerminalInputBuildException | JsonProcessingException e) {
            log.error("invalid input format", e);
            throw new TerminalInputException("invalid input format");
        }

    }

    protected void validateTerminal(final String terminal) throws TerminalSchemaValidatorException {
        loadSchema();
        try {
            final JsonNode actualObj = new ObjectMapper().readTree(terminal);
            final ProcessingReport report = schema.validate(actualObj);
            if (!report.isSuccess()) {
                final List<FailedValidationTerminalOutputVO> problemList = new ArrayList<>();
                final Iterator<ProcessingMessage> itProcessingMessage = report.iterator();
                while (itProcessingMessage.hasNext()) {
                    final JsonNode node = itProcessingMessage.next().asJson();
                    final String nodeMessageText = node.get("message") != null ? node.get("message").asText() : "";
                    final JsonNode nodeInstance = node.get("instance") != null ? node.get("instance") : null;
                    final String nomeInstancePointerText = nodeInstance != null && nodeInstance.get("pointer") != null
                            ? nodeInstance.get("pointer").asText()
                            : "";
                    final FailedValidationTerminalOutputVO item = new FailedValidationTerminalOutputVO(nomeInstancePointerText,
                            nodeMessageText);
                    problemList.add(item);
                    final String textFailValidation = String.format("Fail validation Json: %s, Error: %s", terminal, nodeMessageText);
                    log.error(textFailValidation);
                }
                throw new TerminalSchemaValidatorException(new FailedValidationErrorsVO(problemList));
            }
        } catch (ProcessingException e) {
            log.error("Fail on use schema validator", e);
            throw new SchemaValidationException("Occured a problem using schema validatior");
        } catch (IOException e) {
            log.error("Fail on load/read the json parsed", e);
            throw new JsonException("Occured a problem on json parsed");
        }
    }

    protected void loadSchema() {
        if (schema == null) {
            try {
                JsonNode jsonNodeValidator = null;
                final Reader schemaValidatorReader = new InputStreamReader(schemaValidator.getInputStream());
                try {
                    jsonNodeValidator = JsonLoader.fromReader(schemaValidatorReader);
                } finally {
                    schemaValidatorReader.close();
                }
                schema = JsonSchemaFactory.byDefault().getJsonSchema(jsonNodeValidator);
            } catch (ProcessingException e) {
                log.error("Fail on bind schema validator", e);
                throw new SchemaValidationException("Occured a problem binding schema validatior");
            } catch (IOException e) {
                log.error("Fail on load/read the schema validator", e);
                throw new JsonException("Occured a problem on load/read schema validator");
            }
        }
    }

}
