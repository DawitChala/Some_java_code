import java.util.ArrayList;
class Node{
  boolean slutt = false;
  boolean start = false;
  boolean forgreining = false;
  char bokstav;
  boolean forsteForgreining = false;
  ArrayList<Node> neste_noder = new ArrayList<Node>();
  public Node(char bokstav){
    this.bokstav = bokstav;
  }
  public char getletter(){
    return this.bokstav;
  }
  public boolean endWord(){
    return slutt;
  }
  public void makeEnd(){
    slutt=true;
  }
  public void leggTilNeste(Node node){
    neste_noder.add(node);
  }
  public ArrayList<Node> hent_neste_noder(){
    return neste_noder;

  }
  public boolean startWord(){
    return start;
  }
  public void makeStart(){
    start = true;
  }
  public boolean forgreiningsord(){
    return forgreining;
  }
  public void makeforgreiningsord(){
    forgreining = true;
  }
  public boolean getForsteForgreining(){
    return forsteForgreining;
  }
  public void makeForsteforgreining(){
    forsteForgreining = true;
  }

}
