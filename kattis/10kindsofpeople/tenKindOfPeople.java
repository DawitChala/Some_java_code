import java.util.Scanner;
public class tenKindOfPeople {
    static short[][] map ;
    static short[][] untouched_map;
    static short maxX;
    static short maxY;
    static int highest_1 =1;
    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(System.in);
            short y = sc.nextShort();
            short x = sc.nextShort();
            maxX = (short) (x - 1);
            maxY = (short) (y - 1);
            map = new short[y][x];
            untouched_map = new short[y][x];
            sc.nextLine();
            for(short i = 0 ; i<y ; i++){
                String map_frag = sc.nextLine();
                   for (int j = 0; j < x; j++) {
                    map[i][j] = Short.parseShort(Character.toString(map_frag.charAt(j)));
                    untouched_map[i][j] = Short.parseShort(Character.toString(map_frag.charAt(j)));
                } 
            }
            short number_of_paths = sc.nextShort();
            sc.nextLine();
            short [][]paths_to_verify = new short[number_of_paths][4];
            
            for (int i = 0; i < number_of_paths; i++) {
                for (int j = 0; j < 4; j++) {
                    paths_to_verify[i][j] = sc.nextShort();
                }               
            }
            sc.close();
            //cheking for 1's
            check_for_ones();
            check_for_zeros();
            String output = "";
            for (int i = 0; i < number_of_paths; i++) {
                    int fromY = paths_to_verify[i][0]-1;
                    int fromX = paths_to_verify[i][1]-1;
                    int toY = paths_to_verify[i][2]-1;
                    int toX = paths_to_verify[i][3]-1;
                    if(untouched_map[fromY][fromX] == 1){
                        if(map[fromY][fromX] == map[toY][toX]){
                           output+="decimal";
                        }
                        else{
                           output+="neither";
                        }
                        if(number_of_paths-1!=i){
                            output+="\n";
                        }
                        continue;
                    }
                    else{
                        if(map[fromY][fromX] == map[toY][toX]){
                           output+="binary";
                        }
                        else{
                           output+="neither";
                        }
                        if(number_of_paths-1!=i){
                            output+="\n";
                        }
                        continue;
                    }
            }
            System.out.println(output);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void check_for_ones(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                boolean stemmer = false;
                if(map[i][j] == 0){
                    continue;
                }   
                if(i!=0){
                    if(map[i-1][j]!= 0 ){
                        stemmer = true;
                        map[i][j] = map[i-1][j];
                    }
                }
                if(j!=0){
                    if(map[i][j-1]!= 0 ){
                        if(stemmer){
                            if(map[i][j]>map[i][j-1]){
                                change_in_arr(map[i][j], map[i][j-1]);
                            }
                            else{
                                change_in_arr(map[i][j-1], map[i][j]);
                            }
                        }
                        else{
                            map[i][j] = map[i][j-1]; 
                        }
                        stemmer = true;
                    }
                }
                if(stemmer==false){
                    highest_1++;
                    map[i][j] = (short) highest_1;
                }
            }
        }
    }

    public static void check_for_zeros(){
        int minste_0 = highest_1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                boolean stemmer = false;
                
                
                if(map[i][j] !=0){
                    continue;
                }   
                if(i!=0){
                    if(map[i-1][j]> highest_1 ){
                        stemmer = true;
                        map[i][j] = map[i-1][j];

                    }
                }
                if(j!=0){
                    if(map[i][j-1]> highest_1){
                        if(stemmer){
                            if(map[i][j]>map[i][j-1]){
                                change_in_arr(map[i][j], map[i][j-1]);
                            }
                            else{
                                change_in_arr(map[i][j-1], map[i][j]);
                            }
                        }
                        else{
                            map[i][j] = map[i][j-1]; 
                        }
                        stemmer = true;
                    }
                }
                if(stemmer==false){
                    
                    minste_0++;
                    map[i][j] = (short) minste_0;
            }
            }
            
        }
        
    }
 
    public static void change_in_arr(int from, int to) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]==from){
                    map[i][j] = (short) to;
                }
            }
        }
    }
}
