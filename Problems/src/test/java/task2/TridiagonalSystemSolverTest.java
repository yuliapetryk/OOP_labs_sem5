package task2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class TridiagonalSystemSolverTest {

    @Test
    public void testTriDiagonalSolver() {
        double[][] matrix = {
                {1, 1, 0, 0},
                {1, 3, 1, 0},
                {0, 1, 3, 1},
                {0, 0, 1, 3}
        };
        double[] vector = {1, 2, 3, 4};

        TridiagonalMatrixAlgorithm  solver = new TridiagonalMatrixAlgorithm();
        double[] result = solver.solveParallel(matrix,vector);

        double[] expected = {10.0 / 13, 3.0 / 13, 7.0 / 13, 15.0 / 13};

        assertArrayEquals(expected, result, 1e-10);
    }
}
