package rc.sudokugenius.views.components;

public class Stopwatch extends javax.swing.JPanel {

    private int sec = 0;
    private int min = 0;
    private int hour = 0;
    private boolean started;
    private Thread thread;

    public Stopwatch() {
        initComponents();
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public void start() {
        started = true;
        thread = new Thread(new Timer());
        thread.start();
    }

    public void stop() {
        started = false;

        if (thread != null) {
            thread.interrupt();
        }
    }

    public boolean isStarted() {
        return started;
    }

    public String getTotalTime() {
        return new StringBuilder().append(hour < 10 ? "0" : "").
                append(hour).
                append(":").
                append(min < 10 ? "0" : "").
                append(min).
                append(":").
                append(sec < 10 ? "0" : "").
                append(sec).
                toString();
    }

    public void reset() {
        stop();

        sec = 0;
        min = 0;
        hour = 0;

        lblHour.setText("00");
        lblMinutes.setText("00");
        lblSeconds.setText("00");
    }

    private class Timer extends Thread {

        @Override
        public void run() {
            while (started) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                if (!started) {
                    return;
                }

                sec = sec == 59 ? 0 : sec + 1;
                lblSeconds.setText((sec < 10 ? "0" : "") + sec);

                if (sec == 0) {
                    min = min == 59 ? 0 : min + 1;
                    lblMinutes.setText((min < 10 ? "0" : "") + min);

                    if (min == 0) {
                        hour++;
                        lblHour.setText((hour < 10 ? "0" : "") + hour);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHour = new rc.sudokugenius.views.components.ChalkLabel();
        chalkLabel2 = new rc.sudokugenius.views.components.ChalkLabel();
        lblMinutes = new rc.sudokugenius.views.components.ChalkLabel();
        chalkLabel4 = new rc.sudokugenius.views.components.ChalkLabel();
        lblSeconds = new rc.sudokugenius.views.components.ChalkLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setOpaque(false);

        lblHour.setText("00");

        chalkLabel2.setText(":");

        lblMinutes.setText("00");

        chalkLabel4.setText(":");

        lblSeconds.setText("00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chalkLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chalkLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(chalkLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(chalkLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rc.sudokugenius.views.components.ChalkLabel chalkLabel2;
    private rc.sudokugenius.views.components.ChalkLabel chalkLabel4;
    private rc.sudokugenius.views.components.ChalkLabel lblHour;
    private rc.sudokugenius.views.components.ChalkLabel lblMinutes;
    private rc.sudokugenius.views.components.ChalkLabel lblSeconds;
    // End of variables declaration//GEN-END:variables
}
