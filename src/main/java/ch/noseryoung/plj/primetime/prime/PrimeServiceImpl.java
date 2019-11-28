package ch.noseryoung.plj.primetime.prime;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrimeServiceImpl implements PrimeService {

  private int checkPrime(int number) {
    if (number <= 1) {
      return 2;
    }
    for (int i = 2; i < number; i++) {
      if (number % i == 0) {
        return 0;
      }
    }
    return number;
  }

  @Override
  public boolean isPrime(int number) {
    return checkPrime(number) == number;
  }

  @Override
  public List<Integer> getPrimes(int maxNumber) {
    return getPrimes(2, maxNumber);
  }

  @Override
  public List<Integer> getPrimes(int minNumber, int maxNumber) {
    List<Integer> primes = new ArrayList<>();
    if (minNumber > maxNumber || minNumber == maxNumber || minNumber < 0) {
      return null;
    }

    for (int i = minNumber; i <= maxNumber; i++) {
      if (isPrime(i)) primes.add(i);
    }
    return primes;
  }

  @Override
  public List<Integer> getFibonacciSequence(int limit) {
    if (limit < 0) return null;

    List<Integer> numbers = new ArrayList<>();
    int tempNumber = 1;
    int tempNumber1 = 0;
    for (int i = 0; i < limit; i = tempNumber) {
      numbers.add(i);
      tempNumber += tempNumber1;
      tempNumber1 = i;
    }
    return numbers;
    //Stream.iterate(new long[]{0, 1}, p -> new long[]{p[1]})
  }
}
