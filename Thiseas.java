import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Thiseas {

    //function that gives the coordinates of the exit
    static boolean solveMaze(String maze[][], int Ex, int Ey,boolean[][]visited){       

        StringStackImpl path = new StringStackImpl();

        //curent = 5, 4
        String current = Ex + ", " + Ey;        

        //push the first coordinate into the stack
        path.push(current);                     

        //if direction is 0 the it goes up!
        //if direction is 1 the it goes down!
        //if direction is 2 the it goes right!
        //if direction is 3 the it goes left!
        //if direction is 4 then restract!
        int direction = 0;                      

        while(!path.isEmpty()){


            //To register the coordinates again with the updated values
            String[] Exy = path.peek().split(", ");     //Exy = [5,4]
            Ex = Integer.parseInt(Exy[0]);
            Ey = Integer.parseInt(Exy[1]);

            //current gets the element on top
            current = path.peek();
            

            //If we find 0 in one of the rows or columns print result
            if((Ex == 0 || Ex == maze.length - 1 || Ey == 0 || Ey == maze[0].length - 1) && maze[Ex][Ey].equals("0")){

                System.out.println(path.peek());
                return true;
               
            }

            //go up!
            if(direction==0){               

                //If there is a row above and its value is "0" and you havent been there again go.
                if(Ex - 1 >=0 && maze[Ex - 1][Ey].equals("0")&& visited[Ex - 1][Ey]){      

                    String current1 = (Ex - 1) + ", " + Ey;   

                    //push the element into the stack
                    path.push(current1);   
                    //set visited array equal to false if visited                     
                    visited[Ex-1][Ey] = false;
                    //update coordinates
                    Ex-=1;
                    direction = -1;

                }
            //go down
            }else if(direction == 1){       

                //If there is a row underneath and its value is "0" and you havent been there again go.
                if(Ex + 1 < maze.length && maze[Ex + 1][Ey].equals("0")&&visited[Ex+1][Ey]){    

                    String current1 = (Ex + 1) + ", " + Ey;     

                    path.push(current1);
                    //set visited array equal to false if visited                        
                    visited[Ex+1][Ey] = false;
                    //update coordinates
                    Ex+=1;
                    //set direction equal to -1 to check all directions again
                    direction = -1;
                }

            //go right
            }else if(direction == 2){      


                //If there is a column right and its value is "0" and you havent been there again go.
                if(Ey + 1 < maze[0].length && maze[Ex][Ey + 1].equals("0")&&visited[Ex][Ey+1]){    

                    String current1 = Ex + ", " + (Ey + 1);      

                    path.push(current1);
                    //set visited array equal to false if visited                      
                    visited[Ex][Ey + 1]= false;
                    //update coordinates
                    Ey+=1;
                    //set direction equal to -1 to check all directions again
                    direction = -1;
                }

            //go left
            }else if(direction == 3){       

                //If there is a column left and its value is "0" and you havent been there again go.
                if(Ey - 1 >=0 && maze[Ex][Ey - 1].equals("0")&&visited[Ex][Ey-1]){    

                    String current1 = Ex + ", " + (Ey - 1);    

                    path.push(current1);
                    //set visited array equal to false if visited                 
                    visited[Ex][Ey-1] = false;
                    //update coordinates
                    Ey-=1;
                    //set direction equal to -1 to check all directions again
                    direction = -1;
                }

            // if direction = 4 then we should restract!
            }else{      
                //set visited array equal to false because it was visited, and pop that element off the stack           
                visited[Ex][Ey] = false;
                path.pop();
                //set direction equal to -1 to check all directions again
                direction = -1;
            }
            //end of loop: check all directions!
            direction ++;
        }
        //if there is no exit
        System.out.println("No exit...");
        return false;

    }



    //function that returns true if the maze containts "E" in it and has the correct dimensions of the maze
    static boolean isSafe(String[][] maze,int x,int y) throws FileNotFoundException{
        //reads file


        //check if the maze in the text has correct numbers
        if(x!=maze.length||y!=maze[0].length){
            System.out.println("Error: The dimensions given on the first line are not the same with the size of the Maze!");
            return false;
        }
        //check if there is "E" in the maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if(maze[i][j].contains("E")){
                    return true;
                }
                }
            }
            System.out.println("Error: Maze does not contain the starting point \"E\"!");
            return false;
        }

    

    
    public static void main(String[] args) {

        
        try {//reads file from console
            
            //this scanner is to count the dimensions of the maze
            Scanner scan = new Scanner(new File(args[0])); 

            //this scanner is to read the values from the file to the maze
            Scanner sc = new Scanner(new File(args[0]));              
             

            //Took the coordinates of the maze
            String[] maze_coord = scan.nextLine().split(" ");  

            //Took the coordinates of the starting point
            String[] Ecoord = scan.nextLine().split(" ");
            
            
            //r counts how many lines there are in the maze
            int r = 1;
            //array that has its value (1 or 0 or E) for elements
            String [] col = scan.nextLine().split(" ");
            //loop to count the lines
            while(scan.hasNextLine()){
                scan.nextLine();
                r++;
            }
            
            //Initialize our 2D array for the maze
            String[][] maze = new String[r][col.length];  

            //Initialize our 2D array for the visited path
            boolean[][] visited = new boolean[r][col.length];




            //set visited array's elements equal to true
            for (int i = 0; i < visited.length; i++)
            {
                for (int j = 0; j < visited[i].length; j++)
                {
                    visited[i][j] = true;
                }
            }


            //skip 2 first lines
            sc.nextLine();
            sc.nextLine();


            //Register data from file to a 2D array
            while (sc.hasNextLine()){                       
                for (int i = 0; i < maze.length; i++) {                             
                    String[] line = sc.nextLine().trim().split(" ");      
                    for (int j = 0; j < line.length; j++) {
                        maze[i][j] = line [j];
                    }
                }
            }

            

            if(isSafe(maze,Integer.parseInt(maze_coord[0]),Integer.parseInt(maze_coord[1]))){
                solveMaze(maze,Integer.parseInt(Ecoord[0]), Integer.parseInt(Ecoord[1]),visited);
            }

             scan.close();   
             sc.close();
            }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}


