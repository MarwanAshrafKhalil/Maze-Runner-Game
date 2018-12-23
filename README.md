# Maze-Runner-Game
Hello,This is Maze Runner Game written in JAVA,you can clone the project or check the code mentioned below,If you have any enquiries feel free to contact me:
LinkedIn: www.linkedin.com/in/marwan-ashraf
Email: marwan.a.k.1997@gmail.com
___________________________________________________________________________________________________________________________________
Game Description:
Requirements:
1.	generate a maze of size 20 x 20.
2.	The maze will always have an exit, “E”, to escape the maze.
3.	You must display whole maze, other than what is visible by Johnny (2 units), all must be blacked out with “#”. (Common sense: Johnny     cannot see through walls)
4.	Johnny should be able to move in 4 directions, namely up, down, left, right in the maze depending on whether there are any obstacles     or not.
5.	The lost items/ valuables should be randomly scattered around the maze, represented by “@”.
6.	If Johnny leaves the maze without all the lost items, Johnny is considered a disgrace to the Maze Runner’s community. 

Crazy ideas:
1.	An algorithm to generate the N x N maze depending on user input for size (N >= 20)
2.	Johnny can collect  bonus items (e.g. lantern – to increase his visibility to 4 units)
___________________________________________________________________________________________________________________________________
Main class:
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
        _____________________________________________________________________________________________________________________
        Movement class:
        public class Movement {

    int x;
    int[][] maze = new int[x][x];
    //R1 r1:row , r2:column
    int r1;
    int r2;
    //R2 r21:row , r22:column
    int r21;
    int r22;
    //L1 l1: row , l2: column
    int l1;
    int l2;
    //L2 l21: row , l22: column
    int l21;
    int l22;
    //U1 u1:row , u2:column
    int u1;
    int u2;
    //U2 u21:row , u22:column
    int u21;
    int u22;
    //D1 d1: row , d2: column
    int d1;
    int d2;
    //D2 d21: row , d22: column
    int d21;
    int d22;
    //R3 r31:row , r32:column
    int r31;
    int r32;
    //R4 r41:row , r42:column
    int r41;
    int r42;
    //L3 l31: row , l32: column
    int l31;
    int l32;
    //L4 l41: row , l42: column
    int l41;
    int l42;
    //U3 u31:row , u32:column
    int u31;
    int u32;
    //U4 u41:row , u42:column
    int u41;
    int u42;
    //D3 d31: row , d32: column
    int d31;
    int d32;
    //D4 d41: row , d42: column
    int d41;
    int d42;
    //vision space positions
    int R1;
    int L1;
    int U1;
    int D1;
    int R2;
    int L2;
    int U2;
    int D2;
    int R3;
    int R4;
    int L3;
    int L4;
    int U3;
    int U4;
    int D3;
    int D4;

    Movement(int x, int k, int l) {
        this.x = x;
        r1 = k;
        r2 = l + 1;
        r21 = k;
        r22 = l + 2;
        l1 = k;
        l2 = l - 1;
        l21 = k;
        l22 = l - 2;
        u1 = k - 1;
        u2 = l;
        u21 = k - 2;
        u22 = l;
        d1 = k + 1;
        d2 = l;
        d21 = k + 2;
        d22 = l;
        r31 = k;
        r32 = l + 3;
        r41 = k;
        r42 = l + 4;
        l31 = k;
        l32 = l - 3;
        l41 = k;
        l42 = l - 4;
        u31 = k - 3;
        u32 = l;
        u41 = k - 4;
        u42 = l;
        d31 = k + 3;
        d32 = l;
        d41 = k + 4;
        d42 = l;

    }

    public void w() {
        --r1;--r21;--l1;--l21;
        --u1;
        --u21;
        --d1;
        --d21;
        --r31;
        --r41;
        --l31;
        --l41;
        --u31;
        --u41;
        --d31;
        --d41;

    }

    public void d() {
        ++r2;
        ++r22;
        ++l2;
        ++l22;
        ++u2;
        ++u22;
        ++d2;
        ++d22;
        ++r32;
        ++r42;
        ++l32;
        ++l42;
        ++u32;
        ++u42;
        ++d32;
        ++d42;
    }

    public void s() {
        ++r1;
        ++r21;
        ++l1;
        ++l21;
        ++u1;
        ++u21;
        ++d1;
        ++d21;
        ++r31;
        ++r41;
        ++l31;
        ++l41;
        ++u31;
        ++u41;
        ++d31;
        ++d41;
    }

    public void a() {
        --r2;
        --r22;
        --l2;
        --l22;
        --u2;
        --u22;
        --d2;
        --d22;
        --r32;
        --r42;
        --l32;
        --l42;
        --u32;
        --u42;
        --d32;
        --d42;
    }

    public void update(int[][] maze) {
        R1 = maze[r1][r2];
        L1 = maze[l1][l2];
        U1 = maze[u1][u2];
        D1 = maze[d1][d2];
        R2 = maze[r21][r22];
        L2 = maze[l21][l22];
        U2 = maze[u21][u22];
        D2 = maze[d21][d22];
        R3 = maze[r31][r32];
        L3 = maze[l31][l32];
        U3 = maze[u31][u32];
        D3 = maze[d31][d32];
        R4 = maze[r41][r42];
        L4 = maze[l41][l42];
        U4 = maze[u41][u42];
        D4 = maze[d41][d42];

    }

    public void display(int[][] maze, int bonusChecker) {

        for (int i = 0; i < x; i++) {

            for (int j = 0; j < x; j++) {

                if ((i == r1) && (j == r2)) {

                    switch (R1) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");

                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }
                } else if ((R1 != 1) && (i == r21) && (j == r22)) {
                    switch (R2) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }
                } else if ((R1 != 1) && (R2 != 1)&& (bonusChecker>=1) && (i == r31) && (j == r32)) {
                    switch (R3) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }}
else if ((R1 != 1) && (R2 != 1)&& (bonusChecker>=1) &&(R3!=1)&& (i == r41) && (j == r42)) {
                    switch (R4) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }
                } else if ((i == l1) && (j == l2)) {
                    switch (L1) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");

                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;
                    }
                } else if ((L1 != 1) && (i == l21) && (j == l22)) {
                    switch (L2) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }} else if ((L1 != 1) && (L2!=1)&& (bonusChecker>=1)&&(i == l31) && (j == l32)) {
                    switch (L3) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }} else if ((L1 != 1) && (L2!=1)&& (bonusChecker>=1)&&(L3!=1)&&(i == l41) && (j == l42)) {
                    switch (L4) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("|");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }
                } else if ((i == u1) && (j == u2)) {
                    switch (U1) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");

                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }
                } else if ((U1 != 1) && (i == u21) && (j == u22)) {
                    switch (U2) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }} else if ((U1 != 1) && (U2!=1)&& (bonusChecker>=1)&&(i == u31) && (j == u32)) {
                    switch (U3) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }} else if ((U1 != 1) && (U2!=1)&&(U3!=1)&& (bonusChecker>=1)&&(i == u41) && (j == u42)) {
                    switch (U4) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                } }else if ((i == d1) && (j == d2)) {
                    switch (D1) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");

                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }
                } else if ((D1 != 1) && (i == d21) && (j == d22)) {
                    switch (D2) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }} else if ((D1 != 1) && (D2!=1)&& (bonusChecker>=1)&&(i == d31) && (j == d32)) {
                    switch (D3) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                    }} else if ((D1 != 1) && (D2!=1)&&(D3!=1)&& (bonusChecker>=1)&&(i == d41) && (j == d42)) {
                    switch (D4) {
                        case 8:
                            System.out.print("$");

                            break;
                        case 1:
                            System.out.print("-");
                            break;
                        case 0:
                            System.out.print(" ");
                            break;
                        case 5:
                            System.out.print("@");
                            break;
                        case 3:
                            System.out.print("E");
                            break;

                } }else if (maze[i][j] == 9) {
                    System.out.print("J");
                } else {
                    System.out.print("#");
                }

            }
            System.out.println();
        }
    }
}
