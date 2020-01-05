package com.example.threadconcurrency.listquestion;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
@Slf4j
public class listDemo {

    public static void main(String[] args) {
        List<String> colors = Stream.of("blue", "red1", "yellow").collect(toList());

        List<String> colorList = Stream.of("blue", "red", "yellow","White").collect(toList());


        List<List<String>> list2 = new ArrayList<List<String>>() {{
            add(colors);
            add(colorList);
        }};
        // List<List<String>>转换list 拉平
        List<String> collectList = list2.stream().flatMap(strings -> strings.stream().map(i -> i + 1)).collect(toList());
        log.info("collectList before{}"+collectList.size());
        for (int i = 0, len = collectList.size(); i < len; i++) {
            if (collectList.get(i).contains("red")) {
                String remove = collectList.remove(i);
                log.info("collectList remove{}"+remove);
            }
        }
        log.info("collectList after{}"+collectList.size());
    }
}
