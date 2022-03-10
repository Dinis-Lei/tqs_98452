/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        // todo: instantiate a dip passing valid or invalid arrays
        int[] validNumbers = {1, 10, 37, 45, 46};
        int[] validStars = {6, 9};
        int[] invalidNumbers = {1, 10, 37};
        int[] invalidStars = {1, 3, 15};

        assertDoesNotThrow(() -> {new Dip(validNumbers, validStars);});
        assertThrows( IllegalArgumentException.class, () -> {new Dip(invalidNumbers, validStars);});
        assertThrows( IllegalArgumentException.class, () -> {new Dip(validNumbers, invalidStars);});
        assertThrows( IllegalArgumentException.class, () -> {new Dip(invalidNumbers, invalidStars);});

    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

}
