/** https://codeforces.com/contest/518/problem/B
 *  idea: greedy
 */
import java.util.Scanner;
 
public class TanyaAndPostcard {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		char[] targetArr = str1.toCharArray();
		String str2 = sc.next();
		char[] newpaperArr = str2.toCharArray();
		int[] target = new int[128];
		int[] newpaper = new int[128];
		for (char c: targetArr) {
			target[(int)c] += 1;
		}
		for (char c: newpaperArr) {
			newpaper[(int)c] += 1;
		}
		
		int yal = 0;
		int whool = 0;
		int tmp = 0;
		int gap = 'a' - 'A';
		for (int i='A'; i < 'Z'+1; i++) {
			tmp = Math.min(target[i], newpaper[i]);
			yal += tmp;
			target[i] -= tmp;
			newpaper[i] -= tmp;
			tmp = Math.min(target[i+gap], newpaper[i+gap]);
			yal += tmp;
			target[i+gap] -= tmp;
			newpaper[i+gap] -= tmp;
			whool += Math.min(target[i], newpaper[i+gap]);
			whool += Math.min(target[i+gap], newpaper[i]);
		}
 
		System.out.println(yal + " " + whool);
	}
}