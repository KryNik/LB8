import java.util.*;

public class mission1 {
    public static void main(String[] args) {
        System.out.println("Введите размер матриц:");
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


        for (; v < matrix.length; v++){
            int[] vis = new int[n];
            for (int i = 0; i < vis.length; i++)
                vis[i] = 10000;
            Queue<Integer> queue = new LinkedList<>();
            BFSD(n, v, vis, queue, matrix);

            for (int i = 0; i < vis.length; i++){
                if (vis[i] == 10000)
                    vis[i] = 0;
            }

            for (int i = 0; i < vis.length; i++){
                Ex[j][i] = vis[i];
            }

            j++;
            int Max = 0;
            for (int i = 0; i < vis.length; i++){
                if (vis[i] > Max)
                    Max = vis[i];
            }
            if (Max > Diametr & Max != 0)
                Diametr = Max;
            if (Max < radius & Max != 0){
                radius = Max;
            }
        }

        System.out.println("Эксцентриситеты: ");
        int[] Excentr = new int[n];
        for (int i = 0; i < Ex.length; i++) {
            int Max = 0;
            for (int k = 0; k < Ex[i].length; k++){
                if (Ex[i][k] > Max)
                    Max = Ex[i][k];
                Excentr[i] = Max;
            }
            System.out.print(Excentr[i] + " ");
        }
        System.out.println();
        System.out.println(Diametr);
        System.out.println(radius);

        int[] per = new int[n];
        int[] middle = new int[n];

        for (int i = 0; i < Ex.length; i++){
            if (Excentr[i] == Diametr)
                per[i] = i + 1;
            if (Excentr[i] == radius){
                middle[i] = i + 1;
            }
        }

        System.out.println("\nПериферийные вершины: " + Arrays.toString(per));
        System.out.println("\nЦентральные вершины: " + Arrays.toString(middle));

        int[] Isolirovannie = new int[n];
        Isolirovannie(n, matrix, Isolirovannie);
        int[] Conc = new int[n];
        Conc(n, matrix, Conc);
        int[] Domination = new int[n];
        Domination(n, matrix, Domination);

        System.out.println("\nИзолированные вершины: " + Arrays.toString(Isolirovannie));
        System.out.println("\nКонцевые вершины: " + Arrays.toString(Conc));
        System.out.println("\nДоминирующие вершины: " + Arrays.toString(Domination));
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


    public static void BFSD(int n, int v, int[] vis, Queue<Integer> queue, int[][] matrix){
        vis[v] = 0;
        queue.offer(v);
        while (queue.peek() != null){
            int s = queue.poll();
            //System.out.print(s + 1 + " ");
            for (int i = 0; i < n; i++){
                if (matrix[s][i] > 0 & vis[i] > vis[s] + matrix[s][i]){
                    queue.offer(i);
                    vis[i] = vis[s] + matrix[s][i];
                }
            }
        }
    }

    public static void Isolirovannie(int n, int[][] matrix, int[] Isolirovannie){
        for (int i = 0; i < matrix.length; i++){
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++){
                count++;
                if (matrix[i][j] != 0)
                    break;
                if (matrix[i][j] == 0 & count == matrix.length){
                    Isolirovannie[i] = i + 1;
                }
            }
        }
    }

    public static void Conc(int n, int[][] matrix, int[] Conc){
        for (int i = 0; i < matrix.length; i++){
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++){
                if (matrix[i][j] != 0)
                    count++;
                if (j == matrix.length - 1 & count == 1){
                    Conc[i] = i + 1;
                }
            }
        }
    }

    public static void Domination(int n, int[][] matrix, int[] Domination){
        for (int i = 0; i < matrix.length; i++){
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++){
                if (matrix[i][j] != 0)
                    count++;
                if (j == matrix.length - 1 & count == matrix.length - 1){
                    Domination[i] = i + 1;
                }
            }
        }
    }
}
