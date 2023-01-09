import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Thiseas {

    static boolean solveMaze(String maze[][], int Ex, int Ey,boolean[][]visited){       

        StringStackImpl path = new StringStackImpl();
        String current = Ex + ", " + Ey;        

        path.push(current);                     
        int direction = 0;                      

        while(!path.isEmpty()){


            String[] Exy = path.peek().split(", ");     
            Ex = Integer.parseInt(Exy[0]);
            Ey = Integer.parseInt(Exy[1]);

            current = path.peek();
            

            if((Ex == 0 || Ex == maze.length - 1 || Ey == 0 || Ey == maze[0].length - 1) && maze[Ex][Ey].equals("0")){

                System.out.println(path.peek());
                return true;
               
            }

            if(direction==0){               

                if(Ex - 1 >=0 && maze[Ex - 1][Ey].equals("0")&& visited[Ex - 1][Ey]){      

                    String current1 = (Ex - 1) + ", " + Ey;   

                    path.push(current1);       
                    visited[Ex-1][Ey] = false;
                    Ex-=1;
                    direction = -1;

                }
            }else if(direction == 1){       

                if(Ex + 1 < maze.length && maze[Ex + 1][Ey].equals("0")&&visited[Ex+1][Ey]){    

                    String current1 = (Ex + 1) + ", " + Ey;     

                    path.push(current1);
                    visited[Ex+1][Ey] = false;
                    Ex+=1;
                    direction = -1;
                }

            }else if(direction == 2){      


                if(Ey + 1 < maze[0].length && maze[Ex][Ey + 1].equals("0")&&visited[Ex][Ey+1]){    

                    String current1 = Ex + ", " + (Ey + 1);      
                    path.push(current1);  
                    visited[Ex][Ey + 1]= false;
                    Ey+=1;
                    direction = -1;
                }

            }else if(direction == 3){       

                if(Ey - 1 >=0 && maze[Ex][Ey - 1].equals("0")&&visited[Ex][Ey-1]){    

                    String current1 = Ex + ", " + (Ey - 1);    

                    path.push(current1);
                    visited[Ex][Ey-1] = false;
                    Ey-=1;
                    direction = -1;
                }

            }else{         
                visited[Ex][Ey] = false;
                path.pop();
                direction = -1;
            }
            direction ++;
        }
        System.out.println("No exit...");
        return false;

    }

    
    public static void main(String[] args) {
        try {//reads file from console
            Scanner scan = new Scanner(new File(args[0])); 
            Scanner sc = new Scanner(new File(args[0]));              
             
            String[] maze_coord = scan.nextLine().split(" ");  

            String[] Ecoord = scan.nextLine().split(" ");
        
            int r = 1;
            String [] col = scan.nextLine().split(" ");
            while(scan.hasNextLine()){
                scan.nextLine();
                r++;
            }
            
            String[][] maze = new String[r][col.length];  

            boolean[][] visited = new boolean[r][col.length];

            for (int i = 0; i < visited.length; i++)
            {
                for (int j = 0; j < visited[i].length; j++)
                {
                    visited[i][j] = true;
                }
            }

            sc.nextLine();
            sc.nextLine();

            while (sc.hasNextLine()){                       
                for (int i = 0; i < maze.length; i++) {                             
                    String[] line = sc.nextLine().trim().split(" ");      
                    for (int j = 0; j < line.length; j++) {
                        maze[i][j] = line [j];
                    }
                }
            }
                solveMaze(maze,Integer.parseInt(Ecoord[0]), Integer.parseInt(Ecoord[1]),visited);

             scan.close();   
             sc.close();
            }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}