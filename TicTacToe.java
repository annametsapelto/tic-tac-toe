import java.util.Scanner;
import java.lang.* ;
/**
* This program lets the player play a game of tic-tac-toe against the computer.
* 
* In this program a human player can play a game of tic-tac-toe against the computer.
* The player chooses the board size and number of game marks 
* so basically this game can be also five in a row.
*@author Anna Metsapelto
*
*/
public class TicTacToe {
    // Initializing static variables.
        static int rows = 0 ;
        static int cols = 0 ;
        static int marksForWin = 0 ;
        static char[][] board = null;
        static char playerMark = 'X' ;
        static char aiMark = '0' ;
        static boolean victory = false ;
        /** The main method where the board size and number of winning marks are determined and the game is played.
        */
        public static void main(String [] args) {
        Scanner input = new Scanner(System.in) ;
        while (rows<3 || rows>30) {
            try {
            // The player is asked how many rows the board has
            System.out.println("Enter the number of rows (min 3, max 30).") ;
            rows = Integer.parseInt(input.next());
            } catch (NumberFormatException a) {
                System.out.println("You didn't enter a number.") ;
                }
             
            // If the input is too small, too big or otherwise incorrect, it will be asked again.
            while (rows<3 || rows>30) {
                try {
                System.out.println("Please, enter a valid number of rows between 3 and 30.") ;
                rows = Integer.parseInt(input.next()) ;
            } catch (NumberFormatException a) {
                System.out.println("You didn't enter a number.") ;
            }
            }
        }
            while (cols<3 || cols>30) {  
            try {
            //The player is asked how many columns the board has    
            System.out.println("Enter the number of columns (min 3, max 30).") ;
                cols = Integer.parseInt(input.next()) ;
            } catch (NumberFormatException a) {
                System.out.println("You didn't enter a number.") ;
            }
             // If the input is too small, too big or otherwise incorrect, it will be asked again.
            while (cols<3 || cols>30) {
                try {
                System.out.println("Please, enter a valid number of columns between 3 and 30).") ;
                cols = Integer.parseInt(input.next()) ;
            } catch (NumberFormatException a) {
                System.out.println("You didn't enter a number.") ;
                }
            }
            }
        while (marksForWin<3 || marksForWin>5) {    
        try {
        // The player is asked how many marks are needed to win.    
        System.out.println("How many marks are needed to win (3-5)?") ;
            marksForWin = Integer.parseInt(input.next()) ;
        } catch (NumberFormatException a){
            System.out.println("You didn't enter a number.") ;
        }
            // If the input is too small, too big or otherwise incorrect, it will be asked again.
            while (marksForWin<3 || marksForWin>5) {
                try {
                System.out.println("Please, enter a valid number of marks between 3 and 5.") ;
                marksForWin = Integer.parseInt(input.next()) ;
            } catch (NumberFormatException a) {
                    System.out.println("You didn't enter a number.") ;
            }
            }
        }
            // If the input is larger than either the number of rows or columns, it will be asked again.
            while (marksForWin>rows || marksForWin>cols) {
                try {
                System.out.println("The number of marks has to be smaller or the same as the number of rows or columns.") ;
                System.out.println("Please, enter a suitable number of marks.") ;
                marksForWin = Integer.parseInt(input.next()) ;
                } catch (NumberFormatException a) {
                    System.out.println("You didn't enter a number.") ;
                }
            }
            // The board size is determined here
        board = new char [rows][cols] ;
        initializeBoard() ;
        while (!victory) {
            playerSetMark() ;
            aiSetMark() ;
        }
        }
 
 
    /**Sets an empty board at the beginning of a new game. 
    */
    public static void initializeBoard() {
        // Going through the rows.
        for (int i=0; i<rows; i++) {
            // Going through the columns
            for (int j=0; j<cols; j++) {
                // Filling the board with -.
                board[i][j]= '-' ;
            } 
        }
    }
    /**In this method the player chooses where their mark is located. 
    */
    public static void playerSetMark() {
        Scanner input = new Scanner(System.in) ;
        int row=-1 ;
        int col=-1 ;
        boolean validInput= false ;
        boolean rowOk=false ;
        boolean colOk=false ;
        // First we check that there are empty slots available.
        if (isBoardFull()==false) {
            // The question is repeated if the chose slot is already taken.   
            while (validInput==false) {
                while (rowOk==false) {
                    try {
                    System.out.println("Set mark (row, column).") ;
                    // The first number is the row and it is reduced by one to match the index numbering.
                    row = Integer.parseInt(input.next())-1 ;
                    } catch (NumberFormatException a) {
                    System.out.println("You didn't enter a number.") ;
                    }
                    // If the row is too small or too big to fit the board, the player is asked to re-enter the row number.
                    if (row>rows-1 || row<0) {
                    System.out.println("The board does not have a row with that number.") ;
                    }
                    if (row<rows && row>=0){
                        rowOk=true;
                    }
                }
                while (colOk==false) {
                    try {
                    // The second number is the column, reduced by one for the index.
                    col = Integer.parseInt(input.next())-1 ;
                    } catch (NumberFormatException a) {
                        System.out.println("You didn't enter a number. Enter the column.") ;
                    }
                    while (col>cols-1 || col<0) {
                        try {
                        System.out.println("The board does not have a column with that number. Enter a column within the board.") ;
                        col = Integer.parseInt(input.next())-1 ;
                        } catch (NumberFormatException a) {
                            System.out.println("You didn't enter a number.") ;
                        }
                    }
                if(col<cols && col>=0) {
                    colOk=true ;
                }
            }
            if(board[row][col]!='-') {
                System.out.println("There already is a mark.") ;
                rowOk=false ;
                colOk=false ;
            }
            // Here we check that the input is valid and then we can move on.            
            if (board[row][col]=='-' && col<=cols && col>=0 && row<=rows && row>=0) {
                validInput= true; 
            }
        }
        // If the player's move is fine, the next method applies the mark on the board.
        setMark(row, col, playerMark) ;
        // The current board is printed.
        printCurrentBoard() ;
        // We go to see if the game has been won.
        checkForWin(playerMark) ;
        }
    }
     
    /**This is the method that places either the player's or AI's mark on the board.
    * This gets the @param row and @param col for the location and @param mark to apply the right mark.
    */
    public static void setMark(int row, int col, char mark) {
        if (row>=0 && row<=rows-1 && col>=0 && col<=cols-1) {
            if (board[row][col]=='-') {
                board[row][col] = mark ;
            }
        }
    }
    /**This method prints the current board. 
    */
    public static void printCurrentBoard() {
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                System.out.print(board[i][j]) ;    
            }
            System.out.println();
        }
    }
    /**This method randomly applies 0 on the board in a free slot. 
    */
    public static void aiSetMark() {
        System.out.println("The computer's turn.") ;
        boolean empty= true;
        if (isBoardFull()==false) {
        while (empty) {
            int row = (int) (Math.random()*rows) ;
            int col = (int) (Math.random()*cols) ;
            if (board[row][col] == '-'){
                empty= false;
                System.out.print(row+1) ;
                System.out.println(col+1) ;
                setMark(row, col, aiMark) ;
            }
        }
        printCurrentBoard() ;
        checkForWin(aiMark) ;
        }        
    }
    /**This method checks if there are empty slots available and if not, ends the game in a tie.
    * This @return isFull as false if the game board is not full. 
    */
    public static boolean isBoardFull() {
        boolean isFull=true ;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (board[i][j]=='-') {
                    isFull=false ;
                } 
            }
        }
        if (isFull==true) {
            System.out.println("The board is full. We have a tie!") ;
            System.exit(0) ;
        }
        return isFull ;
    }
    /**This method announces the winner and ends the game.
    * This gets the @param player which tells if the player or the computer won. 
    */
    public static void weHaveAWinner(char player){
        if (player=='X'){
           System.out.println("The player has won the game!") ; 
           System.exit(0) ;
        } else {
            System.out.println("The computer has won the game!") ;
            System.exit(0) ;
       }
    }
    /**This method goes through the rows, columns, diagonal and reverse diagonal
    * to see if there are enough marks to win. 
    *This gets the @param rightMark to know which mark it should find.
    */
    public static void checkForWin(char rightMark) {
        int row=0 ;
        int col=0 ;
        int counter=0 ;
        char currentChar=rightMark ;
        //Here we loop through the array and see if we have enough marks to win in the rows.
        for (row=0; row<rows; row++) {
            for (col=0; col<(cols-(marksForWin-1)); col++) {
                while (board[row][col]==currentChar) {
                    col++ ;
                    counter++ ;
                    if (counter>=marksForWin) {
                        weHaveAWinner(rightMark) ;
                    }
                }
                counter=0 ;
            }
        }
        //Here we loop through the array and see if we have enough marks to win in the columns.
        for (col=0; col<cols; col++) {
            for (row=0; row<(rows-(marksForWin-1)); row++) {
                while (board[row][col]==currentChar) {
                    row++ ;
                    counter++ ;
                    if (counter>=marksForWin) {
                        weHaveAWinner(rightMark) ;
                    }
                }
                counter=0 ;
            }
        }
        //Here we loop though the array and see if we have enough marks to win in diagonal.
        for (col=0; col<(cols-(marksForWin-1)); col++) {
            for (row=0; row<(rows-(marksForWin-2)); row++) {
                while (board[row][col]==currentChar) {
                    row++ ;
                    col++ ;
                    counter++ ;
                    if (counter>=marksForWin) {
                        weHaveAWinner(rightMark) ;
                    }
                }
                counter=0 ;
            }
        }
        //Here we loop though the array starting from the last spot of the first row 
        //and see if we have enough marks to win in reverse diagonal.
        for (col=cols-1; col>0+(marksForWin-2); col--) {
            for (row=0; row<(rows-(marksForWin-1)); row++) {
                while (board[row][col]==currentChar) {
                    row++ ;
                    col-- ;
                    counter++ ;
                    if (counter>=marksForWin) {
                        weHaveAWinner(rightMark) ;
                    }
                }
                counter=0;
            }
        }
    }
}