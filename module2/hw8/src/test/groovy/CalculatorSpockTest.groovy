import spock.lang.Specification

class CalculatorSpockTest extends Specification {

    def "two plus two should equal four"() {
        given:
        def num1 = new Calculator(2)
        def num2 = new Calculator(2)

        when:
        def result = num1 + num2
        println(num1 + num2)

        then:
        assert result.number == 4
    }

    def "four minus two should equal two"() {
        given:
        def num1 = new Calculator(4)
        def num2 = new Calculator(2)

        when:
        def result = num1 - num2

        then:
        assert result.number == 2
    }

    def "four minus one should equal three"() {
        given:
        def calculator = new Calculator()

        when:
        def result = calculator.calculate('4-1')

        then:
        assert result == 3
    }

    def "5+(3*1) should equal 8"() {
        given:
        def calculator = new Calculator()

        when:
        def result = calculator.calculate("5+(3*1)")

        then:
        assert result == 8
    }

    def "5-(4/2) should equal 3"() {
        given:
        def calculator = new Calculator()

        when:
        def result = calculator.calculate("5-(4/2)")

        then:
        assert result == 3
    }

    def "5*4-5 should equal 15"() {
        given:
        def calculator = new Calculator()

        when:
        def result = calculator.calculate("5*4-5")

        then:
        assert result == 15
    }

}
