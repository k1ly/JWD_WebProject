package by.epamtc.lyskovkirill.restaurant.util.hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SHA256PasswordHashTest {
    private static SHA256PasswordHash passwordHash;

    @BeforeEach
    void init() {
        passwordHash = SHA256PasswordHash.getInstance();
    }

    @Test
    void testComputeHashPasswordNotNull() {
        byte[] pswHash = passwordHash.computeHash("Password1234".getBytes());
        assertNotNull(pswHash);
    }

    @Test
    void testSamePasswordHashShouldBeEqual() {
        byte[] pswHash = passwordHash.computeHash("Password1234".getBytes());
        String testPsw = new String("Password1234");
        byte[] testPswHash = passwordHash.computeHash(testPsw.getBytes());
        assertArrayEquals(pswHash, testPswHash);
    }

    @Test
    void testDifferentPasswordHashShouldNotBeEqual() {
        byte[] pswHash = passwordHash.computeHash("Password1234".getBytes());
        String testPsw = new String("wrong_password");
        byte[] testPswHash = passwordHash.computeHash(testPsw.getBytes());
        assertNotEquals(pswHash, testPswHash);
        assertFalse(Arrays.equals(pswHash, testPswHash));
    }
}
