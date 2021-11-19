package ru.cft.focusstart.task3.minesweeper.timer;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.view.MainWindow;

@Slf4j
public class TimerGame {
    private Thread timer;
    private int timeSec;
    private boolean isStop;
    private MainWindow mainWindow;

    public void setMainWindow(MainWindow mainWindow){
        this.mainWindow = mainWindow;
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
                mainWindow.setTimerValue(timeSec);

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
        mainWindow.setTimerValue(timeSec);
    }
}
