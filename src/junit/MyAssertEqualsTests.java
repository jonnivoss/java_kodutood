package junit;

import org.junit.Test;

import java.util.Arrays;

public class MyAssertEqualsTests {

    @Test
    public void testsPrimitiveEqualitySuccess() {
        myAssertEquals(1, 1);
    }

    @Test(expected = AssertionError.class)
    public void testsPrimitiveEqualityFailure() {
        myAssertEquals(1, 2);
    }

    @Test
    public void testsObjectEqualitySuccess() {
        myAssertEquals(200L, 200L);
    }

    @Test(expected = AssertionError.class)
    public void testsObjectEqualityFailure() {
        myAssertEquals(100L, 200L);
    }

    @Test
    public void testsArrayEqualitySuccess() {
        int[] a = {1, 2};
        int[] b = {1, 2};

        myAssertEquals(a, b);
    }

    @Test(expected = AssertionError.class)
    public void testsArrayEqualityFailure1() {
        int[] a = {1, 2};
        int[] b = {1};

        myAssertEquals(a, b);
    }

    @Test(expected = AssertionError.class)
    public void testsArrayEqualityFailure2() {
        int[] a = {};
        int[] b = {1};

        myAssertEquals(a, b);
    }

    @Test(expected = AssertionError.class)
    public void testsArrayEqualityFailure3() {
        int[] a = {1, 2};
        int[] b = {1, 3};

        myAssertEquals(a, b);
    }



    public void myAssertEquals(int expected, int actual) {
        if (expected != actual) {
            String message = "%s is not equal to %s"
                    .formatted(actual, expected);
            throw new AssertionError(message);
        }
    }

    public void myAssertEquals(Long expected, Long actual) {
        if(!expected.equals(actual)){
            String message = "%s is not equal to %s"
                    .formatted(actual, expected);
            throw new AssertionError(message);
        }
    }

    public void myAssertEquals(int[] expected, int[] actual) {
        String message = "%s is not equal to %s"
                .formatted(actual, expected);
        if(expected.length == 0 || actual.length == 0){
            throw new AssertionError(message);
        }
        if(expected.length != actual.length){
            throw new AssertionError(message);
        }
        for (int i = 0; i < actual.length; i++) {
            if(actual[i] != expected[i]){
                throw new AssertionError(message);
            }
        }
    }
}
