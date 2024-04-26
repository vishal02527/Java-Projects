import java.util.Scanner;

public class QuizApp {

        private static final int PASS_SCORE = 70;
        private static final int QUESTION_COUNT = 10;
        private static final int SCORE_PER_QUESTION = 10;
        private static final int TIME_LIMIT_SECONDS = 300; // 5 minutes

        private static Scanner scanner = new Scanner(System.in);
        private static int totalScore = 0;
        private static String username;
        private static boolean hasRegistered = false;
        private static String[][] questions;

        public static void main(String[] args) {
                System.out.println("\n********");
                System.out.println("Quiz App");
                System.out.println("********");

                int choice;

                do {
                        System.out.println("\nMain Menu:\n");
                        System.out.println("1. Register User");
                        System.out.println("2. Rules and Instructions");
                        System.out.println("3. Select category for quiz");
                        System.out.println("4. Play Quiz");
                        System.out.println("5. Exit\n");

                        System.out.print("Enter your choice[1-5]: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        System.out.println("\n1. Register User");
                                        registerUser();
                                        break;
                                case 2:
                                        System.out.println("\n2. Rules and Instructions");
                                        displayRules();
                                        break;
                                case 3:
                                        System.out.println("\n3. Select category for quiz");
                                        if (!hasRegistered) {
                                                System.out.println(
                                                                "\nPlease register yourself and then select the category.");
                                        } else {
                                                selectCategory();
                                        }
                                        break;
                                case 4:
                                        System.out.println("\n4. Play Quiz");
                                        if (!hasRegistered) {
                                                System.out.println(
                                                                "\nPlease register yourself and then play the quiz.");
                                        } else {
                                                playQuiz();
                                        }
                                        break;
                                case 5:
                                        System.out.println("\nExiting QuizApp. Goodbye!\n");
                                        break;
                                default:
                                        System.out.println("\nInvalid choice. Please try again.");
                        }
                } while (choice != 5);

                scanner.close();
        }

        private static void registerUser() {
                System.out.print("\nEnter your name: ");
                username = scanner.nextLine();

                System.out.print("Enter your email: ");
                String email = scanner.next();

                hasRegistered = true;
                System.out.println("\nCongratulations " + username + ", you are registered successfully!");
        }

        private static void displayRules() {
                int TIME_LIMIT_MINUTES = TIME_LIMIT_SECONDS / 60;
                System.out.println("\n----- Quiz Rules and Instructions -----\n");
                System.out.println("1. There are " + QUESTION_COUNT + " multiple-choice questions.");
                System.out.println("2. Each correct answer scores " + SCORE_PER_QUESTION + " points.");
                System.out.println("3. No points are deducted for wrong answers.");
                System.out.println("4. You need to score at least " + PASS_SCORE + " to pass the quiz.");
                System.out.println("5. You have " + TIME_LIMIT_MINUTES + " minutes to complete the quiz.");
        }

        private static void selectCategory() {
                System.out.println("\nChoose a category:\n");
                System.out.println("1. Geography");
                System.out.println("2. Science");
                System.out.println("3. English");
                System.out.println("4. Computer Science");
                System.out.println("5. Mathematics");

                System.out.print("\nEnter your choice: ");
                int categoryChoice = scanner.nextInt();

                switch (categoryChoice) {
                        case 1:
                                String[][] geographyQuestions = {
                                                { "What is the capital of France?", "Paris", "Berlin", "London", "Rome",
                                                                "A" },
                                                { "Which country is known as the 'Land of the Rising Sun'?", "Japan",
                                                                "China", "Korea",
                                                                "Vietnam", "A" },
                                                { "What is the capital of Australia?", "Sydney", "Melbourne",
                                                                "Canberra", "Brisbane", "C" },
                                                { "Which river is the longest river in the world?", "Yangtze", "Nile",
                                                                "Mississippi", "Amazon",
                                                                "D" },
                                                { "Which desert is the largest in the world?", "Arabian", "Sahara",
                                                                "Gobi", "Kalahari", "B" },
                                                { "Which country is known as the 'Land of Thousand Lakes'?", "Norway",
                                                                "Canada", "Sweden",
                                                                "Finland", "D" },
                                                { "What is the tallest mountain in the world?", "Kangchenjunga", "K2",
                                                                "Mount Everest",
                                                                "Lhotse", "C" },
                                                { "Which continent is the largest by land area?", "Asia", "Africa",
                                                                "North America", "Europe",
                                                                "A" },
                                                { "What is the capital of Brazil?", "Rio de Janeiro", "Brasília",
                                                                "São Paulo", "Salvador",
                                                                "B" },
                                                { "Which ocean is the largest?", "Arctic Ocean", "Atlantic Ocean",
                                                                "Indian Ocean",
                                                                "Pacific Ocean", "D" },
                                };
                                questions = geographyQuestions;
                                System.out.println("\nCategory set to Geography. You can now play the Geography quiz.");
                                break;
                        case 2:
                                String[][] scienceQuestions = {
                                                { "Which planet is known as the Red Planet?", "Jupiter", "Venus",
                                                                "Mars", "Mercury", "C" },
                                                { "Who is known as the 'Father of Computers'?", "Charles Babbage",
                                                                "Alan Turing", "Bill Gates",
                                                                "Steve Jobs", "A" },
                                                { "What is the chemical symbol for gold?", "Ag", "Au", "Go", "Gl",
                                                                "B" },
                                                { "Which element has the atomic number 1?", "Oxygen", "Hydrogen",
                                                                "Helium", "Carbon", "B" },
                                                { "What is the process by which plants make their own food called?",
                                                                "Respiration", "Digestion",
                                                                "Photosynthesis", "Fermentation", "C" },
                                                { "Which force is responsible for keeping planets in orbit around the Sun?",
                                                                "Magnetic Force",
                                                                "Frictional Force", "Centrifugal Force",
                                                                "Gravitational Force", "D" },
                                                { "What is the powerhouse of the cell?", "Mitochondria", "Ribosome",
                                                                "Nucleus",
                                                                "Endoplasmic Reticulum", "A" },
                                                { "What is the largest planet in our solar system?", "Earth", "Jupiter",
                                                                "Mars", "Saturn",
                                                                "B" },
                                                { "Which scientist proposed the theory of relativity?", "Isaac Newton",
                                                                "Stephen Hawking",
                                                                "Albert Einstein", "Galileo Galilei", "C" },
                                                { "What is the main component of Earth's atmosphere?", "Oxygen",
                                                                "Carbon Dioxide", "Nitrogen",
                                                                "Argon", "C" }
                                };
                                questions = scienceQuestions;
                                System.out.println("\nCategory set to Science. You can now play the Science quiz.");
                                break;
                        case 3:
                                String[][] englishQuestions = {
                                                { "What is the plural form of 'child'?", "Child", "Childs", "Childes",
                                                                "Children", "D" },
                                                { "Which word is a synonym for 'happy'?", "Melancholy", "Elated",
                                                                "Despondent", "Gloomy", "B" },
                                                { "What is the past tense of 'run'?", "Ran", "Runned", "Run", "Running",
                                                                "A" },
                                                { "Which of the following is a preposition?", "Book", "Run", "Under",
                                                                "Sing", "C" },
                                                { "What is the comparative form of 'good'?", "Best", "Better", "Gooder",
                                                                "Bestest", "B" },
                                                { "Which literary device is used to compare two unlike things without using 'like' or 'as'?",
                                                                "Simile", "Metaphor", "Hyperbole", "Personification",
                                                                "B" },
                                                { "What is a group of crows called?", "Herd", "Flock", "Murder", "Pack",
                                                                "C" },
                                                { "Which of the following is a conjunction?", "But", "Apple", "Run",
                                                                "Quickly", "A" },
                                                { "What is the opposite of 'victory'?", "Loss", "Win", "Tie", "Defeat",
                                                                "D" },
                                                { "Which word means 'to cancel out'?", "Annihilate", "Create",
                                                                "Magnify", "Reduce", "A" },
                                };
                                questions = englishQuestions;
                                System.out.println("\nCategory set to English. You can now play the English quiz.");
                                break;
                        case 4:
                                String[][] computerScienceQuestions = {
                                                { "What does HTML stand for?", "Hyper Text Markup Language",
                                                                "Hyperlinks and Text Markup Language",
                                                                "Home Tool Markup Language",
                                                                "Hyper Tool Markup Language", "A" },
                                                { "Which programming language is known as the 'mother of all languages'?",
                                                                "Python", "Java",
                                                                "C", "Assembly", "C" },
                                                { "What does CPU stand for?", "Computer Processing Unit",
                                                                "Central Personal Unit",
                                                                "Central Processing Unit", "Computer Personal Unit",
                                                                "C" },
                                                { "What is the primary function of a firewall?", "Data Encryption",
                                                                "Virus Removal",
                                                                "Network Monitoring", "Unauthorized Access Prevention",
                                                                "D" },
                                                { "Which company developed the Java programming language?", "Microsoft",
                                                                "Sun Microsystems",
                                                                "IBM", "Apple", "B" },
                                                { "What is the file extension for a Python file?", ".py", ".java",
                                                                ".cpp", ".txt", "A" },
                                                { "What does SQL stand for?", "Simple Query Language",
                                                                "Standard Question Language",
                                                                "Structured Query Language", "Simple Question Language",
                                                                "C" },
                                                { "Which data structure operates on a Last In First Out (LIFO) principle?",
                                                                "Queue", "Array",
                                                                "Linked List", "Stack", "D" },
                                                { "What is the concept of connecting physical devices and objects to the internet?",
                                                                "Virtual Reality", "Cloud Computing",
                                                                "Internet of Things", "Artificial Intelligence",
                                                                "C" },
                                                { "Which algorithm sorts data by repeatedly stepping through the list, comparing adjacent elements and swapping them if they are in the wrong order?",
                                                                "Bubble Sort", "Quick Sort", "Merge Sort",
                                                                "Insertion Sort", "A" },
                                };
                                questions = computerScienceQuestions;
                                System.out.println(
                                                "\nCategory set to Computer Science. You can now play the Computer Science quiz.");
                                break;
                        case 5:
                                String[][] mathQuestions = {
                                                { "What is the value of π (Pi) to two decimal places?", "3.12", "3.14",
                                                                "3.16", "3.18", "B" },
                                                { "What is the formula to find the area of a rectangle?", "A = πr²",
                                                                "A = ½ bh", "A = lw",
                                                                "A = s²", "C" },
                                                { "Which of the following is not a type of triangle based on sides?",
                                                                "Equilateral",
                                                                "Isosceles", "Scalene", "Right", "D" },
                                                { "What is the value of the square root of 144?", "10", "11", "12",
                                                                "13", "C" },
                                                { "Which mathematical constant is approximately equal to 2.71828?",
                                                                "Phi (Φ)", "Pi (π)",
                                                                "Euler's Number (e)", "Golden Ratio (φ)", "C" },
                                                { "What is the sum of the angles in a triangle?", "90 degrees",
                                                                "180 degrees", "270 degrees",
                                                                "360 degrees", "B" },
                                                { "If a car travels 60 miles in 2 hours, what is its average speed?",
                                                                "20 mph", "30 mph",
                                                                "40 mph", "50 mph", "B" },
                                                { "What is the formula for the volume of a sphere?", "V = 4/3πr³",
                                                                "V = πr²h", "V = lwh",
                                                                "V = bh", "A" },
                                                { "Which of the following is a prime number?", "9", "15", "21", "23",
                                                                "D" },
                                                { "What is the result of 5! (5 factorial)?", "120", "60", "30", "20",
                                                                "B" },
                                };
                                questions = mathQuestions;
                                System.out.println(
                                                "\nCategory set to Mathematics. You can now play the Mathematics quiz.");
                                break;
                        default:
                                System.out.println("\nInvalid choice. Returning to main menu.");
                }
        }

        private static void playQuiz() {
                if (questions == null) {
                        System.out.println("\nPlease select a category first.");
                        return;
                }

                long startTime = System.currentTimeMillis();

                for (int i = 0; i < questions.length; i++) {
                        String question = questions[i][0];
                        String optionA = questions[i][1];
                        String optionB = questions[i][2];
                        String optionC = questions[i][3];
                        String optionD = questions[i][4];
                        String correctAnswer = questions[i][5];

                        System.out.println("\nQuestion " + (i + 1) + ": " + question);
                        System.out.println("A. " + optionA);
                        System.out.println("B. " + optionB);
                        System.out.println("C. " + optionC);
                        System.out.println("D. " + optionD);

                        String userAnswer;
                        boolean validInput = false;

                        do {
                                System.out.print("Enter your answer (A/B/C/D): ");
                                userAnswer = scanner.next().toUpperCase();

                                if (userAnswer.equals("A") || userAnswer.equals("B") || userAnswer.equals("C")
                                                || userAnswer.equals("D")) {
                                        validInput = true;
                                } else {
                                        System.out.println("\nPlease enter a valid option[A/B/C/D]\n");
                                }
                        } while (!validInput);

                        if (userAnswer.equals(correctAnswer)) {
                                totalScore += SCORE_PER_QUESTION;
                                System.out.println("\nCorrect!");
                        } else {
                                System.out.println("\nIncorrect. The correct answer is " + correctAnswer);
                        }
                }

                long endTime = System.currentTimeMillis();
                int elapsedTimeSeconds = (int) ((endTime - startTime) / 1000);

                System.out.println("\nQuiz completed!");
                System.out.println("Total Score: " + totalScore + "/" + (QUESTION_COUNT * SCORE_PER_QUESTION));
                System.out.println("Time taken: " + elapsedTimeSeconds + " seconds");

                if (totalScore >= PASS_SCORE && elapsedTimeSeconds <= TIME_LIMIT_SECONDS) {
                        System.out.println("\nCongratulations! " + username + ", You have passed the quiz.");
                } else {
                        System.out.println("\nSorry " + username + ", you are failed. Better luck next time!");
                }

                totalScore = 0; // Resetting the score for next attempt
        }
}