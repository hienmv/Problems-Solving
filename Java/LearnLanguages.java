/**
 * https://codeforces.com/problemset/problem/277/A #dsu #dfs
 *
 * <p>other way: treat m languages as : n+1 -> n+m, as a node in graph.
 */
import java.util.Scanner;

public class LearnLanguages {
  public static void makeSet(int[] parent, int[] rank) {
    for (int i = 0; i < parent.length; i++) {
      parent[i] = i;
      rank[i] = 0;
    }
  }

  public static int findSet(int u, int[] parent) {
    if (parent[u] != u) {
      parent[u] = findSet(parent[u], parent);
    }
    return parent[u];
  }

  public static boolean unionSet(int u, int v, int[] parent, int[] rank) {
    int up = findSet(u, parent);
    int vp = findSet(v, parent);

    if (up == vp) {
      return false;
    }

    if (rank[up] > rank[vp]) {
      parent[vp] = up;
    } else if (rank[up] < rank[vp]) {
      parent[up] = vp;
    } else {
      parent[up] = vp;
      rank[vp] += 1;
    }

    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    int[] parent = new int[m + 1];
    int[] rank = new int[m + 1];
    makeSet(parent, rank);

    int not_known_language_nums = 0;
    int known_language_nums = 0;
    int have_to_learn_language_nums = 0;
    boolean[] visited_languages = new boolean[m + 1];
    boolean indirect_flg = false;

    for (int i = 0; i < n; i++) {
      known_language_nums = sc.nextInt();
      if (known_language_nums == 0) {
        not_known_language_nums++;
        continue;
      }

      indirect_flg = true;

      int lang;
      int[] known_languages = new int[known_language_nums];
      for (int l = 0; l < known_language_nums; l++) {
        lang = sc.nextInt();
        known_languages[l] = lang;
        if (!visited_languages[lang]) {
          visited_languages[lang] = true;
          have_to_learn_language_nums++;
        }
        if (l > 0) {
          if (unionSet(known_languages[l], known_languages[l - 1], parent, rank)) {
            have_to_learn_language_nums -= 1;
          }
        }
      }
    }

    if (indirect_flg) {
      System.out.println((not_known_language_nums + have_to_learn_language_nums - 1));
    } else {
      System.out.println((not_known_language_nums));
    }
  }
}
