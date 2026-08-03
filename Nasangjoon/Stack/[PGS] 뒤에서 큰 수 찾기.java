import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        // 배열을 미리 -1로 초기화
        Arrays.fill(answer, -1);

        // 어레이 데크 사용하기!
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }

        // 남은 요소는 이미 -1로 초기화되어 있으므로 별도의 pop 루프 할 필요 ㄴㄴ
        return answer;
    }
}