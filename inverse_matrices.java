import java.util.Scanner;
class inverse_matrices
{  
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("Enter size of matrix to be inversed.");
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        System.out.println("Enter the corresponding matrix values.");
        for(int i = 0 ; i < matrix.length; i++)
        {
            for(int j = 0 ; j < matrix[0].length ; j++)
            {
                System.out.print("Row " + (i+1) + " and Column " + (j+1) + " = ");
                matrix[i][j] = sc.nextInt();
            }
        }
        int det = determinant(matrix);
        if(det == 0)
        {
            System.out.println("Inverse Matrix does not exist!");
            return;
        }
        int[][] adj = transpose(cofactor(matrix));
        float[][] inv = new float[n][n];
        for(int i = 0 ; i < matrix.length ; i++)
        {
            for (int j = 0 ; j < matrix[0].length ; j++)
            {
                inv[i][j] = (float)(adj[i][j]) / det;
            } 
        }

        for(int i = 0 ; i < matrix.length ; i++)
        {
            for (int j = 0 ; j < matrix[0].length ; j++)
            {
                System.out.print(inv[i][j] + "\t");
            }
            System.out.println();
        }
    }
    private static int determinant(int matrix[][])
    {
        if (matrix.length == 0)
        {
            return 1;
        }
        if(matrix.length == 1)
        {
            return matrix[0][0];
        }
        int s = 0;
        for(int j = 0; j < matrix[0].length ; j++)
        {  
            s += determinant(inner_matrix(matrix, 0, j))*matrix[0][j]*(int)Math.pow(-1,0+j);
        }
        return s;

    }
    private static int[][] inner_matrix(int matrix[][], int row_num, int col_num)
    {
        int[][] red_matrix = new int[matrix.length-1][matrix[0].length-1];
        for(int x = 0, r_x = 0 ; x < matrix.length ; x++)
        {
            if(x == row_num)
            {
                continue;
            }
            for(int y = 0 , r_y = 0; y < matrix[0].length; y++)
            {
                if(y == col_num)
                {
                    continue;
                }
                red_matrix[r_x][r_y] = matrix[x][y];
                r_y++;
            }
            r_x++;
        }
        return red_matrix;
    }
    private static int[][] cofactor(int matrix[][])
    {
        int[][] cf_matrix = new int[matrix.length][matrix[0].length]; 
        for(int i = 0; i < matrix.length ; i++)
        {  
            for(int j = 0; j < matrix[0].length ; j++)
            {
                cf_matrix[i][j] = determinant(inner_matrix(matrix, i, j))*(int)Math.pow(-1,i+j);
            }
        }
        return cf_matrix;
    }
    private static int[][] transpose(int matrix[][])
    {
        int[][] tr_matrix = new int[matrix.length][matrix[0].length];
        for(int i = 0 ; i < matrix.length; i++)
        {
            for (int j = 0;j < matrix[0].length; j++)
            {
                tr_matrix[j][i] = matrix[i][j];
            } 
        }
        return tr_matrix;
    }
}