import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

public class StudentInformationSystem {

    // Student class
    static class Student {
        int roll_no;
        String name;
        int age;
        float marks;
        char gender;
        String course;
        int admission_year;
    }

    // Global array to store student records
    static Student[] students = new Student[100];
    static int studentCount = 0; // Number of students in the system(initially 0)
    static Scanner scanner = new Scanner(System.in); // Global Scanner instance to take inputs

    // Method to perform login
    static void login() {
        System.out.print("\nEnter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        if ("vishal".equals(username) && "admin@12".equals(password)) {
            System.out.println("\nLogged in successfully!\n");
        } else {
            System.out.println("\nInvalid username or password. Please try again.\n");
            System.exit(0);
        }
    }

    // Method to add a new student
    static void addStudent() {
        Student newStudent = new Student();

        int rollExists;
        do {
            rollExists = 0;
            System.out.print("\nEnter Student Roll Number: ");
            newStudent.roll_no = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < studentCount; i++) {
                if (students[i].roll_no == newStudent.roll_no) {
                    System.out.println("Roll number already exists. Please give a unique roll number.\n");
                    rollExists = 1;
                    break;
                }
            }
        } while (rollExists == 1);

        System.out.print("Enter Student Name: ");
        newStudent.name = scanner.nextLine();

        while (true) {
            System.out.print("Enter Student Age[16-40]: ");
            newStudent.age = scanner.nextInt();
            if (newStudent.age < 16 || newStudent.age > 40) {
                System.out.println("\nplease fill a valid age from 16-40.\n");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Enter Student Marks in percentage[0-100]: ");
            newStudent.marks = scanner.nextFloat();
            if (newStudent.marks < 0 || newStudent.marks > 100) {
                System.out.println("\nplease fill valid marks between 0-100.\n");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Enter Student gender[m/g]: ");
            newStudent.gender = scanner.next().charAt(0);
            if (newStudent.gender != 'm' && newStudent.gender != 'g') {
                System.out.println("\nplease fill valid gender either m or g.\n");
            } else {
                break;
            }
        }

        System.out.print("Enter Student Course: ");
        newStudent.course = scanner.next();

        while (true) {
            System.out.print("Enter Student Admission Year[2000-2024]: ");
            newStudent.admission_year = scanner.nextInt();
            if (newStudent.admission_year < 2000 || newStudent.admission_year > 2024) {
                System.out.println("\nplease fill a valid admission year.\n");
            } else {
                break;
            }
        }

        students[studentCount] = newStudent;
        studentCount++;

        System.out.println("\n" + newStudent.name + " added successfully.\n");
    }

    // Method to update information of the student
    static void updateStudent(int roll) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].roll_no == roll) {
                String updatedName = students[i].name; // Stored name of the student whose data is to be updated.
                System.out.print("\nEnter new name of the Student: ");
                scanner.nextLine();
                students[i].name = scanner.nextLine();

                while (true) {
                    System.out.print("Enter new age of the Student[16-40]: ");
                    students[i].age = scanner.nextInt();
                    if (students[i].age < 16 || students[i].age > 40) {
                        System.out.println("\nplease fill a valid age from 16-40.\n");
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.print("Enter new marks of the Student in percentage[0-100]: ");
                    students[i].marks = scanner.nextFloat();
                    if (students[i].marks < 0 || students[i].marks > 100) {
                        System.out.println("\nplease fill valid marks between 0-100.\n");
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.print("Enter new gender[m/g] of the Student: ");
                    students[i].gender = scanner.next().charAt(0);
                    if (students[i].gender != 'm' && students[i].gender != 'g') {
                        System.out.println("\nplease fill valid gender either m or g.\n");
                    } else {
                        break;
                    }
                }

                System.out.print("Enter new course of the Student: ");
                students[i].course = scanner.next();

                while (true) {
                    System.out.print("Enter new admission year of the Student[2000-2024]: ");
                    students[i].admission_year = scanner.nextInt();
                    if (students[i].admission_year < 2000 || students[i].admission_year > 2024) {
                        System.out.println("\nplease fill a valid admission year.\n");
                    } else {
                        break;
                    }
                }

                System.out.println("\n" + updatedName + " updated successfully.\n");
                return;
            }
        }
        System.out.println("\nStudent not found.\n");
    }

    // Method to display a list of all existing students
    static void displayAllStudents() {
        if (studentCount == 0) {
            System.out.println("\nNo records found.\n");
            return;
        }

        System.out.println("\nStudent Records:");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Roll No\tName\t\tAge\tMarks\tGender\tCourse\tAdmission Year");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].roll_no + "\t" + students[i].name + "\t" + students[i].age + "\t"
                    + students[i].marks + "\t" + students[i].gender + "\t" + students[i].course + "\t"
                    + students[i].admission_year);
        }

        System.out.println("----------------------------------------------------------------------\n");
    }

    // Method to view or search a student with his/her roll number
    static void searchByRoll(int roll) {
        int found = 0;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].roll_no == roll) {
                found = 1;
                break;
            }
        }

        if (found == 1) {
            System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s %-15s\n",
                    "\nRoll Number", "Name", "Age", "Marks", "Gender", "Course", "Admission Year");

            for (int i = 0; i < studentCount; i++) {
                if (students[i].roll_no == roll) {
                    System.out.printf("%-15d %-20s %-10d %-10.2f %-10c %-20s %-15d\n",
                            students[i].roll_no, students[i].name, students[i].age,
                            students[i].marks, students[i].gender, students[i].course,
                            students[i].admission_year);
                }
            }
        } else {
            System.out.println("\nStudent not found.\n");
        }
    }

    // Method to view or search all students having the same roll number
    static void searchByName(String name) {
        int found = 0;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].name.equals(name)) {
                found = 1;
                break;
            }
        }

        if (found == 1) {
            System.out.printf("\nList of students with the name '%s':\n\n", name);
            System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s %-15s\n",
                    "Roll Number", "Name", "Age", "Marks", "Gender", "Course", "Admission Year");

            for (int i = 0; i < studentCount; i++) {
                if (students[i].name.equals(name)) {
                    System.out.printf("%-15d %-20s %-10d %-10.2f %-10c %-20s %-15d\n",
                            students[i].roll_no, students[i].name, students[i].age,
                            students[i].marks, students[i].gender, students[i].course,
                            students[i].admission_year);
                }
            }
        } else {
            System.out.println("\nNo student found with the name " + name + ".\n");
        }
    }

    // Method to display topper student with highest marks
    static int displayTopperStudent() {
        if (studentCount == 0) {
            System.out.println("\nNo students in the record.\n");
            return -1;
        }

        float highestMarks = students[0].marks;
        int index = 0;

        for (int i = 1; i < studentCount; i++) {
            if (students[i].marks > highestMarks) {
                highestMarks = students[i].marks;
                index = i;
            }
        }

        System.out.println("\nStudent with highest marks:\n\n");
        System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s %-15s\n",
                "Roll Number", "Name", "Age", "Marks", "Gender", "Course", "Admission Year");

        for (int i = 0; i < studentCount; i++) {
            if (students[i].marks == highestMarks) {
                System.out.printf("%-15d %-20s %-10d %-10.2f %-10c %-20s %-15d\n",
                        students[index].roll_no, students[index].name, students[index].age,
                        students[index].marks, students[index].gender, students[index].course,
                        students[index].admission_year);
            }
        }
        return index;
    }

    // Helper method to generate a certificate
    static void generateCertificate(String name, float marks, String course, int year) {
        // Get current time
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM, yyyy");
        String formattedDate = dateFormat.format(now);

        System.out.println("\n\u001B[1;36m===================================================\u001B[0m");
        System.out.println("\u001B[1;36m             Certificate of Achievement   \u001B[0m");
        System.out.println("\u001B[1;36m===================================================\u001B[0m");

        System.out.print("\n      This certificate is proudly presented to\n");
        System.out.printf("\t\t   \u001B[1;32m%s\u001B[0m\n", name);
        System.out.println("for achieving the highest marks in the course ");
        System.out.printf("\u001B[1;32m%s\u001B[0m", course);
        System.out.printf(" with a score of \u001B[1;32m%.0f%%\u001B[0m ", marks);
        System.out.printf("for the academic year \u001B[1;32m%d\u001B[0m.\n", year);
        System.out.printf("\n\t\t   On %s\n", formattedDate);
        System.out.print("\nCEO: Vishal Kumar\t\t     Congratulation\n");

        System.out.println("\u001B[1;36m===================================================\u001B[0m");
    }

    // Method to generate a certificate for the topper student
    static void generateCertificateForTopper(int topperIndex) {
        if (topperIndex != -1) { // If topper student found
            generateCertificate(students[topperIndex].name, students[topperIndex].marks, students[topperIndex].course,
                    students[topperIndex].admission_year);
        }
    }

    // Method to display all the students of a specific age
    static void displayStudentsOfSpecificAge(int displayAge) {
        int found = 0;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].age == displayAge) {
                found = 1;
                break;
            }
        }

        if (found == 1) {
            System.out.printf("\nList of students of the age '%d':\n\n", displayAge);
            System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s %-15s\n",
                    "Roll Number", "Name", "Age", "Marks", "Gender", "Course", "Admission Year");

            for (int i = 0; i < studentCount; i++) {
                if (students[i].age == displayAge) {
                    System.out.printf("%-15d %-20s %-10d %-10.2f %-10c %-20s %-15d\n",
                            students[i].roll_no, students[i].name, students[i].age,
                            students[i].marks, students[i].gender, students[i].course,
                            students[i].admission_year);
                }
            }
        } else {
            System.out.println("\nNo student found of the age " + displayAge + ".\n");
        }
    }

    // Method to display all the students who taken admission in a specific year
    static void displayStudentsOfSpecificYear(int year) {
        int found = 0;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].admission_year == year) {
                found = 1;
                break;
            }
        }

        if (found == 1) {
            System.out.printf("\nList of students in the year '%d':\n\n", year);
            System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s %-15s\n",
                    "Roll Number", "Name", "Age", "Marks", "Gender", "Course", "Admission Year");

            for (int i = 0; i < studentCount; i++) {
                if (students[i].admission_year == year) {
                    System.out.printf("%-15d %-20s %-10d %-10.2f %-10c %-20s %-15d\n",
                            students[i].roll_no, students[i].name, students[i].age,
                            students[i].marks, students[i].gender, students[i].course,
                            students[i].admission_year);
                }
            }
        } else {
            System.out.println("\nNo student found in the admission year " + year + ".\n");
        }
    }

    // Method to display all the students of a specific course
    static void displayStudentsOfSpecificCourse(String displayCourse) {
        int found = 0;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].course.equals(displayCourse)) {
                found = 1;
                break;
            }
        }

        if (found == 1) {
            System.out.printf("\nList of students with the course '%s':\n\n", displayCourse);
            System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s %-15s\n",
                    "Roll Number", "Name", "Age", "Marks", "Gender", "Course", "Admission Year");

            for (int i = 0; i < studentCount; i++) {
                if (students[i].course.equals(displayCourse)) {
                    System.out.printf("%-15d %-20s %-10d %-10.2f %-10c %-20s %-15d\n",
                            students[i].roll_no, students[i].name, students[i].age,
                            students[i].marks, students[i].gender, students[i].course,
                            students[i].admission_year);
                }
            }
        } else {
            System.out.println("\nNo student found of the course " + displayCourse + ".\n");
        }
    }

    /*
     * Method to display a list of all existing students in descending order from
     * highest to lowest marks
     */
    static void sortStudentsByMarks() {
        if (studentCount == 0) {
            System.out.println("\nNo students in the record.\n");
            return;
        }

        Student temp;
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].marks < students[j + 1].marks) {
                    temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        System.out.println("\nStudents sorted by marks in descending order:");
        displayAllStudents();
    }

    // Method to generate a report card for a student
    static void generateReportCard(int roll) {
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].roll_no == roll) {
                found = true;
                System.out.println("\n===================================================");
                System.out.printf("--- Report Card of %s ---\n\n", students[i].name);

                if (students[i].marks >= 90) {
                    System.out.println("Excellent performance! Keep up the good work.");
                } else if (students[i].marks >= 80) {
                    System.out.println("Great job! Continue to strive for excellence.");
                } else if (students[i].marks >= 70) {
                    System.out.println("Good effort! Aim to improve in areas where you can.");
                } else if (students[i].marks >= 60) {
                    System.out.println("Work on strengthening your understanding of certain topics.");
                } else {
                    System.out.println("Seek additional support and dedicate more time to studying.");
                }

                System.out.println("===================================================");

                System.out.println("\n--- Academic Performance ---\n");
                System.out.println("Subject\t\t\t\t\tGrade");
                System.out.println("---------------------------------------------------");

                // Display subjects based on the course
                displaySubjectsByCourse(students[i].course);

                System.out.println("===================================================");
                break;
            }
        }
        if (!found) {
            System.out.println("\nStudent not found.\n");
        }
    }

    // Helper method to display subjects based on the course
    static void displaySubjectsByCourse(String course) {
        Random random = new Random();

        switch (course) {
            case "bca":
                displaySubjectGrade("Digital Electronics", randomGrade(random));
                displaySubjectGrade("Artificial Intelligence", randomGrade(random));
                displaySubjectGrade("Network Security", randomGrade(random));
                displaySubjectGrade("Visual Basic .NET", randomGrade(random));
                break;
            case "bba":
                displaySubjectGrade("Business Communication", randomGrade(random));
                displaySubjectGrade("Marketing Management", randomGrade(random));
                displaySubjectGrade("Operations research", randomGrade(random));
                displaySubjectGrade("Business Mathematics", randomGrade(random));
                break;
            case "bcom":
                displaySubjectGrade("Financial accounting", randomGrade(random));
                displaySubjectGrade("Business economics", randomGrade(random));
                displaySubjectGrade("Income tax", randomGrade(random));
                displaySubjectGrade("Corporate accounting", randomGrade(random));
                break;
            case "btech":
                displaySubjectGrade("Semiconductor Physics", randomGrade(random));
                displaySubjectGrade("Mechanical Workshop", randomGrade(random));
                displaySubjectGrade("Linux Programming", randomGrade(random));
                displaySubjectGrade("Cloud Computing", randomGrade(random));
                break;
            case "mca":
                displaySubjectGrade("Distributed Systems", randomGrade(random));
                displaySubjectGrade("Web Technologies", randomGrade(random));
                displaySubjectGrade("Operational Research", randomGrade(random));
                displaySubjectGrade("Software Engineering", randomGrade(random));
                break;
            case "mcom":
                displaySubjectGrade("Corporate Governance", randomGrade(random));
                displaySubjectGrade("Managerial Economics", randomGrade(random));
                displaySubjectGrade("E-Commerce or ERP", randomGrade(random));
                displaySubjectGrade("Research & Internships", randomGrade(random));
                break;
            case "msc-cs":
            case "msc(cs)":
                displaySubjectGrade("Programming in C", randomGrade(random));
                displaySubjectGrade("Operating System", randomGrade(random));
                displaySubjectGrade("Data Structures", randomGrade(random));
                displaySubjectGrade("Java Programming", randomGrade(random));
                break;
            case "msc-physics":
            case "msc(physics)":
                displaySubjectGrade("Thermodynamics", randomGrade(random));
                displaySubjectGrade("Electrodynamics", randomGrade(random));
                displaySubjectGrade("Nuclear Physics", randomGrade(random));
                displaySubjectGrade("Quantum Physics", randomGrade(random));
                break;
            case "msc-chemistry":
            case "msc(chemistry)":
                displaySubjectGrade("Organic Chemistry", randomGrade(random));
                displaySubjectGrade("Inorganic chemistry", randomGrade(random));
                displaySubjectGrade("Environmental Science", randomGrade(random));
                displaySubjectGrade("Physical Chemistry", randomGrade(random));
                break;
            case "msc-math":
            case "msc":
            case "msc(math)":
                displaySubjectGrade("Real Analysis", randomGrade(random));
                displaySubjectGrade("Fluid Mechanics", randomGrade(random));
                displaySubjectGrade("Differential Geometry", randomGrade(random));
                displaySubjectGrade("Functional Analysis", randomGrade(random));
                break;
            case "bsc-pcm":
            case "bsc":
            case "bsc(pcm)":
                displaySubjectGrade("Kinetic Theory", randomGrade(random));
                displaySubjectGrade("Electromagnetic", randomGrade(random));
                displaySubjectGrade("Thermodynamics", randomGrade(random));
                displaySubjectGrade("Numerical Methods", randomGrade(random));
                break;
            case "bsc-cs":
            case "bsc(cs)":
                displaySubjectGrade("Value and Ethics", randomGrade(random));
                displaySubjectGrade("Computer Organization", randomGrade(random));
                displaySubjectGrade("Technical Writing", randomGrade(random));
                displaySubjectGrade("System Programming", randomGrade(random));
                break;
            case "be":
                displaySubjectGrade("Engineering Physics", randomGrade(random));
                displaySubjectGrade("Disaster Management", randomGrade(random));
                displaySubjectGrade("Concrete Technology", randomGrade(random));
                displaySubjectGrade("Soil Mechanics", randomGrade(random));
                break;
            case "llb":
                displaySubjectGrade("Law of Evidence", randomGrade(random));
                displaySubjectGrade("Company Law", randomGrade(random));
                displaySubjectGrade("Administrative Law", randomGrade(random));
                displaySubjectGrade("Jurisprudence", randomGrade(random));
                break;
            case "bed":
                displaySubjectGrade("Child Development", randomGrade(random));
                displaySubjectGrade("Understanding Teaching", randomGrade(random));
                displaySubjectGrade("Reasoning Ability", randomGrade(random));
                displaySubjectGrade("Physical Education", randomGrade(random));
                break;
            case "ba":
                displaySubjectGrade("Indian Philosophy", randomGrade(random));
                displaySubjectGrade("Political Science", randomGrade(random));
                displaySubjectGrade("Economics", randomGrade(random));
                displaySubjectGrade("History", randomGrade(random));
                break;
            case "ma":
                displaySubjectGrade("American Literature", randomGrade(random));
                displaySubjectGrade("Critical Theory", randomGrade(random));
                displaySubjectGrade("Indian Writing", randomGrade(random));
                displaySubjectGrade("English Language", randomGrade(random));
                break;
            default:
                System.out.println("Subjects are not defined yet for this course by the programmer.");
        }
    }

    // Helper method to display subjects with grades
    static void displaySubjectGrade(String subject, char grade) {
        System.out.printf("%-30s\t\t%c\n", subject, grade);
    }

    // Helper method to generate a random grade
    static char randomGrade(Random random) {
        char[] grades = { 'A', 'B', 'C', 'D', 'E' };
        return grades[random.nextInt(5)];
    }

    // Method to display all students of a specific gender (either m or g)
    static void displayStudentsByGender(char gender) {
        int found = 0;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].gender == gender) {
                found = 1;
                break;
            }
        }

        if (found == 1) {
            System.out.println("\nList of students with gender '" + gender + "':\n");
            System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s %-15s\n", "Roll Number", "Name", "Age", "Marks",
                    "Gender", "Course", "Admission Year");

            for (int i = 0; i < studentCount; i++) {
                if (students[i].gender == gender) {
                    System.out.printf("%-15d %-20s %-10d %-10.2f %-10c %-20s %-15d\n", students[i].roll_no,
                            students[i].name, students[i].age, students[i].marks, students[i].gender,
                            students[i].course,
                            students[i].admission_year);
                }
            }
        } else {
            System.out.println("\nNo students found of gender '" + gender + "'.\n");
        }
    }

    /*
     * Method to calculate and display the total number of students, total number
     * of male and female students
     */
    static void countMaleFemaleStudents() {
        if (studentCount == 0) {
            System.out.println("\nNo students in the record.\n");
            return;
        }

        int maleCount = 0;
        int femaleCount = 0;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].gender == 'm')
                maleCount++;
            else if (students[i].gender == 'g')
                femaleCount++;
        }

        int totalStudents = maleCount + femaleCount;
        System.out.println("\nTotal number of students: " + totalStudents);
        System.out.println("Male Students: " + maleCount);
        System.out.println("Female Students: " + femaleCount);
    }

    // Method to delete a student of a specific roll number
    static void deleteStudent(int roll_no) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].roll_no == roll_no) {
                System.out.println("\n" + students[i].name + " deleted successfully.\n");
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                studentCount--;
                return;
            }
        }
        System.out.println("\nStudent not found.\n");
    }

    public static void main(String[] args) {
        System.out.println("\n**************************");
        System.out.println("Student Information System");
        System.out.println("**************************\n");

        login();

        int choice, roll, topperIndex, displayAge, year;
        String name, displayCourse;
        char displayGender;

        while (true) {
            System.out.println("\nMain Menu:\n");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. Display All Students");
            System.out.println("4. Search Student by Roll Number");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Display Topper Student");
            System.out.println("7. Generate Certificate for Topper Student");
            System.out.println("8. Display Students of a Specific Age");
            System.out.println("9. Display Students Taken Admission in a Specific Year");
            System.out.println("10. Display Students of a Specific course");
            System.out.println("11. Sort Students From Highest to Lowest Marks");
            System.out.println("12. Generate Report Card");
            System.out.println("13. Display Students by Gender");
            System.out.println("14. Count Male and Female Students");
            System.out.println("15. Delete Student");
            System.out.println("16. Exit\n");

            System.out.print("Enter your choice[1-16]: ");
            try {
                choice = scanner.nextInt(); // Moved inside try block to catch any exception
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\nInvalid choice. Please try again.\n");
                scanner.next(); // Clear the buffer
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n1. Add Student");
                    addStudent();
                    break;
                case 2:
                    System.out.println("\n2. Update Student Information");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter Roll Number of the Student to update: ");
                        try {
                            roll = scanner.nextInt(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        updateStudent(roll);
                    }
                    break;
                case 3:
                    System.out.println("\n3. Display All Students");
                    displayAllStudents();
                    break;
                case 4:
                    System.out.println("\n4. Search Student by Roll Number");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter Roll Number of the Student to search: ");
                        try {
                            roll = scanner.nextInt(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        searchByRoll(roll);
                    }
                    break;
                case 5:
                    System.out.println("\n5. Search Student by Name");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter Name of the Student to search: ");
                        try {
                            name = scanner.nextLine(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        searchByName(name);
                    }
                    break;
                case 6:
                    System.out.println("\n6. Display Topper Student");
                    topperIndex = displayTopperStudent();
                    break;
                case 7:
                    System.out.println("\n7. Generate Certificate for Topper Student.");
                    topperIndex = displayTopperStudent();
                    generateCertificateForTopper(topperIndex);
                    break;
                case 8:
                    System.out.println("\n8. Display Students of a Specific Age");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter age to display students: ");
                        try {
                            displayAge = scanner.nextInt(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        displayStudentsOfSpecificAge(displayAge);
                    }
                    break;
                case 9:
                    System.out.println("\n9. Display Students Taken Admission in a Specific Year");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter Admission Year to display students: ");
                        try {
                            year = scanner.nextInt(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        displayStudentsOfSpecificYear(year);
                    }
                    break;
                case 10:
                    System.out.println("\n10. Display Students of a Specific course");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter Course to display students: ");
                        try {
                            displayCourse = scanner.nextLine(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        displayStudentsOfSpecificCourse(displayCourse);
                    }
                    break;
                case 11:
                    System.out.println("\n11. Sort Students From Highest to Lowest Marks");
                    sortStudentsByMarks();
                    break;
                case 12:
                    System.out.println("\n12. Generate Report Card");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter Roll Number of the Student to generate report card: ");
                        try {
                            roll = scanner.nextInt(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        generateReportCard(roll);
                    }
                    break;
                case 13:
                    System.out.println("\n13. Display Students by Gender");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        while (true) {
                            System.out.print("\nEnter gender to display students: ");
                            displayGender = scanner.next().charAt(0);
                            if (displayGender != 'm' && displayGender != 'g') {
                                System.out.println("\nplease fill valid gender either m or g.\n");
                            } else {
                                break;
                            }
                        }
                        displayStudentsByGender(displayGender);
                    }
                    break;
                case 14:
                    System.out.println("\n14. Count Male and Female Students");
                    countMaleFemaleStudents();
                    break;
                case 15:
                    System.out.println("\n15. Delete Student");
                    if (studentCount == 0) {
                        System.out.println("\nNo students in the record.\n");
                    } else {
                        System.out.print("\nEnter Roll Number of the Student to delete: ");
                        try {
                            roll = scanner.nextInt(); // Moved inside try block to catch any exception
                        } catch (Exception e) {
                            System.out.println("\nInvalid roll number. Please try again.\n");
                            scanner.next(); // Clear the buffer
                            continue;
                        }
                        deleteStudent(roll);
                    }
                    break;
                case 16:
                    System.out.println("\nExiting... Thank you for using the system.\n");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
        }
    }
}
