import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            inDegree[e[1]]++;
            graph.get(e[1]).add(e[0]);
            inDegree[e[0]]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 1 || inDegree[i] == 0) {
                q.add(i);
                inDegree[i] = 0;
            }
        }
        int count = q.size();
        while (!q.isEmpty()) {
            int size = q.size();
            if (count == n) {
                result = new ArrayList<>(q);
                return result;
            }
            for (int j = 0; j < size; j++) {
                int cur = q.poll();
                List<Integer> nei = graph.get(cur);
                for (int i = 0; i < nei.size(); i++) {
                    inDegree[nei.get(i)]--;
                    if (inDegree[nei.get(i)] == 1) {
                        q.offer(nei.get(i));
                        count++;
                    }
                }
            }
        }

        return result;
    }
}
