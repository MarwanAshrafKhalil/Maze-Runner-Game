/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.runner;

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
