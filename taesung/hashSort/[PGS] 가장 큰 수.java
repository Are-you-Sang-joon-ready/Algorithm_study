import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String[] strs = new String[numbers.length];
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            strs[i] = Integer.toString(numbers[i]);
            sum += numbers[i];
        }
        if (sum != 0) { // 모든 요소가 0인 경우 체크해야 해요...
            Arrays.sort(strs, (s1, s2) -> {
                return (s2 + s1).compareTo((s1 + s2));
            });

            StringBuilder answer = new StringBuilder("");
            for (String str: strs) {
                answer.append(str);
            }
            return answer.toString();
        } else {
            return "0";
        }
    }


}