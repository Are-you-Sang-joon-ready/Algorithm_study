import java.util.*;

class Solution {
	public int solution(String[][] clothes) {
		Map<String, Integer> map = new HashMap<>();
		int answer = 1;

		// 각 부위별 가짓수를 map에 저장
		for (String[] s : clothes) {
			map.put(s[1], map.getOrDefault(s[1], 0) + 1);
		}

		// 부위별 가짓수는 입지않음 상태를 표현하기 위해 + 1 한후 정답 가짓수에 곱합
		for (String key : map.keySet()) {
			answer *= map.get(key) + 1;
		}

		// 최소 하나는 입어야 하기에 모두 입지 않음 상태를 -1하여 정답을 구함
		return answer-1;
	}
}