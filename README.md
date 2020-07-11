# Matrix_Multiplication
Matrix multiplication 2 two matrix using hadoop

Run steps:

Add a file containing both matrix in the hdfs.
  >The values should be of the form (A i j valA) for first matrix and (B j k valB) for second.
  >The file is space seperated.(Note: you can change the separation type to "," etc. by changing split() function regex argument in Map class)
  >Add the file into hdfs file system
    >Make a directory using "hdfs dfs -mkdir <directory name>" eg($ hdfs dfs -mkdir /user)
    >Add the fiel to hadoop file system by using command "hdfs dfs -put <location of file> <directory in hdfs that you have made>" eg($ hdf dfs ~/Documents/A.txt /user/mtxM/input/)

Now to run the program first make a jar file by using the command "mvn clean install".

Once the jar is build using the following command:
$ hadoop jar <location/name/of/jar> matrix_multiplication.MatrixMultiplication <hdfs input file location> <hdfs output location> <i> <j> <k>
where i, j, k are the dimensions of the 2 matrix eg A(i,j) B(j,k)

eg of command:
hadoop /target/matrix_multiplication-0.0.1-SNAPSHOT.jar matrix_multiplication.MatrixMultiplication /user/mtxM/input/A.txt /user/mtxM/output 10000 10000 10000


