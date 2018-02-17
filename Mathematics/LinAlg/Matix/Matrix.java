package Mathematics.LinAlg.Matix;

public class Matrix {
    private int n;
    private int m; //n = reihen, m = spalten
    private double[][] matrix;

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        matrix = new double[n][m];
    }

    public Matrix(double[]... matrix){
        this.matrix = matrix;
        this.n = matrix.length;
        this.m = matrix[0].length;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void setValue(int a, int b, double value) {
        matrix[a][b] = value;
    }

    public double getValue(int a, int b) {
        return matrix[a][b];
    }

    public Matrix add(Matrix toAdd) {
        if(this.n == toAdd.n && this.m == toAdd.m) {
            Matrix toReturn = new Matrix(this.n, this.m);
            for(int a = 0; a < this.n; a++) {
                for(int b = 0; b < this.m; b++) {
                    toReturn.setValue(a,b, this.getValue(a,b) + toAdd.getValue(a,b));
                }
            }
            return toReturn;
        }
        else return null;
    }

    public Matrix scale(double factor) {
        Matrix toReturn = new Matrix(this.n, this.m);
        for(int a = 0; a < this.n; a++) {
            for(int b = 0; b < this.m; b++) {
                toReturn.setValue(a,b, this.getValue(a,b)*factor);
            }
        }
        return toReturn;
    }

    public Matrix multiply(Matrix toMulti) {
        if (this.m == toMulti.n) {
            Matrix toReturn = new Matrix(this.n, toMulti.m);
            for(int a = 0; a < this.n; a++) {
                for(int b = 0; b < this.m; b++) {
                    double sum = 0;
                    for(int s = 0; s < this.m; s++) {
                        sum += this.getValue(a, s) * toMulti.getValue(s, b);
                    }
                    toReturn.setValue(a,b, sum);
                }
            }
            return toReturn;
        }
        else return null;
    }

    public Matrix subMatrix(int rowToOmit) {
        Matrix toReturn = new Matrix(this.n -1, this.m - 1);
        for(int a = 0; a < this.n; a++) {
            for(int b = 1; b < this.m; b++) {
                if(a != rowToOmit) toReturn.setValue((a < rowToOmit) ? a:a-1,b-1,this.getValue(a,b));
            }
        }
        return toReturn;
    }

    public static double det(Matrix a){
        if(a.n == a.m) {
            if (a.n == 1) return a.getValue(0,0);
            if (a.n == 2) return a.getValue(0,0)*a.getValue(1,1) - a.getValue(0,1)*a.getValue(1,0);
            double sum = 0;
            for(int z = 0; z < a.n; z++) {
                sum += ((z%2 == 0) ? 1:-1) * a.getValue(z,0) * Matrix.det(a.subMatrix(z));
            }
            return sum;
        }
        else return Double.NaN;
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for(int a = 0; a < this.n; a++) {
            for(int b = 0; b < this.m; b++) {
                toReturn.append(this.getValue(a,b));
                toReturn.append("|");
            }
            toReturn.append("\n");
        }
        return toReturn.toString();
    }
}
