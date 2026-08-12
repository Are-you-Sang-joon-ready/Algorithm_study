import java.util.ArrayList;
import java.util.List;

class Solution {
  public int solution(int cacheSize, String[] cities) {
    int answer = 0;
    List<String> cache = new ArrayList<>();

    for (String city : cities) {
      city = city.toLowerCase();

      // 캐시에 도시가 있는 경우
      if (cache.contains(city)) {
        answer += 1;
        cache.remove(city);
      } else{
        // 캐시에 도시가 없는 경우
        answer += 5;

        if (cacheSize > 0 && cache.size() == cacheSize) {
          cache.remove(0);
        }
      }
      if (cacheSize > 0) {
        cache.add(city);
      }
    }

    return answer;
  }
}