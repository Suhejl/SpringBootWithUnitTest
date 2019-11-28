package ch.noseryoung.plj.primetime.prime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void isPrime_request11_expectTrue() throws Exception {
    this.mockMvc.perform(
        MockMvcRequestBuilders.get("/prime/{number}", 11)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("true")
        );
  }

  @Test
  public void getPrimes_request11_expect5Primes() throws Exception{
    this.mockMvc.perform(
        MockMvcRequestBuilders.get("/prime/getPrimes/{maxNumber}", 11)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0]", is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1]", is(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[2]", is(5)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[3]", is(7)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[4]", is(11))
        );
  }
}
