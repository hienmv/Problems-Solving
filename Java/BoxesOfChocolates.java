/** #number-theory */
import java.util.Scanner;

class BoxesOfChocolates {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    for (int t = 0; t < testcases; t++) {
      int N = sc.nextInt();
      int B = sc.nextInt();
      int remains = 0;
      for (int i = 0; i < B; i++) {
        int K = sc.nextInt();
        int remainK = 1;
        for (int j = 0; j < K; j++) {
          int aj = sc.nextInt();
          remainK = (remainK * aj) % N;
        }
        remains = (remains + remainK) % N;
      }
      System.out.println((remains));
    }
  }
}
