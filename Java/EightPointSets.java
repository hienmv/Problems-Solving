/*	https://codeforces.com/contest/334/problem/B
#sorting
	treat each column as an array
	check each array: have only 3 distinct number, each number that is not displayed excess 3 times.
	check the poin(x2, y2) exist or not ? 
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class EightPointSets {
	
	static String judgment() {
		Scanner sc = new Scanner(System.in);
		int[] arrX = new int[8];
		int[] arrY = new int[8];
		
		for (int i=0; i < 8; i++) {
			int temp = sc.nextInt();
			arrX[i] = temp;
			temp = sc.nextInt();
			arrY[i] = temp;
		}
		sc.close();

		HashMap<Integer, HashSet<Integer>> mapX= new HashMap<>();
		HashMap<Integer, HashSet<Integer>> mapY= new HashMap<>();
		for (int i=0; i < 8; i++) {
			if (!mapX.containsKey(arrX[i])) {
				HashSet<Integer> set = new HashSet<>();
				set.add(arrY[i]);
				mapX.put(arrX[i], set);
				if(mapX.size() > 3) {
					return "ugly";
				}
			} else {
				HashSet<Integer> set = mapX.get(arrX[i]);
				boolean added = set.add(arrY[i]);
				if(!added || set.size() > 3) {
					return "ugly";
				}
			}
			if (!mapY.containsKey(arrY[i])) {
				HashSet<Integer> set = new HashSet<>();
				set.add(arrX[i]);
				mapY.put(arrY[i], set);
				if(mapY.size() > 3) {
					return "ugly";
				}
			} else {
				HashSet<Integer> set = mapY.get(arrY[i]);
				boolean added = set.add(arrX[i]);
				if(!added || set.size() > 3) {
					return "ugly";
				}
			}
		}

		// check the poin(x2, y2) exist or not 
		int[] sortedArrX = Arrays.copyOf(arrX, 8);
		Arrays.sort(sortedArrX);
		int[] sortedArrY = Arrays.copyOf(arrY, 8);
		Arrays.sort(sortedArrY);
		for (int i=0; i < 8; i++) {
			if (arrX[i] == sortedArrX[3] && arrY[i] == sortedArrY[3]) {
				return "ugly";
			}
		} 
		return "respectable";
	}

	public static void main(String[] args) {
		System.out.println(judgment());
	}
}