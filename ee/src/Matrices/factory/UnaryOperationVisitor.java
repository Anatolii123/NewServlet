package Matrices.factory;

public interface UnaryOperationVisitor {
    //транспонировать
    Operations[][] visit(Matrix operand);
}
