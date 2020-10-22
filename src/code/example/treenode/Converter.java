package code.example.treenode;


// This class required for Question-1 - Infix form to Postfix form converting / Using stack structure
public class Converter {
    private static String invalidExpression = "Invalid Expression";

    private Converter() {
    }

    public static String infixToPostfix(String infixExpression) throws Exception {
        StringBuilder result = new StringBuilder();
        MyStack<Character> stack = new MyStack<>();

        // For each character check all the conditions
        for (int i = 0; i < infixExpression.length(); i++) {
            char character = infixExpression.charAt(i);

            // Check the character is a letter or a digit
            if (Character.isLetterOrDigit(character)) {
                result.append(character);
            } else if (character == '(') {
                stack.push(character);
            } else if (character == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }

                if (!stack.isEmpty() && stack.peek() != '(') {
                    return invalidExpression;
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precision(character) <= precision(stack.peek())) {
                    if (stack.peek() == '(') {
                        return invalidExpression;
                    }
                    result.append(stack.pop());
                }
                stack.push(character);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return invalidExpression;
            }
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static int precision(char character) {
        if (character == '+' || character == '-') {
            return 1;
        } else if (character == '*' || character == '/') {
            return 2;
        } else if (character == '^') {
            return 3;
        }
        return -1;
    }
}
