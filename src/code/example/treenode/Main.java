package code.example.treenode;

import static java.lang.System.*;

public class Main {

    // Main java runner class to execute the code
    public static void main(String[] args) throws Exception {
        String infixExpression = "2*3/(2-1)+5*(4-1)";
        out.println("Infix expression for start:");
        out.println(infixExpression + "\n");

        String postfixExpression = Converter.infixToPostfix(infixExpression);
        out.println("Postfix expression is:");
        out.println(postfixExpression + "\n");

        TreeNode root = TreeNode.buildBinaryExpressionTree(postfixExpression);
        out.println("Infix expression is:");
        TreeNode.inOrder(root);
    }
} 