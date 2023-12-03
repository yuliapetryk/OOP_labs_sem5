package task2;

import java.util.concurrent.RecursiveAction;

 class BackwardSubstitution extends RecursiveAction {
    private final double[] alpha_vector;
    private final double[] beta_vector;
    private final double[] result;
    private final double[][] matrix;
    private final int index;

    public BackwardSubstitution(double[] alpha_vector, double[] beta_vector, double[] result, double[][] matrix, int index) {
        this.alpha_vector = alpha_vector;
        this.beta_vector = beta_vector;
        this.result = result;
        this.matrix = matrix;
        this.index = index;
    }

    @Override
    protected void compute() {
        if (index >= 0) {
            int j = index + 1;
            result[index] = alpha_vector[j] * result[j] + beta_vector[j];
            invokeAll(new BackwardSubstitution(alpha_vector, beta_vector, result, matrix, index - 1));
        }
    }
}
