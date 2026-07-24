class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playTimeSec = converSec(play_time);
        int advTimeSec = converSec(adv_time);
        int[][] times = new int[logs.length][2];

        for (int i = 0; i < logs.length; i++){
            String[] subLog = logs[i].split("-");
            times[i][0] = converSec(subLog[0]);
            times[i][1] = converSec(subLog[1]);
        }

        long[] imos = new long[playTimeSec + 1];
        for (int i = 0; i < times.length; i++){ 
            int start = times[i][0];
            int end = times[i][1];
            imos[start] += 1;
            imos[end] -= 1;
        }

        for (int i = 0; i < playTimeSec; i++){ 
            imos[i+1] += imos[i];
        }

        for (int i = 0; i < playTimeSec; i++){
            imos[i+1] += imos[i];
        }

        long maxTime = imos[advTimeSec - 1];
        int bestStartTime = 0;
        for (int i = 1; i <= playTimeSec - advTimeSec; i++){ 
            long currentTime = imos[i+advTimeSec-1] - imos[i-1];
            if (currentTime > maxTime){
                bestStartTime = i;
                maxTime = currentTime;
            }
        }

        answer = converTime(bestStartTime);

        return answer;
    }

    public int converSec(String time){
        String[] subTime = time.split(":");
        int hours = Integer.parseInt(subTime[0]) * 60 * 60;
        int minutes = Integer.parseInt(subTime[1]) * 60;
        int second = Integer.parseInt(subTime[2]);
        return hours + minutes + second;
    }

    public String converTime(int sec){
        int hours = sec / 3600;
        int minutes = (sec % 3600) / 60;
        int second = sec % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, second);
    }
}