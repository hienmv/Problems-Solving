/* https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=119
    tag: #divide-and-conquer
*/

import java.util.Scanner;

class BitMaps {
    public static void B2D(char[][] arr, int top, int left, int bottom, int right, StringBuilder str) {
        // base case
        if (left >= right || top >= bottom) {
            return;
        }

        // check quarter
        int expectSum = (right - left) * (bottom - top); 
        int sum = 0;
        
        for (int y = left; y < right; y++) {
            for (int x = top; x < bottom; x++) {
                sum += (arr[x][y] == '1') ? 1 : 0;
            }
        }
        
        if (sum == 0) {
            str.append("0");
            return;
        }
        if (sum == expectSum) {
            str.append("1");
            return;
        }

        // divide
        str.append("D");
        int rows = bottom - top;
        int columns = right - left;
        int midHeight = (top + bottom + 1) / 2;
        int midWidth = (left + right + 1) / 2;
        if (rows == 1 ) {
            B2D(arr, top, left, bottom, midWidth, str);
            B2D(arr, top, midWidth, bottom, right, str);
        } else if (columns == 1) {
            B2D(arr, top, left, midHeight, right, str);
            B2D(arr, midHeight, left, bottom, right, str);
        } else {
            B2D(arr, top, left, midHeight, midWidth, str);
            B2D(arr, top, midWidth, midHeight, right, str);
            B2D(arr, midHeight, left, bottom, midWidth, str);
            B2D(arr, midHeight, midWidth, bottom, right, str);
        }
    }
    
    public static void D2B(char[] arr, int[] current, int top, int left, int bottom, int right, char[][] result) {
        // base case
        int idx = current[0];
        if (idx >= arr.length) {
            return;
        }
        // update current idx
        current[0] += 1;
        if (arr[idx] != 'D') {
            for (int y = left; y < right; y++) {
                for (int x = top; x < bottom; x++) {
                    result[x][y] = arr[idx];
                }
            }
            return;
        }
        // divide
        int rows = bottom - top;
        int columns = right - left;
        int midHeight = (top + bottom + 1) / 2;
        int midWidth = (left + right + 1) / 2;
        if (rows == 1 ) {
            D2B(arr, current, top, left, bottom, midWidth, result);
            D2B(arr, current, top, midWidth, bottom, right, result);
        } else if (columns == 1) {
            D2B(arr, current, top, left, midHeight, right, result);
            D2B(arr, current, midHeight, left, bottom, right, result);
        } else {
            D2B(arr, current, top, left, midHeight, midWidth, result);
            D2B(arr, current, top, midWidth, midHeight, right, result);
            D2B(arr, current, midHeight, left, bottom, midWidth, result);
            D2B(arr, current, midHeight, midWidth, bottom, right, result);
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        while(!type.equals("#")) {
            // input
            int rows = sc.nextInt();
            int columns = sc.nextInt();
            
            // calculate
            String resultStr;
            String retType;
            char[] dataArr;
            StringBuilder data= new StringBuilder();
            if (type.equals("B")) {
                // input
                while (data.length() < rows * columns) {
                    data.append(sc.next());
                }
                dataArr = data.toString().toCharArray();

                retType = "D";
                char[][] arr = new char[rows][columns];
                for (int i=0; i < rows; i++) {
                    for (int j=0; j < columns; j++) {
                        arr[i][j] = dataArr[i*columns + j];
                    }
                }
                
                StringBuilder result = new StringBuilder();
                B2D(arr, 0, 0, rows, columns, result);
                resultStr = result.toString();
                // next testcase
                type = sc.next();
                
            } else { // type = D
                // input
                while (true) {
                    String token = sc.next();
                    if (token.equals("D") || token.equals("B") || token.equals("#")) {
                        // next testcase
                        type = token;
                        break;
                    }
                    data.append(token);
                }
                dataArr = data.toString().toCharArray();

                retType = "B";
                char[][] resultArr = new char[rows][columns];
                int[] current = new int[1];
                D2B(dataArr, current, 0, 0, rows, columns, resultArr);
                StringBuilder resultStrBuilder = new StringBuilder();
                for(char[] rowStr : resultArr) {
                    resultStrBuilder.append(rowStr);
                }
                resultStr = resultStrBuilder.toString();
            }

            // result
            System.out.println(retType + " " + rows + " " + columns);
            int len = resultStr.length();
            for (int l=0; l < len; l += 50) {
                System.out.println(resultStr.substring(l, Math.min(l + 50, len)));
            }
        }
    }
}