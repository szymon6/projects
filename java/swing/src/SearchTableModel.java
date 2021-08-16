import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SearchTableModel extends DefaultTableModel {

    private List<Employee> employees;

    public SearchTableModel(List<Employee> employees) {

        setRowCount(0);
        getDataVector().removeAllElements();

        addColumn("Id");
        addColumn("Last Name");
        addColumn("Birth Date");
        addColumn("Employment Date");

        this.employees = employees;

        AddRows();
    }

    void AddRows() {

        String[] str = new String[4];
        for (var em : employees) {
            if (em.getFname() != null)
                str[0] = Long.valueOf(em.getId()).toString();

            if (em.getLname() != null)
                str[1] = em.getLname();

            if (em.getEmployerName() != null)
                str[2] = em.getBirthDate().toString();

            if (em.getBirthDate() != null)
                str[3] = em.getEmploymentDate().toString();

            addRow(str);
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

}
