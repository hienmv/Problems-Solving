import java.io.*;
import java.util.*;

public class Tool {

    private static String[] keywords = {
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
        };
        return header;
    }
    
    private static String[] getStatistic(int problem_number) {
        // TODO
        // separated by difficulty (easy/medium/hard); source (leetcode/codefore/hackerrank/interviewbit...)
        String[] statistic = {
            "```java",
            "Number of problems: " + problem_number,
            "```",
        };
        return statistic;
    }

    private static String[] getFolderPaths() {
       String[] folder_paths = {"Java/", "C/", "C++/", "Python/"};
       return folder_paths;
    }

    private static String getFilePath() {
        return "README.md";
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

    private static TreeMap<String, ArrayList<Item>> getFilesGroupByTags() throws Exception {
        TreeMap<String, ArrayList<Item>> map = new TreeMap<>();
        for( String keyword : keywords) {
            map.put(keyword, new ArrayList<>());
        }
        String[] folder_paths = getFolderPaths();
        for (String folder_path : folder_paths) {
            final File folder = new File(folder_path);
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

        return map;
    }

    private static void flushToReadMe(TreeMap<String, ArrayList<Item>> map) throws Exception {
        // body
        ArrayList<String> body = new ArrayList<>();
        HashSet<String> problem_names = new HashSet<>();
        for (Map.Entry<String, ArrayList<Item>> entry : map.entrySet()) {
            TreeMap<String, String> line_map = new TreeMap<>();
            ArrayList<Item> items = entry.getValue();
            Collections.sort(items);
            for(Item item : items) {
                problem_names.add(item.getName());
                if (line_map.containsKey(item.getName())) {
                    line_map.put(item.getName(), line_map.get(item.getName()) + item.getPath());
                } else {
                    line_map.put(item.getName(), item.getPath());
                }    
            }
            // block header
            body.add("### " + entry.getKey() + " (" + line_map.size() + ")");
            // block body
            String prefix = entry.getKey() != "#todo" ? "- [x] " : "- [ ] ";
            for (Map.Entry<String, String> line_entry : line_map.entrySet()) {
                body.add(prefix + line_entry.getKey() + line_entry.getValue());
            }
        }

        // flush to file
        {
            FileWriter writer = new FileWriter(getFilePath());
            // header
            for(String line : getHeader()) {
                writer.write(line + System.lineSeparator());
            }
            // statistic 
            for(String line : getStatistic(problem_names.size())) {
                writer.write(line + System.lineSeparator());
            }
            // body
            for(String line : body) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TreeMap<String, ArrayList<Item>> filesGroupByTags = getFilesGroupByTags();
        flushToReadMe(filesGroupByTags);
    }
}
