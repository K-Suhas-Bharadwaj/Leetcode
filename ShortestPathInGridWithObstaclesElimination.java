class Solution {
    
    //Vectors that will be used to move up, right, down and left
    private int[][] directionVectors = new int[][]{{0,-1}, {1,0}, {0,1}, {-1,0}};
    
    //Checks if x an y are the co-ordinates within the grid of size m x n
    private boolean isOutOfRange(int x, int y, int m, int n) {
        return x < 0 || y < 0 || x > m - 1 || y > n - 1;
    }
    
    public int shortestPath(int[][] grid, int k) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        //Essential astra to do graph traversal
        boolean[][] visited = new boolean[m][n];
        
        //Q: Which Graph traversal? Ans:BFS
        //Essential astra to do BFS
        Queue<int[]> cellsQueue = new LinkedList<>(); 
        //Each array will be in the format of:
        //{ith row, jth column, number of obstacles to be eliminated to reach the cell at ith row and jth column in the grid}
        
        //Heuristic to do BFS
        //obstacleCount[i][j] is the number of obstacles to be eliminated to reach the cell at ith row and jth column in the grid
        int[][] obstacleCount = new int[m][n];
        
        //We have direct entry to grid[0][0]
        //But we need to eliminate the obstacle if present at grid[0][0]
        //So, if grid[0][0] is 1, then obstacleCount[0][0] is also 1. If grid[0][0] is 0, then obstacleCount[0][0] is also 0
        obstacleCount[0][0] = grid[0][0];
        
        //Sow the seed for BFS
        cellsQueue.add(new int[]{0, 0, obstacleCount[0][0]});
        
        //We have sown the seed for BFS. Now we are at level 0
        int nextLevel = 1;
        
        while(!cellsQueue.isEmpty()) {
            
            int numberOfCellsInPreviousLevel = cellsQueue.size();
            
            //To get the cells of next level, we need to iterate over cells of previous level
            //We do that by processing top "numberOfCellsInPreviousLevel" cells in the queue
            while(numberOfCellsInPreviousLevel --> 0) {
                
                int[] headCellInQueue = new int[3];
                headCellInQueue = cellsQueue.poll();
                
                //Check if you have reached the goal. If yes, return current level of the BFS
                if(headCellInQueue[0] == m -1 && headCellInQueue[1] == n -1) return nextLevel - 1; //which is the current level
                
                int currentObstacleCountOfHeadCell = headCellInQueue[2];
                
                for(int[] directionVector : directionVectors) {
                    
                    int nextX = headCellInQueue[0] + directionVector[0];
                    int nextY = headCellInQueue[1] + directionVector[1];
                    
                    //If either of nextX or nextY are out of range, don't process them
                    if(isOutOfRange(nextX, nextY, m, n)) continue;
                    
                    int oldObstacleCountOfNextCell = obstacleCount[nextX][nextY];
                    int newObstacleCountOfNextCell = currentObstacleCountOfHeadCell + grid[nextX][nextY];
                    //New obstacle count of next will be:
                    //obstacle count of cell from where we arrived to this cell + (1 if this cell has obstacle, 0 if not) 
                    
                    //If you are visiting the next cell for first time by eliminating less than or equal to k obstacles
                    if((!visited[nextX][nextY]) && (newObstacleCountOfNextCell <= k)) {
                        cellsQueue.add(new int[] {nextX, nextY, newObstacleCountOfNextCell});
                        obstacleCount[nextX][nextY] = newObstacleCountOfNextCell;
                        visited[nextX][nextY] = true;
                    } 
                    
                    //Irrespective of you visited the next cell previously or not,
                    //If new obstacle count is better (less) than old obstacle count and new obstacle eliminates at most k obstacles
                    //Then, update the obstacle count for that cell which is the heuristic
                    if((newObstacleCountOfNextCell < oldObstacleCountOfNextCell) && (newObstacleCountOfNextCell <= k)) {
                        cellsQueue.add(new int[] {nextX, nextY, newObstacleCountOfNextCell});
                        obstacleCount[nextX][nextY] = newObstacleCountOfNextCell;
                        visited[nextX][nextY] = true;
                    }
                    
                }
                
            }
            
            //Go to next level
            nextLevel++;
            
        }
        
        //If you can't reach goal state by doing BFS
        return -1;
        
    }
    
}
