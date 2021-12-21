package pers.yibo.algorithms.string;

import pers.yibo.algorithms.fundamentals.Bag;
import pers.yibo.algorithms.graph.DepthFirstSearch;
import pers.yibo.algorithms.graph.DirectedGraph;

import java.util.Stack;

/**
 * 非确定有限状态自动机 - NFA
 * <p>
 * 仅包括字符： * | ( ) .
 * <p>
 * 正则表达式基本操作：
 * <p>
 * 1. 连接操作：例如AB，表示A连接B
 * <p>
 * 2.或操作：'|'表示，例如A|B，表示A 或 B。连接操作优先级高于或操作，AB|CD 表示 AB 或 CD。
 * <p>
 * 3. 闭包操作：'*'表示，闭包操作优先级高于连接操作。AB* 表示 A AB ABB ABBB ...
 *
 * @author yibo
 * @date 2021-12-21 17:10
 * @see <a href="https://zh.wikipedia.org/wiki/非确定有限状态自动机">Wiki: 非确定有限状态自动机</a>
 **/
public class NondeterministicFiniteAutomaton {
    /**
     * 构建有向图
     * <p>
     * '*' : 增加'*'到前一个字符的双向边
     * '|' : 增加'|'到下一个右括号和上一个左括号到下一个字符的边
     * '()*': 增加该字符到下一个字符的边
     */
    private final DirectedGraph graph;

    /**
     * 正则表达式字符串
     */
    private final String regexp;

    /**
     * regexp长度
     */
    private final int m;

    public NondeterministicFiniteAutomaton(String regexp) {
        this.regexp = regexp;
        this.m = regexp.length();

        Stack<Integer> operators = new Stack<>();
        // 初始化有向图，节点+1表示接受状态
        this.graph = new DirectedGraph(m + 1);

        // 初始化graph
        for (int i = 0; i < m; i++) {
            int lp = i;

            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|') {
                operators.add(i);
            }

            if (regexp.charAt(i) == ')') {
                int or = operators.pop();

                // 或操作
                if (regexp.charAt(or) == '|') {
                    lp = operators.pop();
                    graph.addEdge(lp, or + 1);
                    graph.addEdge(or, i);
                }

                if (regexp.charAt(or) == '(') {
                    lp = or;
                }
            }

            // 匹配*则增加双向边
            if (i < m - 1 && regexp.charAt(i + 1) == '*') {
                graph.addEdge(lp, i + 1);
                graph.addEdge(i + 1, lp);
            }

            // 特殊符号则增加向右的单向边
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')') {
                graph.addEdge(i, i + 1);
            }
        }

        if (operators.size() > 0) {
            throw new IllegalArgumentException("Invalid regular expression");
        }
    }

    public boolean recognizes(String text) {
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        Bag<Integer> pc = new Bag<>();
        for (int v = 0; v < graph.getVertices(); v++) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }

        for (int i = 0; i < text.length(); i++) {
            // text中不能包含正则表达式元字符
            if (text.charAt(i) == '*' || text.charAt(i) == '|'
                    || text.charAt(i) == '(' || text.charAt(i) == ')') {
                throw new IllegalArgumentException("text contains the metacharacter '" + text.charAt(i) + "'");
            }

            Bag<Integer> match = new Bag<>();
            for (int v : pc) {
                if (v == m) {
                    continue;
                }
                if (regexp.charAt(v) == text.charAt(i) || regexp.charAt(v) == '.') {
                    match.add(v + 1);
                }
            }
            dfs = new DepthFirstSearch(graph, match);
            pc = new Bag<>();
            for (int v = 0; v < graph.getVertices(); v++) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }

            if (pc.size() == 0) {
                return false;
            }
        }

        for (int v : pc) {
            if (v == m) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        NondeterministicFiniteAutomaton nfa = new NondeterministicFiniteAutomaton("(A*B|AC)D");
        System.out.println(nfa.recognizes("AAAABD"));
        System.out.println(nfa.recognizes("AAAAC"));

        NondeterministicFiniteAutomaton nfa2 = new NondeterministicFiniteAutomaton("(a|(bc)*d)*");
        System.out.println(nfa2.recognizes("abcbcd"));
        System.out.println(nfa2.recognizes("abcbcbcdaaaabcbcdaaaddd"));

    }
}
