import java.util.Arrays;

class Solution {
  public int[] solution(int[] progresses, int[] speeds) {
    int[] result = new int[progresses.length];
    int index = 0;

    int deployDay = (99 - progresses[0]) / speeds[0] + 1;
    int count = 1;

    for (int i = 1; i < progresses.length; i++) {
      int day = (99 - progresses[i]) / speeds[i] + 1;

      if (day <= deployDay) {
        count++;
      } else {
        result[index++] = count;
        deployDay = day;
        count = 1;
      }
    }

    result[index++] = count;

    return Arrays.copyOf(result, index);
  }
}