package org.project.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.project.Configs.Config;
import org.project.Configs.WebConfig;
import org.project.Model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = Config.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class ServiceControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetService() throws Exception {
        int serviceID = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/service/{index}",serviceID)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUppdateService() throws Exception {
        Service service = new Service("Чай в номер",200,"принесем самый вкусный чай");
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/service")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(service)))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.name").value("Чай в номер"))
                .andDo(print());
    }

    @Test
    public void testSortServicePrice()throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/services")).andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(obj);
            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}