import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Employee> employees;
    private JTable table;
    private EmployeeFrame frame;

    private Timer timer;
    private Timer animationTimer;

    private List<PlayerPair> playerPairs;
    private List<Employee> playersToGet;

    private boolean started;

    private JPanel animationPanel;
    private int i = 1;

    public Game(EmployeeFrame frame) {

        this.frame = frame;

    }

    private void creartePairs() {
        playerPairs = new ArrayList<>();

        for (int i = 0; i < playersToGet.size() - 1; i += 2) {

            if (i + 1 >= playersToGet.size()) break;

            PlayerPair pp = new PlayerPair(playersToGet.get(i), playersToGet.get(i + 1));
            playerPairs.add(pp);

        }

    }

    void paySalary() {
        for (var em : employees) {
            em.setMoney(em.getSalary());
        }
    }

    void nextRound() {

        for (var pp : playerPairs) {

            Random random = new Random();
            int p1Coin = random.nextBoolean() ? 1 : 0;
            int p2Coin = random.nextBoolean() ? 1 : 0;

            boolean firsWon = p1Coin != p2Coin;

            Employee winner, looser;

            if (firsWon) {
                winner = pp.getPlayer1();
                looser = pp.getPlayer2();
            } else {
                winner = pp.getPlayer2();
                looser = pp.getPlayer1();
            }

            winner.setMoney(winner.getMoney().add(looser.getMoney()));
            looser.setMoney(BigDecimal.valueOf(0));

            playersToGet.remove(looser);
        }

        createTable();
        creartePairs();

        if (playerPairs.size() == 0) {
            timer.stop();
            animationTimer.stop();
            restartAnimation();
        }

    }

    public void start() {

        if (frame.getEmAL() == null) {
            frame.infShowError("No employee list added");
            return;
        }

        initValues();
        paySalary();
        creartePairs();
        createTable();

        timer = new Timer(2000, e -> nextRound());
        timer.setRepeats(true);
        timer.start();

        started = true;
        frame.infShowMessage("Game started");

        startAnimation();

    }

    void startAnimation() {
        animationPanel = frame.getAnimationPanel();

        animationTimer = new Timer(100, e -> {

            setNextPicture();

        });
        animationTimer.setRepeats(true);
        animationTimer.start();

    }

    void setNextPicture() {
        setPicture(i);
        i++;

        if (i >= 17)
            i = 1;
    }

    void setPicture(int i) {
        String str = "img";
        str += String.valueOf(i);
        str += ".jpg";

        repaintPicture(str);
    }

    private void repaintPicture(String name) {
        ((MyPanel) animationPanel).setImage(name);
        animationPanel.revalidate();
        animationPanel.repaint();
    }

    private void restartAnimation() {
        repaintPicture("img1.jpg");
        i = 0;
    }

    public void stop() {

        if (started) {
            timer.stop();
            animationTimer.stop();
            frame.infShowMessage("Game stopped");
        } else
            frame.infShowError("Game not started ");

    }

    private void createTable() {
        DefaultTableModel model = new GameTableModel(playerPairs);
        table.setModel(model);
        table.setRowHeight(100);
    }

    private void initValues() {
        employees = frame.getEmployees();
        table = frame.getTableGame();

        playersToGet = new ArrayList<>();
        for (var em : employees) {
            if (em.getPlayable())
                playersToGet.add(em);
        }

    }

}
