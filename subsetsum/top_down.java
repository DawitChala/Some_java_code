import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.*;  
import java.util.Scanner;  
import java.io.FileWriter;  

class top_down{
static ArrayList<ArrayList<Integer>> finalsubsetSum = null;
static ArrayList<ArrayList<Integer>> listWindex = null;
static int sum;

  public static void main(String[] args) {

    try{
      Scanner sc=new Scanner(new File(args[0]));
      FileWriter myWriter = new FileWriter(args[1]);
      while(sc.hasNextLine()){
        String input = sc.nextLine();
        int setSum = 0;
        String []listMedTall = input.split(" ");
        System.out.println("kommer inn");
        
        Integer[] dest = new Integer[listMedTall.length-2];
        myWriter.write("INSTANCE "+ listMedTall[0] +" "+ listMedTall[1] + ": ");
        for(int i = 2; i<listMedTall.length;i++){
          myWriter.write(" " + listMedTall[i]);
          dest[i-2]= Integer.parseInt(listMedTall[i]);
         // System.out.println(dest[i-2]);
        }

        Integer sum = Integer.parseInt(listMedTall[0]);
        boolean subset[][] = new boolean[dest.length+1][sum+1];


        ArrayList<Integer> setArraylist = new ArrayList<Integer>();
        listWindex = new ArrayList<ArrayList<Integer>>();
        finalsubsetSum = new ArrayList<ArrayList<Integer>>();
      
        for (int tall : dest ) {
          setArraylist.add(tall);
        }
        subset[dest.length-1][sum] = true;
        if(dest.length>3){
          sjekkerRekursivt(subset,sum,dest.length,setArraylist,new ArrayList<Integer>(),sum,new ArrayList<Integer>());
        }
        else{
          setSum = 0;
          for(int i = 0 ; i<dest.length;i++){
            setSum += dest[i];
          }
          if(setSum == sum){
            if(dest.length == 2){
              myWriter.write("\nYES\nSELECTION ");
              myWriter.write(dest[0] + "[0] "+ dest[1] + "[1]");
            }
            else{
              myWriter.write("\nYES\nSELECTION ");
              myWriter.write(dest[0] + "[0] ");
              
            }
          }
          else{
            System.out.println("jkshdk");
            

          }
        }
        if(finalsubsetSum.size()!=0){
          myWriter.write("\nYES\nSELECTION ");
          for(int i = 0; i<finalsubsetSum.get(0).size() ; i++){
            myWriter.write(finalsubsetSum.get(0).get(i)+ " ["  + listWindex.get(0).get(i)+ "]  " );
          }
        }
        if(finalsubsetSum.size()==0 && setSum != sum){
          myWriter.write("\nNO ");
        }
        myWriter.write("\n\n\n");
        listWindex.clear();
        finalsubsetSum.clear();

      }
      myWriter.close();
      sc.close();
    }
    

    catch(IOException e){

    }



  }
  //here i initilize the boolean array with false
  // values and true values at every 0 index


  public static void sjekkerRekursivt(boolean[][] boolList,
  int indeks,
  int rad,
  ArrayList<Integer> subset,
  ArrayList<Integer> subsetSum,
  int sum,
  ArrayList<Integer> listWindex){
    
    
    //basetilfeller
    if(finalsubsetSum.size()==1){
      return;
    }
    if(boolList[rad][0]==true){
      

      for(int i2 = 0; i2<subset.size();i2++){
        for(int j2 = 0; j2<indeks+1;j2++){
          System.out.print(" "+ boolList[i2][j2]);
        }
        System.out.print("    tallet til raden "+ subset.get(i2));
        System.out.print( "\n\n ");
      }
      setteSum(subsetSum,listWindex);

      return;

    }
    for(int i = rad; i>=0 ;i-- ){
      
      for(int j = 0; j<=sum;j++){
        if(finalsubsetSum.size()==1){
          return;
        }
        
        if(boolList[i][j]==true && sum-subset.get(i)>=0){
         
          //Making a new identical list.
          int radIndeks = boolList.length;
          int kollonneIndeks = boolList[0].length;
          boolean boolListe2[][] = new boolean[radIndeks][kollonneIndeks];
          for(int i2 = 0; i2<boolList.length;i2++){
            for(int j2 = 0; j2<boolList[i].length;j2++){
              boolListe2[i2][j2] = boolList[i2][j2];
            }
          }
          if(i-1>=0){
            boolListe2[i-1][sum - subset.get(i)] = true;
            
            int v = sum - subset.get(i);
            ArrayList<Integer> subsetSum2 = new ArrayList<Integer>();
            ArrayList<Integer> listWindex2 = new ArrayList<Integer>();
            for(int i2 = 0; i2<subsetSum.size();i2++){
              subsetSum2.add(subsetSum.get(i2));
              listWindex2.add(listWindex.get(i2));
            }

            subsetSum2.add(subset.get(i));
            listWindex2.add(i);

            sjekkerRekursivt(boolListe2,indeks,i-1,subset,subsetSum2,v,listWindex2);
          }
          else if(sum-subset.get(i)==0){
            boolList[0][0] = true;
            subsetSum.add(subset.get(0));
            listWindex.add(0);
            setteSum(subsetSum,listWindex);
            return;

          }
          else{
            return;
          }
        }
        if(boolList[i][j]==true){
        
          ArrayList<Integer> subsetSum2 = new ArrayList<Integer>();
          ArrayList<Integer> listWindex2 = new ArrayList<Integer>();
          for(int i2 = 0; i2<subsetSum.size();i2++){
            subsetSum2.add(subsetSum.get(i2));
            listWindex2.add(listWindex.get(i2));
          }
          int radIndeks = boolList.length;
          int kollonneIndeks = boolList[0].length;
          boolean boolListe2[][] = new boolean[radIndeks][kollonneIndeks];
          for(int i2 = 0; i2<boolList.length;i2++){
            for(int j2 = 0; j2<boolList[i].length;j2++){
              boolListe2[i2][j2] = boolList[i2][j2];
              

              
            }
          }
          int v = sum;
          int rrad = rad; 
          if(i-1>=0){
            boolList[i-1][v] =true;
            sjekkerRekursivt(boolListe2,indeks,rrad-1,subset,subsetSum2,v,listWindex2);
          }
        }
      }

    }

  
  }
  public static void setteSum(ArrayList<Integer> subsetSum,ArrayList<Integer> listWithIndeks){
    finalsubsetSum.add(subsetSum);
    listWindex.add(listWithIndeks);
  }
}
