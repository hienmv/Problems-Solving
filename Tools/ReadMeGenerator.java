import java.io.*;
import java.util.*;

public class ReadMeGenerator {

  private static class Item implements Comparable {
    String name;
    String path;
    String original_path;
    String extension;

    Item(String path) {
      int slash_index = path.lastIndexOf("/");
      int dot_index = path.lastIndexOf(".");
      this.name = path.substring(slash_index + 1, dot_index);
      this.extension = path.substring(dot_index + 1);
      this.original_path = path;
      this.path = path.replace("../", "");
    }

    public String getName() {
      return name;
    }

    public String getPath() {
      return " [(" + this.extension + ")](./" + this.path + ")";
    }

    public String getOriginalPath() {
      return " [(" + this.extension + ")](./" + this.original_path + ")";
    }

    public String toString() {
      return getName() + getPath();
    }

    public String toOriginalString() {
      return getName() + getOriginalPath();
    }

    public int compareTo(Object o) {
      Item other = (Item) o;
      if (this.name.equals(other.name)) {
        return this.extension.compareTo(other.extension);
      }
      return this.name.compareTo(other.name);
    }
  }

  private static TreeMap<String, ArrayList<Item>> files_group_by_tags_map; // tag - Item of file
  private static TreeMap<String, ArrayList<String>> files_group_by_sources_map; // file name - url
  private static TreeMap<Integer, ArrayList<Item>> statisticURL; // count - url
  private static ArrayList<String> tags;

  private static String[] getFolderPaths() {
    String[] folder_paths = {"../Java/", "../C/", "../C++/", "../Python/"};
    return folder_paths;
  }

  private static String getFilePath() {
    return "../README.md";
  }

  private static ArrayList<String> getTags() throws Exception {
    tags = new ArrayList<>();
    Scanner scanner = new Scanner(new File("tags.env"));
    while (scanner.hasNextLine()) {
      String tag = scanner.nextLine();
      tags.add(tag);
    }
    ;
    scanner.close();
    return tags;
  }

  private static String[] getHeader() {
    String[] header = {
      "# Problems-Solving",
      "My implementation of useful data structures, algorithms, as well as my solutions to"
          + " programming puzzles.",
    };
    return header;
  }

  private static ArrayList<String> getStatistic() {
    // TODO: separated by difficulty (easy/medium/hard)
    TreeMap<String, Integer> source_count = new TreeMap<>();
    int problems_number = 0;
    for (Map.Entry<String, ArrayList<String>> entry : files_group_by_sources_map.entrySet()) {
      HashSet<String> urls = new HashSet<>();
      for (String url : entry.getValue()) {
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
      for (Item item : items) {
        if (line_map.containsKey(item.getName())) {
          line_map.put(item.getName(), line_map.get(item.getName()) + item.getPath());
        } else {
          line_map.put(item.getName(), item.getPath());
        }
      }
      // block header
      body.add("### " + entry.getKey() + " (" + line_map.size() + ")");
      // block body
      String prefix = entry.getKey().equals("#todo") ? "- [ ] " : "- [x] ";
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

  private static void init() throws Exception {
    // init
    statisticURL = new TreeMap<>();

    files_group_by_tags_map = new TreeMap<>();
    for (String tag : getTags()) {
      files_group_by_tags_map.put(tag, new ArrayList<>());
    }
    files_group_by_sources_map = new TreeMap<>();
  }

  private static void analyticFile(File fileEntry) throws Exception {
    int number_url = 0;

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
            number_url++;
            source = getSource("http://", word);
          } else if (word.contains("https://")) {
            number_url++;
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

    // internal statistic
    if (statisticURL.containsKey(number_url)) {
      statisticURL.get(number_url).add(new Item(fileEntry.getPath()));
    } else {
      ArrayList<Item> url_list = new ArrayList<>();
      url_list.add(new Item(fileEntry.getPath()));
      statisticURL.put(number_url, url_list);
    }
  }

  private static void internalAnalytics() throws Exception {
    FileWriter writer = new FileWriter("internal_analytics.md");

    // 1/statistic by URL count
    String header = "# Statistic by URL count";
    writer.write(header + System.lineSeparator());
    for (Map.Entry<Integer, ArrayList<Item>> entry : statisticURL.entrySet()) {
      ArrayList<Item> urls = entry.getValue();
      writer.write(
          "## URL count " + entry.getKey() + " (" + urls.size() + ") :" + System.lineSeparator());
      Collections.sort(urls);
      for (Item url : urls) {
        writer.write("- " + url.toOriginalString() + System.lineSeparator());
      }
    }

    writer.close();
  }

  private static void analytics() throws Exception {
    for (String folder_path : getFolderPaths()) {
      final File folder = new File(folder_path);
      for (final File fileEntry : folder.listFiles()) {
        analyticFile(fileEntry);
      }
    }

    internalAnalytics();
  }

  private static void flushToReadMe() throws Exception {
    FileWriter writer = new FileWriter(getFilePath());
    // header
    for (String line : getHeader()) {
      writer.write(line + System.lineSeparator());
    }
    // statistic
    for (String line : getStatistic()) {
      writer.write(line + System.lineSeparator());
    }
    // body
    for (String line : getBody()) {
      writer.write(line + System.lineSeparator());
    }
    writer.close();
  }

  private static void generateReadMe() throws Exception {
    init();
    analytics();
    flushToReadMe();
  }

  public static void main(String[] args) throws Exception {
    generateReadMe();
  }
}
