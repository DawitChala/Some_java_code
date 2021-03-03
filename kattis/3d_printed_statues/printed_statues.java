import java.util.Scanner;

public class 3d_printed_statues {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int number_of_stautes = sc.nextInt();
        //int number_of_stautes = 1;
        int days = Integer.MAX_VALUE;
        for (int i = 0; i < number_of_stautes; i++) {
            int day = make_statues(i,number_of_stautes);
            if(day<days){
                days = day;
            } 
        }
        System.out.println(days);
     //   sc.close();
    }
    public static int make_statues(int machines, int goal) {
        
        int machine = 1;
        int statues = 0;
        int days = 0;
        while(machine< machines){
            days ++;
            
            if(machine*2 > machines){
                int diff = machine * 2 - machines;
                machine = machines;
                statues+=diff;

                
            }
            else{
                machine*=2;
            }
        }
        while(statues<goal){
            days ++;
            statues += machine;
        }

        

        System.out.println(machines +" dager " + days);
        return days;
        
    }
}
