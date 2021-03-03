import java.util.Scanner;
import java.io.File;



class forste{


    public static void main(String[] args) {
        
        int [][] board = new int[4][4];
        
        int direction = 0;
        try {
            
            Scanner sc = new Scanner(System.in);
            int current_row = 0;
            while (sc.hasNextLine()) {
                if(current_row<4){
                    String [] input = sc.nextLine().split(" ");
                    board[current_row][0] = Integer.parseInt(input[0]);
                    board[current_row][1] = Integer.parseInt(input[1]);
                    board[current_row][2] = Integer.parseInt(input[2]);
                    board[current_row][3] = Integer.parseInt(input[3]);
                    
                }
                else{
                    direction = Integer.parseInt(sc.nextLine().split("")[0]);
                    break;
                }
                current_row++;
            }
            sc.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        changeBoard(direction,board);
    }


    public static void changeBoard(int direction,int[][] board){

        switch(direction){
            case 0: move_left(board);
                    break;
            case 1: move_up(board);
                    break; 
            case 2: move_right(board);
                    break;
            case 3: move_down(board);
                    break;
        }
    }


    public static void move_down(int[][] board){
        for (int i = 0; i < board.length; i++) {
            int last = -1;
            int lastIndexX = -1;
            int lastIndexY = -1;
            for (int j = 3; j >= 0; j-- ){
                if(last == board[j][i]){
                    
                    board[lastIndexX][lastIndexY] =last*2 ;
                    board[j][i] = 0;
                    last= -1;
                    lastIndexX = -1;
                    lastIndexY = -1;
                }
                else{
                    if(board[j][i]!=0){
                        last = board[j][i];
                        lastIndexY = i;
                        lastIndexX = j;
                    }
                        
                }
            }
        }
        for (int v = 0; v < 4; v++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 3; j >= 0; j-- ){
                    if(board[j][i] == 0){
                        int newj = j ;
                        while(newj>0){
                            
                            int temp = board[newj-1][i];
                            board[newj-1][i] = board[newj][i];
                            board[newj][i] = temp;
                            newj--;
            
                        }
                    }
                }
            }           
        
        }
        viewResult(board);
    }
    
    public static void move_up(int[][] board){
        for (int i = 0; i < board.length; i++) {
            int last = -1;
            int lastIndexX = -1;
            int lastIndexY = -1;
            for (int j = 0; j < board.length; j++) {
                if(last==board[j][i]){
                    board[j][i]*=2;
                    board[lastIndexX][lastIndexY] =0;
                    lastIndexX = -1;
                    lastIndexY = -1;
                    last = -1;
                }
                else{
                    if(board[j][i]!=0){
                        last = board[j][i];
                        lastIndexY = i;
                        lastIndexX = j;
                    }
                }
                
            }
        }
        for(int v = 0; v<4 ; v++){

            for (int i = 0; i < board.length; i++) {          
                for (int j = 0; j < board.length; j++) {
                    if(0==board[j][i]){
                        int newj = j;
                        while(newj<3){
                            int temp = board[newj+1][i];
                            board[newj+1][i] = board[newj][i];
                            board[newj][i] = temp;
                            newj++;
                
                        }
                    }
                    
                }
            }
        }
        viewResult(board);
    }

    public static void move_right(int[][] board){
        int n = board.length;
        for (int i = 0; i < n; i++) {
            int last = -1;
            int lastIndexX = -1;
            int lastIndexY = -1;
            for (int j = 3; j >= 0; j--) {
                
                if(last == board[i][j]){
                    board[i][j] =0;
                    board[lastIndexX][lastIndexY] = last*2;
                    lastIndexX = -1;
                    lastIndexY = -1;
                    last =-1;
                }
                else{
                    if(board[i][j]!=0){
                        lastIndexX = i;
                        last = board[i][j];
                        lastIndexY = j;

                    }
                }
            }
        }
        for(int v = 0 ; v <4 ; v++){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(board[i][j]==0){
                        int newJ = j;
                        while(newJ>0){
                            int temp = board[i][newJ];
                            board[i][newJ] = board[i][newJ-1];
                            board[i][newJ-1] = temp;
                            newJ--;
                        }

                    }
                    
                }
            }
        }
        viewResult(board);
    }

    public static void move_left(int[][] board){
        int n = board.length;
        for (int i = 0; i < n; i++) {
            int last = -1;
            int lastIndexX = -1;
            int lastIndexY = -1;
            for (int j = 0; j < n; j++) {
                if(last == board[i][j]){
                    board[i][j] =0;
                    board[lastIndexX][lastIndexY] = last*2;
                    lastIndexX = -1;
                    lastIndexY = -1;
                    last =-1;
                }
                else{
                    if(board[i][j]!=0){
                        lastIndexX = i;
                        last = board[i][j];
                        lastIndexY = j;

                    }
                }
            }
        }
        
        for(int v = 0 ; v <4 ; v++){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(board[i][j]==0){
                        
                        int newJ = j;
                        while(newJ<3){

                            int temp = board[i][newJ];
                            board[i][newJ] = board[i][newJ+1];
                            board[i][newJ+1] = temp;
                            newJ++;
                        }

                    }
                    
                }
            }
        }

        viewResult(board);

    }


    public static void viewResult(int[][] board) {
        String output = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                output += Integer.toString(board[i][j])+" ";
            }
            if(i!=3){
                output+="\n";

            }
        }
        System.out.println(output);
    }

}