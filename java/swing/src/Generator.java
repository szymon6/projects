import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Generator {

    private static EmployeesAL emAL;
    private static FaceContainer faceContainer;

    static public void generate(int number, String path) throws IOException, WrongInputArraysLengthException {

        emAL = new EmployeesAL(0, "BDA", new ArrayList<>());
        uniqueAdd(emAL.getEmployeeList(), number);

        faceContainer = new FaceContainer();
        uniqueAddFace(number);

        emAL.setFaceContainer(faceContainer);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(emAL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void uniqueAddFace(int n) throws IOException {

        BufferedImage generatedFace;

        for (int i = 0; i < n; i++) {
            generatedFace = createGraphics();
            faceContainer.getBufferedImages().add(generatedFace);
        }

        faceContainer.enbufferAll();

    }

    //region generowanie zdjecia

    static private int DEFAULT_WIDTH = 450, DEFAULT_HEIGHT = 450;
    private static int eyeSize;
    private static int eyeHeight;
    private static int eyeWidth;

    private static int p1x;
    private static int p1y;

    private static int p2x;
    private static int p2y;

    private static int p3x;
    private static int p3y;

    private static int mountY;
    private static int mountWidth;
    private static int startAngle;

    private static int hair1x;
    private static int hair1y;

    private static int hair2x;
    private static int hair2y;

    private static int prob;

    private static int col;

    private static int earsSize;
    private static int earsX;
    private static int earsY;

    private static Random r = new Random();

    static void randomize() {
        eyeSize = r.nextInt(15) + 10;
        eyeHeight = r.nextInt(20) + 100;
        eyeWidth = r.nextInt(15);

        p1x = r.nextInt(10);
        p1y = r.nextInt(10);

        p2x = r.nextInt(10);
        p2y = r.nextInt(10);

        p3x = r.nextInt(10);
        p3y = r.nextInt(10);

        mountY = r.nextInt(30);
        startAngle = r.nextInt(5) == 1 ? r.nextInt(30) : r.nextInt(30) + 160;
        mountWidth = r.nextInt(20) - 10;

        col = r.nextInt(20);

        earsSize = r.nextInt(10);
        earsX = r.nextInt(10);
        earsY = r.nextInt(10);

    }

    public static BufferedImage createGraphics() {
        randomize();

        BufferedImage bufferedImage = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        double leftX = 70;
        double topY = 20;
        double width = 280;
        double height = 330;
        //rysuje białe tło
        var backRect = new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        g2.setColor(Color.WHITE);
        g2.fill(backRect);
        var rect = new Rectangle2D.Double(leftX, topY, width, height);
        // rysuje twarz - elipsę
        g2.setColor(new Color(255 - col, 207 - col, 160 - col));
        var ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.fill(ellipse);

        //uszy
        g2.fillOval(330 + earsX, 150 + earsY, 40 + earsSize, 70 + earsSize);
        g2.fillOval(50 - earsX, 150 + earsY, 40 + earsSize, 70 + earsSize);

        g2.setColor(Color.BLACK);
        g2.draw(ellipse);

        g2.setColor(Color.WHITE);
        g2.fillOval(Double.valueOf(leftX).intValue() + 100 - eyeWidth - eyeSize / 2, Double.valueOf(topY).intValue() - eyeSize / 2 + eyeHeight, eyeSize * 2, eyeSize * 2);
        g2.fillOval(Double.valueOf(leftX).intValue() + 170 + eyeWidth - eyeSize / 2, Double.valueOf(topY).intValue() - eyeSize / 2 + eyeHeight, eyeSize * 2, eyeSize * 2);
        g2.setColor(Color.BLACK);

        //oczy
        g2.fillOval(Double.valueOf(leftX).intValue() + 100 - eyeWidth, Double.valueOf(topY).intValue() + eyeHeight, eyeSize, eyeSize);
        g2.fillOval(Double.valueOf(leftX).intValue() + 170 + eyeWidth, Double.valueOf(topY).intValue() + eyeHeight, eyeSize, eyeSize);

        //nos
        g2.setStroke(new BasicStroke(3));
        g2.draw(new Line2D.Float(210 + p1x, 140 + p1y, 190 + p2x, 220 + p2y));
        g2.draw(new Line2D.Float(190 + p2x, 220 + p2y, 210 + p3x, 220 + p3y));
        //usta
        g2.drawArc(Double.valueOf(leftX).intValue() + 100, Double.valueOf(topY).intValue() + 220 + mountY, 80 + mountWidth, 20, startAngle, 160);

        //włosy
        int xdiff = 0;
        int hairLenght;
        prob = r.nextInt(8);

        if (prob != 0) {
            hairLenght = r.nextInt(30) - 20;

            for (int i = 0; i < 14; i++) {

                hair1x = r.nextInt(5);
                hair1y = r.nextInt(5);

                hair1x = r.nextInt(5);
                hair2y = r.nextInt(10);

                g2.draw(new Line2D.Float(140 + xdiff + hair1x, 40 + hair1y, 140 + xdiff + hair2x, 10 + hair2y + hairLenght));
                xdiff += 10;
            }

        }
        //Zarost
        prob = r.nextInt(4);
        if (prob == 0) {
            xdiff = 0;
            hairLenght = r.nextInt(30) - 20;
            for (int i = 0; i < 8; i++) {

                hair1x = r.nextInt(5);
                hair1y = r.nextInt(5);

                hair1x = r.nextInt(5);
                hair2y = r.nextInt(10);

                g2.draw(new Line2D.Float(170 + xdiff + hair1x, 360 + hair2y, 170 + xdiff + hair2x, 330 + hair1y + hairLenght));
                xdiff += 10;
            }
        }

        g2.dispose();

        return bufferedImage;

    }

//endregion

    static void uniqueAdd(List<Employee> employees, int number) throws IOException, WrongInputArraysLengthException {

        Random r = new Random();

        List<String> names = readFile(System.getProperty("user.dir") + "\\dane\\imiona.txt");
        List<String> surnames = readFile(System.getProperty("user.dir") + "\\dane\\nazwiska.txt");
        List<String> codes = readAddresses(System.getProperty("user.dir") + "\\dane\\kody.txt");
        List<String> companies = randCompanyList(r, 100);

        for (int i = 0; i < number; i++) {

            Employee em = new Employee();

            em.setId(Math.abs(r.nextLong()));
            em.setFname(stringFormat(names.get(r.nextInt(names.size() / 10))));
            em.setLname(stringFormat(surnames.get(r.nextInt(surnames.size() / 3))));

            em.setAddressLine(codes.get(r.nextInt(codes.size())));
            em.setBirthDate(randDate(r, LocalDate.of(1960, 1, 1)));
            em.setEmployerName(companies.get(r.nextInt(companies.size())));
            em.setEmploymentDate(randDate(r, em.getBirthDate().plusYears(18)));
            em.setSalary(BigDecimal.valueOf(r.nextInt(5000) + 1800));
            em.setJobtitle(genFromArrayJob(JobTitles, Lens));
            em.setPlayable(r.nextInt(2) == 0);

            if (i > 0) {
                while (check(employees, em))
                    em.setId(Math.abs(r.nextLong()));

            }

            employees.add(em);
        }

    }

    private static void uniqueAdd(Employee[] employees) throws IOException, WrongInputArraysLengthException {

        Random r = new Random();

        List<String> names = readFile(System.getProperty("user.dir") + "\\dane\\imiona.txt");
        List<String> surnames = readFile(System.getProperty("user.dir") + "\\dane\\nazwiska.txt");
        List<String> codes = readAddresses(System.getProperty("user.dir") + "\\dane\\kody.txt");
        List<String> companies = randCompanyList(r, 100);

        for (int i = 0; i < employees.length; i++) {

            Employee em = new Employee();

            em.setId(Math.abs(r.nextLong()));
            em.setFname(names.get(r.nextInt(names.size() / 10)));
            em.setLname(surnames.get(r.nextInt(surnames.size() / 3)));

            em.setAddressLine(codes.get(r.nextInt(codes.size())));
            em.setBirthDate(randDate(r, LocalDate.of(1960, 1, 1)));
            em.setEmployerName(companies.get(r.nextInt(companies.size())));
            em.setEmploymentDate(randDate(r, em.getBirthDate().plusYears(18)));
            em.setSalary(BigDecimal.valueOf(r.nextInt(5000) + 1800));
            em.setJobtitle(genFromArrayJob(JobTitles, Lens));

            if (i > 0) {
                while (check(employees, em)) {
                    em.setId(Math.abs(r.nextLong()));
                }

            }

            employees[i] = em;
        }

    }

    private static String stringFormat(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    private static boolean check(List<Employee> employees, Employee employee) {

        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).getId() == employee.getId())
                return true;

        }
        return false;
    }

    private static boolean check(Employee[] employees, Employee employee) {

        for (int i = 0; i < employees.length; i++) {

            if (employees[i] == null) break;

            if (employees[i].getId() == employee.getId())
                return true;

        }
        return false;
    }

//Dane

    private static List<String> randCompanyList(Random r, int size) throws WrongInputArraysLengthException {

        List<String> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            results.add(genAcronym(2 + r.nextInt(3), "") + " " + genFromArrays(legalForms, legalLens));
        }
        return results;
    }

    private static LocalDate randDate(Random random, LocalDate startDate) {

        LocalDate result;

        do {
            result = startDate.plusYears(random.nextInt(30)).plusMonths(random.nextInt(12)).plusDays(random.nextInt(31));
        } while (result.getYear() > 2019);

        return result;

    }

    public static List<String> readFile(String fileName) throws FileNotFoundException {
        List<String> results = new ArrayList<>();
        try (Scanner in = new Scanner(new FileInputStream(fileName))) {
            in.nextLine();
            while (in.hasNext()) {
                String s = in.nextLine();
                int pos = s.indexOf(",");
                String ins = (pos == -1) ? s : s.substring(0, pos);
                results.add(ins);
            }
        }
        return results;
    }

//Generowanie Adresów

    private static List<String> readAddresses(String filename) throws IOException {
        List<String> lst = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"));
        String line = null;
        String pattern = "[0-9]+";
        Pattern r = Pattern.compile(pattern);
        while ((line = bf.readLine()) != null) {
            String[] parts = line.split(";");

            String s1 = parts[1];
            String s2 = parts[2];
            Matcher m = r.matcher(s2);
            Optional<String> matched = (!m.find()) && s1.contains("ul") ? Optional.of(s2 + " " + parts[0] + " " + rebuildString(s1, " od ")) : Optional.empty();

            matched.ifPresent(lst::add);

        }

        return lst;
    }

    private static String rebuildString(String str, String toRemove) {
        Random random = new Random();
        int ind = str.indexOf(toRemove);
        String result = (ind != -1) ? (str.substring(0, ind)) : str;
        return result + " " + (random.nextInt(100) + 1);
    }

// Generowanie nazwy firmy

    private static final String[] legalForms = {"sp. z o.o.", "Spółka Jawna", "S.A.", "Spółka Komandytowa", "s.c."};
    private static final Integer[] legalLens = {10, 2, 5, 1, 2};

    public static String genAcronym(int acLen, String separator) {
        Random random = new Random();
        var capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<Character> results = new ArrayList<>();
        for (var i = 0; i < acLen; i++) {
            results.add(capitals.charAt(random.nextInt(acLen)));
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder separatorsb = new StringBuilder(separator);
        results.forEach(w -> {
            sb.append(w);
            sb.append(separator);
        });

        return sb.toString();
    }

    public static String genFromArrays(String[] array, Integer[] lens) throws WrongInputArraysLengthException {
        if (array.length != lens.length)
            throw new WrongInputArraysLengthException("Improper input arrays!!!");
        Random random = new Random();
        var sumLen = Stream.of(lens).reduce(0, (x, y) -> x + y);
        var resultArr = new String[sumLen];
        var start = 0;
        for (var i = 0; i < lens.length; i++) {
            for (var j = start; j < (lens[i] + start); j++) {
                resultArr[j] = array[i];
            }
            start += lens[i];
        }
        var gen = random.nextInt(sumLen);
        return resultArr[gen];
    }

    // Generowanie stanowsik
    private static final String[] JobTitles = {
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

    private static final Integer[] Lens = {3, 1, 4, 8, 9, 7, 11, 6, 7, 3};

    public static String genFromArrayJob(String[] array, Integer[] lens) throws WrongInputArraysLengthException {
        if (array.length != lens.length)
            throw new WrongInputArraysLengthException("Improper input arrays!!!");
        Random random = new Random();
        var sumLen = Stream.of(lens).reduce(0, (x, y) -> x + y);
        var resultArr = new String[sumLen];
        var start = 0;
        for (var i = 0; i < lens.length; i++) {
            for (var j = start; j < (lens[i] + start); j++) {
                resultArr[j] = array[i];
            }
            start += lens[i];
        }
        var gen = random.nextInt(sumLen);
        return resultArr[gen];
    }

}



