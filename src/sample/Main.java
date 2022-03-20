package sample;
import javafx.application.Application;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost/";
    public static final String user = "root";
    public static final String pass = "orange";
    public Connection conn;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create Database Student
            System.out.println("Connecting and creating database...");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            String createDatabase = "CREATE DATABASE IF NOT EXISTS Student";
            PreparedStatement createD = conn.prepareStatement(createDatabase);
            createD.executeUpdate();
            System.out.println("Database Student created successfully.");
            String url1 = "jdbc:mysql://localhost/student";
            Class.forName(driver);
            conn = DriverManager.getConnection(url1, user, pass);

            // Drop Tables if they exist
            System.out.println("Dropping tables...");
            String dropC = "DROP TABLE IF EXISTS Classes";
            String dropC1 = "DROP TABLE IF EXISTS Courses";
            String dropS = "DROP TABLE IF EXISTS Students";
            PreparedStatement dC = conn.prepareStatement(dropC);
            dC.executeUpdate();
            PreparedStatement dC1 = conn.prepareStatement(dropC1);
            dC1.executeUpdate();
            PreparedStatement dS = conn.prepareStatement(dropS);
            dS.executeUpdate();

            // Create Tables for Students, Courses, Classes
            System.out.println("Creating tables...");
            String studentsTable = "CREATE TABLE IF NOT EXISTS Students" +
                    "(empID INTEGER NOT NULL, " +
                    "firstName varchar(255) NOT NULL, " +
                    "lastName varchar(255) NOT NULL, " +
                    "email varchar(255) NOT NULL, " +
                    "sex char(1) NOT NULL CHECK (sex IN ('F', 'M', 'U')), " +
                    "PRIMARY KEY(empID))";
            PreparedStatement studentsT = conn.prepareStatement(studentsTable);
            studentsT.executeUpdate();
            System.out.println("Table Students created successfully.");

            String coursesTable = "CREATE TABLE IF NOT EXISTS Courses " +
                    "(courseID INTEGER NOT NULL, " +
                    "courseTitle varchar(255) NOT NULL, " +
                    "department varchar(255) NOT NULL, " +
                    "PRIMARY KEY(courseID))";
            PreparedStatement coursesT = conn.prepareStatement(coursesTable);
            coursesT.executeUpdate();
            System.out.println("Table Courses created successfully.");

            String classesTable = "CREATE TABLE IF NOT EXISTS Classes " +
                    "(courseID INTEGER NOT NULL, " +
                    "studentID INTEGER NOT NULL, " +
                    "sectionNo INTEGER NOT NULL CHECK (sectionNo IN (1, 2, 3)), " +
                    "year INTEGER NOT NULL, " +
                    "semester varchar(255) NOT NULL CHECK " +
                        "(semester IN ('Fall', 'Spring', 'Summer', 'Winter')), " +
                    "grade char(1) NOT NULL CHECK (grade IN ('A', 'B', 'C', 'D', 'F', 'W')), " +
                    "PRIMARY KEY(sectionNo, courseID, studentID), " +
                    "FOREIGN KEY(courseID) REFERENCES Courses(courseID), " +
                    "FOREIGN KEY(studentID) REFERENCES Students(empID))";
            PreparedStatement classesT = conn.prepareStatement(classesTable);
            classesT.executeUpdate();
            System.out.println("Table Classes created successfully.");

            // Creating Arrays for Attributes for Tables
            // 30 Students:
            int[] eID = new int[]{11266424, 11821112, 18811803, 19910119, 21345113, 22233291,
                                24081145, 33929935, 35286659, 42299632, 55772194, 58944825,
                                63627728, 66385885, 73203134, 74638275, 77351330, 77442994,
                                77739921, 79182291, 80356738, 89208038, 90499200, 90799549,
                                91624228, 94773558, 99300246, 99422334, 99781821, 99948372};
            String[] fN = new String[]{"Abigail", "Adam", "Alissa", "Ben", "Carol", "Clay",
                                        "Daniel", "Eric", "Esther", "Frank", "George", "Georgina",
                                        "Isaac", "Jacob", "Jamal", "Jane", "Janice", "Jessica",
                                        "Jonathon", "Karen", "Leon", "Lillian", "Linda", "Lucy",
                                        "Martha", "Nadia", "Noah", "Samuel", "Sarah", "Xavier"};
            String[] lN = new String[]{"Adams", "Akhter", "Anderson", "Chen", "Davis", "Garcia",
                                        "Gomez", "Gonzales", "Hussain", "Jacobson", "Kahnwald", "Kim",
                                        "Lee", "Martinez", "Miller", "Minaj", "Nakamura", "Nguyen",
                                        "Park", "Patel", "Peters", "Rivera", "Rodriguez", "Sato",
                                        "Smith", "Taylor", "Ward", "Warner", "Williams", "Wong"};
            String[] e = new String[]{"aadams4@citymail.cuny.edu", "aakhter32@citymail.cuny.edu","aanderson6@citymail.cuny.edu",
                                        "bchen5@citymail.cuny.edu", "cdavis86@citymail.cuny.edu", "cgarcia45@citymail.cuny.edu",
                                        "dgomez8@citymail.cuny.edu", "egonzales77@citymail.cuny.edu", "ehussain93@citymail.cuny.edu",
                                        "fjacobson73@citymail.cuny.edu", "gkahnwald93@citymail.cuny.edu", "gkim4@citymail.cuny.edu",
                                        "ilee54@citymail.cuny.edu", "jmartinez83@citymail.cuny.edu", "jmiller73@citymail.cuny.edu",
                                        "jminaj55@citymail.cuny.edu", "jnakamura43@citymail.cuny.edu", "jnguyen92@citymail.cuny.edu",
                                        "jpark73@citymail.cuny.edu", "kpatel77@citymail.cuny.edu", "lpeters59@citymail.cuny.edu",
                                        "lrivera57@citymail.cuny.edu", "lrodriguez59@citymail.cuny.edu", "lsato99@citymail.cuny.edu",
                                        "msmith44@citymail.cuny.edu", "ntaylor86@citymail.cuny.edu", "nward35@citymail.cuny.edu",
                                        "swarner64@citymail.cuny.edu", "swilliams37@citymail.cuny.edu", "xwong12@citymail.cuny.edu"};
            String[] s = new String[]{"F", "M", "F", "M", "F", "M", "M", "M", "F", "M", "U", "F", "M", "M", "M",
                                        "U", "F", "F", "M", "F", "M", "F", "F", "F", "F", "F", "M", "M", "M", "M"};
            // 3 Courses:
            int[] cID = new int[]{10293, 23294, 36273};
            String[] cT = new String[]{"CSC 220 [Algorithms]", "ECO 104 [Intro to Quantitative Economics]",
                                        "MATH 391 [Differential Equations]"};
            String[] d = new String[]{"Computer Science", "Economics", "Mathematics"};

            // Classes
            // For this project, classes I've chosen are CSC 220 Fall 2020, ECO 104 Spring 2020, MATH 391 Summer 20193
            int[] sN = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
            int[] y = new int[]{2020, 2019, 2019, 2020, 2019, 2020, 2019, 2020, 2020, 2020, 2020, 2020, 2020, 2020, 2020,
                                2019, 2020, 2019, 2020, 2020, 2020, 2020, 2020, 2020, 2019, 2020, 2020, 2020, 2020, 2020};
            String[] sem = new String[]{"Fall", "Summer", "Summer", "Fall", "Summer", "Fall", "Summer", "Spring", "Fall", "Spring",
                                        "Fall", "Spring", "Spring", "Fall", "Fall", "Summer", "Spring", "Summer", "Fall", "Spring",
                                        "Fall", "Fall", "Fall", "Fall", "Summer", "Fall", "Spring", "Fall", "Fall", "Fall"};
            String[] g = new String[]{"D", "C", "B", "F", "A", "W", "B", "D", "C", "A", "B", "F", "W", "B", "A",
                                      "F", "B", "W", "B", "C", "B", "D", "A", "A", "D", "A", "C", "B", "B", "B"};
            int[] cID1 = new int[]{10293, 36273, 36273, 10293, 36273, 10293, 36273, 23294, 10293, 23294, 10293, 23294, 23294, 10293, 10293,
                                    36273, 23294, 36273, 10293, 23294, 10293, 10293, 10293, 10293, 36273, 10293, 23294, 10293, 10293, 10293};

            // Insert Values into Students, Courses, Classes
            System.out.println("Inserting values...");
            String studentsInsert = "INSERT IGNORE INTO Students (empID, firstName, lastName, email, sex) " +
                                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement studentsI = conn.prepareStatement(studentsInsert);
            for (int i = 0; i < 30; i++) {
                studentsI.setInt(1, eID[i]);
                studentsI.setString(2, fN[i]);
                studentsI.setString(3, lN[i]);
                studentsI.setString(4, e[i]);
                studentsI.setString(5, s[i]);
                studentsI.executeUpdate();
            }
            System.out.println("Students Table insertion successful.");

            String coursesInsert = "INSERT IGNORE INTO Courses (courseID, courseTitle, department) " +
                                    "VALUES (?, ?, ?)";
            PreparedStatement coursesI = conn.prepareStatement(coursesInsert);
            for (int i = 0; i < 3; i++) {
                coursesI.setInt(1, cID[i]);
                coursesI.setString(2, cT[i]);
                coursesI.setString(3, d[i]);
                coursesI.executeUpdate();
            }
            System.out.println("Courses Table insertion successful.");

            String classesInsert = "INSERT IGNORE INTO Classes (courseID, studentID, sectionNo, year, semester, grade) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement classesI = conn.prepareStatement(classesInsert);
            for (int i = 0; i < 30; i++) {
                classesI.setInt(1, cID1[i]);
                classesI.setInt(2, eID[i]);
                classesI.setInt(3, sN[i]);
                classesI.setInt(4, y[i]);
                classesI.setString(5, sem[i]);
                classesI.setString(6, g[i]);
                classesI.executeUpdate();
            }
            System.out.println("Classes Table insertion successful.");

            // Draw Pie Chart
            StackPane pane = new StackPane();
            Scene scene = new Scene(pane, 1000, 500);
            Canvas canvas = new Canvas(1000,500);
            canvas.setMouseTransparent(true);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            //Dropdown Menus
            Label enter = new Label("Course:  ");
            enter.setFont(new Font("Arial", 15));
            ObservableList<String> courses = FXCollections.observableArrayList(cT);
            ChoiceBox<String> c = new ChoiceBox(courses);
            Label enter1 = new Label("       Semester:  ");
            enter1.setFont(new Font("Arial", 15));
            String[] sem1 = new String[]{"Fall", "Spring", "Summer"};
            ObservableList<String> semesters = FXCollections.observableArrayList(sem1);
            ChoiceBox<String> c1 = new ChoiceBox(semesters);
            Label enter2 = new Label("       Year:  ");
            enter2.setFont(new Font("Arial", 15));
            Integer[] yrs = new Integer[]{2020, 2019};
            ObservableList<Integer> years = FXCollections.observableArrayList(yrs);
            ChoiceBox<Integer> c2 = new ChoiceBox(years);
            Label note = new Label("* * NOTE: For this project, classes I've chosen are CSC 220 Fall 2020, ECO 104 Spring 2020,"+
                                    "\nMATH 391 Summer 2019, due to limited values in Tables. * *");
            Font font = Font.font("Verdana", FontPosture.ITALIC, 15);
            note.setFont(font);
            note.setTranslateX(0);
            note.setTranslateY(220);
            HBox hb = new HBox();
            hb.setPadding(new Insets(10, 5 , 5, 50));
            hb.getChildren().addAll(enter, c, enter1, c1, enter2, c2);
            Button r = new Button("Get Results");
            r.setFont(new Font("Arial", 15));
            r.setTranslateX(260);
            r.setTranslateY(-229);
            r.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    String cChoice = c.getValue();
                    String sChoice = c1.getValue();
                    Integer yChoice = c2.getValue();
                    String[] grades = new String[]{"A", "B", "C", "D", "F", "W"};
                    int n = grades.length;
                    MyPieChart p = new MyPieChart(new MyPoint(70, 120), 300, 300, n,
                            Main.getProbOrFreq(conn, cChoice, sChoice, yChoice,"Prob"),
                            Main.getProbOrFreq(conn, cChoice, sChoice, yChoice,"Freq"));
                    p.draw(gc);
                }
            });
            System.out.println("The number of students for each letter grade in CSc 22000 [Algorithms] in the Fall 2020 semester:");
            LinkedHashMap<String, Double> t = Main.getProbOrFreq(conn, "CSC 220 [Algorithms]",
                                                        "Fall", 2020,"Freq");
            for (Map.Entry<String, Double> entry : t.entrySet()) {
                String grade = entry.getKey();
                Double numStudents = entry.getValue();
                System.out.println("Grade: " + grade + " -> Students: " + Math.round(numStudents));
            }
            pane.getChildren().add(hb);
            pane.getChildren().add(r);
            pane.getChildren().add(note);
            pane.getChildren().add(canvas);
            primaryStage.setTitle("Pie Chart");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Gets LinkedHashMaps for Probabilities or Frequencies
     */
    public static LinkedHashMap<String, Double> getProbOrFreq(Connection conn, String course, String semester, Integer year, String fp)
    {
        try
        {
            String[] grades = new String[]{"A", "B", "C", "D", "F", "W"};
            int n = grades.length;
            LinkedHashMap<String, Double> map = new LinkedHashMap<String, Double>(n);
            int[] freq = new int[n];
            int total = 0;
            for (int i = 0; i < n; i++) {
                int count = 0;
                Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT Classes.grade, Students.firstName, Students.lastName, " +
                                                        "Courses.courseTitle, Classes.semester, Classes.year " +
                                                        "FROM ((Classes INNER JOIN Students ON Classes.studentID = Students.empID) " +
                                                        "INNER JOIN Courses ON Classes.courseID = Courses.courseID) " +
                                                        "WHERE Classes.grade = '" + grades[i] + "' && Courses.courseTitle = '" + course + "' " +
                                                        "&& Classes.semester = '" + semester + "' && Classes.year = '" + year + "'");
                while (rset.next()) {
                    count++;
                    freq[i] = count;
                }
                total += count;
            }
            if(fp.equals("Prob")) {
                for (int i = 0; i < n; i++) {
                    String g = grades[i];
                    double p = (freq[i]*100)/(double)(total);
                    map.put(g, p);
                }
            } else if(fp.equals("Freq")) {
                for (int i = 0; i < n; i++) {
                    String a = grades[i];
                    double f = freq[i];
                    map.put(a, f);
                }
            }
            return map;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

