package pokapi;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.servlet.DispatcherServlet;
import pokapi.configuration.SwaggerConfig;
import pokapi.repository.EstTypeRepository;
import pokapi.repository.PokemonRepository;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pokapi.repository.TypeRepository;


import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(SwaggerConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SwaggerAPIIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PokemonRepository pokemonRepository;
    @MockBean
    private TypeRepository typeRepository;
    @MockBean
    private EstTypeRepository estTypeRepository;


    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "USER")
    public void swaggerJsonExists() throws Exception {
        String contentAsString = mockMvc
                .perform(MockMvcRequestBuilders.get("/v2/api-docs")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("group", "pokapi"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
        try (Writer writer = new FileWriter(new File("target/generated-sources/swagger.json"))) {
            IOUtils.write(contentAsString, writer);
        }
    }
}