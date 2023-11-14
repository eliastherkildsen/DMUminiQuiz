import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // creates array to keep track of player points.
    public static int[] points = new int[0];

    public static void main(String[] args) {

        // create array to track points and questions asked.

        int numberOfRounds = 3;
        int numberOfPlayers = setup();
        int Roundcount = 0;

        do {
            // picks player.
            int player = Roundcount % numberOfPlayers;
            // present question to player
            presentQuestion(player);

            Roundcount++;

        }while (Roundcount < numberOfPlayers + 1 * numberOfRounds);
        System.out.println(Arrays.toString(points));

    }

    private static void presentQuestion(int player) {
        // gets question index.
        int questionIndex = generateQuestion();
        // stores answer to question.
        boolean answer = questions.answers[questionIndex];
        // stores question.
        String question = questions.Prompts[questionIndex];
        // prints question.
        String prompt = String.format("question til spiller %d %n %s enter true/false", player + 1, question);
        // prompts user to answer question .
        boolean userInput = getUserInputBool(prompt);

        if (userInput == answer){
            points[player] += 1;
            System.out.println("correct!");
        };

        if (userInput != answer) {
            System.out.println("wrong!");
        }




    }

    public static int setup() {
        // pick number of players.
        int playerCount = pickNumberOfPlayer();

        // adjust the size og playerScore to account for number of players.
        points = new int[playerCount];
        return playerCount;
    }

    /***
     *
     * @return the index of the question.
     */
    private static int generateQuestion() {
        // flag
        boolean isAsked = false;
        // creat random obj.
        Random random = new Random();
        int randomNumber;

        do {

            // generates a random number, to be used for picking question.
            randomNumber = random.nextInt(questions.Prompts.length);

            // checks if the picked number exists in
            for (boolean index : questions.questionsAsked) {
                // checks if question at index is asked. (true)
                if (!questions.questionsAsked[randomNumber]) {
                    // set asked at index of question to true.
                    questions.questionsAsked[randomNumber] = true;
                    // increments metadata on questions. (questions asked count)
                    break;
                }
            }
            return randomNumber;

        }while (true);

    }


    public static int pickNumberOfPlayer() {
        String prompt = "Enter the total amount of players: (1-4)";
        int numberOfPlayers = getUserInputInt(prompt);
        System.out.printf("%nYou have selected %d players.%n", numberOfPlayers);
        return numberOfPlayers;
    }
    
    public static int getUserInputInt(String prompt){

        // creates scanner obj.
        Scanner scanner = new Scanner(System.in);

        do {
            // prompt user
            System.out.println(prompt);

            //  checks if input is a number.
            if (scanner.hasNextInt()){
                int input = scanner.nextInt();
                if ( input > 0 && input <= 4)
                    return input;
            }

            // error handling
            System.out.printf("%sYou can only enter numbers between 1 and 4! %s%n",ANSI_RED, ANSI_RESET);
            scanner.nextLine();

        }while (true);
    }
    public static boolean getUserInputBool(String prompt){

        // creates scanner obj.
        Scanner scanner = new Scanner(System.in);

        do {
            // prompt user
            System.out.println(prompt);

            //  checks if input is a number.
            if (scanner.hasNextBoolean()) {
                return scanner.nextBoolean();
            }
            // error handling
            System.out.printf("%sYou can only enter true or false ! %s%n", ANSI_RED, ANSI_RESET);
            scanner.nextLine();

        }while (true);
    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

}
