import java.util.*;

public class mission2 {
    public static void main(String[] args) {
        System.out.println("Введите размер матрицы:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        int[][] Ex = new int[n][n];
        int j = 0;
        int Diametr = 0;
        int radius = 10000;

        rand(n, matrix);
        output(n, matrix);

        System.out.println();
        System.out.println("Введите вершину:");
        Scanner in1 = new Scanner(System.in);
        int v = in1.nextInt() - 1;

        int[][] incidenceMatrix = new int[n][n*n];
        IncidenceMatrix(n, matrix, incidenceMatrix);
    }

    public static void rand(int n, int[][] matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) ((Math.random() * 2) + 0);
                matrix[j][i] = matrix[i][j];

                if (i == j) {
                    matrix[i][j] = 0;
                }
                if (matrix[i][j] == 1) {
                    matrix[i][j] = (int) (Math.random() * 10);
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
    }

    public static void output(int n, int[][] matrix) {
        System.out.println("\nМатрица 1:\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void IncidenceMatrix(int n, int[][] matrix, int[][] incidenceMatrix){
        var s = 1;
        var num = 0;
        var x = 0;
        var d = -1;
        var r = 10000;
        int[] extr = new int[n];



        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                    incidenceMatrix[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++){
            for(int j = s; j < n; j++){
                if (matrix[i][j] > 0){
                    incidenceMatrix[i][j] = num;
                    x++;
                    num++;
                }
            }
            s++;
        }

        s = 1;
        for (int i = 0; i < n; i++){
            for(int j = s; j < n; j++){
                incidenceMatrix[j][i] = incidenceMatrix[i][j];
            }
            s++;
        }

        int[][] M1 = new int[n][x];
        for (int i = 0; i < n; i++){
            for(int j = 0; j < x; j++){
                M1[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (incidenceMatrix[i][j] > - 1){
                    M1[i][incidenceMatrix[i][j]] = matrix[i][j];
                }
            }
        }

        System.out.println();
        for(int i = 0; i < x; i++){
            System.out.print(i + 1 + " ");
        }

        for(int i = 0; i < n; i++){
            System.out.println();
            for(int j = 0; j < x; j++){
                System.out.print(M1[i][j] + " ");
            }
        }


        int[] dist = new int[n];
        for(int i = 0; i < n; i++){
            extr[i] = -1;
        }

        for (int i = 0; i < n; i++){
            for (int k = 0; k < n; k++){
                dist[k] = 9999;
            }
            BFSDMIX(i, dist, M1, n, x);
            for (int j = 0; j < n; j++)
            {
                if (extr[i] < dist[j] && dist[j] != 9999)
                    extr[i] = dist[j];
            }
        }

        System.out.println("\n\nЭксцентриситет: ");
        for (int i = 0; i < n; i++){
            System.out.print(extr[i] + " ");
        }


        for (int i = 0; i < n; i ++){
            if (d < extr[i])
                d = extr[i];
            if (r > extr[i] && extr[i] != 0)
                r = extr[i];
        }

        System.out.println("\n\nРадиус: " + r);
        System.out.println("Диаметр: " + d);;
        System.out.println("\nПереферийные вершины: ");
        for (int i = 0; i < n; i++){
            if (extr[i] == d)
                System.out.print(i + 1 + " ");
        }

        System.out.println("\n\nЦентральные вершины: ");
        for (int i = 0; i < n; i++){
            if (extr[i] == r)
                System.out.print(i + 1 + " ");
        }
    }

    public static void BFSDMIX(int x, int[] dist, int[][] Arr, int n, int p){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        dist[x] = 0;
        while (queue.peek() != null){
            x = queue.poll();
            for (int i = 0; i < p; i++){
                if (Arr[x][i] > 0){
                    for (int j = 0; j < n; j++){
                        if(Arr[j][i] > 0 && dist[j] > dist[x] + Arr[j][i]){
                            queue.offer(j);
                            dist[j] = dist[x] + Arr[j][i];
                        }
                    }
                }
            }
        }
    }
}
