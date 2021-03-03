import java.util.Scanner;
import java.io.File;

public class tenKindOfPeople_recursion {
    static short[][] map ;
    static short[][] untouched_map;
    static short maxX;
    static short maxY;
    static boolean solution =false;
    
    
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("eks.txt"));
            short y = sc.nextShort();
            short x = sc.nextShort();
            maxX = (short) (x - 1);
            maxY = (short) (y - 1);
            map = new short[y][x];
            sc.nextLine();
            for(short i = 0 ; i<y ; i++){
                String map_frag = sc.nextLine();
                   for (int j = 0; j < x; j++) {
                    map[i][j] = Short.parseShort(Character.toString(map_frag.charAt(j)));
                } 
            }
            untouched_map = map.clone();
            short number_of_paths = sc.nextShort();
            sc.nextLine();
            short [][]paths_to_verify = new short[number_of_paths][4];
            String output = "";
            for (int i = 0; i < number_of_paths; i++) {
                for (int j = 0; j < 4; j++) {
                    paths_to_verify[i][j] = sc.nextShort();
                    
                }               
                short startX =  (short)( paths_to_verify[i][1] - 1);
                short startY = (short)(paths_to_verify[i][0]-1);
                short sluttY = (short) (paths_to_verify[i][2]-1);
                short sluttX = (short) ( paths_to_verify[i][3]-1);
                
                if(map[startY][startX] != map[sluttY][sluttX]){
                    if(i!=number_of_paths-1){
                        output+="neither\n";
                    }
                    else{
                        output+="neither";
                        System.out.println(output);
                        return;
                        
                    }
                    continue;
                }
                else{
                    if(map[startY][startX]==1){
                        if(i!=number_of_paths-1){
                            output+="decimal\n";
                        }
                        else{
                            output+="decimal";
                            System.out.println(output);
                            return;
                            
                        }
                    }
                    else{
                        if(i!=number_of_paths-1){
                            output+="binary\n";
                        }
                        else{
                            output+="binary";
                            System.out.println(output);
                            return;
                            
                        }


                    }
                }
            }
            System.out.println(output);
            
            sc.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        
    }
    public static void findPath(short currentX, short currentY , short sluttX , short sluttY, short type) {
        if(solution){
            return;
        }
        
        if(currentX==sluttX && currentY==sluttY && map[currentY][currentX]==type){
            solution =true;
            return;
        }
        map[currentY][currentX] = (short) -1;
        
        if(currentX+1<=maxX && map[currentY][currentX+1]==type){
            if(solution){
                return;
            }
            findPath((short) (currentX+1), (short) (currentY), (short) (sluttX), (short) (sluttY), (short) (type));
            if(solution){
                return;
            }
        }
        if(currentX-1>=0 && map[currentY][currentX-1]==type){
            if(solution){
                return;
            }
            findPath((short) (currentX-1), (short) (currentY), (short) (sluttX), (short) (sluttY), (short) (type));
            if(solution){
                return;
            }
        }
        if(currentY+1<=maxY && map[currentY+1][currentX]==type){
            if(solution){
                return;
            }
            findPath((short) (currentX), (short) (currentY+1), (short) (sluttX), (short) (sluttY), (short) (type));
            if(solution){
                return;
            }
        }
        if(currentY-1>=0 && map[currentY-1][currentX]==type){
            if(solution){
                return;
            }
            findPath((short) (currentX), (short) (currentY-1), (short) (sluttX), (short) (sluttY), (short) (type));
            if(solution){
                return;
            }
        }
    }
    
}
