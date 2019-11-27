package ch.noseryoung.plj.primetime.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/prime")
public class PrimeController {
  private PrimeService primeService;

  @Autowired
  public PrimeController(PrimeService primeService) {
    this.primeService = primeService;
  }

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return new ResponseEntity<>("Hello", HttpStatus.OK);
  }

  @GetMapping("/{number}")
  public ResponseEntity<String> isPrime(@PathVariable Integer number) {
    String message = "";
    if (primeService.isPrime(number)) {
      message = number + " is a prime number";
    } else message = number + " is not a prime number";

    return new ResponseEntity<>(message, HttpStatus.OK);
  }

  @GetMapping("/getPrimes")
  public ResponseEntity<List<Integer>> getPrimes(@PathParam("maxNumber") Integer maxNumber) {
    return new ResponseEntity<>(primeService.getPrimes(maxNumber), HttpStatus.OK);
  }

  @GetMapping("/getPrimesWithMinimum")
  public ResponseEntity<List<Integer>> getPrimes(@PathParam("minNumber") Integer minNumber, @PathParam("maxNumber") Integer maxNumber) {
    return new ResponseEntity<>(primeService.getPrimes(minNumber, maxNumber), HttpStatus.OK);
  }

  @GetMapping("/getFibonacciSequence")
  public ResponseEntity<List<Integer>> getFibonacciSequence(@PathParam("limit") Integer limit) {
    return new ResponseEntity<>(primeService.getFibonacciSequence(limit), HttpStatus.OK);
  }
}
