import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MyTableModel extends DefaultTableModel {

    private List<Employee> employees;
    private List<ByteArrayWrapper> wrappedImages;

    private JProgressBar progressBar;
    private JDialog dialog;
    private int size;

    public MyTableModel(EmployeesAL employeesAL) {

        addColumn("First Name");
        addColumn("Last Name");
        addColumn("Salary");
        addColumn("Birth date");
        addColumn("Picture");
        addColumn("Playable");

        employees = employeesAL.getEmployeeList();
        wrappedImages = employeesAL.getFaceContainer().getWrappers();

        showProgressBarDialog();

        AddRows();

    }

    // region dodawanie wierszy
    void AddRows() {

        Object[] objects = new Object[6];
        if (employees == null)
            return;

        int index = 0;
        for (var em : employees) {

            objects[0] = em.getFname();
            objects[1] = em.getLname();
            objects[2] = em.getSalary().toString();
            objects[3] = em.getBirthDate().toString();

            ImageIcon icon = new ImageIcon(wrappedImages.get(index).getImage());

            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            objects[4] = icon;

            objects[5] = em.getPlayable();

            addRow(objects);
            updateProgressBar(index + 1);
            index++;
        }

    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 4)
            return ImageIcon.class;

        if (column == 5)
            return Boolean.class;

        return Object.class;
    }
    //endregion

    //region edycja
    @Override
    public boolean isCellEditable(int row, int column) {

        return column != 4;

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        System.out.println(columnIndex);

        boolean changed = false;

        if (columnIndex == 0)
            changed = editFirsName((String) aValue, rowIndex);
        else if (columnIndex == 1)
            changed = editLastName((String) aValue, rowIndex);
        else if (columnIndex == 2)
            changed = editSalary((String) aValue, rowIndex);
        else if (columnIndex == 3)
            changed = editBirthDate((String) aValue, rowIndex);
        else if (columnIndex == 5)
            changed = editPlayable((Boolean) aValue, rowIndex);

        if (changed)
            super.setValueAt(aValue, rowIndex, columnIndex);

    }

    private boolean editFirsName(String name, int index) {
        if (EmployeeFrame.checkString(name)) {
            employees.get(index).setFname(EmployeeFrame.stringFormat(name));
            return true;
        } else
            return false;

    }

    private boolean editLastName(String name, int index) {
        if (EmployeeFrame.checkString(name)) {
            employees.get(index).setLname(EmployeeFrame.stringFormat(name));
            return true;
        } else
            return false;

    }

    private boolean editSalary(String salary, int index) {

        if (EmployeeFrame.checkNumber(salary)) {
            int n = Integer.parseInt(salary);
            employees.get(index).setSalary(BigDecimal.valueOf(n));
            return true;
        } else
            return false;

    }

    private boolean editBirthDate(String str, int index) {

        if (EmployeeFrame.checkDate(str)) {

            int y, m, d;
            y = Integer.parseInt(str.substring(0, 4));
            m = Integer.parseInt(str.substring(5, 7));
            d = Integer.parseInt(str.substring(8, 10));
            employees.get(index).setBirthDate(LocalDate.of(y, m, d));
            return true;
        } else
            return false;

    }

    private boolean editPlayable(Boolean bool, int index) {

        employees.get(index).setPlayable(bool);
        return true;
    }

    //endregion

    //region progressbar
    void showProgressBarDialog() {

        dialog = new JDialog();
        dialog.setTitle("Creating table");

        dialog.setSize(230, 160);
        dialog.setLocation(150, 150);

        JPanel p = new JPanel();
        dialog.add(p);
        p.setLayout(null);

        JLabel l = new JLabel("Creating table");
        l.setBounds(50, 20, 200, 20);
        p.add(l);

        //progres bar
        progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBounds(50, 50, 100, 20);
        p.add(progressBar);

        size = employees.size();

        dialog.setVisible(true);

    }

    void updateProgressBar(int i) {

        double percent = (double) i / size * 100;

        progressBar.setValue((int) percent);

        if (percent == 100) {
            dialog.dispose();
            dialog.setModal(true);
        }
        //    progressBar.setValue(0);

    }
    //endregion

}
