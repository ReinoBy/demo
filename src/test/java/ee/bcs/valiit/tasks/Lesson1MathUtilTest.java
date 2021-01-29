package ee.bcs.valiit.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.AssertionErrors;

public class Lesson1MathUtilTest {

    @Test
    void min(){
        Assertions.assertEquals(3, Lesson1MathUtil.min(3,4));
        Assertions.assertEquals(-4, Lesson1MathUtil.min(3,-4));
    }

    @Test
    void max() {
        Assertions.assertEquals(4, Lesson1MathUtil.max(3,4));
        Assertions.assertEquals(3, Lesson1MathUtil.max(3,-4));
    }

    @Test
    void abs() {
        Assertions.assertEquals(4, Lesson1MathUtil.abs(-4));
        Assertions.assertEquals(0, Lesson1MathUtil.abs(0));
        Assertions.assertEquals(7, Lesson1MathUtil.abs(7));

    }

    @Test
    void isEven() {
        Assertions.assertTrue(Lesson1MathUtil.isEven(8));
        Assertions.assertFalse(Lesson1MathUtil.isEven(7));
    }

    @Test
    void testMin() {
        Assertions.assertEquals(3, Lesson1MathUtil.min(3,4,7));
        Assertions.assertEquals(-4, Lesson1MathUtil.min(3,-4,5));
        Assertions.assertEquals(2, Lesson1MathUtil.min(3,4,2));
        Assertions.assertEquals(-7, Lesson1MathUtil.min(3,-4,-7));

    }

    @Test
    void testMax() {
        Assertions.assertEquals(7, Lesson1MathUtil.max(3,4,7));
        Assertions.assertEquals(5, Lesson1MathUtil.max(3,-4,5));
        Assertions.assertEquals(4, Lesson1MathUtil.max(3,4,2));
        Assertions.assertEquals(3, Lesson1MathUtil.max(3,-4,-7));
    }
}
