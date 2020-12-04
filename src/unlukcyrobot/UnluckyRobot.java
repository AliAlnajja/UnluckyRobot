/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlukcyrobot;
import java.util.Random;
import java.util.Scanner;




/**
 *
 * @author Ali Alnajjar
 */
public class UnluckyRobot {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //variables
        int totalScore = 300;
        int itrCount = 0;
        char direction;
        int x = 0;
        int y = 0;
        
        //outputs
        do {
            displayInfo(x, y, itrCount, totalScore);
            itrCount++;
            direction = inputDirection();
            int reward = reward();
            if (doesExceed(x, y, direction)){
                System.out.println("Exceeded boundary, -2000 damage applied");
                totalScore -= 2000;
                System.out.println("");
            }
            else if (direction == 'u'){
                totalScore -=10;
                y++;
                punishOrMercy(direction, reward);
            }
            else if (direction == 'd'){
                totalScore -= 50;
                y--;
            }
            else if (direction == 'l'){
                totalScore -= 50;
                x--;
            }
            else {
                totalScore -= 50;
                x++;
            }
            System.out.println("");
            totalScore += reward;
        } while (!isGameOver(x, y, totalScore, itrCount));   
                evaluation(totalScore);
    }
    /**
     * To give the current coordinate of the robot and how many iterations
     * it has made so far and its score.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param itrCount keeps count of the iterations made so far.
     * @param totalScore the total score the robot has.
     */
    public static void displayInfo(int x, int y, int itrCount, int totalScore){
        System.out.printf("For point (x=%d, y=%d) at iterations: %d"
                + "the total score is: %d\n", x, y, itrCount, totalScore);
    }
    /**
     * To receive the coordinates of the robot and see if its exceeding
     * the edges.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param direction the direction the robot is going (up, down, left and
     * right).
     * @return returns true if is exceeding false if not. 
     */
    public static boolean doesExceed(int x, int y, char direction){  
        return ((y >= 4 && Character.toLowerCase(direction) == 'u')
                || (x <= 0 &&  Character.toLowerCase(direction) == 'l')
                || (y <= 0 && Character.toLowerCase(direction) == 'd') 
                || (x >= 4 &&Character.toLowerCase(direction) == 'r'));
    }
    /**
     * To show the reward the robot receives each time he moves to a cell.
     * @return returns the value of the reward he got.
     */
    public static int reward(){
        Random rand = new Random();
        int dice = rand.nextInt(6) + 1;
       
        switch (dice){
            case 1:
                System.out.println("Dice: 1, reward: -100");
                return -100;
            case 2:
                System.out.println("Dice: 2, reward: -200");
                return -200;
            case 3: 
                System.out.println("Dice: 3, reward: -300");
                return -300;
            case 4:
                System.out.println("Dice: 4, reward: 300");
                return 300;
            case 5:
                System.out.println("Dice: 5, reward 400");
                return 400;
            default:
                System.out.println("Dice: 6, reward 600");
                return 600;
        }
    }
    /**
     * To show if the robot will receive mercy or not only if he went up.
     * @param direction the direction of the robot.
     * @param reward the reward he will receive after the mercy has been given
     * or not.
     * @return returns the reward of the robot after mercy has been given or 
     * not.
     */
    public static int punishOrMercy(char direction, int reward){
        if (reward < 0 && direction == 'u'){
            Random rand = new Random();
            int coin = rand.nextInt(2);
            if (coin == 0)
                System.out.printf
        ("Coin: tail | Mercy, the negative reward is removed.\n");
            else 
                System.out.printf
        ("Coin: head | No mercy, the negative reward is applied.\n");
        }
        return reward;       
    }
    /**
     * To make the name of the robot in title case.
     * @param str the name of the robot.
     * @return returns the name input into a title case.
     */
    public static String toTitleCase(String str){
        char c = str.charAt(0);
        String str1 = str.substring(1, str.indexOf(' ') + 1);
        String str2 = str.substring(str.indexOf(' '), str.indexOf(' ') + 2);
        char c1 = str2.charAt(1);
        String str3 = str.substring(str.indexOf(' ') + 2, str.length());
        String str4 = Character.toTitleCase(c) + str1.toLowerCase() + 
                Character.toTitleCase(c1) + str3.toLowerCase();
        return str4;
    }
    /**
     * To show the results of the robot.
     * @param totalScore the total score earned by the robot so far in the game.
     */
    public static void evaluation(int totalScore){
        Scanner console = new Scanner(System.in);
        System.out.print("Please enter your name (only two words): ");
        String name = console.nextLine();
        
        if(totalScore >= 2000)
            System.out.println("Victory! " + toTitleCase(name) +
                    ", your score is " + totalScore);
        else 
            System.out.println("Mission failed! " + toTitleCase(name) +
                    ", your score is " + totalScore);
    }
    /**
     * To ask the user to input the correct direction if wrong repeat the 
     * question.
     * @return returns a char which is the direction the user wants the robot
     * to move.
     */
    public static char inputDirection(){
        Scanner console = new Scanner(System.in);
        char directionLetter = ' ';
        boolean isValid;
        
        do {
            System.out.print("Please input a valid direction: ");
            directionLetter = console.nextLine().toLowerCase().charAt(0);
            isValid = (directionLetter == 'u' || directionLetter == 'd' ||
                directionLetter == 'l' || directionLetter == 'r');
        } while (!isValid); 
            return directionLetter;
        }
    /**
     * To make the game over if it achieves a certain score, iteration or
     * reaches the x and y desired.
     * @param x the x coordinate the robot needs to achieve for the game to 
     * stop
     * @param y the y coordinate the robot needs to achieve for the game to 
     * stop
     * @param totalScore the total score the robot has.
     * @param itrCount the iterations made by the robot.
     * @return returns true if any of those conditions have applied false if not.
     */
    public static boolean isGameOver(int x, int y, int totalScore, int itrCount){
        return (itrCount > 20 || totalScore < -1000 || totalScore > 2000
                || x == 4 && y == 4 || x == 4 && y == 0);    
    }
}

