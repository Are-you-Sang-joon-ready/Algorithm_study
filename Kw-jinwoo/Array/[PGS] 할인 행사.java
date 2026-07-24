import java.util.*;
import java.util.stream.*;

class Solution {
    
    /*	[문제이해]
	 *
	 *	회원가입조건 : 원하는 제품 & 수량 -> 할인날짜와 10일 연속으로 일치할 경우?
	 *
	 *	예시)
	 *		정현 : 바3 / 사2 / 쌀2 / 돼2 / 냄1 -> 회원가입의 조건!
	 *		XYZ  : 치 > 사 > 사 > 바 > 쌀 / > 사 > 돼 > 바 > 돼 > 쌀 /  > 냄 > 바 > 사 > 바
	 *		회원 : X  > X  > O  > O  > O
	 *
	 *	입력
	 *		String[] want 		: 원하는 제품목록
	 *		int[] number		: 원하는 제품수량 목록
	 *		String[] discount	: 할인 제품목록
	 *	출력
	 *		회원등록 날짜의 총 일수!
	 *
	 *	제한사항
	 *		1 <= want길이 = number길이 <= 10
	 *		1 <= number원소 <= 10 (number원소합==10)
	 *		10 <= discount길이 <= 100,000
	 *		want, discount 원소들은 모두 알파벳 소문자 (12자이하)
	 * */

	/*	[문제풀이]
	 *
	 *	결핍집합 운용 : 모자란 원소만큼의 개수가 필요하다..!
	 *		count : 결핍집합 원소가 모두 0이면 -> answer++
	 *	
	 *	0. 결핍집합 생성
	 *		결핍집합 : int[] number 그대로 사용
	 *		결핍사전 : List<String> -> 결핍사전에 있는지 검증! (.contains(str))
	 *	1. 1일차 결핍집합 산출 			& count
	 *		결핍집합 산출 : discount 0-9 순회 -> 결핍집합 비우기!!
	 *	2. 2일차부터 결핍집합 update 		& count
	 *		신규할인 -> 결핍집합 비우기 update
	 *		종료할인 -> 결핍집합 채우기 update
	 *		count : 결핍집합순회 -> 모두 0이면 answer++
	 *		
	 * */
    
    public int solution(String[] want, int[] number, String[] discount) {
        int pointer = 0;
        int answer = 0;
		List<String> lackDict = Arrays.stream(want).collect(Collectors.toList());
        
        for ( ; pointer < 10; pointer++) {
            String onSale = discount[pointer];
            if (lackDict.contains(onSale)) {
                number[lackDict.indexOf(onSale)]--;
            }
        }
        
        boolean canMem = true;
        for (int i : number) {
            if (i != 0) {
                canMem = false;
            }
        }
        if (canMem) {
            answer++;
        }
        
        for (pointer=10 ; pointer < discount.length; pointer++) {
            canMem = true;
            
            String onSale = discount[pointer];
            String offSale = discount[pointer-10];
            if (lackDict.contains(onSale)) {
                number[lackDict.indexOf(onSale)]--;
            }
            if (lackDict.contains(offSale)) {
                number[lackDict.indexOf(offSale)]++;
            }
            
            for (int i : number) {
                if (i != 0) {
                    canMem = false;
                }
            }
            if (canMem) {
                answer++;
            }
        }
        
        return answer;
    }
}