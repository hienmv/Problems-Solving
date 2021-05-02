import java.io.*;
import java.util.*;

public class ReadMeGenerator {

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

    private static TreeMap<String, ArrayList<Item>> files_group_by_tags_map;
    private static TreeMap<String, ArrayList<String>> files_group_by_sources_map;
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

    private static String[] getFolderPaths() {
        String[] folder_paths = {"Java/", "C/", "C++/", "Python/"};
        return folder_paths;
     }
 
    private static String getFilePath() {
         return "README.md";
     }

    private static ArrayList<String> getStatistic() {
        // TODO: separated by difficulty (easy/medium/hard)
        TreeMap<String, Integer> source_count = new TreeMap<>();
        int problems_number = 0;
        for (Map.Entry<String, ArrayList<String>> entry : files_group_by_sources_map.entrySet()) {
            HashSet<String> urls = new HashSet<>();
            for(String url : entry.getValue()) {
                urls.add(url);
            }
            source_count.put(entry.getKey(), urls.size());
            problems_number += urls.size();
        }
        
        ArrayList<String> info = new ArrayList<>();
        // list
        {
            info.add("```java");
            info.add("Number of problems : " + problems_number);
            for (Map.Entry<String, Integer> entry : source_count.entrySet()) {
                info.add("- " + entry.getKey() + " : " + entry.getValue());
            }
            info.add("```");
        }

        // pie-chart
        {
            info.add("```mermaid");
            info.add("pie");
            info.add("\ttitle Category by sources");
            for (Map.Entry<String, Integer> entry : source_count.entrySet()) {
                info.add("\t\"" + entry.getKey() + "\" : " + entry.getValue());
            }
            info.add("```");
        }

        return info;
    }

    private static ArrayList<String> getBody() {
        // body
        ArrayList<String> body = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Item>> entry : files_group_by_tags_map.entrySet()) {
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
            body.add("### " + entry.getKey() + " (" + line_map.size() + ")");
            // block body
            String prefix = entry.getKey() != "#todo" ? "- [x] " : "- [ ] ";
            for (Map.Entry<String, String> line_entry : line_map.entrySet()) {
                body.add(prefix + line_entry.getKey() + line_entry.getValue());
            }
        }

        return body;
    }

    private static String getSource(String protocol, String url) {
        int url_idx = url.indexOf(protocol);
        int next_slash_idx = url.indexOf("/", url_idx + protocol.length());
        return url.substring(url_idx + protocol.length(), next_slash_idx);
    }

    private static void analytics() throws Exception {
        // init
        files_group_by_tags_map = new TreeMap<>();
        for( String keyword : keywords) {
            files_group_by_tags_map.put(keyword, new ArrayList<>());
        }
        files_group_by_sources_map = new TreeMap<>();

        String[] folder_paths = getFolderPaths();
        for (String folder_path : folder_paths) {
            final File folder = new File(folder_path);
            for (final File fileEntry : folder.listFiles()) {
                Scanner scanner = new Scanner(fileEntry);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split(" ");
                    for (String word : words) {
                        // by tags
                        {
                            if (files_group_by_tags_map.containsKey(word)) {
                                files_group_by_tags_map.get(word).add(new Item(fileEntry.getPath()));
                            }
                        }
                        
                        // by sources
                        {
                            String source = "";
                            if (word.contains("http://")) {
                                source = getSource("http://", word);
                            }
                            else if (word.contains("https://")) {
                                source = getSource("https://", word);
                            }
                            if (!source.isEmpty()) {
                                if (source.startsWith("www.")) {
                                    source = source.substring(4);
                                }
                                if (files_group_by_sources_map.containsKey(source)) {
                                    files_group_by_sources_map.get(source).add(word);
                                } else {
                                    ArrayList<String> url = new ArrayList<>();
                                    url.add(word);
                                    files_group_by_sources_map.put(source, url);
                                }
                            }
                        }
                    }
                }
                scanner.close();
            }
        }
    }

    private static void flushToReadMe() throws Exception {
        // flush to file
        FileWriter writer = new FileWriter(getFilePath());
        // header
        for(String line : getHeader()) {
            writer.write(line + System.lineSeparator());
        }
        // statistic 
        for(String line : getStatistic()) {
            writer.write(line + System.lineSeparator());
        }
        // body
        for(String line : getBody()) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        analytics();
        flushToReadMe();
    }
}
