class Solution {
  public int[] solution(int[] sequence, int k) {
    int left = 0;
    int sum = 0;

    int answerLeft = 0;
    int answerRight = sequence.length;

    for (int right = 0; right < sequence.length; right++) {
      sum += sequence[right];

      while (sum > k) {
        sum-= sequence[left];
        left++;
      }

      if (sum == k) {
        int currentLength = right - left + 1;
        int answerLength = answerRight - answerLeft + 1;

        if (currentLength < answerLength) {
          answerLeft = left;
          answerRight = right;
        }
      }
    }
    return new int[]{answerLeft, answerRight};
  }
}