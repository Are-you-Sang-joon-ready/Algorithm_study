class Solution {
  boolean solution(String s) {
    int count = 0;

    for (char bracket : s.toCharArray()) {
      if (bracket == '(') {
        count++;
      } else {
        count--;
      }

      // 닫는 괄호가 먼저 나온 경우
      if (count < 0) {
        return false;
      }
    }

    // 열린 괄호가 모두 닫혔는지 확인
    return count == 0;
  }
}