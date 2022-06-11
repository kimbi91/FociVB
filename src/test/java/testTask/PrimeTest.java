package testTask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeTest {

    @Test
    public void isPrimeTest() {
        assertTrue(Prime.isPrime(2));
        assertTrue(Prime.isPrime(-1));
        assertTrue(Prime.isPrime(47));
        assertTrue(Prime.isPrime(Integer.MAX_VALUE));

        assertFalse(Prime.isPrime(38));
        assertFalse(Prime.isPrime(100));
        assertFalse(Prime.isPrime(60));
    }

}