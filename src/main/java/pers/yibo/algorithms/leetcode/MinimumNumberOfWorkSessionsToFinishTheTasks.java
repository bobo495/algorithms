package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 1986. 完成任务的最少工作时间段
 * <p>
 * https://leetcode-cn.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks
 * <p>
 * 你需要按照如下条件完成给定任务：
 * <p>
 * 如果你在某一个时间段开始一个任务，你需要在 同一个时间段完成它。
 * 完成一个任务后，你可以 立马开始一个新的任务。
 * 你可以按 任意顺序完成任务。
 *
 * @author yibo
 * @date 2021-10-25 16:50
 **/
public class MinimumNumberOfWorkSessionsToFinishTheTasks {

    public int minSessions(int[] tasks, int sessionTime) {
        int sum = 0;
        for (int task : tasks) {
            sum += task;
        }

        int minSize = sum / sessionTime;

        List<Integer> list = new ArrayList<>();
        while (minSize > 0) {
            list.add(0);
            minSize--;
        }
        if (sum % sessionTime != 0) {
            list.add(0);
        }

        return addOne(tasks, 0, sessionTime, list, Integer.MAX_VALUE);
    }

    public int addOne(int[] tasks, int index, int sessionTime, List<Integer> list, int minSession) {
        if (index == tasks.length) {
            System.out.println(list);
            return list.size();
        }

        HashSet<Integer> set = new HashSet<>();
        boolean isAdd = false;
        for (int i = 0; i < list.size(); i++) {
            List<Integer> tmpList = new ArrayList<>(list);
            int tmp = list.get(i) + tasks[index];
            if (tmp <= sessionTime && !set.contains(list.get(i))) {
                tmpList.set(i, tmp);
                minSession = Math.min(minSession,
                        addOne(tasks, index + 1, sessionTime, tmpList, minSession));
                isAdd = true;
                set.add(list.get(i));
            }
        }

        if (!isAdd) {
            list.add(tasks[index]);
            minSession = Math.min(minSession,
                    addOne(tasks, index + 1, sessionTime, list, minSession));
        }
        return minSession;
    }
}
