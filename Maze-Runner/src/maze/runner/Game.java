/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.runner;

import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        char key;
        int randomNumber;
        int counterPlayer = 0;
        int counterExit = 0;
        int counterBonus = 0;
        int coins = 0;
        int nexti = 0, nextj = 0, previousi = 0, previousj = 0;
        int Exiti = 0, Exitj = 0;
        int leftCoins;
        int bonusChecker = 0;
        int dimension = 50;
//creating the maze

        do {
            if (dimension < 20) {
                System.out.println("Enter a valid number!");
            }
            System.out.print("Enter the dimension of the maze(>=20): ");
            dimension = s.nextInt();
        } while (!(dimension >= 20));

        int[][] maze = new int[dimension][dimension];
        //placing the walls and track
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i < 4) {
                    maze[i][j] = 1;
                } else if (i > (dimension - 5)) {
                    maze[i][j] = 1;
                } else if ((j <= 3) || (j >= (dimension - 4))) {
                    maze[i][j] = 1;
                } else {
                    randomNumber = r.nextInt(5);
                    if (randomNumber == 1) {
                        maze[i][j] = randomNumber;
                    } else {
                        randomNumber = 0;
                        maze[i][j] = randomNumber;
                    }
                }
            }

        }
        //placing the player                           
        do {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if ((maze[i][j] == 0) && (counterPlayer == 0)) {
                        randomNumber = r.nextInt(5);
                        if (randomNumber == 4 && (counterPlayer == 0)) {
                            maze[i][j] = 9;
                            ++counterPlayer;
                        }
                    }
                }
            }
        } while (counterPlayer == 0);
//placing the exit position
        do {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if ((maze[i][j] == 0) && (counterExit == 0)) {
                        randomNumber = r.nextInt(5);
                        if (randomNumber == 3 && (counterExit == 0)) {
                            maze[i][j] = 3;
                            ++counterExit;
                        }
                    }

                }
            }
        } while (counterExit == 0);
//detecting the player and exit positions
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (maze[i][j] == 9) {
                    nexti = previousi = i;
                    nextj = previousj = j;
                }
                if (maze[i][j] == 3) {
                    Exiti = i;
                    Exitj = j;
                }
            }
        }
//placing the coins
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (maze[i][j] == 0) {
                    int c = r.nextInt(2) + 4;

                    if (c == 5) {
                        maze[i][j] = 5;
                        coins++;
                    }
                }
            }
        }
//placing bonnus (4 slots vision)
        do {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if ((maze[i][j] == 0)) {
                        randomNumber = r.nextInt(6);
                        if (randomNumber == 5 && (counterBonus <= 5)) {
                            maze[i][j] = 8;
                            ++counterBonus;
                        }
                    }
                }
            }
        } while (counterBonus == 0);

        leftCoins = coins;

        //using Movement class
        Movement game = new Movement(dimension, nexti, nextj);
        game.update(maze);
        game.display(maze, bonusChecker);

        System.out.println("Collect " + leftCoins + " coins to win!");
        do {

            System.out.print("Enter your next move (W, A, S, D): ");
            key = s.next().toLowerCase().charAt(0);
            switch (key) {

                case ('w'):
                    nexti--;
                    if ((maze[nexti][nextj] == 0) || (maze[nexti][nextj] == 3)) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.w();
                        game.update(maze);
                    } else if (maze[nexti][nextj] == 5) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.w();
                        game.update(maze);
                        leftCoins--;
                    } else if (maze[nexti][nextj] == 8) {
                        bonusChecker++;
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.w();
                        game.update(maze);

                    } else {
                        nexti++;
                    }
                    break;
                case ('d'):
                    nextj++;
                    if ((maze[nexti][nextj] == 0) || (maze[nexti][nextj] == 3)) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.d();
                        game.update(maze);
                    } else if (maze[nexti][nextj] == 5) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        leftCoins--;
                        game.d();
                        game.update(maze);
                    } else if (maze[nexti][nextj] == 8) {
                        bonusChecker++;
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.d();
                        game.update(maze);

                    } else {
                        nextj--;
                    }
                    break;
                case ('a'):
                    nextj--;
                    if ((maze[nexti][nextj] == 0) || (maze[nexti][nextj] == 3)) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.a();
                        game.update(maze);

                    } else if (maze[nexti][nextj] == 5) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        leftCoins--;
                        game.a();
                        game.update(maze);
                    } else if (maze[nexti][nextj] == 8) {
                        bonusChecker++;
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.a();
                        game.update(maze);

                    } else {
                        nextj++;
                    }
                    break;
                case ('s'):
                    nexti++;
                    if ((maze[nexti][nextj] == 0) || (maze[nexti][nextj] == 3)) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.s();
                        game.update(maze);

                    } else if (maze[nexti][nextj] == 5) {
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        leftCoins--;
                        game.s();
                        game.update(maze);
                    } else if (maze[nexti][nextj] == 8) {
                        bonusChecker++;
                        maze[nexti][nextj] = 9;
                        maze[previousi][previousj] = 0;
                        game.s();
                        game.update(maze);

                    } else {
                        nexti--;
                    }
                    break;
            }
            game.display(maze, bonusChecker);
            if (leftCoins > 1) {
                System.out.println("You need more " + leftCoins + " coins to win!");
            } else if (leftCoins == 1) {
                System.out.println("You need " + leftCoins + " more coin to win!");
            }
            previousi = nexti;
            previousj = nextj;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (bonusChecker >= 1) {
                        if (maze[i][j] == 8) {
                            maze[i][j] = 0;
                        }
                    }
                }
            }
        } while (!(maze[Exiti][Exitj] == 9));
        if (leftCoins == 0) {
            System.out.println("BRAVO! \n Score: " + coins + " coins");
        } else {
            System.out.println("You are a disgrace to the Maze Runner’s community.");
        }

    }
}
