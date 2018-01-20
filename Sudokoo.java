import java.util.Scanner;

public class Sudokoo {

    public int rown, coln;

    public static void main(String[] args) {

        int i, j, k;

        Sudokoo sudo = new Sudokoo();//creates sudokoo object

        Scanner sc = new Scanner(System.in);//creates scanner object

        int numGrids = sc.nextInt();//scans in number of grids

        for (k = 0; k < numGrids; k++) {

            int[][] grid = new int[9][9];//initializes grid array

            for (i = 0; i < 9; i++) {

                for (j = 0; j < 9; j++) {

                    grid[i][j] = sc.nextInt();//scans in numbers

                }

            }

            if(sudo.solve(grid)){//if grid is right, print

                System.out.print("Test case " + (k+1) + ":\n\n");
                sudo.printG(grid);

             }
             else {//else print no solution message

                System.out.print("Test case " + (k+1) + ":\n\n");
                System.out.print("No solution possible.\n\n\n");
            }
        }

    }

    public boolean solve(int[][] grid){//solves grid

        int row, col;

        if (!ifZero(grid))
            return true;//returns true if the grid is solved

        row = rown;
        col = coln;

        for(int n = 0; n < 9; n++){
            if(isGood(grid, row, col, n+1)){//checks if location is legal

                grid[row][col] = n + 1;// stores the number if the spot is legal

                if(solve(grid)) //recursive call
                    return true;

                grid[row][col] = 0;//sets location to zero
            }
        }

        return false;//else return false

    }

    public boolean ifZero(int[][] grid){//check if a location contains a zero and keeps track of that location
        int row, col;

        for(row = 0; row < 9; row++){
            for(col = 0; col < 9; col++){
                if(grid[row][col]==0) {
                    this.rown = row;
                    this.coln = col;
                    return true;
                }
            }
        }

        return false;

    }

    public boolean rDupe(int[][] grid, int row, int n){//checks if there is a dupe of n in the row

        for(int col = 0; col < 9; col++) {
            if (grid[row][col] == n)
                return true;
        }

            return false;
    }

    public boolean cDupe(int[][] grid, int col, int n){//checks if there is a dupe of n in the column

        for(int row = 0; row < 9; row++) {
            if (grid[row][col] == n)
                return true;
        }

            return false;
    }

    public boolean bDupe(int[][] grid, int startRow, int startCol, int n){//checks if there is a dupe of n in the box

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if (grid[row+startRow][col+startCol] == n)
                    return true;
            }
        }

        return false;
    }

    public boolean isGood(int [][] grid, int row, int col, int n){//checks if location is legal to put number in
        return !rDupe(grid, row, n) && !cDupe(grid, col, n) && !bDupe(grid, row-row%3, col - col%3, n);
    }

    public void printG(int[][] grid){//prints out grid

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                System.out.print(grid[i][j] + " ");

            }

            System.out.print("\n");

        }
        System.out.print("\n\n");
    }

}
