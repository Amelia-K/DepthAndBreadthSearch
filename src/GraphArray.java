import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name - DepthAndBreadthSearch
 * File Name - GraphArray
 * Created by Amelia Kawasaki on 3/10/2017.
 * <p>
 * [Description goes here]
 * <p>
 *
 */
public class GraphArray {
    static int size;
    int[][] weightArray;
    //int[][] weightArrayCopy;

    /*
    int calcTotalWeight(){
        int total = 0;
        for (int i = 0; i < size; i++){
            for (int k = 0; k < size; k++){
                total = total + smallestSpanning[i][k];
            }
        }
        return total;
    }
    */

    static void printGrid (int[][] array){
        for (int i = 0; i < size; i++){
            for (int k = 0; k < size; k++){
                System.out.print(array[i][k] + " ");
            }
            System.out.println("");
        }
    }

    void fillConnection (int end1, int end2, int weight){
            if (weightArray[end1][end2] < weight) {
                weightArray[end1][end2] = weight;
                weightArray[end2][end1] = weight;
            }
    }

    static boolean checkRepeat (GraphArray myGraphArray, int[][] nodeLinks, int find){
        boolean rBool = false;
        for(int i = 0; i < myGraphArray.size; i++){
            for(int j = 0; j < myGraphArray.size; j++) {
                if (nodeLinks[i][j] == find){
                    rBool = true;
                }
            }
        }
        return rBool;
    }

    static void breadthSearch (GraphArray myGraphArray, int start, int end){
        String result = "";
        List<Integer> visitedNode = new ArrayList<Integer>();
        int[][] nodeLinks = new int[myGraphArray.size][myGraphArray.size];

        for (int[] row: nodeLinks)
            Arrays.fill(row, -1);

        int counter = 0;
        boolean finished = false;

        visitedNode.add(start);
        nodeLinks[start][start] = start;

        while (!finished) {
            for (int i = 0; i < myGraphArray.size; i++) {
                if (myGraphArray.weightArray[visitedNode.get(counter)][i] != 0) {
                    if (!checkRepeat(myGraphArray, nodeLinks, i)) {
                        visitedNode.add(i);
                        nodeLinks[i][visitedNode.indexOf(visitedNode.get(counter))] = i;
                        System.out.println("");
                        //printGrid(nodeLinks);
                        if (i == end) {
                            finished = true;
                        }
                    }
                }
            }
            counter++;
        }

        int total = 0;
        int saveBackNode = end;
        result = result + " " + end;

        while (saveBackNode != start) {
            for (int j = 0; j < myGraphArray.size; j++) {
                if (nodeLinks[saveBackNode][j] == saveBackNode) {
                    total = total + myGraphArray.weightArray[visitedNode.get(j)][saveBackNode];
                    saveBackNode = visitedNode.get(j);
                    result = saveBackNode + " " + result;
                    break;
                }
            }
        }

        System.out.println("The breadth result is:");
        System.out.println( result );
        System.out.println("and the total is: " + total);
        System.out.println("The number of nodes visited is " + (visitedNode.size() - 1));
    }

    static void depthSearch(GraphArray myGraphArray, int start, int end){
        List<Integer> vistedNode = new ArrayList<Integer>();
        List<Integer> currentNodePath = new ArrayList<Integer>();
        int visitedNodeInt = 0;

        vistedNode.add(start);
        int currentNode = start;
        currentNodePath.add(start);
        boolean finished = false;
        int total = 0;


        while(!finished) {
            for (int i = 0; i < myGraphArray.size; i++) {
                if ((myGraphArray.weightArray[currentNode][i] != 0) && (vistedNode.indexOf(i) == -1)) {
                    total = total + myGraphArray.weightArray[currentNode][i];
                    currentNode = i;
                    visitedNodeInt++;
                    if (currentNode != end) {
                        currentNodePath.add(currentNode);
                        vistedNode.add(currentNode);

                        break;
                    } else {
                        finished = true;
                        currentNodePath.add(currentNode);
                        vistedNode.add(currentNode);
                        break;
                    }
                }
                else if ((i + 1) == myGraphArray.size){
                    //set the current node to the one before the current one in the currentNodePath
                    int lastNode = currentNodePath.get(currentNodePath.indexOf(currentNode) - 1);
                    total = total - myGraphArray.weightArray[currentNode][lastNode];
                    int temp = currentNodePath.get(currentNodePath.indexOf(currentNode) - 1);
                    currentNodePath.remove(currentNodePath.indexOf(currentNode));
                    currentNode = temp;

                }
            }
        }

        System.out.println("The depth result is: ");

        for (int i = 0; i < currentNodePath.size(); i++){
            System.out.print(currentNodePath.get(i) + " ");
        }

        System.out.println("");
        System.out.println("and the total is: " + total);
        System.out.println("The number of visited nodes is " + visitedNodeInt);
    }

    static void searching (GraphArray myGraphArray, int start, int end){

        final long startTime = System.currentTimeMillis();
        System.out.println("");
        breadthSearch(myGraphArray, start, end);
        System.out.println("");
        depthSearch(myGraphArray, start, end);
        System.out.println("");
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds" );
    }

    GraphArray (int s){
        size = s;
        weightArray = new int[s][s];
        //smallestSpanning = new int[s][s];

        /*
        fillConnection(0, 1, 5);
        fillConnection(0, 3, 3);
        fillConnection(0, 4, 4);
        fillConnection(1, 3, 3);
        fillConnection(1, 5, 2);
        fillConnection(1, 9, 4);
        fillConnection(1, 2, 3);
        fillConnection(2, 9, 2);
        fillConnection(3, 4, 5);
        fillConnection(3, 5, 3);
        fillConnection(3, 6, 4);
        fillConnection(4, 6, 4);
        fillConnection(4, 7, 2);
        fillConnection(5, 6, 4);
        fillConnection(5, 8, 3);
        fillConnection(5, 9, 3);
        fillConnection(6, 7, 3);
        fillConnection(6, 8, 2);
        fillConnection(7, 8, 3);
        fillConnection(8, 9, 4);
        //printGrid(weightArray);

        //printGrid(weightArray);
        */

    }

}
