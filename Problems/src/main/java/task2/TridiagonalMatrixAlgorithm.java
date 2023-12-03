package task2;

import java.util.concurrent.ForkJoinPool;

public class TridiagonalMatrixAlgorithm {

    private double calculate_z(double[][] matrix, double[] alpha_vector, int i) {
        return -(matrix[i][i] + alpha_vector[i] * matrix[i][i - 1]);
    }

    public double[] solveParallel(double[][] matrix, double[] vector) {
        int n = matrix.length;
        double[] alpha_vector = new double[n];
        double[] beta_vector = new double[n];

        alpha_vector[1] = -matrix[0][1] / matrix[0][0];
        beta_vector[1] = vector[0] / matrix[0][0];

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        try {
            forkJoinPool.invoke(new RecursiveSolver(matrix, alpha_vector, beta_vector, vector, 1, n - 1));
        } finally {
            forkJoinPool.shutdown();
        }

        double[] result = new double[n];
        int last = n - 1;
        double last_z = calculate_z(matrix, alpha_vector, last);

        result[last] = (matrix[last][last - 1] * beta_vector[last] - vector[last]) / last_z;

        forkJoinPool = new ForkJoinPool();

        try {
            forkJoinPool.invoke(new BackwardSubstitution(alpha_vector, beta_vector, result, matrix, n - 2));
        } finally {
            forkJoinPool.shutdown();
        }

        return result;
    }
}
