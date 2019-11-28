package ch.noseryoung.plj.primetime.prime;

import java.util.List;

public interface PrimeService {
  boolean isPrime(int number);

  List<Integer> getPrimes(int maxNumber);

  List<Integer> getPrimes(int minNumber, int maxNumber);

  List<Integer> getFibonacciSequence(int limit);
}
