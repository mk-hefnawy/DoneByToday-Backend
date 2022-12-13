package com.hefnawy.DoneByToday.todo_feature_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TodoControllerTest {

    private static final String BASE_URL = "http://localhost:8090/";
    TestRestTemplate restTemplate = new TestRestTemplate();
    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_Test(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BASE_URL + "todo/test/", String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo("Test String");
    }

    @Test
    public void test_WithMockMVC_Test() throws Exception{
        mockMvc.perform(get(BASE_URL + "todo/test/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Test String"));
    }
}