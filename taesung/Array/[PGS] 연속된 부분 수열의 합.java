
class Solution {
    public int[] solution(int[] sequence, int k) {
        int minLength = sequence.length;
        int start = 0;
        int answerLeft = 0;
        int answerRight = sequence.length;
        int sum = 0;

        for (int end = 0; end < sequence.length; end++) {
            sum += sequence[end];

            while (sum > k) {
                sum -= sequence[start++];
            }

            if (sum == k) {
                if (minLength > end - start) {
                    minLength = end - start;
                    answerRight = end;
                    answerLeft = start;
                }
            }

        }
        return new int[]{answerLeft, answerRight};
    }
}