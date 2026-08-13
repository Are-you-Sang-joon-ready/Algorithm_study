import java.util.ArrayList;
import java.util.List;
class Solution {
    public String solution(int[] numbers) {
		StringBuilder answer = new StringBuilder();
		List<String> list = new ArrayList<>();
		for (int number : numbers) {
			list.add(String.valueOf(number));
		}
		list.sort((a, b) -> (b + a).compareTo(a + b));
        if (list.get(0).equals("0")) {
            return "0";
        }
		for (String s : list) {
			answer.append(s);
		}
		return answer.toString();
	}		
}