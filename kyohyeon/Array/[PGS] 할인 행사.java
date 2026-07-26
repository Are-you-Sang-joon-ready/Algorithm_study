import java.util.HashMap;
import java.util.Map;

class Solution {
  public int solution(String[] want, int[] number, String[] discount) {
    int answer = 0;

    Map<String, Integer> wantMap = new HashMap<>();
    Map<String, Integer> windowMap = new HashMap<>();

    for (int i = 0; i < want.length; i++) {
      wantMap.put(want[i], number[i]);
    }

    // 처음 10일의 할인 제품 개수 저장
    for (int i = 0; i < 10; i++) {
      windowMap.put(
          discount[i],
          windowMap.getOrDefault(discount[i], 0) + 1
      );
    }

    if (wantMap.equals(windowMap)) {
      answer++;
    }

    // 10일 구간을 하루씩 이동
    for (int i = 10; i < discount.length; i++) {
      String out = discount[i - 10];
      String in = discount[i];

      // 구간에서 빠지는 제품 개수 감소
      windowMap.put(out, windowMap.get(out) - 1);

      if (windowMap.get(out) == 0) {
        windowMap.remove(out);
      }

      // 구간에 들어오는 제품 개수 증가
      windowMap.put(in, windowMap.getOrDefault(in, 0) + 1);

      if (wantMap.equals(windowMap)) {
        answer++;
      }
    }

    return answer;
  }
}