import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeFrame {

    private Game game;

    private int defaultTableCellMaxWidth;
    private int defaultTableCellWidth;

    private List<ByteArrayWrapper> wrappedFaces;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private FaceContainer faceContainer;

    public EmployeesAL getEmAL() {
        return emAL;
    }

    private EmployeesAL emAL;

    public List<Employee> getEmployees() {
        return employees;
    }

    private List<Employee> employees;
    private Employee currentEmployee;
    private boolean loaded;
    private JLabel[] errorMessages = new JLabel[10];

    private static JFrame frame;

    private JPanel panel1;
    private JButton addButton;
    private JButton saveButton;
    private JButton writeButton;
    private JButton findButton;
    private JComboBox comboBoxSearch;
    private JButton firstButton;
    private JButton lastButton;
    private JButton nextButton;
    private JButton previousButton;
    private JTextField textFieldAddress;
    private JTextField textFieldBirthDate;
    private JTextField textFieldLastName;
    private JTextField textFieldFirstName;
    private JTextField textFieldId;
    private JTextField textFieldEmployerName;
    private JTextField textFieldEmploymentDate;
    private JTextField textFieldReleaseDate;
    private JComboBox comboBoxJobTitle;
    private JTextField textFieldSalary;
    private JLabel message;
    private JLabel labelId;
    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelAddress;
    private JLabel labelBirthDate;
    private JLabel labelEmployerName;
    private JLabel labelEmploymentDate;
    private JLabel labelReleaseDate;
    private JLabel labelSalary;
    private JLabel labelJobTitle;
    private JTable table;
    private JPanel picture;
    private JTextField textFieldSearch;
    private JButton resetButton;
    private JTabbedPane tabbedPane;

    public JTable getTableGame() {
        return tableGame;
    }

    private JTable tableGame;

    public JPanel getAnimationPanel() {
        return animationPanel;
    }

    private JPanel animationPanel;

    private static final String[] searchCriteriaNames = {
            "Id",
            "Last Name",
            "Birth Date",
            "Employment Date"
    };

    private static final String[] jobTitles = {
            "Manager",
            "Dyrektor",
            "Kierownik",
            "Konstruktor",
            "Analityk",
            "Architekt",
            "Sprzedawca",
            "Fotograf",
            "Krawiec",
            "Recepcjonista"
    };

    public EmployeeFrame() {

        $$$setupUI$$$();
        createMenu();
        init();
        setListeners();

    }

    public static void main(String[] args) {
        frame = new JFrame("EmployeeFrame");
        frame.setResizable(false);
        frame.setContentPane(new EmployeeFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    //region Menu
    void createMenu() {

        JMenuBar jMenuBar = new JMenuBar();
        frame.setJMenuBar(jMenuBar);

        jMenuBar.add(menuFile());
        jMenuBar.add(menuTable());
    }

    JMenu menuFile() {
        JMenu menu = new JMenu("File");

        //exit
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> {
                    System.exit(JFrame.EXIT_ON_CLOSE);
                }
        );
        menu.add(exitMenuItem);

        //import
        JMenuItem importMenuItem = new JMenuItem("Import");
        importMenuItem.addActionListener(e -> {
                    openLoadWindow();
                }
        );
        menu.add(importMenuItem);

        //clear
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(e -> {
                    clearFields();

                }

        );
        menu.add(clearMenuItem);

        //generate
        JMenuItem generateMenuItem = new JMenuItem("Generate");
        generateMenuItem.addActionListener(e -> {
                    showGenerateDialog();
                }

        );
        menu.add(generateMenuItem);

        //start game
        JMenuItem startGame = new JMenuItem("Start game");
        startGame.addActionListener(e -> {
                    game.start();
                }

        );
        menu.add(startGame);

        //stop game
        JMenuItem stopGame = new JMenuItem("Stop game");
        stopGame.addActionListener(e -> {
                    game.stop();
                }

        );
        menu.add(stopGame);

        return menu;
    }

    JMenu menuTable() {
        JMenu menu = new JMenu("Table");

        String[] columnNames = {
                "First Name",
                "Last Name",
                "Salary",
                "Birth Date",
                "Picture",
                "Playable"

        };

        for (int i = 0; i < columnNames.length; i++)
            menu.add(createCheckBoxMenuItem(columnNames[i], i));

        return menu;
    }

    JCheckBoxMenuItem createCheckBoxMenuItem(String text, int index) {

        JCheckBoxMenuItem item = new JCheckBoxMenuItem(text);
        item.setState(true);
        item.addActionListener(e -> {

            if (item.getState())
                showColumn(index);
            else
                hideColumn(index);

        });

        return item;
    }
    //endregion

    void init() {
        resetButton.setEnabled(false);
        game = new Game(this);

    }

    void setListeners() {

        //region boczne
        firstButton.addActionListener(e -> {

            if (loaded) {

                currentEmployee = employees.get(0);
                updateInformation();

                infClear();
            } else
                infShowError("File not loaded");

        });

        lastButton.addActionListener(e -> {

            if (loaded) {
                currentEmployee = employees.get(employees.size() - 1);
                updateInformation();

                infClear();
            } else
                infShowError("File not loaded");

        });

        previousButton.addActionListener(e -> {
            if (!loaded) {
                infShowError("File not loaded");
                return;
            }

            int currentIndex = employees.indexOf(currentEmployee);

            if (currentIndex > 0) {
                currentEmployee = employees.get(--currentIndex);
                updateInformation();
                infClear();
            }

            currentEmployee = employees.get(currentIndex);
        });

        nextButton.addActionListener(e -> {
            if (!loaded) {
                infShowError("File not loaded");
                return;
            }

            int currentIndex = employees.indexOf(currentEmployee);

            if (currentIndex < employees.size() - 1) {
                currentEmployee = employees.get(++currentIndex);
                updateInformation();
                infClear();
            }

            currentEmployee = employees.get(currentIndex);
        });

        //endregion

        //region górne
        addButton.addActionListener(e -> {
            infClear();
            add();
        });

        saveButton.addActionListener(e -> {
            infClear();
            save();
        });

        writeButton.addActionListener(e -> {
            infClear();
            openWriteWindow();
        });

        findButton.addActionListener(e -> {
            infClear();
            find();
        });

        resetButton.addActionListener(e -> {
            infClear();
            resetSearching();
        });

        //endregion

        tabbedPane.addChangeListener(e -> {
            if (loaded)
                updateInformation();
        });

    }

    //region inf

    public void infShowMessage(String str) {
        message.setText(str);
        message.setForeground(Color.black);

    }

    public void infShowError(String str) {
        message.setText(str);
        message.setForeground(Color.red);

    }

    public void infClear() {
        message.setText("");

        for (var e : errorMessages) {
            e.setText("");
        }

    }

    //endregion

    //region Load Write

    void openLoadWindow() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\dane"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            loadWithProgressBar(frame, fileChooser.getSelectedFile().toString());

    }

    private void loadWithProgressBar(Component parent, String path) {

        File file = new File(path);

        new Thread(() -> {

            ProgressMonitorInputStream pMonitorInputStream;
            try (BufferedInputStream bis = new BufferedInputStream(pMonitorInputStream = new ProgressMonitorInputStream(parent, "Reading file", new FileInputStream(file)))) {
                ProgressMonitor progressMonitor = pMonitorInputStream.getProgressMonitor();
                progressMonitor.setMillisToDecideToPopup(1);
                progressMonitor.setMillisToPopup(1);

                byte[] buffer = new byte[2048];
                while ((bis.read(buffer)) != -1) {
                    progressMonitor.setNote(bis.available() / 1024 + " more kb to read.");

                }

                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                emAL = (EmployeesAL) in.readObject();

                employees = emAL.getEmployeeList();
                currentEmployee = employees.get(0);
                newTableModel();

                faceContainer = emAL.getFaceContainer();
                wrappedFaces = faceContainer.getWrappers();
                game = new Game(this);

                updateInformation();
                infShowMessage("File loaded");
                loaded = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();

    }

    void openWriteWindow() {

        if (loaded) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\dane"));

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                write(fileChooser.getSelectedFile().toString());
            }

        } else {
            infShowError("File not loaded");
        }

    }

    void write(String path) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(emAL);
            infShowMessage("Writed");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //endregion

    //region information update
    void updateInformation() {
        updateFields();
        updateImage();
    }

    void updateFields() {

        textFieldId.setText(Long.valueOf(currentEmployee.getId()).toString());

        var firstName = (null == currentEmployee.getFname()) ? "" : currentEmployee.getFname().toString();
        textFieldFirstName.setText(firstName);

        var lastName = (null == currentEmployee.getLname()) ? "" : currentEmployee.getLname().toString();
        textFieldLastName.setText(lastName);

        var FieldAddress = (null == currentEmployee.getAddressLine()) ? "" : currentEmployee.getAddressLine().toString();
        textFieldAddress.setText(FieldAddress);

        var FieldBirthDate = (null == currentEmployee.getBirthDate()) ? "" : currentEmployee.getBirthDate().toString();
        textFieldBirthDate.setText(FieldBirthDate);

        var FieldEmployerName = (null == currentEmployee.getEmployerName()) ? "" : currentEmployee.getEmployerName().toString();
        textFieldEmployerName.setText(FieldEmployerName);

        var EmploymentDate = (null == currentEmployee.getEmploymentDate()) ? "" : currentEmployee.getEmploymentDate().toString();
        textFieldEmploymentDate.setText(EmploymentDate);

        var fireDateString = (null == currentEmployee.getReleaseDate()) ? "" : currentEmployee.getReleaseDate().toString();
        textFieldReleaseDate.setText(fireDateString);

        var Salary = (null == currentEmployee.getSalary()) ? "" : currentEmployee.getSalary().toString();
        textFieldSalary.setText(Salary);

        comboBoxJobTitle.setEditable(true);

        var selection = (null == currentEmployee.getJobtitle()) ? comboBoxJobTitle.getItemAt(0).toString() : currentEmployee.getJobtitle().toString();

        var model = comboBoxJobTitle.getModel();
        boolean found = false;
        for (var i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).toString().equalsIgnoreCase(selection)) {
                comboBoxJobTitle.setSelectedIndex(i);
                found = true;
            }
        }
        if (!found) {
            comboBoxJobTitle.addItem(selection);
            comboBoxJobTitle.setSelectedItem(selection);
        }

    }

    void clearFields() {

        textFieldId.setText("");
        textFieldFirstName.setText("");
        textFieldLastName.setText("");
        textFieldAddress.setText("");
        textFieldBirthDate.setText("");
        textFieldEmployerName.setText("");
        textFieldEmploymentDate.setText("");
        textFieldReleaseDate.setText("");
        textFieldSalary.setText("");

        comboBoxJobTitle.setEditable(true);
        comboBoxJobTitle.setSelectedItem(jobTitles[0]);

    }

    void updateImage() {

        BufferedImage pictureToShow = null;

        if (emAL.getEmployeeList().contains(currentEmployee)) {

            int index = emAL.getEmployeeList().indexOf(currentEmployee);

            try {
                pictureToShow = faceContainer.debuffer(wrappedFaces.get(index).getImage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else
            pictureToShow = new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);

        ((MyPanel) picture).setBufferedImage(pictureToShow);
        picture.revalidate();
        picture.repaint();

    }
    //endregion

    //region walidacja

    public static boolean checkNumber(String str) {
        try {
            Long.parseLong(str);
        } catch (NumberFormatException e) {

            return false;

        }
        return true;
    }

    int checkId(String id) {

        long idl;
        try {
            idl = Long.parseLong(id);
        } catch (NumberFormatException e) {

            return 1;

        }

        for (var em : employees) {
            if (idl == em.getId())
                return 2;
        }

        return 0;

    }

    public static boolean checkString(String str) {
        return str.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
    }

    public static boolean checkDate(String str) {

        if (!str.matches("\\d{4}-[01]\\d-[0-3]\\d"))
            return false;

        try {
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }

        return true;

    }

    public static String stringFormat(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    //endregion

    void add() {

        if (emAL == null) {
            infShowError("No employee list added");
            return;
        }

        Employee em = new Employee();

        Random r = new Random();

        long id;
        boolean used = false;
        do {
            id = Math.abs(r.nextLong());

            for (var emp : employees) {
                if (id == emp.getId())
                    used = true;
            }

        } while (used);

        em.setId(id);

        currentEmployee = em;
        updateFields();

    }

    void save() {

        if (emAL == null) {
            infShowError("No employee list added");
            return;
        }

        Employee em = currentEmployee;
        boolean error = false;

        //region Id
        if (textFieldId.getText().equals("")) {
            error = true;
            labelId.setText("Id required");

        } else if (checkId(textFieldId.getText()) == 1) {
            error = true;
            labelId.setText("Incorrect id");
        }
        //endregion

        //region FName
        if (textFieldFirstName.getText().equals("")) {
            error = true;
            labelFirstName.setText("First name required");
        } else if (!checkString(textFieldFirstName.getText())) {
            error = true;
            labelFirstName.setText("Incorrect name");
        }
        //endregion

        //region Lname
        if (textFieldLastName.getText().equals("")) {
            error = true;
            labelLastName.setText("Last name required");
        } else if (!checkString(textFieldLastName.getText())) {
            error = true;
            labelLastName.setText("Incorrect last name");
        }
        //endregion

        //region Adres
        if (textFieldAddress.getText().equals("")) {
            error = true;
            labelAddress.setText("Adress reguired");
        }
        //endregion adres

        //region Birth Date

        if (textFieldBirthDate.getText().equals("")) {
            error = true;
            labelBirthDate.setText("BirthDate required");
        } else if (!checkDate(textFieldBirthDate.getText())) {
            error = true;
            labelBirthDate.setText("Incorrect Birth Date");
        }

        //endregion Date

        //region Employer Name
        if (textFieldEmployerName.getText().equals("")) {
            error = true;
            labelEmployerName.setText("Employer name required");
        }
        //endregion

        //region Employment Date

        if (textFieldEmploymentDate.getText().equals("")) {
            error = true;
            labelEmploymentDate.setText("Employment Date required");
        } else if (!checkDate(textFieldEmploymentDate.getText())) {
            error = true;
            labelEmploymentDate.setText("Incorrect Employment Date");
        }

        //endregion Date

        //region Release Date

        if (!textFieldReleaseDate.getText().equals("") && !checkDate(textFieldReleaseDate.getText())) {
            error = true;
            labelReleaseDate.setText("Incorrect Release Date");
        }

        //endregion Date

        //region sallary
        if (textFieldSalary.getText().equals("")) {
            error = true;
            labelSalary.setText("Sallary required");
        } else if (!checkNumber(textFieldSalary.getText())) {
            error = true;
            labelSalary.setText("Incorrect Sallary");
        }
        //end region

        if (error)
            return;

        //dodawanie

        em.setId(Long.parseLong(textFieldId.getText()));
        em.setFname(stringFormat(textFieldFirstName.getText()));
        em.setLname(stringFormat(textFieldLastName.getText()));
        em.setAddressLine(textFieldAddress.getText());

        int y, m, d;
        String str = textFieldBirthDate.getText();
        y = Integer.parseInt(str.substring(0, 4));
        m = Integer.parseInt(str.substring(5, 7));
        d = Integer.parseInt(str.substring(8, 10));
        em.setBirthDate(LocalDate.of(y, m, d));

        em.setEmployerName(textFieldEmployerName.getText());

        str = textFieldEmploymentDate.getText();
        y = Integer.parseInt(str.substring(0, 4));
        m = Integer.parseInt(str.substring(5, 7));
        d = Integer.parseInt(str.substring(8, 10));
        em.setEmploymentDate(LocalDate.of(y, m, d));

        if (!textFieldReleaseDate.getText().equals("")) {

            str = textFieldReleaseDate.getText();
            y = Integer.parseInt(str.substring(0, 4));
            m = Integer.parseInt(str.substring(5, 7));
            d = Integer.parseInt(str.substring(8, 10));

            em.setReleaseDate(LocalDate.of(y, m, d));
        }

        em.setSalary(BigDecimal.valueOf(Long.parseLong(textFieldSalary.getText())));
        em.setJobtitle((String) comboBoxJobTitle.getSelectedItem());

        if (!employees.contains(em)) {
            employees.add(em);
            try {
                wrappedFaces.add(new ByteArrayWrapper(faceContainer.enbuffer(Generator.createGraphics())));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        updateFields();
        newTableModel();

        infShowMessage("Saved");

    }

    //region generowanie
    void showGenerateDialog() {
        JDialog d = new JDialog(frame, "Generate employyes");
        JPanel p = new JPanel();
        p.setLayout(null);

        JLabel l = new JLabel("Give number");
        l.setBounds(50, 20, 200, 20);
        p.add(l);

        JTextField tf = new JTextField();
        tf.setBounds(50, 50, 100, 20);
        p.add(tf);

        JButton b = new JButton("Generate");
        b.setBounds(50, 70, 100, 20);
        p.add(b);

        //error message
        JLabel le = new JLabel("");
        le.setForeground(Color.red);
        le.setBounds(50, 90, 200, 20);
        p.add(le);

        d.add(p);
        d.setSize(230, 160);
        d.setLocation(150, 150);
        d.setVisible(true);

        b.addActionListener(e -> {

            if (tf.getText().equals("")) {
                le.setText("no data");
            } else if (checkNumber(tf.getText())) {
                int n = Integer.parseInt(tf.getText());

                if (n <= 0 || n > 1500)
                    le.setText("give number from 1-1500");
                else {
                    le.setText("");
                    generateEmployees(n);
                    d.dispose();
                }

            } else {
                le.setText("bad format");
            }

        });

    }

    void generateEmployees(int n) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\dane"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            try {
                Generator.generate(n, fileChooser.getSelectedFile().toString());
                infShowMessage(n + " employees generated");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    //endregion

    //region wyszykiwanie

    void find() {

        List<Employee> searchResults = new ArrayList<>();

        if (emAL == null) {
            infShowError("No employee list added");
            return;
        }

        String searchingPhrase = textFieldSearch.getText();

        if (searchingPhrase.equals("")) {
            infShowError("Searching field is empty");
            return;
        }

        String str = (String) comboBoxSearch.getSelectedItem();

        switch (str) {
            case "Id":

                if (checkId(searchingPhrase) == 1) {
                    infShowError("Incorrect id");
                    return;
                }

                for (var em : employees) {
                    if (em.getId() == Long.parseLong(searchingPhrase)) {
                        searchResults.add(em);

                    }

                }

                break;
            case "Last Name":

                for (var em : employees) {
                    if (em.getLname().equalsIgnoreCase(searchingPhrase)) {
                        searchResults.add(em);

                    }
                }

                break;
            case "Birth Date":

                if (!checkDate(searchingPhrase)) {
                    infShowError("Incorrect date");
                    return;
                }

                for (var em : employees) {
                    if (em.getBirthDate().toString().equals(searchingPhrase)) {
                        searchResults.add(em);
                    }
                }

                break;
            case "Employment Date":

                if (!checkDate(searchingPhrase)) {
                    infShowError("Incorrect date");
                    return;
                }

                for (var em : employees) {
                    if (em.getEmploymentDate().toString().equals(searchingPhrase)) {
                        searchResults.add(em);

                    }
                }

                break;
        }

        if (searchResults.size() == 0) {
            infShowMessage("Not Found");
        } else {
            infShowMessage("Found: " + searchResults.size());
            employees = searchResults;
            currentEmployee = employees.get(0);
            updateInformation();

            resetButton.setEnabled(true);
            newSearchingTableModel();
        }

    }

    void resetSearching() {
        employees = emAL.getEmployeeList();
        currentEmployee = employees.get(0);
        updateInformation();

        resetButton.setEnabled(false);
        newTableModel();

        textFieldSearch.setText("");
        infShowMessage("Restarted");
    }

    //endregion

    //region tabela

    void newSearchingTableModel() {

        DefaultTableModel model = new SearchTableModel(employees);

        table.setModel(model);
        table.setRowHeight(20);

    }

    void newTableModel() {

        DefaultTableModel model = new MyTableModel(emAL);
        table.setModel(model);
        table.setRowHeight(100);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();

                    if (column == 4)
                        showPicture(row);

                }
            }
        });

        defaultTableCellMaxWidth = table.getColumnModel().getColumn(0).getMaxWidth();
        defaultTableCellWidth = table.getColumnModel().getColumn(0).getWidth();

    }

    void hideColumn(int index) {
        var column = table.getColumnModel().getColumn(index);
        column.setMinWidth(0);
        column.setMaxWidth(0);
    }

    void showColumn(int index) {
        var column = table.getColumnModel().getColumn(index);
        column.setMaxWidth(defaultTableCellMaxWidth);
        column.setMinWidth(defaultTableCellWidth);
    }

    void showPicture(int index) {
        JDialog dialog;

        dialog = new JDialog();

        dialog.setSize(450, 450);
        dialog.setLocation(150, 150);

        MyPanel p = new MyPanel();
        dialog.add(p);
        p.setLayout(null);

        BufferedImage picture = null;
        try {
            picture = faceContainer.debuffer(wrappedFaces.get(index).getImage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        p.setBufferedImage(picture);
        p.revalidate();
        p.repaint();

        dialog.setVisible(true);

    }

    //endregion

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */

    private void createUIComponents() {

        message = new JLabel();
        comboBoxJobTitle = new JComboBox(jobTitles);
        comboBoxSearch = new JComboBox(searchCriteriaNames);

        //Komunikaty do walidacji
        labelId = new JLabel("");
        labelFirstName = new JLabel("");
        labelLastName = new JLabel("");
        labelAddress = new JLabel("");
        labelBirthDate = new JLabel("");
        labelEmployerName = new JLabel("");
        labelEmploymentDate = new JLabel("");
        labelReleaseDate = new JLabel("");
        labelSalary = new JLabel("");
        labelJobTitle = new JLabel("");

        errorMessages[0] = labelId;
        errorMessages[1] = labelFirstName;
        errorMessages[2] = labelLastName;
        errorMessages[3] = labelAddress;
        errorMessages[4] = labelBirthDate;
        errorMessages[5] = labelEmployerName;
        errorMessages[6] = labelEmploymentDate;
        errorMessages[7] = labelReleaseDate;
        errorMessages[8] = labelSalary;
        errorMessages[9] = labelJobTitle;

        for (var e : errorMessages) {
            e.setForeground(Color.red);
        }

        picture = new MyPanel();
        tabbedPane = new JTabbedPane();
        animationPanel = new MyPanel();

        // TODO: place custom component creation code here
    }

//region Wygenerowany kod

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setEnabled(true);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel1.add(panel2, BorderLayout.NORTH);
        addButton = new JButton();
        addButton.setHorizontalAlignment(0);
        addButton.setHorizontalTextPosition(0);
        addButton.setText("Add");
        addButton.setVerticalAlignment(0);
        panel2.add(addButton);
        saveButton = new JButton();
        saveButton.setText("Save");
        panel2.add(saveButton);
        writeButton = new JButton();
        writeButton.setText("Write");
        panel2.add(writeButton);
        findButton = new JButton();
        findButton.setText("Find");
        panel2.add(findButton);
        comboBoxSearch.setToolTipText("");
        panel2.add(comboBoxSearch);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel2.add(panel3);
        final JLabel label1 = new JLabel();
        label1.setText("Search:");
        panel3.add(label1, BorderLayout.NORTH);
        textFieldSearch = new JTextField();
        panel3.add(textFieldSearch, BorderLayout.CENTER);
        resetButton = new JButton();
        resetButton.setText("Reset");
        panel3.add(resetButton, BorderLayout.SOUTH);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel1.add(panel4, BorderLayout.WEST);
        lastButton = new JButton();
        lastButton.setText(">>");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(lastButton, gbc);
        nextButton = new JButton();
        nextButton.setText(">");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(nextButton, gbc);
        previousButton = new JButton();
        previousButton.setText("<");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(previousButton, gbc);
        firstButton = new JButton();
        firstButton.setText("<<");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(firstButton, gbc);
        panel1.add(tabbedPane, BorderLayout.CENTER);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        tabbedPane.addTab("Edit employee", panel5);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel5.add(panel6, gbc);
        textFieldAddress = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 200;
        panel6.add(textFieldAddress, gbc);
        textFieldBirthDate = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldBirthDate, gbc);
        textFieldLastName = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldLastName, gbc);
        textFieldFirstName = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldFirstName, gbc);
        textFieldId = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldId, gbc);
        textFieldEmployerName = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldEmployerName, gbc);
        textFieldEmploymentDate = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldEmploymentDate, gbc);
        textFieldReleaseDate = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldReleaseDate, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Id");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("First Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Last Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("BirthDate");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label5, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Employer Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label6, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Employment Date");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label7, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("Release Date");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label8, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Salary");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label9, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(comboBoxJobTitle, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("Job Title");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label10, gbc);
        textFieldSalary = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(textFieldSalary, gbc);
        message.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(message, gbc);
        final JLabel label11 = new JLabel();
        label11.setText("Address");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label11, gbc);
        labelId.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelId, gbc);
        labelFirstName.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelFirstName, gbc);
        labelLastName.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelLastName, gbc);
        labelAddress.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelAddress, gbc);
        labelBirthDate.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelBirthDate, gbc);
        labelEmployerName.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelEmployerName, gbc);
        labelEmploymentDate.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelEmploymentDate, gbc);
        labelReleaseDate.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelReleaseDate, gbc);
        labelSalary.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelSalary, gbc);
        labelJobTitle.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(labelJobTitle, gbc);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tabbedPane.addTab("Employee List", panel7);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel7.add(scrollPane1);
        table = new JTable();
        scrollPane1.setViewportView(table);
        tabbedPane.addTab("Picture", picture);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tabbedPane.addTab("Game", panel8);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel8.add(scrollPane2);
        tableGame = new JTable();
        scrollPane2.setViewportView(tableGame);
        tabbedPane.addTab("Animation", animationPanel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    //endregion

}








