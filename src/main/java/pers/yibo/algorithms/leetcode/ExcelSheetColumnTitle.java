package pers.yibo.algorithms.leetcode;

/**
 * 168. Excel表列名称
 * https://leetcode-cn.com/problems/excel-sheet-column-title/
 *
 * @author yibo
 * @date 2021-09-16 15:56
 **/
public class ExcelSheetColumnTitle {

    public static String convertToTitle(int columnNumber) {
        StringBuilder out = new StringBuilder();
        while (columnNumber > 26) {
            char c = (char) (columnNumber % 26 + 'A' - 1);
            columnNumber = columnNumber / 26;
            if(c=='@') {
                c='Z';
                columnNumber-=1;
            }
            out.insert(0, c);
        }

        char c = (char) (columnNumber + 'A' - 1);
        out.insert(0, c);

        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(52));
    }
}
