import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // creates array to keep track of player points.
    public static int[] points = new int[0];

    public static void main(String[] args) {



        int numberOfRounds;
        // select number of players
        int numberOfPlayers = setup();  // also create array to track points and questions asked.
        int Roundcount = 0;

        // prompt player to pick number of rounds
        numberOfRounds = getUserInputInt("Pick the number of rounds you want to play");

        do {
            // picks player.
            int player = Roundcount % (numberOfPlayers);
            // present question to player
            presentQuestion(player);

            Roundcount++;

        }while (Roundcount < (numberOfPlayers) * numberOfRounds);

        // print results after game end.
        for (int i = 0; i < points.length; i++) {
            System.out.printf("%splayer %d %s has %d points! %n", ANSI_YELLOW, i + 1, ANSI_RESET, points[i]);
        }


    }

    private static void presentQuestion(int player) {
        // gets question index.
        int questionIndex = generateQuestion();
        // stores answer to question.
        boolean answer = questions.answers[questionIndex];
        // stores question.
        String question = questions.Prompts[questionIndex];
        // prints question.
        String prompt = String.format("%s question til spiller %d%s %n %s enter true/false", ANSI_YELLOW, player +1, ANSI_RESET , question);
        // prompts user to answer question .
        boolean userInput = getUserInputBool(prompt);

        // adds point if question answer is correct, and prompts answer result ex. true/false
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
        // create prompt.
        String prompt = "Enter the total amount of players: (1-4)";
        // store input
        int numberOfPlayers = getUserInputInt(prompt);
        // prompt player
        System.out.printf("%nYou have selected %d players.%n", numberOfPlayers);
        // return the number of players.
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

            //  checks if input is a number and returns if true.
            if (scanner.hasNextBoolean()) {
                return scanner.nextBoolean();
            }
            // error handling
            System.out.printf("%sYou can only enter true or false ! %s%n", ANSI_RED, ANSI_RESET);
            scanner.nextLine();

        }while (true);
    }

    // colors!
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

}
