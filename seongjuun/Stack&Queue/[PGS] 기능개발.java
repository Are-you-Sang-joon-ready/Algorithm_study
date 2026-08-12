import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int progress: progresses){
            queue.offer(progress);
        }
        int deployed = 0; 
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                queue.offer(queue.poll() + speeds[i+deployed]);
            }
            
            int count = 0;
            while (!queue.isEmpty() && queue.peek() >= 100) {
                queue.poll();
                count++;
            }
            if (count > 0){
                answer.add(count);
                deployed += count;
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}