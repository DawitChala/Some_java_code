import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

class A1_paper{
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("eks.txt"));
            int wanted_size = sc.nextInt();
            
            int[] paper_size = new int[wanted_size-1];
            for (int i = 0; i < wanted_size-1; i++) {
                paper_size[i] = sc.nextInt();
            }  
            sc.close();
            try_diffrent_variants(paper_size);
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    static int try_diffrent_variants(int[] paper){
        float even_total = 594;
        int odd_total = 1189;
        double tape = 0.0;
        if(paper[0] > 2){
            System.out.println("riktig");
            return 0;
        }
        for (int i = 1; i < paper.length; i++) {
            int antall = paper[i];
            int indeks = i-1;
            while(antall >= 2 ){
                
                paper[i]-=2;
                antall = paper[i];
                paper[indeks]++;
                
                int ny_indeks = indeks ;
                int old_val = paper[ny_indeks];

                while(old_val>=2 && ny_indeks>0){
                    paper[ny_indeks]-=2;
                    System.out.println("inn");
                    ny_indeks -- ;
                    paper[ny_indeks]+=1;
                    if(paper[0] == 2){
                        System.out.println(Arrays.toString(paper));
                        System.out.println("riktig");
                        return 0;
                    }



                }
                /* while(antall_forrige>=2 && ny_forrige_indeks >= 0 ){
                    
                    if(paper[ny_forrige_indeks]==2){
                        paper[ny_forrige_indeks]++;
                        antall_forrige = paper[ny_forrige_indeks];
                        paper[ny_forrige_indeks+1] -=2;

                    }

                    ny_forrige_indeks--;

                } */
                
                
            }
            if(paper[0]==2){
                System.out.println("riktig svar");
                return 1;
            }
        }
        
        System.out.println("feil");
        System.out.println(Arrays.toString(paper));
        return 1;

    }
}