public class MergeTimelines {
    public static void main(String args[] ) throws Exception {
        // 面经题 Interval
        // 3.1. 先热身：做一个 intersection of 2 intervals。例：[3, 6] & [4, 9] -> [4, 6]
        //    => mergeTimeline
        // 3.2. 真正的 Interval：
        //  [[-inf, 0, true], [0, 5, false], [5, inf, true]]
        //  [[-inf, 3, true], [3, 6, false], [6, inf, true]]
        // ->[[-inf, 0, true], [0, 3, false], [3, 5, false], [5, 6, false], [6, inf, true]]
        //    => mergeTimelinesSeparate
        // 3.3 把不同的 interval 但有相同 boolean 值的 merge 到一起
        // ->[[-inf, 0, true], [0, 6, false], [6, inf, true]]
        //    => mergeTimelines

        List<Interval> list1 = Arrays.asList(new Interval(Integer.MIN_VALUE, 0, true), new Interval(0, 5, false), new Interval(5, Integer.MAX_VALUE, true));
        List<Interval> list2 = Arrays.asList(new Interval(Integer.MIN_VALUE, 3, true), new Interval(3, 6, false), new Interval(6, Integer.MAX_VALUE, true));

        List<Interval> mergedSeparateList = mergeTimelinesSeparate(list1, list2);
        for (Interval inter : mergedSeparateList) {
            System.out.println(inter.start + ", " + inter.end + ", " + inter.val);
        }

        List<Interval> mergedList = mergeTimelines(mergedSeparateList);
        for (Interval inter : mergedList) {
            System.out.println(inter.start + ", " + inter.end + ", " + inter.val);
        }
    }

    private static List<Interval> mergeTimelines(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        Interval prev = null;
        for (Interval curr : intervals) {
            if (prev == null || curr.val != prev.val) {
                result.add(curr);
                // System.out.println("add interval: " + curr.start + ", " + curr.end + ", " + curr.val);
                prev = curr;
            } else {
                // System.out.println("prev end changed from " + prev.end + " to " + Math.max(prev.end, curr.end));
                prev.end = Math.max(prev.end, curr.end);
                prev.val = prev.val && curr.val;
            }
        }
        return result;
    }

    private static List<Interval> mergeTimelinesSeparate(List<Interval> list1, List<Interval> list2) {
        List<Interval> result = new ArrayList<>();
        int n = list1.size();
        int m = list2.size();
        int i = 0, j = 0;
        while (i < n && j < m) {
            Interval inter1 = list1.get(i);
            Interval inter2 = list2.get(j);
            if (inter1.end < inter2.end) {
                result.add(mergeTimeline(inter1, inter2));
                i++;
            } else if (inter1.end > inter2.end) {
                result.add(mergeTimeline(inter1, inter2));
                j++;
            } else {
                result.add(mergeTimeline(inter1, inter2));
                i++;
                j++;
            }
        }
        while (i < n) {
            result.add(list1.get(i));
            i++;
        }
        while (j < m) {
            result.add(list2.get(j));
            j++;
        }
        return result;
    }

    private static Interval mergeTimeline(Interval inter1, Interval inter2) {
        return new Interval(Math.max(inter1.start, inter2.start), Math.min(inter1.end, inter2.end), inter1.val && inter2.val);
    }
}
class Interval {
    int start;
    int end;
    boolean val;

    Interval(int s, int e, boolean v) {
        this.start = s;
        this.end = e;
        this.val = v;
    }
}
