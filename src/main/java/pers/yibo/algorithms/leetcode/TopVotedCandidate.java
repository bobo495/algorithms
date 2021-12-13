package pers.yibo.algorithms.leetcode;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

/**
 * 911. 在线选举
 * <p>
 * https://leetcode-cn.com/problems/online-election
 * <p>
 * 给你两个整数数组 persons 和 times 。在选举中，第i张票是在时刻为times[i]时投给候选人 persons[i]的。
 * <p>
 * 对于发生在时刻 t 的每个查询，需要找出在t 时刻在选举中领先的候选人的编号。
 * <p>
 * 在t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 * <p>
 * 实现 TopVotedCandidate 类：
 * <p>
 * TopVotedCandidate(int[] persons, int[] times) 使用persons 和 times 数组初始化对象。
 * int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 *
 * @author yibo
 * 2021/12/11 16:57
 */
public class TopVotedCandidate {

    /**
     * 时间为i时的选举人
     */
    int[] result;

    /**
     * 选举人对应的票数
     */
    int[] count;

    int[] times;

    /**
     * TreeSet
     */
    TreeSet<Person> treeSet;

    private static class Person {
        int id;
        int count;

        public Person(int id, int count) {
            this.id = id;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return id == person.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public TopVotedCandidate(int[] persons, int[] times) {
        this.result = new int[persons.length];
        this.count = new int[persons.length];
        this.treeSet = new TreeSet<>((o1, o2) -> Integer.compare(o2.count, o1.count));
        this.times = times;

        for (int i = 0; i < persons.length; i++) {
            count[persons[i]]++;
            if (!treeSet.isEmpty() && treeSet.first().count == count[persons[i]]) {
                treeSet.pollFirst();
            }
            treeSet.add(new Person(persons[i], count[persons[i]]));
            result[i] = treeSet.first().id;
        }
    }

    public int q(int t) {
        int l = 0;
        int r = times.length - 1;
        int index = (r + l) / 2;
        while (l <= r) {
            if (times[index] == t) {
                return result[index];
            }
            if (times[index] < t) {
                if (index == r) {
                    return result[r];
                }
                if (times[index + 1] > t) {
                    return result[index];
                }
                l = index + 1;
            } else {
                if (times[index - 1] < t) {
                    return result[index - 1];
                }
                r = index - 1;
            }
            index = (r + l) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] persons = new int[]{0, 0, 1, 1, 2};
        int[] times = new int[]{0, 67, 69, 74, 87};

        TopVotedCandidate t = new TopVotedCandidate(persons, times);
        System.out.println(Arrays.toString(t.result));
        System.out.println(t.q(100));

    }
}
