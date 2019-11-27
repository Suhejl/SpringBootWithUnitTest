package ch.noseryoung.plj.primetime.primary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class PrimeServiceImplTest {

  @InjectMocks
  PrimeServiceImpl primeServiceImpl = new PrimeServiceImpl();

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void isPrime_request11_expectTrue() {
    boolean isPrime = primeServiceImpl.isPrime(11);
    Assertions.assertThat(isPrime).isEqualTo(true);
  }

  @Test
  void isPrime_request0_expectFalse() {
    boolean isPrime = primeServiceImpl.isPrime(0);
    Assertions.assertThat(isPrime).isEqualTo(false);
  }

  @Test
  void getPrimes_requestMaxNumber5_expect3Primes() {
    List<Integer> collectionOfPrimes = primeServiceImpl.getPrimes(5);
    Assertions.assertThat(collectionOfPrimes).isNotEmpty().hasSize(3).startsWith(2).endsWith(5).containsExactly(2, 3, 5);
  }

  @Test
  void getPrimes_requestMaxNumberNeg1_expectNull() {
    List<Integer> collectionOfPrimes = primeServiceImpl.getPrimes(-1);
    Assertions.assertThat(collectionOfPrimes).isNull();
  }

  @Test
  void getPrimes_requestMinNumber5AndMaxNumber20_expect6Primes() {
    List<Integer> collectionOfPrimes = primeServiceImpl.getPrimes(5, 20);
    Assertions.assertThat(collectionOfPrimes).isNotEmpty().hasSize(6).containsExactly(5, 7, 11, 13, 17, 19);
  }

  @Test
  void getPrimes_requestMinNumberNeg1AndMaxNumber20_expectNull() {
    List<Integer> collectionOfPrimes = primeServiceImpl.getPrimes(-1, 20);
    Assertions.assertThat(collectionOfPrimes).isNull();
  }

  @Test
  void getFibonacciSequence_requestLimit20_expect8Numbers() {
    List<Integer> collectionOfFibonacciSequenceNumbers = primeServiceImpl.getFibonacciSequence(20);
    Assertions.assertThat(collectionOfFibonacciSequenceNumbers).isNotEmpty().hasSize(8).containsExactly(0, 1, 1, 2, 3, 5, 8, 13);
  }

  @Test
  void getFibonacciSequence_requestLimitNeg1_expectNull() {
    List<Integer> collectionOfFibonacciSequenceNumbers = primeServiceImpl.getFibonacciSequence(-1);
    Assertions.assertThat(collectionOfFibonacciSequenceNumbers).isNull();
  }
}