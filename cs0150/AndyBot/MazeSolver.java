package AndyBot;

import cs015.prj.AndyBotSupport.AndyBot;
import cs015.prj.AndyBotSupport.MazeSolverSupport;

public class MazeSolver extends MazeSolverSupport{

    public MazeSolver(AndyBot andyBot) {
        // This is the constructor.
        // **Please do not alter this part of the code**
        super();
        this.solve(andyBot);
    }

    /**
     * The method can manipulate an AndyBot instance to solve the maze.
     */
    public void solve(AndyBot andyBot){
        // TO DO: Call move methods on the robot so that it knows
        // which steps to take.



        // TO DO: Nest a call to getHint within a call to solveRoadBlock
        // so that the hint is passed to solveRoadBlock. (Make sure to do
        // this before you attempt to move up!)

        

        //solveRoadBlock is passed the return of getHint which is a integer.

        
    }

    /**
     * Solves a roadblock using the passed in integer.
     */
    public void solveRoadBlock(int x){
        // TO DO: call enterPassword and pass it 4 times x, minus 6
        
        
        }
}
