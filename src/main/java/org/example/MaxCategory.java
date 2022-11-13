package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class MaxCategory {

    public static List<Product> productList = new ArrayList<>();
    private Map<String, Integer> maxCategory = new HashMap<>();

    public void addProduct(BufferedReader in) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String inRead = in.readLine();
        Product product = gson.fromJson(inRead, Product.class);
        productList.add(product);// добавляем в массив корзины сообщения с клиента
    }

    public static Map loadFromTSV(File file) throws IOException {
        List<String[]> categories = new ArrayList<>();
        Map<String, Integer> postServer = new HashMap<>();
        categories = Files.lines(file.toPath())
                .map(line -> line.split("\t"))
                .collect(Collectors.toList());
        Map<String, String> resultsMap = new HashMap<String, String>();
        for (String[] parts : categories) {
            resultsMap.put((String) parts[0], (String) parts[1]);
        }

        for (Product index : productList) {
            if (!resultsMap.containsKey(index.title)) {
                if (!postServer.containsKey("другое")) {
                    postServer.put("другое", (int) index.sum);
                } else {
                    int sum = postServer.get("другое");
                    sum += index.sum;
                    postServer.put("другое", sum);
                }
            }
            if (resultsMap.containsKey(index.title)) {
                if (postServer.isEmpty()) {
                    postServer.put(resultsMap.get(index.title), (int) index.sum);
                } else {
                    int sum = postServer.containsKey(resultsMap.get(index.title)) ? postServer.get(resultsMap.get(index.title)) : 0;
                    sum += index.sum;
                    postServer.put(resultsMap.get(index.title), sum);
                }
            }
        }
        String maxFinCategory = Collections.max(postServer.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue)).getKey();
        int maxFinSum = postServer.get(maxFinCategory);
        JSONObject jsonMaxSum = new JSONObject();
        jsonMaxSum.put("categories", maxFinCategory);
        jsonMaxSum.put("sum", maxFinSum);
        return jsonMaxSum;
    }


}
