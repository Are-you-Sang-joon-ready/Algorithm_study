import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
  public int[] solution(int[] progresses, int[] speeds) {
    Queue<Integer> queue = new ArrayDeque<>();

    for (int i = 0; i < progresses.length; i++) {
      int days = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
      queue.offer(days);
    }

    List<Integer> result = new ArrayList<>();
    while (!queue.isEmpty()) {
      int deployDay = queue.poll();
      int count = 1;

      while (!queue.isEmpty() && queue.peek() <= deployDay) {
        queue.poll();
        count++;
      }
      result.add(count);
    }
    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}