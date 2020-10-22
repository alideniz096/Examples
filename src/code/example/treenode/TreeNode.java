package code.example.treenode;

// This class required for Question-2 - Tree structure with stack
public class TreeNode {
    private char value;
    private TreeNode left;
    private TreeNode right;

    //  Constructor for Question2.Homework.TreeNode
    public TreeNode(char item) {
        value = item;
        left = null;
        right = null;
    }

    //   Steps of inOrder method in Question2.Homework.TreeNode structure
    //   1. Traverse the left subtree, i.e., call Inorder(left-subtree)
    //   2. Visit the root.
    //   3. Traverse the right subtree, i.e., call Inorder(right-subtree)
    public static void inOrder(TreeNode tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.value + " ");
            inOrder(tree.right);
        }
    }

    // Utility function to check the character is operator or not
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Utility function to build binary expression tree for given postfixExpression
    public static TreeNode buildBinaryExpressionTree(String postfixExpression) throws Exception {
        char[] postfixCharArray = postfixExpression.toCharArray();

        MyStack<TreeNode> stack = new MyStack<>();
        TreeNode finalTree;
        TreeNode tempTree1;
        TreeNode tempTree2;

        // Traverse through every character of input expression
        for (char c : postfixCharArray) {

            // If the character is operand, simply push into stack
            if (!isOperator(c)) {
                finalTree = new TreeNode(c);
                stack.push(finalTree);
            }
            // Else the character is operator
            else {
                finalTree = new TreeNode(c);

                // Pop(remove) two top nodes
                tempTree1 = stack.pop();
                tempTree2 = stack.pop();

                //  Make them children
                finalTree.right = tempTree1;
                finalTree.left = tempTree2;

                // Add this subexpression to stack
                stack.push(finalTree);
            }
        }

        finalTree = stack.peek();
        stack.pop();

        return finalTree;
    }
}