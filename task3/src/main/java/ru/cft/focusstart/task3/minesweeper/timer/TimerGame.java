package ru.cft.focusstart.task3.minesweeper.timer;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.view.View;

@Slf4j
public class TimerGame {
    private Thread timer;
    private int timeSec;
    private boolean isStop;
    private View view;

    public void setView(View view){
        this.view = view;
    }

    public Thread getTimer() {
        return timer;
    }

    public int getTimeSec() {
        return timeSec;
    }

    public void createAndStartTimer() {
        isStop = false;

        timer = new Thread(() -> {
            while(!isStop) {
                timeSec++;
                view.setTimerValue(timeSec);

                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex){
                    log.warn("Неожиданное прерывание таймера: {}", ex.getMessage());
                }
            }
        });

        timer.start();
    }


    public void stopTimer(){
        isStop = true;

        try {
            if (timer!= null)
                timer.join();
        } catch (InterruptedException ignored){}
    }

    public void resetTimer(){
        timeSec = 0;
        view.setTimerValue(timeSec);
    }
}
