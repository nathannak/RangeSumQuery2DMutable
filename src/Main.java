public class Main {

    static int[][] matrix = new int[][] {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};


    public static void main(String[] args) {


        NumMatrix obj = new NumMatrix(matrix);
        obj.update(3, 2, 2);
        int param_2 = obj.sumRegion(2,1,4,3);

    }


}

class NumMatrix {

    private int[][] colSums;
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if(   matrix           == null
                || matrix.length    == 0
                || matrix[0].length == 0   ){
            return;
        }

        this.matrix = matrix;

        int m   = matrix.length;
        int n   = matrix[0].length;
        colSums = new int[m + 1][n];

        // colSums row 0 is all 0, colSums row 1 equal to matrix row 0 [sum of all zero's for colSums row 0  and matrix row 0]
        // colSums row 2 has sum of matrix row 0 and matrix row 1
        // colSums row 3 has sum of matrix row 0 and matrix row 1 and matrix row 2
        for(int i = 1; i <= m; i++){
            for(int j = 0; j < n; j++){
                colSums[i][j] = colSums[i - 1][j] + matrix[i - 1][j];
            }
        }
    }

    //time complexity for the worst case scenario: O(m)
    public void update(int row, int col, int val) {
        for(int i = row + 1; i < colSums.length; i++){
            //remove current value and add new one
            colSums[i][col] = colSums[i][col] - matrix[row][col] + val;
        }

        matrix[row][col] = val;
    }

    //time complexity for the worst case scenario: O(n)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ret = 0;

        for(int j = col1; j <= col2; j++){
            //colSums row 5 is sum of [first 4], so that minus colSums row 2 [sum of matrix row 1 and 0 ] == sum of row 2,3,4
            ret += colSums[row2 + 1][j] - colSums[row1][j];
        }

        return ret;
    }

}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
