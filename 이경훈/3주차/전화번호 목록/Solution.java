import java.util.*;

class Solution {
	public boolean solution(String[] phone_book) {
		Arrays.sort(phone_book);
		for(int i=0;i<phone_book.length-1;i++){
			int len = phone_book[i].length();
			if (phone_book[i+1].length()>len &&
				phone_book[i].equals(phone_book[i+1].substring(0,len))){
				return false;
			}
			// 더 간단한 풀이 for문 이거로 대체하면 됩니다
			// if (phoneBook[i + 1].startsWith(phoneBook[i])) {
			// 	return false;
			// }
		}
		return true;
	}
}