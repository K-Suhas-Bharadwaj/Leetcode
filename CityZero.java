class Solution {
    public int updateDependents(int updatedCity, boolean[][] edge, boolean[] canReachZero) {
        int n = edge.length;
        int changes = 0;
        for(int i = 1; i < n; i++) {
            if(edge[updatedCity][i] && !canReachZero[i]) {
                edge[updatedCity][i] = false;
                edge[i][updatedCity] = true;
                canReachZero[i] = true;
                changes += 1 + updateDependents(i, edge, canReachZero);
            }
        }
        return changes;
    }
    public int minReorder(int n, int[][] connections) {
        boolean[][] edge = new boolean[n][n];
        boolean[] canReachZero = new boolean[n];
        int changes = 0;
        for(int[] connection : connections) {
            if(connection[0] == 0 && !canReachZero[connection[1]]) {
                edge[connection[1]][0] = true;
                canReachZero[connection[1]] = true;
                changes++;
            } else if(connection[1] == 0) { 
                canReachZero[connection[0]] = true;
                edge[connection[0]][connection[1]] = true;
            } else {
                edge[connection[0]][connection[1]] = true;
            }
        }
        for(int i = 1; i < n; i++) {
            if(canReachZero[i]) {
                for(int j = 1; j < n; j++) {
                    if(edge[j][i] && !canReachZero[j]) {
                        canReachZero[j] = true;
                        changes += updateDependents(j, edge, canReachZero);
                    }
                    if(edge[i][j] && !canReachZero[j]) {
                        edge[i][j] = false;
                        edge[j][i] = true;
                        canReachZero[j] = true;
                        changes += 1 + updateDependents(j, edge, canReachZero);
                    }
                }
            } else {
                for(int j = 1; j < n; j++) {
                    if(edge[i][j] && canReachZero[i] && !canReachZero[j]) {
                        edge[i][j] = false;
                        edge[j][i] = true;
                        canReachZero[j] = true;
                        changes += 1 + updateDependents(j, edge, canReachZero);
                    } else if(edge[i][j] && canReachZero[j]) {
                        canReachZero[i] = true;
                    }
                }
            }
        }
        return changes;
    }
}
