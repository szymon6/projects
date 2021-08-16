import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GameTableModel extends DefaultTableModel {

    private List<PlayerPair> playerPairs;

    public GameTableModel(List<PlayerPair> playerPairs) {

        setRowCount(0);
        getDataVector().removeAllElements();

        addColumn("Player1");
        addColumn("Player2");
        addColumn("Player1 money");
        addColumn("Player2 money");

        this.playerPairs = playerPairs;

        AddRows();
    }

    void AddRows() {

        String[] str = new String[4];
        for (var pp : playerPairs) {

            str[0] = pp.getPlayer1().getFname() + " " + pp.getPlayer1().getLname();
            str[1] = pp.getPlayer2().getFname() + " " + pp.getPlayer2().getLname();
            str[2] = String.valueOf(pp.getPlayer1().getMoney());
            str[3] = String.valueOf(pp.getPlayer2().getMoney());

            addRow(str);
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

}
