package task2;

import java.util.concurrent.RecursiveAction;

class RecursiveSolver extends RecursiveAction {
    private final double[][] matrix;
    private final double[] alpha_vector;
    private final double[] beta_vector;
    private final double[] vector;
    private final int start;
    private final int end;

    public RecursiveSolver(double[][] matrix, double[] alpha_vector, double[] beta_vector, double[] vector, int start, int end) {
        this.matrix = matrix;
        this.alpha_vector = alpha_vector;
        this.beta_vector = beta_vector;
        this.vector = vector;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= 1) {
            calculateAlphaBetaInRange(matrix, alpha_vector, beta_vector, vector, start, end);
        } else {
            int mid = start + (end - start) / 2;

            invokeAll(
                    new RecursiveSolver(matrix, alpha_vector, beta_vector, vector, start, mid),
                    new RecursiveSolver(matrix, alpha_vector, beta_vector, vector, mid, end)
            );
        }
    }
    private void calculateAlphaBetaInRange(double[][] matrix, double[] alpha_vector, double[] beta_vector, double[] vector, int start, int end) {
        for (int i = start; i < end; i++) {
            double z = calculate_z(matrix, alpha_vector, i);
            alpha_vector[i + 1] = matrix[i][i + 1] / z;
            beta_vector[i + 1] = (matrix[i][i - 1] * beta_vector[i] - vector[i]) / z;
        }
    }

    private double calculate_z(double[][] matrix, double[] alpha_vector, int i) {
        return -(matrix[i][i] + alpha_vector[i] * matrix[i][i - 1]);
    }
}