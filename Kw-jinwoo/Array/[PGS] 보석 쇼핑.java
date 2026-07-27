import java.util.*;
import java.util.stream.*;

class Solution {
    
    /*  [문제이해]
            구매범위 : 진열대의 특정 범위의 물건들을 모두 싹쓸이
            목적달성 : 진열된 "모든 종류의 보석을 적어도 1개 이상" 포함하는 "가장 짧은 (연속)구간"을 찾아서 구매
            진열대 : {1-DIA, 2-RUBY, 3-RUBY, 4-DIA, 5-DIA, 6-EMERALD, 7-SAPPHIRE, 8-DIA}
                모든종류 - DIA/RUBY/EMERALD/SAPPHIRE
                
            입력
                String[] gems : 진열대 번호 순서대로 보석이름
            출력
                int[] : 가장 짧은 구간, 시작 진열대 번호 & 끝 진열대 번호
                    (만약 같은길이구간 여러개 -> 시작번호가 짧은 구간)
            제한사항
                1 <= gems.length <= 100,000
                1 <= gems 원소길이 <= 10
    */
    
    /*  [문제풀이]
            윈도우 : 구매범위 구간, int front ~ int back 으로 계산, front=0&back=0 시작
                    윈도우 조건불충
                        윈도우확장 : back++;
                    윈도우 조건충족
                        윈도우축소 : front--; 
                윈도우 크기계산 : 가장 작을때 optimized

            카운터 : 윈도우 내부 모든 보석종류별 카운팅, int[] gemCount, 모두 0으로 시작
                보석사전 : 모든 보석종류 리스트, List<String> gemDict, gemCount와 같은인덱싱
                보석위치 : 모든 보석종류 이름별 카운터 인덱스값, Map<String, Integer>
                        (gemDict.indexOf(name) 으로 인한 인덱스값 탐색 시간복잡도를 해소..!!)
                
            윈도우 조건검사
                gemCount 모든요소 > 0 -> 충족
    */
    
    public int[] solution(String[] gems) {
        int[] answer = {0, 0};
        int front = 0; int back = 0; int OptLength = gems.length;
        
        List<String> gemDict = new ArrayList<>();
        Map<String, Integer> gemPos = new HashMap<>();
        gemDict = Arrays.stream(gems).distinct().collect(Collectors.toList());
        int[] gemCount = new int[gemDict.size()];
        for (int i = 0; i < gemDict.size(); i++) {
            gemPos.put(gemDict.get(i), i);
        }
        
        gemCount[gemPos.get(gems[back])]++;
        if (gemCheck(gemCount)) {
            answer[0]++;
            answer[1]++;
            return answer;
        } else {
            back++;
            gemCount[gemPos.get(gems[back])]++;
        }
        
        while(true) {
            if (gemCheck(gemCount)) {
                if ((back - front) < OptLength) {
                    answer[0] = front;
                    answer[1] = back;
                    OptLength = back - front;
                }
                gemCount[gemPos.get(gems[front])]--;
                front++;
            } else {
                back++;
                if (back > gems.length-1) {
                    break;
                }
                gemCount[gemPos.get(gems[back])]++;
            }
        }
        
        answer[0]++;
        answer[1]++;
        return answer;
    }
    
    private boolean gemCheck(int[] gemCount) {
        boolean result = true;
        for (int i : gemCount) {
            if (i < 1) {
                result = false;
            }
        }
        return result;
    }
}