package Matrices.factory;

import java.util.ArrayList;
import java.util.List;

public class MatrixReader_From_Servlet implements MatrixReader {

    @Override
    public List<Matrix> readMatrix(String matrix) {
        int rows = Character.getNumericValue(matrix.charAt(0));
        int columns = Character.getNumericValue(matrix.charAt(1));
        String line;
        line = matrix.substring(2,matrix.length()+1);
        List<String> lines = new ArrayList<String>();
        List<Matrix> matrices2 = new ArrayList<Matrix>();
        Object[] linesAsArray;

        for (int i = 0; i < rows; i++) {
            lines.add(line.substring(i*columns+1,(i+1)*columns+1));
        }
        linesAsArray = lines.toArray(new String[lines.size()]);
        matrices2.add(setInternal(lines, linesAsArray));
        return matrices2;
    }

    @Override
    public Matrix setInternal(List<String> lines, Object[] linesAsArray) {
        Matrix mat = new Matrix();
        if (linesAsArray.length != 0) {
            String String_Array[] = new String[linesAsArray.length];
            for (int i=0;i<String_Array.length;i++)
                String_Array[i]=linesAsArray[i].toString();
            int[][] matrix = new int[lines.size()][String_Array[0].split("").length];
            Operations[][] matrix2 = new Operations[matrix.length][matrix[0].length];
            for(int i = 0; i < lines.size(); i++) {
                for(int j = 0; j < String_Array[0].split("").length; j++) {
                    matrix[i][j] = Integer.parseInt(String_Array[i].split("")[j]);
                    matrix2[i][j] = new MyDouble((double) matrix[i][j]);
                    System.out.print(matrix[i][j] + "\t");
                }
                System.out.println();
            }
            mat.setMatrix(matrix2);
            mat.setA(matrix.length);
            mat.setB(matrix[0].length);
        }
        return mat;
    }
}
