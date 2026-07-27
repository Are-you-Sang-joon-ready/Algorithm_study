/// 죠르디"의 동영상 재생시간 길이 play_time, 공익광고의 재생시간 길이 adv_time, 시청자들이 해당 동영상을 재생했던 구간 정보 logs가 매개변수로 주어질 때,
/// 이때, 공익광고가 들어갈 시작 시각을 구해서 return 하도록 solution 함수를 완성해주세요.
/// 만약, 시청자들의 누적 재생시간이 가장 많은 곳이 여러 곳이라면, 그 중에서 가장 빠른 시작 시각dmf return
/// play_time, adv_time은 HH:MM:SS

/// 각 시청 시간 시작 및 종료 시간을 기준으로 누적합?
/// X 구간 외에 구간이 답일 여지가 있음 그냥 초로 해도 360000니 괜찮음

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int maxLength = toSecond(play_time);
        long[] total = new long[maxLength + 1];
        for (String log : logs) {
            String[] times = log.split("-");
            int start = toSecond(times[0]);
            int end = toSecond(times[1]);

            total[start] ++;
            total[end] --;
        }

        for (int i = 1; i <= maxLength; i++) { // 초별로 차분 배열을 누적합
            total[i] += total[i - 1];
        }

        long window = 0;
        int advTime = toSecond(adv_time);

        for (int i = 0; i < advTime; i++) {
            window += total[i];
        }
        long maxView = window;
        int answerTime = 0;

        for (int start = 1; start + advTime <= maxLength; start++) {
            window -= total[start -1];
            window += total[start + advTime - 1];

            if (window > maxView) {
                maxView = window;
                answerTime = start;
            }
        }

        return toTime(answerTime);

    }

    public int toSecond(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 3600
                + Integer.parseInt(t[1]) * 60
                + Integer.parseInt(t[2]);
    }

    public String toTime(int time) {
        int hour = time/3600;
        int min = (time % 3600) / 60;
        int second = time % 60;

        return String.format("%02d:%02d:%02d", hour, min, second);
    }
}

