package pers.yibo.algorithms.fundamentals;

import java.util.NoSuchElementException;

/**
 * 算术表达式求值
 *
 * @author yibo
 * @date 2021-07-29 11:47
 **/
@SuppressWarnings("AlibabaAvoidComplexCondition")
public class Evaluate {
    private static final String ADD_OPERATOR = "+";
    private static final String MINUS_OPERATOR = "-";
    private static final String MULTIPLY_OPERATOR = "*";
    private static final String DIVIDE_OPERATOR = "/";
    private static final String SQRT_OPERATOR = "sqrt";

    /**
     * Dijkstra双栈方法
     *
     * 使用场景：
     *  1. 表达式最外层包含括号
     *  2. 括号内最多包含两个元素
     *
     * @param str 算术表达式
     * @return 表达式结果
     */
    public static double evaluate(String str) {
        // 移除所有空字符，统一括号类型
        String strFormat = str
                .replaceAll("\\s+", "")
                .replaceAll("[\\[{]", "(")
                .replaceAll("[]}]", ")");

        Stack<String> operators = new Stack<>();
        Stack<Double> values = new Stack<>();

        StringBuilder word = new StringBuilder();
        boolean isNumber = false;
        for (int i = 0; i < strFormat.length(); i++) {
            if (strFormat.charAt(i) >= '0' && strFormat.charAt(i) <= '9' || strFormat.charAt(i) == '.') {
                if (!isNumber) {
                    // 如果当前为单词，则将数字添加至operator
                    word = addWord(operators, values, word, false);
                    isNumber = true;
                }
                // 如果是数字
                word.append(strFormat.charAt(i));
            } else if ((strFormat.charAt(i) >= 'a' && strFormat.charAt(i) <= 'z')
                    || (strFormat.charAt(i) >= 'A' && strFormat.charAt(i) <= 'Z')) {
                if (isNumber) {
                    // 如果当前为数字，则将数字添加至values
                    word = addWord(operators, values, word, true);
                    isNumber = false;
                }
                // 字母添加到word
                word.append(strFormat.charAt(i));
            } else if (strFormat.charAt(i) == '(') {
                // 左括号不做处理
                word = addWord(operators, values, word, isNumber);
            } else if (strFormat.charAt(i) == '+' || strFormat.charAt(i) == '-' || strFormat.charAt(i) == '*' || strFormat.charAt(i) == '/') {
                // 运算符，入栈operators
                operators.push(String.valueOf(strFormat.charAt(i)));
                // 左括号不做处理
                word = addWord(operators, values, word, isNumber);
            } else if (strFormat.charAt(i) == ')') {
                // 右括号，触发计算
                word = addWord(operators, values, word, isNumber);
                String operator = operators.pop();
                double value = values.pop();
                switch (operator) {
                    case ADD_OPERATOR:
                        value = values.pop() + value;
                        break;
                    case MINUS_OPERATOR:
                        value = values.pop() - value;
                        break;
                    case MULTIPLY_OPERATOR:
                        value = values.pop() * value;
                        break;
                    case DIVIDE_OPERATOR:
                        value = values.pop() / value;
                        break;
                    case SQRT_OPERATOR:
                        value = Math.sqrt(value);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported Operator: " + operator);
                }
                values.push(value);
            }
        }
        return values.pop();
    }

    private static StringBuilder addWord(Stack<String> operators, Stack<Double> values, StringBuilder word, boolean isNumber) {
        if (word.length() != 0) {
            // word非空，判断是否数字，数字添加到values，sqrt添加到operators
            if (isNumber) {
                values.push(Double.valueOf(word.toString()));
            } else {
                if (word.toString().equalsIgnoreCase(SQRT_OPERATOR)) {
                    operators.push(SQRT_OPERATOR);
                }
            }
        }
        return new StringBuilder();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("( 1 + ( ( 2 + 3) * ( 4 * 5 ) ) )"));
        System.out.println(evaluate("( ( 1 + sqrt ( 5.0 ) ) / 2.0 )"));
    }
}
