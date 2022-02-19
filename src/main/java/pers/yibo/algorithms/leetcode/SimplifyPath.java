package pers.yibo.algorithms.leetcode;

/**
 * 71. 简化路径
 * <p>
 * https://leetcode-cn.com/problems/simplify-path/
 *
 * @author yibo
 * @date 2022-01-06 09:02
 **/
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] paths = path.split("/", -1);
        String[] stack = new String[paths.length];
        int stackIndex = -1;

        for (String p : paths) {
            if (p.equals(".") || p.length() == 0) {
                continue;
            }
            if (p.equals("..")) {
                stackIndex = (stackIndex < 0) ? -1 : stackIndex - 1;
                continue;
            }
            stack[++stackIndex] = p;
        }

        System.out.println(stackIndex);

        if (stackIndex == -1) {
            return "/";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= stackIndex; i++) {
            builder.append('/').append(stack[i]);
        }
        return builder.toString();
    }
}
