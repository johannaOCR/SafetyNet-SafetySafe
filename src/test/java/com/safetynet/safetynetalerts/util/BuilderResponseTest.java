package com.safetynet.safetynetalerts.util;

import com.safetynet.safetynetalerts.Util.BuilderResponse;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BuilderResponseTest {
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("BuilderResponseTest") ;
    private final BuilderResponse builderResponseUnderTest= new BuilderResponse();

    @Test
    public void testCustomResponse() {
        logger.info("testCustomResponse()");
        String obj =  "test";
        final ResponseEntity<?> result = builderResponseUnderTest.customResponse(obj);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCustomResponseNull() {
        logger.info("testCustomResponseNull()");
        final ResponseEntity<?> result = builderResponseUnderTest.customResponse(null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testResponseBooleanTrue() {
        logger.info("testResponseBooleanTrue()");
        final ResponseEntity<?> result = builderResponseUnderTest.responseBoolean(true);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testResponseBoolean() {
        logger.info("testResponseBoolean()");
        final ResponseEntity<?> result = builderResponseUnderTest.responseBoolean(false);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
