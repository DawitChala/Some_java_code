import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException; 
import java.io.*;  
import java.util.Scanner;  
import java.io.FileWriter;   
class bottom_up{
static ArrayList<Integer> listWsubset = new ArrayList<Integer>();
static ArrayList<Integer> listWIndex = new ArrayList<Integer>();
static boolean[][] subset = null;

  public static void main(String[] args) {
    try {
      Scanner sc=new Scanner(new File(args[0]));
      FileWriter myWriter = new FileWriter(args[1]);
      while (sc.hasNextLine()) {
          String input = sc.nextLine();
          String []listMedTall = input.split(" ");
          System.out.println("kommer inn");
          Integer sum = 0;
          Integer[] dest = new Integer[listMedTall.length-2];
          myWriter.write("INSTANCE "+ listMedTall[0] +" "+ listMedTall[1] + ": ");
          
          for(int i = 2; i<listMedTall.length;i++){
            myWriter.write(" " + listMedTall[i]);
            dest[i-2]= Integer.parseInt(listMedTall[i]);
           // System.out.println(dest[i-2]);
           int temp = i-2;
          
            sum+=dest[i-2];
          }
          subset = makeBoolArray(dest, dest.length, sum);
          //frste er elemnt i lista og andre er hvilken liste
          System.out.println(" ");
          if(findValues(subset,dest.length,Integer.parseInt(listMedTall[1]),dest)==true){
            myWriter.write("\nYES\nSELECTION ");
            int teller = 0;
            for (int tall : listWsubset ) {
              System.out.print(tall+" ");
              myWriter.write(Integer.toString(tall)+ " [" + listWIndex.get(teller)+ "]  ");
              teller++;
            }
          }
          else{
            myWriter.write("\nNO");
            
            System.out.println("fant ikke lsning");
          }
          myWriter.write("\n\n\n");
          System.out.println();
          listWsubset.clear();
          listWIndex.clear();
          subset = null;
       }   
      sc.close();
      myWriter.close();

      }catch (IOException e) {
         e.printStackTrace();
       }

  }
  
  public static boolean[][] makeBoolArray(Integer[] set, Integer n,Integer sum){


      // The value of subset[i][j] will be true if
      // there is a subset of set[0..j-1] with sum
      // equal to i
      boolean[][] subset = new boolean[n+1][sum+1];

      // If sum is 0, then answer is true
      for (int i = 0; i <= n; i++){
          subset[i][0] = true;
        }
      // If sum is not 0 and set is empty,
      // then answer is false
      for (int i = 1; i <= sum; i++){
          subset[0][i] = false;
        }
      // Fill the subset table in botton up manner
      for (int i = 1; i <= n; i++) {
          for (int j = 1; j <= sum; j++) {
              if (j < set[i - 1])
                  subset[i][j] = subset[i - 1][j];
              if (j >= set[i - 1])
                  subset[i][j] = subset[i - 1][j]
                                 || subset[i - 1][j - set[i - 1]];
          }
      }
      return subset;
  }

  //x er element i lista y er hvilken liste
  public static boolean findValues(boolean[][] list, Integer y, Integer x,Integer set[]){
    if(x==0|| y==0){
      //listWsubset.add(set[y]);
      return true;
    }
    //System.out.println("x er: "+ x + " y er: "+ y);
    //System.out.println(list[y]);
    if(list[y][x]==false){
      return false;
    }
    else{
      if(list[y-1][x]==false){
        listWsubset.add(set[y-1]);
        listWIndex.add(y-1);
      //  System.out.println("appender");
        findValues(list,y-1,x-set[y-1],set);
        return true;
      }
      if(list[y-1][x]==true){
      //  System.out.println("appender ikke");
        findValues(list,y-1,x,set);
      }
    }
    return true;
  }
}
