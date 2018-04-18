import org.junit.Test

class CalculatorTest extends GroovyTestCase {

    def calculator = new Calculator()

    @Test
    void testCalculateOverloadingPlus() {
        def num1 = new Calculator(2)
        def num2 = new Calculator(2)

        def result = num1 + num2
        println(num1 + num2)

        assertEquals(4, result.number)
    }

    @Test
    void testCalculateOverloadingMinus() {
        def num1 = new Calculator(6)
        def num2 = new Calculator(2)

        def result = num1 - num2
        println(num1 - num2)

        assertEquals(4, result.number)
    }

    @Test
    void testCalculateWithTwoParams() {
        assertEquals(6, calculator.calculate("4+2"))
        assertEquals(2, calculator.calculate("4-2"))
        assertEquals(8, calculator.calculate("4*2"))
        assertEquals(2, calculator.calculate("4/2"))
    }

    @Test
    void testCalculateWithUnlimitedParams() {
        assertEquals(6, calculator.calculate("86-30-50"))
        assertEquals(6, calculator.calculate("86-30-50+50-25-25"))
        assertEquals(10, calculator.calculate("8+4-2"))
        assertEquals(16, calculator.calculate("8*4/2"))
        assertEquals(10, calculator.calculate("8+4+1+1-2-2"))
        assertEquals(8, calculator.calculate("8+4+2*3-10"))
    }

    @Test
    void testCalculateWithBrackets() {
        assertEquals(10, calculator.calculate("(8-3)*2"))
        assertEquals(10, calculator.calculate("8+(4-2)"))
        assertEquals(6, calculator.calculate("2+2*(5-3)"))
        assertEquals(17, calculator.calculate("23-10+2*(5-3)"))
        assertEquals(15, calculator.calculate("2+2*(5-3+3)+3"))
    }

}
