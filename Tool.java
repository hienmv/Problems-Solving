import java.io.*;
import java.util.*;

public class Tool {

    static String[] keywords = {
        "#01knapsack",
        "#ad-hoc-1",
        "#array",
        "#backtracking",
        "#bellman-ford",
        "#bfs",
        "#binary-search",
        "#binary-search-tree",
        "#bit-manipulation",
        "#brute-force",
        "#constructive-algorithms",
        "#deque",
        "#dfs",
        "#dijkstra",
        "#divide-and-conquer",
        "#dsu",
        "#dynamic-programming",
        "#floyd-warshall",
        "#geometry",
        "#graph",
        "#greedy",
        "#hash-table",
        "#heap",
        "#implementation",
        "#kmp",
        "#knapsack",
        "#lcs",
        "#linked-list",
        "#lis",
        "#map",
        "#math",
        "#mst",
        "#number-theory",
        "#optimized",
        "#pointer",
        "#prim",
        "#priority-queue",
        "#queue",
        "#recursion",
        "#refactor",
        "#segment-tree",
        "#shortest-path",
        "#simple-way",
        "#slicing-window",
        "#sorting",
        "#special-problem",
        "#stack",
        "#string",
        "#todo",
        "#topological-sort",
        "#tree",
        "#trie",
        "#two-pointer",
    };

    private static String[] getHeader() {
        String[] header = { 
            "# Problems-Solving",
            "My implementation of useful data structures, algorithms, as well as my solutions to programming puzzles.",
            "## Top coding problems"
        };
        return header;
    }
    private static class Item implements Comparable {
        String name;
        String path;
        String extension;

        Item(String path) {
            this.path = path;
            int slash_index = path.lastIndexOf("/");
            int dot_index = path.indexOf(".");
            this.name = path.substring(slash_index + 1, dot_index);
            this.extension = path.substring(dot_index + 1);
        }
        public String getName() {
            return name;
        }
        public String getPath() {
            return " [(" + this.extension +")](./" + this.path + ")";
        }

        public int compareTo(Object o) {
            Item other = (Item) o;
            if (this.name.equals(other.name)) {
                return this.extension.compareTo(other.extension);
            }
            return this.name.compareTo(other.name);
        }
    }

    public static void main(String[] args) throws Exception {
        TreeMap<String, ArrayList<Item>> map = new TreeMap<>();
        for( String keyword : keywords) {
            map.put(keyword, new ArrayList<>());
        }
        String[] folder_names = {"Java/", "C/", "C++/", "Python/"};
        for (String name : folder_names) {
            final File folder = new File(name);
            for (final File fileEntry : folder.listFiles()) {
                Scanner scanner = new Scanner(fileEntry);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split(" ");
                    for (String word : words) {
                        if (map.containsKey(word)) {
                            map.get(word).add(new Item(fileEntry.getPath()));
                        }
                    }
                }
                scanner.close();
            }
        }

        flushToReadMe(map);
    }

    private static void flushToReadMe(TreeMap<String, ArrayList<Item>> map) throws Exception {
        FileWriter writer = new FileWriter("README.md"); 
        // header
        for(String line : getHeader()) {
            writer.write(line + System.lineSeparator());
        }
        // body
        for (Map.Entry<String, ArrayList<Item>> entry : map.entrySet()) {
            TreeMap<String, String> line_map = new TreeMap<>();
            ArrayList<Item> items = entry.getValue();
            Collections.sort(items);
            for(Item item : items) {
                if (line_map.containsKey(item.getName())) {
                    line_map.put(item.getName(), line_map.get(item.getName()) + item.getPath());
                } else {
                    line_map.put(item.getName(), item.getPath());
                }    
            }
            // block header
            writer.write("### " + entry.getKey() + " (" + line_map.size() + ")" + System.lineSeparator());
            // block body
            for (Map.Entry<String, String> line_entry : line_map.entrySet()) {
                writer.write("- [ ] " + line_entry.getKey() + line_entry.getValue() + System.lineSeparator());
            }
        }
        writer.close();
    }
}
