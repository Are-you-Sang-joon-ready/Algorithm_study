import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++){
            queue.offer(new int[]{priorities[i], i});
        }
        for (int i = 9; i > 0; i--){
            final int target = i;
            while (queue.stream().anyMatch(q -> q[0] == target)){
                int[] p = queue.poll();
                if (p[0] == i){
                    answer++;
                    if (p[1] == location){
                        return answer;
                    }
                }else {
                    queue.offer(p);
                }
            }
        }
        
        return answer;
    }
}