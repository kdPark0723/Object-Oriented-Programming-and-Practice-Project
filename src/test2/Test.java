package test2;

import tensor.*;

import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        System.out.println("스칼라의 생성(default 접근 지정자 사용)");
        {
            System.out.println("01. 값(double 혹은 Double)을 지정하여 스칼라를 생성할 수 있다.");
            Scalar scalar = Factory.createScalarFromInitialValue(1.0);
            System.out.println("1.0의 값을 갖는 scalar 생성");
            System.out.println("scalar = " + scalar);
            System.out.println();
        }
        {
            System.out.println("02. i 이상 j 미만의 무작위 값을 요소로 하는 스칼라를 생성할 수 있다.");
            Scalar scalar = Factory.createScalarFromRandomValue(0.0, 1.0);
            System.out.println("0.0이상 1.0 미만의 무작위 값을 요소로 하는 scalar 생성");
            System.out.println("scalar = " + scalar);
            System.out.println();
        }

        System.out.println("벡터의 생성(default 접근 지정자 사용)");
        {
            System.out.println("03. 지정한 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터를 생성할 수 있다.");
            try {
                Vector vector = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
                System.out.println("1.0을 벡터의 모든 요소로 하는  vector 생성");
                System.out.println("vector = " + vector);
                System.out.println();
            } catch (SizeLessThanZeroException e) {
                System.out.println("Error: createVectorFromInitialValue: 벡터의 크기가 1보다 작습니다.");
                System.out.println();
            }

            System.out.println("04. i 이상 j 미만의 무작위 값을 요소로 하는 n-차원 벡터를 생성할 수 있다.");
            try {
                Vector vector = Factory.createVectorFromRandomValue(3, Factory.createScalarFromInitialValue(0.0), Factory.createScalarFromInitialValue(1.0));
                System.out.println("0.0이상 1.0 미만의 무작위 값을 벡터의 모든 요소로 하는 vector 생성");
                System.out.println("vector = " + vector);
                System.out.println();
            } catch (SizeLessThanZeroException e) {
                System.out.println("Error: createVectorFromRandomValue: 벡터의 크기가 1보다 작습니다.");
                System.out.println();
            }

            System.out.println("05. 1차원 배열로부터 n-차원 벡터를 생성할 수 있다.");
            Scalar[] array = new Scalar[3];
            System.out.println("1차원 배열 array의 요소");
            for (int i = 0; i < array.length; i++) {
                array[i] = Factory.createScalarFromInitialValue(1.0 + i);
                System.out.print(array[i] + " ");
            }
            System.out.println();
            // 1.0 2.0 3.0
            Vector vector = Factory.createVectorFromArray(array);
            System.out.println("1차원 배열 array를 통해 vector 생성");
            System.out.println("vector = " + vector);
            System.out.println();
        }

        System.out.println("행렬의 생성(default 접근 지정자 사용)");
        {
            System.out.println("06. 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬을 생성할 수 있다.");
            try {
                Matrix matrix = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
                System.out.println("1.0을 행렬의 모든 요소로 하는 matrix 생성");
                System.out.println("matrix = " + matrix);
                System.out.println();
            } catch (SizeLessThanZeroException e) {
                System.out.println("Error: createMatrixFromInitialValue: 행렬의 행 또는 열의 크기가 1보다 작습니다.");
                System.out.println();
            }

            System.out.println("07. i 이상 j 미만의 무작위 값을 요소로 하는 m x n 행렬을 생성할 수 있다.");
            try {
                Matrix matrix = Factory.createMatrixFromRandomValue(3, 3, Factory.createScalarFromInitialValue(0.0), Factory.createScalarFromInitialValue(1.0));
                System.out.println("0.0이상 1.0 미만의 무작위 값을 행렬의 모든 요소로 하는 matrix 생성");
                System.out.println("matrix = " + matrix);
                System.out.println();
            } catch (SizeLessThanZeroException e) {
                System.out.println("Error: createMatrixFromRandomValue: 행렬의 행 또는 열의 크기가 1보다 작습니다.");
                System.out.println();
            }

            System.out.println("08. csv 파일로부터 m x n 행렬을 생성할 수 있다.");
            try {
                Matrix matrix = Factory.createMatrixFromCSVFile("C:\\Users\\User\\eclipse-workspace\\src\\test\\dummyMatrix.csv");
                System.out.println("csv 파일을 통해 matrix 생성");
                System.out.println("matrix = " + matrix);
                System.out.println();
            } catch (FileNotFoundException e) {
                System.out.println("Error: createMatrixFromCSVFile: csv 파일의 경로가 잘못되었습니다");
                System.out.println();
            } catch (WrongCSVFormatException e) {
                System.out.println("Error: createMatrixFromCSVFile: csv 파일의 형식이 잘못되었습니다.");
                System.out.println();
            }

            System.out.println("09. 2차원 배열로부터 m x n 행렬을 생성할 수 있다.");
            try {
                Scalar[][] array = new Scalar[3][3];
                System.out.println("2차원 배열 array의 모든 요소");
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        array[i][j] = Factory.createScalarFromInitialValue(1.0);
                        System.out.print(array[i][j] + " ");
                    }
                    System.out.println();
                }
                Matrix matrix = Factory.createMatrixFromArray(array);
                System.out.println("2차원 배열 array를 통해 matrix 생성");
                System.out.println("matrix = " + matrix);
                System.out.println();
            } catch (SizeLessThanZeroException e) {
                System.out.println("Error: createMatrixFromArray: 행렬의 행 또는 열의 크기가 1보다 작습니다.");
                System.out.println();
            }

            System.out.println("10. 단위 행렬을 생성할 수 있다.");
            try {
                Matrix matrix = Factory.createIdentityMatrix(3);
                System.out.println("3x3크기의 단위 행렬 matrix 생성");
                System.out.println("matrix = " + matrix);
                System.out.println();
            } catch (SizeLessThanZeroException e) {
                System.out.println("Error: createIdentityMatrix: 행렬의 행 또는 열의 크기가 1보다 작습니다.");
                System.out.println();
            }
        }

        System.out.println("스칼라, 벡터, 행렬의 기본 기능");
        {
            System.out.println("11v. 특정 위치의 요소를 지정/조회할 수 있다. (스칼라로 입출력)");
            try {
                Vector vector = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
                System.out.println("vector 원본");
                System.out.println(vector);
                System.out.println();
                vector.set(0, Factory.createScalarFromInitialValue(2.0));
                System.out.println("vector의 0번째 원소 2.0으로 지정 완료...");
                System.out.println("수정된 vector");
                System.out.println(vector);
                System.out.println();
                System.out.println("vector의 0번째 원소 조회");
                System.out.println(vector.get(0));
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            try {
                System.out.println("11m. 특정 위치의 요소를 지정/조회할 수 있다. (스칼라로 입출력)");
                Matrix matrix = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
                System.out.println("matrix 원본");
                System.out.println(matrix);
                System.out.println();
                matrix.set(0, 0, Factory.createScalarFromInitialValue(2.0));
                System.out.println("matrix의 (0,0)원소 2.0으로 지정 완료...");
                System.out.println("수정된 matrix");
                System.out.println(matrix);
                System.out.println();
                System.out.println("matrix의 (0,0)원소 조회");
                System.out.println(matrix.get(0, 0));
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("12. (only 스칼라) 값을 지정/조회할 수 있다. (Double로 입출력, 오토박싱 가능(double로 입력가능))");
            Scalar scalar = Factory.createScalarFromInitialValue(0.0);
            System.out.println("scalar 원본");
            System.out.println(scalar.get());
            System.out.println();

            Double D = 1.0;
            scalar.set(D);
            System.out.println("scalar의 값 Double 형으로 1.0 지정 완료...");
            System.out.println("수정된 scalar");
            System.out.println(scalar.get());
            System.out.println();

            double d = 2.0;
            scalar.set(d);
            System.out.println("scalar의 값 double 형으로 2.0 지정 완료...");
            System.out.println("수정된 scalar");
            System.out.println(scalar.get());
            System.out.println();
        }
        {
            System.out.println("13v. 크기 정보를 조회할 수 있다.");
            Vector vector = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("vector 생성");
            System.out.println(vector);
            System.out.println();
            System.out.println("vector의 크기");
            System.out.println(vector.size());
            System.out.println();

            System.out.println("13m. 크기 정보를 조회할 수 있다.");
            Matrix matrix = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("matrix 생성");
            System.out.println(matrix);
            System.out.println();
            System.out.println("matrix의 행 개수: " + matrix.size().row + " matrix의 열 개수: " + matrix.size().col);
            System.out.println();
        }
        {
            System.out.println("14. (@Override toString()) 객체를 콘솔에 출력할 수 있다.");
            System.out.println("- 14s. 스칼라: double 값 하나");
            Scalar scalar = Factory.createScalarFromInitialValue(1.0);
            System.out.println("scalar 객체 출력");
            System.out.println(scalar.toString());
            System.out.println();

            System.out.println("- 14v. 벡터: double 값들을 1차원 배열 모양으로");
            Vector vector = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("vector 객체 출력");
            System.out.println(vector.toString());
            System.out.println();

            System.out.println("- 14m. 행렬: double 값들을 2차원 배열 모양으로");
            Matrix matrix = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("matrix 객체 출력");
            System.out.println(matrix.toString());
            System.out.println();
        }
        {
            System.out.println("15s. (@Override equals()) 객체의 동등성 판단을 할 수 있다.");
            Scalar scalar1 = Factory.createScalarFromInitialValue(1.0);
            System.out.println("첫 번째 scalar1 객체 생성");
            System.out.println(scalar1);
            System.out.println();

            Scalar scalar2 = Factory.createScalarFromInitialValue(1.0);
            System.out.println("두 번째 scalar2 객체 생성");
            System.out.println(scalar2);
            System.out.println();

            Scalar scalar3 = Factory.createScalarFromInitialValue(2.0);
            System.out.println("세 번째 scalar3 객체 생성");
            System.out.println(scalar3);
            System.out.println();

            System.out.println("scalar1과 scalar2의 동등성 검사");
            System.out.println(scalar1.equals(scalar2));
            System.out.println();

            System.out.println("scalar1과 scalar3의 동등성 검사");
            System.out.println(scalar1.equals(scalar3));
            System.out.println();

            System.out.println("15v. (@Override equals()) 객체의 동등성 판단을 할 수 있다.");
            Vector vector1 = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("첫 번째 vector1 객체 생성");
            System.out.println(vector1);
            System.out.println();

            Vector vector2 = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("두 번째 vector2 객체 생성");
            System.out.println(vector2);
            System.out.println();

            Vector vector3 = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(2.0));
            System.out.println("세 번째 vector3 객체 생성");
            System.out.println(vector3);
            System.out.println();

            System.out.println("vector1과 vector2의 동등성 검사");
            System.out.println(vector1.equals(vector2));
            System.out.println();

            System.out.println("vector1과 vector3의 동등성 검사");
            System.out.println(vector1.equals(vector3));
            System.out.println();

            System.out.println("15m. (@Override equals()) 객체의 동등성 판단을 할 수 있다.");
            Matrix matrix1 = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("첫 번째 matrix1 객체 생성");
            System.out.println(matrix1);
            System.out.println();

            Matrix matrix2 = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("두 번째 matrix2 객체 생성");
            System.out.println(matrix2);
            System.out.println();

            Matrix matrix3 = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(2.0));
            System.out.println("세 번째 matrix3 객체 생성");
            System.out.println(matrix3);
            System.out.println();

            System.out.println("matrix1과 matrix2의 동등성 검사");
            System.out.println(matrix1.equals(matrix2));
            System.out.println();

            System.out.println("matrix1과 matrix3의 동등성 검사");
            System.out.println(matrix1.equals(matrix3));
            System.out.println();
        }
        {
            System.out.println("16. (implements Comparable) 스칼라의 경우 값의 대소 비교를 할 수 있다.");
            Scalar scalar1 = Factory.createScalarFromInitialValue(1.0);
            System.out.println("첫 번째 scalar1 객체 생성");
            System.out.println(scalar1);
            System.out.println();

            Scalar scalar2 = Factory.createScalarFromInitialValue(1.0);
            System.out.println("두 번째 scalar2 객체 생성");
            System.out.println(scalar2);
            System.out.println();

            Scalar scalar3 = Factory.createScalarFromInitialValue(2.0);
            System.out.println("세 번째 scalar3 객체 생성");
            System.out.println(scalar3);
            System.out.println();

            System.out.println("값의 대소 비교 : a<b 일 경우 1, a=b 일 경우 0, a>b 일 경우 -1");
            System.out.println("a = scalar1, b = scalar2 일 때의 대소 비교");
            System.out.println(scalar1.compareTo(scalar2));
            System.out.println();

            System.out.println("a = scalar1, b = scalar3 일 때의 대소 비교");
            System.out.println(scalar1.compareTo(scalar3));
            System.out.println();

            System.out.println("a = scalar3, b = scalar1 일 때의 대소 비교");
            System.out.println(scalar3.compareTo(scalar1));
            System.out.println();
        }
        {
            System.out.println("17s. (@Override clone()) 객체 복제를 할 수 있다.");
            Scalar scalar = Factory.createScalarFromInitialValue(1.0);
            System.out.println("scalar 객체 생성");
            System.out.println(scalar);
            System.out.println();

            Scalar scalarClone = scalar.clone();
            System.out.println("scalar 객체의 clone 생성");
            System.out.println(scalarClone);
            System.out.println();

            scalarClone.set(2.0);
            System.out.println("clone에 2.0 지정");
            System.out.println("scalar 객체 출력");
            System.out.println(scalar);
            System.out.println();
            System.out.println("scalar 객체의 clone 출력");
            System.out.println(scalarClone);
            System.out.println();

            System.out.println("17v. (@Override clone()) 객체 복제를 할 수 있다.");
            Vector vector = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("vector 객체 생성");
            System.out.println(vector);
            System.out.println();

            Vector vectorClone = vector.clone();
            System.out.println("vector 객체의 clone 생성");
            System.out.println(vectorClone);
            System.out.println();

            vectorClone.set(0, Factory.createScalarFromInitialValue(2.0));
            System.out.println("clone의 0번째 원소에 2.0 지정");
            System.out.println("vector 객체 출력");
            System.out.println(vector);
            System.out.println();
            System.out.println("vector 객체의 clone 출력");
            System.out.println(vectorClone);
            System.out.println();

            System.out.println("17m. (@Override clone()) 객체 복제를 할 수 있다.");
            Matrix matrix = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("matrix 객체 생성");
            System.out.println(matrix);
            System.out.println();

            Matrix matrixClone = matrix.clone();
            System.out.println("matrix 객체의 clone 생성");
            System.out.println(matrixClone);
            System.out.println();

            matrixClone.set(0, 0, Factory.createScalarFromInitialValue(2.0));
            System.out.println("clone의 (0,0) 원소에 2.0 지정");
            System.out.println("matrix 객체 출력");
            System.out.println(matrix);
            System.out.println();
            System.out.println("matrix 객체의 clone 출력");
            System.out.println(matrixClone);
            System.out.println();
        }

        System.out.println("스칼라의 연산 (non-static 메소드로 구현) *연산 결과는 자신의 새로운 값이 된다.");
        {
            System.out.println("18. 스칼라는 다른 스칼라와 덧셈이 가능하다");
            System.out.println("19. 스칼라는 다른 스칼라와 곱셈이 가능하다");
            Scalar scalar1 = Factory.createScalarFromInitialValue(1.0);
            System.out.println("scalar1 = " + scalar1);
            System.out.println();
            Scalar scalar2 = Factory.createScalarFromInitialValue(2.0);
            System.out.println("scalar2 = " + scalar2);
            System.out.println();
            Scalar scalar3 = Factory.createScalarFromInitialValue(3.0);
            System.out.println("scalar3 = " + scalar3);
            System.out.println();

            scalar1.add(scalar2);
            System.out.println("scalar1.add(scalar2) = " + scalar1);
            System.out.println();

            scalar2.mul(scalar3);
            System.out.println("scalar2.mul(scalar3) = " + scalar2);
            System.out.println();
        }

        System.out.println("벡터의 연산 (non-static 메소드로 구현) *연산 결과는 자신의 새로운 값이 된다.");
        {
            System.out.println("20. 벡터는 다른 벡터와 덧셈이 가능하다");
            System.out.println("21. 벡터는 다른 스칼라와 곱셈이 가능하다");
            Scalar scalar = Factory.createScalarFromInitialValue(2.0);
            System.out.println("scalar = " + scalar);
            System.out.println();
            Vector vector1 = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("vector1 = " + vector1);
            System.out.println();
            Vector vector2 = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(2.0));
            System.out.println("vector2 = " + vector2);
            System.out.println();

            try {
                vector1.add(vector2);
                System.out.println("vector1.add(vector2) = " + vector1);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: add: 두 벡터의 크기가 다릅니다.");
                System.out.println();
            }

            vector2.mul(scalar);
            System.out.println("vector2.mul(scalar) = " + vector2);
            System.out.println();
        }

        System.out.println("행렬의 연산 (non-static 메소드로 구현) *연산 결과는 자신의 새로운 값이 된다.");
        {
            System.out.println("22. 행렬은 다른 행렬과 덧셈이 가능하다");
            System.out.println("23. 행렬은 다른 행렬과 곱셈이 가능하다");
            Matrix matrix1 = Factory.createIdentityMatrix(3);
            System.out.println("matrix1 = " + matrix1);
            System.out.println();
            Matrix matrix2 = Factory.createIdentityMatrix(3);
            System.out.println("matrix2 = " + matrix2);
            System.out.println();
            Matrix matrix3 = Factory.createIdentityMatrix(3);
            matrix3.set(1, 1, Factory.createScalarFromInitialValue(3.0));
            System.out.println("matrix3 = " + matrix3);
            System.out.println();

            try {
                matrix1.add(matrix2);
                System.out.println("matrix1.add(matrix2) = " + matrix1);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: add: 두 행렬의 크기가 다릅니다.");
                System.out.println();
            }

            try {
                matrix1.mulByLeft(matrix3);
                System.out.println("matrix1.mulByLeft(matrix3) = " + matrix1);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: mulByLeft: 첫 번째 행렬의 열과 두 번째 행렬의 행의 크기가 다릅니다.");
                System.out.println();
            }

            matrix1 = Factory.createIdentityMatrix(3);
            matrix1.add(matrix2);

            try {
                matrix2.mulByRight(matrix3);
                System.out.println("matrix2.mulByRight(matrix3) = " + matrix2);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: mulByRight: 첫 번째 행렬의 열과 두 번째 행렬의 행의 크기가 다릅니다.");
                System.out.println();
            }
        }

        System.out.println("스칼라의 연산 (디폴트 static 메소드로 구현) *연산 결과는 자신의 새로운 값이 된다.");
        {
            System.out.println("24. 전달받은 두 스칼라의 덧셈이 가능하다");
            Scalar scalar1 = Factory.createScalarFromInitialValue(1.0);
            System.out.println("scalar1 = " + scalar1);
            System.out.println();
            Scalar scalar2 = Factory.createScalarFromInitialValue(2.0);
            System.out.println("scalar2 = " + scalar2);
            System.out.println();

            Scalar scalar = Tensors.scalarAdd(scalar1, scalar2);
            System.out.println("Tensors.scalarAdd(scalar1, scalar2) = " + scalar);
            System.out.println();

            System.out.println("25. 전달받은 두 스칼라의 곱셈이 가능하다");
            scalar = Tensors.scalarMul(scalar1, scalar2);
            System.out.println("Tensors.scalarMul(scalar1, scalar2) = " + scalar);
            System.out.println();
        }

        System.out.println("벡터의 연산 (디폴트 static 메소드로 구현) *연산 결과는 자신의 새로운 값이 된다.");
        {
            System.out.println("26. 전달받은 두 벡터의 덧셈이 가능하다 (길이가 같을 때)");
            Vector vector1 = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("vector1 = " + vector1);
            System.out.println();
            Vector vector2 = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(2.0));
            System.out.println("vector2 = " + vector2);
            System.out.println();

            try {
                Vector vector = Tensors.vectorAdd(vector1, vector2);
                System.out.println("Tensors.VectorAdd(vector1, vector2) = " + vector);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: Tensors.VectorAdd: 두 벡터의 크기가 다릅니다.");
                System.out.println();
            }

            System.out.println("27. 전달받은 스칼라와 벡터의 곱셈이 가능하다 (벡터의 모든 요소에 스칼라를 곱한다)");
            Scalar scalar1 = Factory.createScalarFromInitialValue(2.0);
            System.out.println("scalar1 = " + scalar1);
            System.out.println();

            Vector vector = Tensors.vectorMul(vector2, scalar1);
            System.out.println("Tensors.VectorMul(vector2, scalar1):" + vector);
            System.out.println();
        }

        System.out.println("행렬의 연산 (디폴트 static 메소드로 구현) *연산 결과는 자신의 새로운 값이 된다.");
        {
            System.out.println("28. 전달받은 두 행렬의 덧셈이 가능하다 (크기가 같을 때)");
            Matrix matrix1 = Factory.createIdentityMatrix(3);
            matrix1.set(0, 0, Factory.createScalarFromInitialValue(2.0));
            matrix1.set(1, 1, Factory.createScalarFromInitialValue(2.0));
            matrix1.set(2, 2, Factory.createScalarFromInitialValue(2.0));

            Matrix matrix2 = Factory.createIdentityMatrix(3);
            matrix2.set(0, 0, Factory.createScalarFromInitialValue(3.0));
            matrix2.set(1, 1, Factory.createScalarFromInitialValue(3.0));
            matrix2.set(2, 2, Factory.createScalarFromInitialValue(3.0));

            Matrix matrix3 = Factory.createMatrixFromInitialValue(3, 1, Factory.createScalarFromInitialValue(1.0));
            matrix3.set(1, 0, Factory.createScalarFromInitialValue(3.0));

            System.out.println("matrix1 = " + matrix1);
            System.out.println();
            System.out.println("matrix2 = " + matrix2);
            System.out.println();
            System.out.println("matrix3 = " + matrix3);
            System.out.println();

            try {
                Matrix matrix = Tensors.matrixAdd(matrix1, matrix2);
                System.out.println("Tensors.matrixAdd(matrix1, matrix2):" + matrix);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: Tensors.matrixAdd: 두 행렬의 크기가 다릅니다.");
                System.out.println();
            }

            System.out.println("29. 전달받은 두 행렬의 곱셈이 가능하다 ((mxn)x(nxl) 일 때)");
            try {
                Matrix matrix = Tensors.matrixMul(matrix1, matrix3);
                System.out.println("Tensors.matrixMul(matrix1, matrix3) = " + matrix);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: Tensors.matrixMul: 첫 번째 행렬의 열과 두 번째 행렬의 행의 크기가 다릅니다.");
                System.out.println();
            }
        }

        System.out.println("벡터의 고급 기능");
        {
            System.out.println("30. n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환할 수 있다");
            Vector vector = Factory.createVectorFromInitialValue(3, Factory.createScalarFromInitialValue(1.0));
            vector.set(1, Factory.createScalarFromInitialValue(2.0));
            vector.set(2, Factory.createScalarFromInitialValue(3.0));
            System.out.println("vector = " + vector);
            System.out.println();

            System.out.println("vector.toMatrixNx1() = " + vector.toMatrixNx1());
            System.out.println();

            System.out.println("31. n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환할 수 있다");
            System.out.println("vector.toMatrix1xN() = " + vector.toMatrix1xN());
            System.out.println();
        }

        System.out.println("행렬의 고급 기능");
        {
            Matrix matrix1 = Factory.createMatrixFromInitialValue(2, 2, Factory.createScalarFromInitialValue(1.0));
            Matrix matrix2 = Factory.createMatrixFromInitialValue(2, 2, Factory.createScalarFromInitialValue(1.0));
            System.out.println("32. 행렬은 다른 행렬과 가로로 합쳐질 수 있다 (두 행렬의 행 수가 같아야 가능)");
            System.out.println("matrix1 = " + matrix1);
            System.out.println();
            System.out.println("matrix2 = " + matrix2);
            System.out.println();

            try {
                matrix1.concatAsCol(matrix2);
                System.out.println("matrix1.concatAsCol(matrix2) = " + matrix1);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: concatAsCol: 두 행렬의 행의 수가 다릅니다.");
                System.out.println();
            }

            System.out.println("33. 행렬은 다른 행렬과 세로로 합쳐질 수 있다 (두 행렬의 열 수가 같아야 가능)");
            matrix1 = Factory.createMatrixFromInitialValue(2, 2, Factory.createScalarFromInitialValue(1.0));
            try {
                matrix1.concatAsRow(matrix2);
                System.out.println("matrix1.concatAsRow(matrix2) = " + matrix1);
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: concatAsRow: 두 행렬의 열의 수가 다릅니다.");
                System.out.println();
            }

            System.out.println("-  위의 두 기능은 디폴트 static 메소드로도 구현하라 ");
            matrix1 = Factory.createMatrixFromInitialValue(2, 2, Factory.createScalarFromInitialValue(1.0));
            try {
                System.out.println("Tensors.matrixConcatAsCol(matrix1, matrix2) = " + Tensors.matrixConcatAsCol(matrix1, matrix2));
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: concatAsCol: 두 행렬의 행의 수가 다릅니다.");
                System.out.println();
            }

            matrix1 = Factory.createMatrixFromInitialValue(2, 2, Factory.createScalarFromInitialValue(1.0));
            try {
                System.out.println("Tensors.matrixConcatAsRow(matrix1, matrix2) = " + Tensors.matrixConcatAsRow(matrix1, matrix2));
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: concatAsRow: 두 행렬의 열의 수가 다릅니다.");
                System.out.println();
            }
        }
        {
            System.out.println("34. 행렬은 특정 행을 벡터 형태로 추출해 줄 수 있다 (행 벡터 추출)");
            Matrix matrix = Factory.createIdentityMatrix(3);
            matrix.set(1, 1, Factory.createScalarFromInitialValue(2.0));
            matrix.set(1, 1, Factory.createScalarFromInitialValue(3.0));
            System.out.println("matrix = " + matrix);
            System.out.println();
            try {
                System.out.println("matrix.getRow(0) = " + matrix.getRow(0));
                System.out.println();
                System.out.println("matrix.getRow(1) = " + matrix.getRow(1));
                System.out.println();
                System.out.println("matrix.getRow(2) = " + matrix.getRow(2));
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("35. 행렬은 특정 열을 벡터 형태로 추출해 줄 수 있다 (열 벡터 추출)");
            try {
                System.out.println("matrix.getCol(0) = " + matrix.getCol(0));
                System.out.println();
                System.out.println("matrix.getCol(1) = " + matrix.getCol(1));
                System.out.println();
                System.out.println("matrix.getCol(2) = " + matrix.getCol(2));
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        {
            System.out.println("36. 행렬은 특정 범위의 부분 행렬을 추출해 줄 수 있다 - 인덱스로 범위지정");
            Matrix matrix = Factory.createMatrixFromInitialValue(4, 4, Factory.createScalarFromInitialValue(0.0));
            matrix.set(1, 1, Factory.createScalarFromInitialValue(1.0));
            matrix.set(1, 2, Factory.createScalarFromInitialValue(2.0));
            matrix.set(2, 1, Factory.createScalarFromInitialValue(1.0));
            matrix.set(2, 2, Factory.createScalarFromInitialValue(2.0));

            System.out.println("matrix = " + matrix);
            System.out.println();

            try {
                System.out.println("matrix의 (1,1)~(2,2)까지의 부분 행렬 = " + matrix.getSub(new Matrix.Range(1, 3), new Matrix.Range(1, 3)));
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("37. 행렬은 특정 범위의 부분 행렬을 추출해 줄 수 있다 - minor 행렬");
            try {
                System.out.println("matrix의 첫번째 행과 첫번째 열을 제외한 나머지 부분 행렬");
                System.out.println(matrix.getMinor(0, 0));
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("38. 행렬은 전치행렬을 새로 생성하여 구해줄 수 있다 (transpose)");
            System.out.println("matrix의 전치행렬 = " + matrix.getTranspose());
            System.out.println();

            System.out.println("39. 행렬은 대각 요소의 합을 구해줄 수 있다. (nxn 행렬) (trace)");
            try {
                System.out.println("matrix의 대각 요소의 합 = " + matrix.getTrace());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: getTrace: 정사각행렬이 아님.");
                System.out.println();
            }

            System.out.println("40. 행렬은 자신이 정사각 행렬인지 여부를 판별해 줄 수 있다.");
            System.out.println("matrix.isSquare() = " + matrix.isSquare());
            System.out.println();
        }
        {
            System.out.println("41. 행렬은 자신이 상삼각 행렬인지 여부를 판별해 줄 수 있다. (nxn 행렬) (upper triangular matrix)");
            Matrix matrix1 = Factory.createIdentityMatrix(3);
            Matrix matrix2 = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(1.0));
            System.out.println("matrix1 = " + matrix1);
            System.out.println();
            System.out.println("matrix2 = " + matrix2);
            System.out.println();

            try {
                System.out.println("matrix1.isUpperTriangular() = " + matrix1.isUpperTriangular());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: isUpperTriangular: 정사각행렬이 아님.");
                System.out.println();
            }

            try {
                System.out.println("matrix2.isUpperTriangular() = " + matrix2.isUpperTriangular());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: isUpperTriangular: 정사각행렬이 아님.");
                System.out.println();
            }

            System.out.println("42. 행렬은 자신이 하삼각 행렬인지 여부를 판별해 줄 수 있다  (nxn 행렬) (lower triangular matrix)");
            try {
                System.out.println("matrix1.isLowerTriangular() = " + matrix1.isLowerTriangular());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: isLowerTriangular: 정사각행렬이 아님.");
                System.out.println();
            }

            try {
                System.out.println("matrix2.isLowerTriangular() = " + matrix2.isLowerTriangular());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: isLowerTriangular: 정사각행렬이 아님.");
                System.out.println();
            }

            System.out.println("43. 행렬은 자신이 단위 행렬인지 여부를 판별해 줄 수 있다 (nxn 행렬) (identity matrix)");
            try {
                System.out.println("matrix1.isIdentity() = " + matrix1.isIdentity());
                System.out.println();
                System.out.println("matrix2.isIdentity() = " + matrix2.isIdentity());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: isIdentity: 정사각행렬이 아님.");
                System.out.println();
            }

            System.out.println("44. 행렬은 자신이 영 행렬인지 여부를 판별해 줄 수 있다 (zero matrix)");
            Matrix matrix3 = Factory.createMatrixFromInitialValue(3, 3, Factory.createScalarFromInitialValue(0.0));
            System.out.println("matrix3 = " + matrix3);
            System.out.println();
            System.out.println("matrix3.isZero() = " + matrix3.isZero());
            System.out.println();
            System.out.println("matrix1.isZero() = " + matrix1.isZero());
            System.out.println();

            System.out.println("45. 행렬은 특정 두 행의 위치를 맞교환할 수 있다");
            try {
                System.out.println("matrix1 = " + matrix1);
                System.out.println();
                matrix1.swapRow(0, 1);
                System.out.println("matrix1.swapRow(0, 1) = " + matrix1);
                System.out.println();
                matrix1.swapRow(0, 1);
                System.out.println("matrix1.swapRow(0, 1) = " + matrix1);
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
            System.out.println("46. 행렬은 특정 두 열의 위치를 맞교환할 수 있다");
            try {
                System.out.println("matrix1 = " + matrix1);
                System.out.println();
                matrix1.swapCol(0, 2);
                System.out.println("matrix1.swapCol(0, 2) = " + matrix1);
                System.out.println();
                matrix1.swapCol(0, 2);
                System.out.println("matrix1.swapCol(0, 2) = " + matrix1);
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("47. 행렬은 특정 행에 상수배(스칼라)를 할 수 있다.");
            try {
                System.out.println("matrix2 = " + matrix2);
                System.out.println();
                matrix2.mulRow(1, Factory.createScalarFromInitialValue(2.0));
                System.out.println("matrix2.mulRow(1, Factory.createScalarFromInitialValue(2.0)) = " + matrix2);
                System.out.println();
                matrix2.mulRow(1, Factory.createScalarFromInitialValue(1.0 / 2.0));
                System.out.println("matrix2.mulRow(1, Factory.createScalarFromInitialValue(1.0/2.0)) = " + matrix2);
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("48. 행렬은 특정 열에 상수배(스칼라)를 할 수 있다.");
            try {
                matrix2.mulCol(1, Factory.createScalarFromInitialValue(2.0));
                System.out.println("matrix2.mulCol(1, Factory.createScalarFromInitialValue(2.0)) = " + matrix2);
                System.out.println();
                matrix2.mulCol(1, Factory.createScalarFromInitialValue(1.0 / 2.0));
                System.out.println("matrix2.mulCol(1, Factory.createScalarFromInitialValue(1.0/2.0)) = " + matrix2);
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("49. 행렬은 특정 행에 다른 행의 상수배를 더할 수 있다 .");
            try {
                matrix2.mulRowAndAddOther(0, 1, Factory.createScalarFromInitialValue(2.0));
                System.out.println("matrix2.mulRowAndAddOther(0, 1, Factory.createScalarFromInitialValue(2.0)) = " + matrix2);
                System.out.println();
                matrix2.mulRowAndAddOther(0, 1, Factory.createScalarFromInitialValue(-2.0));
                System.out.println("matrix2.mulRowAndAddOther(0, 1, Factory.createScalarFromInitialValue(-2.0)) = " + matrix2);
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("50. 행렬은 특정 열에 다른 열의 상수배를 더할 수 있다 .");
            try {
                matrix2.mulColAndAddOther(0, 1, Factory.createScalarFromInitialValue(2.0));
                System.out.println("matrix2.mulColAndAddOther(0, 1, Factory.createScalarFromInitialValue(2.0)) = " + matrix2);
                System.out.println();
                matrix2.mulColAndAddOther(0, 1, Factory.createScalarFromInitialValue(-2.0));
                System.out.println("matrix2.mulColAndAddOther(0, 1, Factory.createScalarFromInitialValue(-2.0)) = " + matrix2);
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }

            System.out.println("51. 자신으로부터 RREF 행렬을 구해서 반환해 줄 수 있다. (row reduced echelon form");
            Matrix matrix4 = Factory.createMatrixFromInitialValue(2, 3, Factory.createScalarFromInitialValue(0.0));
            matrix4.set(0, 0, Factory.createScalarFromInitialValue(0.0));
            matrix4.set(0, 1, Factory.createScalarFromInitialValue(1.0));
            matrix4.set(0, 2, Factory.createScalarFromInitialValue(2.0));
            matrix4.set(1, 0, Factory.createScalarFromInitialValue(0.0));
            matrix4.set(1, 1, Factory.createScalarFromInitialValue(3.0));
            matrix4.set(1, 2, Factory.createScalarFromInitialValue(4.0));
            // 0  1  2
            // 0  3  4

            System.out.println("matrix4 = " + matrix4);
            System.out.println();
            System.out.println("matrix4.getRREF() = " + matrix4.getRREF());
            System.out.println();

            System.out.println("52. 행렬은 자신이 RREF 행렬인지 여부를 판별해 줄 수 있다");
            System.out.println("matrix4.isRREF() = " + matrix4.isRREF());
            System.out.println();
            System.out.println("matrix4.getRREF().isRREF() = " + matrix4.getRREF().isRREF());
            System.out.println();

            System.out.println("53. 행렬은 자신의 행렬식을 구해줄 수 있다 .(nxn행렬) (determinant)");
            matrix3.set(0, 0, Factory.createScalarFromInitialValue(0.0));
            matrix3.set(0, 1, Factory.createScalarFromInitialValue(1.0));
            matrix3.set(0, 2, Factory.createScalarFromInitialValue(0.0));
            matrix3.set(1, 0, Factory.createScalarFromInitialValue(-2.0));
            matrix3.set(1, 1, Factory.createScalarFromInitialValue(0.0));
            matrix3.set(1, 2, Factory.createScalarFromInitialValue(0.0));
            matrix3.set(2, 0, Factory.createScalarFromInitialValue(0.0));
            matrix3.set(2, 1, Factory.createScalarFromInitialValue(0.0));
            matrix3.set(2, 2, Factory.createScalarFromInitialValue(-2.0));
            //   0  1  0
            //  -2  0  0
            //   0  0 -3

            System.out.println("matrix3 = " + matrix3);
            System.out.println();

            try {
                System.out.println("matrix3.getDeterminant() = " + matrix3.getDeterminant());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: getDeterminanat: 정사각행렬이 아님.");
                System.out.println();
            }

            System.out.println("54. 행렬은 자신의 역행렬을 구해줄 수 있다.(nxn행렬)");
            try {
                System.out.println("matrix3.getInverse() = " + matrix3.getInverse());
                System.out.println();
            } catch (CalculateNotSupportedException e) {
                System.out.println("Error: getInverse: 정사각행렬이 아니거나, 행렬식이 0임.");
                System.out.println();
            }
        }
    }
}