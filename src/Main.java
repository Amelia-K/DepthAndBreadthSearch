import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project Name - DepthAndBreadthSearch
 * File Name - Main
 * Created by Amelia Kawasaki on 3/10/2017.
 * <p>
 * [Description goes here]
 * <p>
 */
public class Main {
    static void enterFileConnections (GraphArray myGraph){
        //ArrayList<Integer> nodeList = new ArrayList<Integer>();

        boolean endoffile = false;
        int i,j, value;
        try (DataInputStream dataIn = new DataInputStream(new FileInputStream("bingraph800-"))) {
            while (!endoffile){
                try  {
                    i=dataIn.readInt();
                    j=dataIn.readInt();
                    value=dataIn.readInt();
                    myGraph.fillConnection(i,j, value);
                }
                catch (EOFException exc){
                    System.out.println("Endoffile");
                    endoffile=true;

                }
            }
        }
        catch (IOException exc) {
            System.out.println("Read error.");
            return;
        }
    }
    public static void main (String args[]){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        GraphArray myGraphArray = new GraphArray(800);
        enterFileConnections(myGraphArray);
        System.out.println("Please enter the starting node.");
        int start = reader.nextInt();
        System.out.println("Please enter the ending node.");
        int end = reader.nextInt();
        GraphArray.searching(myGraphArray, start, end);

    }
}
