/*
 Author Shubham Srivastava
 Problem Amazon Code Ninja - Connected Sets
*/
 
import java.util.*;
 
 
public class Solution {
 
    // Scanner for reading from System.in
    private static Scanner scanner;
    // assume Unicode UTF-8 encoding
    private static final String charsetName = "UTF-8";
 
    private static int totalTests;
    private static int[][] matrix;
    private static List<int[][]> matrixList;
    private static int connectedSets;
    private static int matSize;
    private static List<Integer> matrixSize;
    private static Set<String> connectedPath;
 
    // Initialize scanner
    static {
        scanner = new Scanner(new java.io.BufferedInputStream(System.in), charsetName);
        matrixSize = new ArrayList<Integer>();
        matrixList = new ArrayList<int[][]>();
        connectedPath = new HashSet<String>();
        connectedSets = 0;
    }
 
    /*
     * Method for printing an object to System.out
     */
    private static void print(Object o){
        System.out.println(o.toString());
    }
 
    /*
     * Main method
     */
    public static void main(String[] args) {
        totalTests = scanner.nextInt();
        while(totalTests-- != 0){
            matSize = scanner.nextInt();
            matrixSize.add(matSize);
            populateMatrix(matSize);
            matrixList.add(matrix);
        }
        for(int iCount=0; iCount<matrixList.size(); iCount++){
            matSize = matrixSize.get(iCount);
            matrix = matrixList.get(iCount);
            for(int i=0; i<matSize; i++)
                for(int j=0; j<matSize; j++)
                    process(i, j, true);
            print(connectedSets);
            connectedSets = 0;
 
        }
    }
 
    /*
     * Method to populate 2d-matrix
     */
    public static void populateMatrix(int matSize) {
        matrix = new int[matSize][matSize];
        for(int i=0; i<matSize; i++)
            for(int j=0; j<matSize; j++)
                matrix[i][j] = scanner.nextInt();
    }
 
    /*
     * Method to clear off the current connected path by setting the co-ord to zero
     */
    public static void clearPath() {
        for(String coOrd : connectedPath){
            String[] ij = coOrd.split(",");
            matrix[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] = 0;
        }
        connectedPath.clear();
    }
 
    /*
     * Method to recursively find connected path
     */
    public static void process(int i, int j, boolean incrementFlag){
        boolean flagToSend = incrementFlag;
        String coOrd = i+","+j;
        if(i<0 || j<0 || i==matSize || j==matSize || matrix[i][j] == 0 || connectedPath.contains(coOrd))
            return;
        if(matrix[i][j] == 1){
            connectedPath.add(coOrd);
            if(incrementFlag){
                connectedSets ++;
                flagToSend = false;
            }
            process(i, j+1, flagToSend);
            process(i+1, j+1, flagToSend);
            process(i+1, j, flagToSend);
            process(i+1, j-1, flagToSend);
            process(i, j-1, flagToSend);
            process(i-1, j-1, flagToSend);
            process(i-1, j, flagToSend);
            process(i-1, j+1, flagToSend);
            if(incrementFlag){
                clearPath();
            }
        }
    }
 
}
