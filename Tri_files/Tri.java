import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;  
import java.util.Scanner;  
import java.io.FileWriter;   // Import the FileWriter class

class Tri{
  static ArrayList<Node> tries = new ArrayList<>();
  static String printebleString = "";
  static ArrayList<String> listwithprinteble = new ArrayList<>();
  static ArrayList<String> stringstoAdd = new ArrayList<>();
  static ArrayList<String> stringsToCheck = new ArrayList<>();
  static ArrayList<StringObj> listWStringObj = new ArrayList<>();
  static int right = 0;
  static int left = 0;
  static int numberOFStrings= 0;

  public static void main(String[] args) {
    ArrayList<String> strings = null;
    System.out.println(args[0]);
    strings = readFromFile(args[0]);
    for(int i = 0 ; i<stringstoAdd.size();i++){
      printebleString = "";
      hjelpeMetode(new StringObj(stringstoAdd.get(i)));
      makeStrings();
      System.out.println(listwithprinteble.get(i));
    }
    for (Node node : tries) {
      System.out.println(node.getletter());
      
    }
    writeToFile(args[1]);
  }
  public static ArrayList<String> readFromFile(String filename){
    try {
      
      Scanner sc=new Scanner(new File(filename));
      numberOFStrings =Integer.parseInt(sc.nextLine());
      int tempCount=0;
      while (sc.hasNextLine()) {
          if(numberOFStrings>tempCount){
            stringstoAdd.add(sc.nextLine());
            tempCount++;
          }
          else{
            stringsToCheck.add(sc.nextLine());
          }

      }
      System.out.println("antall ord "+ stringstoAdd.size());
      sc.close();

      }catch (IOException e) {
         e.printStackTrace();
       }
       ArrayList<String> strings = new ArrayList<String>();
      return strings;
  }
  public static void settInnNyttOrd(Node this_node,StringObj stringO){
   
    if(this_node.getletter()==stringO.getNext()){
      if(this_node.hent_neste_noder().size()!=0){
        stringO.addOneMore();
        if(stringO.ferdig()==true){
          this_node.makeEnd();
          return;
        }
        for(Node node : this_node.hent_neste_noder() ){
          if(node.getletter() == stringO.getNext()){
            if(this_node.hent_neste_noder().size()>1){
            }
            settInnNyttOrd(node,stringO);            
            return;
          }
          else{
            node = null;
            boolean forst = true;
            for(char bokstav : stringO.hentResten()){
              node = new Node(bokstav);
              if(this_node.hent_neste_noder().size()>1){
                node.makeForsteforgreining();
              }
              if(forst == true){
                forst = false;
                this_node.makeforgreiningsord();
              }
              stringO.addOneMore();
              this_node.leggTilNeste(node);
              this_node = node;
            }
            this_node.makeEnd();
            return;
          }
        }
      }
      else{
        boolean bool = true;
        Node leggetil = null;
        this_node.makeForsteforgreining();
        stringO.addOneMore();
        for (char add : stringO.hentResten()) {
          leggetil = new Node(add);
          this_node.leggTilNeste(leggetil);
          this_node = leggetil;
        }
        this_node.makeEnd();
      }
    }
    else{
      Node node = null;
      for(char bokstav : stringO.hentResten()){
        node = new Node(bokstav);
        if(this_node.hent_neste_noder().size()>1){
          node.makeForsteforgreining();
        }
        stringO.addOneMore();
        this_node.leggTilNeste(node);
        this_node = node;
      }
      return;
    }
  }
  public static void hjelpeMetode(StringObj stringO){   
    if(stringO.hentResten().length==1){
      tries.add(new Node(stringO.hentResten()[0]));

    }
    if(tries.size()==0){  
      fyll(stringO);       
    }
    else{
       for(Node node : tries){
          if(node.getletter()==stringO.getNext()){
          settInnNyttOrd(node,stringO);          
          return;
        }
      }
      fyll(stringO);      
    }
  }
  public static void fyll(StringObj stringO){
    Node node = null;
    Node node2 = null;
    boolean help = true;
    for(char bokstav: stringO.hentString()){
      if(help==true){
        help = false;
        node  = new Node(stringO.getNext()) ;
        stringO.addOneMore();
        tries.add(node);
      }
      else{
        node2 = new Node(bokstav);
        node.leggTilNeste(node2);
        node = node2;
      }
    }
    node2.makeEnd();
    return;
  }
  public static void makeStrings(){
    for(Node node:tries){
      System.out.println();
      
      printebleString = printebleString + "(";
      
      left ++ ;
      makeStringshelp(node,node);
      while(right<left){
        
        printebleString = printebleString + ")";
        right++;
      }    
    }
    printebleString += "        ";
    String newString = printebleString;
    listwithprinteble.add(newString);
    printebleString = "";  
  }
  public static void makeStringshelp(Node gamle,Node utskriftNode){
    if (gamle.hent_neste_noder().size()>1) {
      left++;
      printebleString = printebleString + "(";
    }
    printebleString = printebleString + utskriftNode.getletter();
    if(utskriftNode.endWord()==true && utskriftNode.hent_neste_noder().size() == 0){
      right++;
      printebleString = printebleString + ")";
    }
    
    for(Node node: utskriftNode.hent_neste_noder()){
      makeStringshelp(utskriftNode,node);
    }
  }
  public static boolean validString(String streng){
    Node forstenode = null;
    for(int i = 0 ; i<tries.size();i++){
      System.out.println(tries.get(i).getletter() + " " + streng.charAt(0));
      if(tries.get(i).getletter() == streng.charAt(0)){
        forstenode = tries.get(i);
        System.out.println(forstenode.getletter());
      }
    }
      boolean bool= true;
      int antallKorrekte = 0;
      Node node = forstenode;
      while(bool){
        System.out.println("streng = " + streng);
        System.out.println("indeksen paa den over =" + antallKorrekte);
        if(node.getletter()==streng.charAt(antallKorrekte)){
          System.out.println("kommer inn hit");
          antallKorrekte ++;
          if(antallKorrekte == streng.length()){
            if(node.endWord() == true){
              return true;
            }
            else{
              return false;
            }
          }
          if(node.hent_neste_noder().size()>0){
            
            boolean tempnode = false;
            for (Node next : node.hent_neste_noder()) {
              System.out.println( "i triet " + next.getletter() + " strengen som er sann " + streng.charAt(antallKorrekte));
              if(next.getletter()== streng.charAt(antallKorrekte)){

                System.out.println ("inne i loopen si triet " + next.getletter() + " strengen som er sann " + streng.charAt(antallKorrekte));
                tempnode = true;
                node = next;
                System.out.println(next.hent_neste_noder().size());
              }       
            }
            if(tempnode == false){
              return false;
            }
          }
          else{
            return false;
          }
        }
      }
      return false;
  
  
  }
  public static void writeToFile(String filename){
    
      try {
        FileWriter myWriter = new FileWriter(filename);
        for(int i =0 ; i< listwithprinteble.size() ;i++ ){
          myWriter.write(stringstoAdd.get(i)+": " + listwithprinteble.get(i) +"\n");
        }
        for(int i = 0 ; i< stringsToCheck.size();i++){
          myWriter.write(stringsToCheck.get(i) + ": ");
          System.out.println("ord jeg sjekker "+ stringsToCheck.get(i));
          if(validString(stringsToCheck.get(i)) == true){
            myWriter.write("yes \n");
          }
          else{
            myWriter.write("no \n");
          }
        }
        myWriter.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  }
}

