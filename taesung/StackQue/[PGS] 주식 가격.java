//문제 설명
//초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

//        prices	        return
//        [1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
//          [1, 3, 2, 3, 1, 2, 3]
// > for 문 돌고 나면 ['', 1, 2, 1, '', '', '']
//          [6, 1, 2, 1, 2, 1, 0]

import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                // if 문이면 마지막만 체크하는데 가령 2, 3, 1 이면 1이 들어올 때 3만 체크하므로, while로 2까지 체크 해야함
                answer[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            answer[stack.peek()] = length - stack.pop() - 1;
        }
        return answer;
    }
}