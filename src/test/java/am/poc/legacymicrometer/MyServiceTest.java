package am.poc.legacymicrometer;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MyServiceTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void given_when_then() throws Exception {
        final MvcResult mvcResultRoot = mvc.perform(MockMvcRequestBuilders
                .get("/xxx"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResultRoot.getResponse().getContentAsString());

        final MvcResult mvcResultPrometheus = mvc.perform(MockMvcRequestBuilders
                .get("/prometheus"))
                .andExpect(status().isOk())
                .andReturn();

        String strMvcResultPrometheus= mvcResultPrometheus.getResponse().getContentAsString();
        System.out.println(strMvcResultPrometheus);

        Assertions.assertThat(strMvcResultPrometheus).contains("calculateYyy");
    }
}