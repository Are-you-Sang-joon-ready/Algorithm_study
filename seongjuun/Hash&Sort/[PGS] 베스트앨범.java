import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
		class Genre {
			int total = 0;
			int firstId = Integer.MIN_VALUE;
			int firstPlay = Integer.MIN_VALUE;
			int secondId = Integer.MIN_VALUE;
			int secondPlay = Integer.MIN_VALUE;

			public void add(int i, int play) {
				total += play;
				if (play > firstPlay){
					secondId = firstId;
					secondPlay = firstPlay;
					firstPlay = play;
					firstId = i;
				}else if (play > secondPlay){
					secondPlay = play;
					secondId = i;
				}
			}
		}

		Map<String, Genre> map = new HashMap<>();
		for (int i = 0; i < genres.length; i++) {
			map.computeIfAbsent(genres[i], k -> new Genre()).add(i, plays[i]);
		}

		List<Integer> answer = new ArrayList<>();
		List<Genre> genreList = new ArrayList<>(map.values());
		genreList.sort(Comparator.comparingInt((Genre o) -> o.total).reversed());
		
		for (Genre value : genreList) {
			answer.add(value.firstId);
			if (value.secondId != Integer.MIN_VALUE) {
				answer.add(value.secondId);
			}
		}

		return answer.stream().mapToInt(i -> i).toArray();
	}
}