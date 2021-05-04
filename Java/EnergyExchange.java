/**
 * https://codeforces.com/problemset/problem/68/B #binary-search
 *
 * <p>sumTransfer = sum(a[i] - M, if a[i] > M) sumEnergy = sum(a[i])
 *
 * <p>n * M <= sumEnergy - sumTransfer * k / 100
 *
 * <p>M
 *
 * <p>|right - left| > 10^-7
 */
import java.util.Arrays;
import java.util.Scanner;

public class EnergyExchange {
  public static double getSumTransfer(double[] sumArr, double[] arr, double val) {
    int left = 0;
    int right = sumArr.length - 1;
    int targetIdx = -1;
    int mid;
    while (left <= right) {
      mid = left + (right - left) / 2;
      if (arr[mid] > val) {
        targetIdx = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    if (targetIdx == -1) return 0;
    double minusVal = (targetIdx == 0) ? 0 : sumArr[targetIdx - 1];
    return (sumArr[sumArr.length - 1] - minusVal - (sumArr.length - targetIdx) * val);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    double[] arr = new double[n];
    double[] sumArr = new double[n];
    double tmp;
    for (int i = 0; i < n; i++) {
      tmp = sc.nextDouble();
      arr[i] = tmp;
    }
    Arrays.sort(arr);

    sumArr[0] = arr[0];
    for (int i = 1; i < n; i++) {
      sumArr[i] = sumArr[i - 1] + arr[i];
    }

    double sumEnergy = sumArr[n - 1];
    double left = 0;
    double right = sumEnergy / sumArr.length;
    double mid;
    double sumTransfer = 0;
    double val = 0;
    while (Math.abs(left - right) > 1e-7) {
      mid = left + (right - left) / 2;
      sumTransfer = getSumTransfer(sumArr, arr, mid);
      if (n * mid <= sumEnergy - sumTransfer * k / 100) {
        val = mid;
        left = mid;
      } else {
        right = mid;
      }
    }
    System.out.println(val);
  }
}
