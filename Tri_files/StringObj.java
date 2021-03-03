class StringObj{
  String streng;
  int antallBokstaver;
  int nrOfcorrect = 0;
  StringObj(String ord){
    streng = ord ;

    antallBokstaver=streng.length();
  }
  public boolean korrekt(){
    if (antallBokstaver == nrOfcorrect){
      return true;
    }
    else{
      return false;
    }
  }
  public void addOneMore(){
    nrOfcorrect++;
  }
  public char getNext(){

    return streng.charAt(nrOfcorrect);
  }
  public char[] hentString(){
    return streng.toCharArray();
  }
  public char[] hentResten(){
    String nystreng="";

    for (int i =nrOfcorrect;i<streng.length(); i++) {
      nystreng += streng.charAt(i);

    }
    return nystreng.toCharArray();
  }
  public boolean ferdig(){
    if(nrOfcorrect == streng.length()){
      return true;
    }
    else{
      return false;
    }
  
  }



}
