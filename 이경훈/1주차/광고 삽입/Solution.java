import java.util.*;

class Solution {

	public String solution(String play_time, String adv_time, String[] logs) {
		int playSecond = toSeconds(play_time);
		int advSecond = toSeconds(adv_time);

		// 종료 시각이 playSecond와 같을 수 있으므로 +1
		int[] timeline = new int[playSecond + 1];

		// 각 로그의 시작 시각에는 +1, 종료 시각에는 -1
		for (String log : logs) {
			String[] split = log.split("-");

			int start = toSeconds(split[0]);
			int end = toSeconds(split[1]);

			timeline[start]++;
			timeline[end]--;
		}

		// 각 초의 시청자 수 계산
		for (int i = 1; i < playSecond; i++) {
			timeline[i] += timeline[i - 1];
		}

		// prefix[i]: 0초부터 i초 직전까지의 누적 시청 시간
		long[] prefix = new long[playSecond + 1];

		for (int i = 0; i < playSecond; i++) {
			prefix[i + 1] = prefix[i] + timeline[i];
		}

		long maxViewTime = -1;
		int answerStart = 0;

		// 광고가 재생 시간 안에 끝나는 모든 시작 시각 확인
		for (int start = 0; start + advSecond <= playSecond; start++) {
			long currentViewTime =
				prefix[start + advSecond] - prefix[start];

			// 같은 값이면 앞선 시작 시각을 유지
			if (currentViewTime > maxViewTime) {
				maxViewTime = currentViewTime;
				answerStart = start;
			}
		}

		return toTime(answerStart);
	}

	private int toSeconds(String time) {
		String[] split = time.split(":");

		return Integer.parseInt(split[0]) * 3600
			+ Integer.parseInt(split[1]) * 60
			+ Integer.parseInt(split[2]);
	}

	private String toTime(int seconds) {
		int hour = seconds / 3600;
		int minute = seconds % 3600 / 60;
		int second = seconds % 60;

		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
}