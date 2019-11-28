package ch.noseryoung.plj.primetime.prime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PrimeControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PrimeService primeService;

  @BeforeEach
  void setUp() {
    given(primeService.isPrime(11)).willReturn(true);
    given(primeService.isPrime(6)).willReturn(false);


    given(primeService.getPrimes(-1)).willReturn(null);
    given(primeService.getPrimes(0)).willReturn(null);
    given(primeService.getPrimes(1)).willReturn(null);
    given(primeService.getPrimes(11)).willReturn(Arrays.asList(2, 3, 5, 7, 11));

    given(primeService.getPrimes(-1, 20)).willReturn(null);
    given(primeService.getPrimes(0, 20)).willReturn(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
    given(primeService.getPrimes(1, 20)).willReturn(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
    given(primeService.getPrimes(11, 20)).willReturn(Arrays.asList(11, 13, 17, 19));
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void sayHello() {
  }

  @Test
  void isPrime_request11_expectTrue() throws Exception {
    mvc.perform(
        MockMvcRequestBuilders.get("/prime/{number}", 11)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("true")
        );
    verify(primeService, times(1)).isPrime(11);
  }

  @Test
  void getPrimes_request11_expect5Primes() throws Exception{
    mvc.perform(
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
    verify(primeService, times(1)).getPrimes(11);

  }

  @Test
  void getPrimes_requestNeg1_expectNull() throws Exception {
    mvc.perform(
        MockMvcRequestBuilders.get("/prime/getPrimes/{maxNumber}", -1)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("")
        );

    verify(primeService, times(1)).getPrimes(-1);
  }

  @Test
  void getFibonacciSequence() {
  }
}