class Calculator {

    static final char PLUS = '+'
    static final char MINUS = '-'
    static final char MULTIPLY = '*'
    static final char DIVIDE = '/'

    int number

    Calculator() {}

    Calculator(int number) {
        this.number = number
    }

    Calculator plus(Calculator other) {
        return new Calculator(this.number + other.number)
    }

    Calculator minus(Calculator other) {
        return new Calculator(this.number - other.number)
    }

    def calculate(String expression) {
        LinkedList<Integer> numbers = new LinkedList<>()
        LinkedList<Character> operations = new LinkedList<>()

        for (int i = 0; i < expression.length(); i++) {
            char sign = expression.&charAt(i)

            if (sign == '(') {
                operations.add('(' as char)
            } else if (sign == ')') {
                while (operations.getLast() != '(') {
                    processOperator(numbers, operations.removeLast())
                }
                operations.removeLast()
            } else if (isOperator(sign)) {
                while (!operations.isEmpty() && priority(operations.getLast()) >= priority(sign)) {
                    processOperator(numbers, operations.removeLast())
                }
                operations.add(sign)
            } else {
                StringBuilder number = new StringBuilder()
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number.append(expression.charAt(i++))
                }
                i--
                numbers.add(number.toInteger())
            }
        }

        while (!operations.isEmpty()) {
            processOperator(numbers, operations.removeLast())
        }

        def result = { "Result - [ ${it} ]" }
        println result(numbers.get(0) ?: '~')

        numbers.get(0)
    }

    private static boolean isOperator(char operation) {
        return [PLUS, MINUS, MULTIPLY, DIVIDE].contains(operation)
    }

    private static int priority(char operation) {

        switch (operation) {
            case PLUS:
            case MINUS:
                return 1
            case MULTIPLY:
            case DIVIDE:
                return 2
            default:
                return -1
        }
    }

    private static void processOperator(LinkedList<Integer> list, char operation) {
        def num1 = list.removeLast()
        def num2 = list.removeLast()

        switch (operation) {
            case PLUS:
                list.add(num2 + num1)
                break
            case MINUS:
                list.add(num2 - num1)
                break
            case MULTIPLY:
                list.add(num2 * num1)
                break
            case DIVIDE:
                list.add(num2 / num1)
                break
        }

    }

    @Override
    public String toString() {
        return """
result = $number
               """
    }

}
