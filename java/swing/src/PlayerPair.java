public class PlayerPair {

    private Employee player1;
    private Employee player2;

    public PlayerPair(Employee player1, Employee player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Employee getPlayer1() {
        return player1;
    }

    public void setPlayer1(Employee player1) {
        this.player1 = player1;
    }

    public Employee getPlayer2() {
        return player2;
    }

    public void setPlayer2(Employee player2) {
        this.player2 = player2;
    }

}
