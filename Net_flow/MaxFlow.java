// Java program for implementation of Ford Fulkerson algorithm 
import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.LinkedList; 

class MaxFlow 
{ 
    static String cut="";
    static int V; 
    static int[][] graf;
    static int steps;

	
	boolean bfs(int graf2[][], int s, int t, int alt[]) 
	{ 
		
		boolean visited[] = new boolean[V]; 
		for(int i=0; i<V; ++i) 
			visited[i]=false; 

	
		LinkedList<Integer> queue = new LinkedList<Integer>(); 
		queue.add(s); 
		visited[s] = true; 
		alt[s]=-1; 

		// Standard BFS Loop 
		while (queue.size()!=0) 
		{ 
            int u = queue.poll(); 
            if(u==0){
                cut="0";
            }
            else{
                cut+=" "+ u;
            }
			for (int v=0; v<V; v++) 
			{ 
				if (visited[v]==false && graf2[u][v] > 0) 
				{ 
					queue.add(v); 
					alt[v] = u; 
					visited[v] = true; 
				} 
			} 
		} 

		
		return (visited[t] == true); 
    } 
    
    int[][] findFlow(int[][] ogGraph){
        int[][] flow = new int[V][V];
        
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(i!=0){
                    
                    
                    flow[j][i] = graf[i][j];
                }
                if(ogGraph[i][j]==0){
                    flow[i][j]=0;
                }
                if(flow[i][j] > ogGraph[i][j]){
                    flow[i][j] = ogGraph[i][j];
                }
            }
            
        }
        for (int i = 0; i < flow.length; i++) {
            for (int j = 0; j < flow.length; j++) {
                if(ogGraph[i][j]==0){
                    flow[i][j]=0;
                }
                if(flow[i][j] > ogGraph[i][j]){
                    flow[i][j] = ogGraph[i][j];
                }

    
            }
        }
        
        
        return flow;
        

    }


	int fordFulkerson(int graph[][], int s, int t) 
	{ 
		int u, v; 

		
		int graf2[][] = new int[V][V]; 

		for (u = 0; u < V; u++) 
			for (v = 0; v < V; v++) 
				graf2[u][v] = graph[u][v]; 

		
		int alt[] = new int[V]; 

		int max_flow = 0; 
		while (bfs(graf2, s, t, alt)) 
		{ 
		 
			int path_flow = Integer.MAX_VALUE; 
			for (v=t; v!=s; v=alt[v]) 
			{ 
				u = alt[v]; 
				path_flow = Math.min(path_flow, graf2[u][v]); 
			} 

		
			for (v=t; v != s; v=alt[v]) 
			{ 
				u = alt[v]; 
				graf2[u][v] -= path_flow; 
				graf2[v][u] += path_flow; 
			} 

	
            max_flow += path_flow; 
            steps++;
		} 
        graf = graf2;
		
		return max_flow; 
	} 


	public static void main (String[] args) throws java.lang.Exception 
	{ 
        
        int graph[][] = readFromFile(args[0]);
		
		MaxFlow m = new MaxFlow(); 
        
        String output = "Max flow: " + m.fordFulkerson(graph, 0, 4); 
        output += "\nCut: "+ cut;
        output+="\nSteps: " + steps+"\n";
        
        for(int[] i : m.findFlow(graph)){
            for (int j : i) {
                output+=j+" ";
            }
            output+="\n";
        }
       
        writeToFile(output,args[1]);

    } 
    static void writeToFile(String output,String filname){
        try {
            FileWriter myWriter = new FileWriter(filname);  
            myWriter.write(output);   
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    static int[][] readFromFile(String filname){
        try {
            File myObj = new File(filname);
            Scanner myReader = new Scanner(myObj);
            V = Integer.parseInt(myReader.nextLine());
            int board[][] = new int[V][V];
            int count_rows =0;
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] numbers= data.split(" ");
              int count =0;
              for(String numb:numbers){
                board[count_rows][count] = Integer.parseInt(numb);
                count++;
              }
              count_rows++;
            }
            myReader.close();
            return board;
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          int[][] board = new int[234][232];

          return board;

    }
} 

