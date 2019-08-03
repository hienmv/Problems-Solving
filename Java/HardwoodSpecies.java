/*	https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1167
*	idea: greedy
*/

import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

public class HardwoodSpecies {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcases = Integer.parseInt(sc.nextLine());
		sc.nextLine();

		String s;
		int idx = 0;
		float rate = 0.0f;
		for (int t=0; t < testcases; t++) {
			int numHardWoods = 0;
			HashMap<String, Integer> hashMap = new HashMap<>();
			ArrayList<String> arrHardWood = new ArrayList<>();
			ArrayList<Integer> arrHardWoodNum = new ArrayList<>();
			while(sc.hasNextLine()) {
				s = sc.nextLine();
				if (s.isEmpty()) { 
					break;
				}
				if (hashMap.containsKey(s)) {
					idx = hashMap.get(s);
					arrHardWoodNum.set(idx, arrHardWoodNum.get(idx)+1);
				} else {
					arrHardWood.add(s);
					arrHardWoodNum.add(1);
					idx = arrHardWood.size() - 1;
					hashMap.put(s, idx);
				}
				numHardWoods++;
			}
			Collections.sort(arrHardWood);
			for (String str : arrHardWood) {
				idx = hashMap.get(str);
				rate = 100.0f * (float) arrHardWoodNum.get(idx) / numHardWoods;
				System.out.println(String.format("%s %.4f", str, rate));
			}
			if (t < testcases - 1) {
				System.out.println("");
			}
		}
	}
}