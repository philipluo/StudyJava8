package com.philip;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

import static java.util.Arrays.asList;

public class PhilipStudyChapter7 {

    private final static List<Album> albums =
            asList(SampleData.sampleShortAlbum,
                    SampleData.aLoveSupreme,
                    SampleData.manyTrackAlbum);

    @Test
    public void countTracks(){
        long tracks = albums.stream()
                .flatMap(Album::getTracks)
                .count();
        System.out.println(tracks);

        //another implementation
        tracks = albums.stream()
                .mapToLong(value -> value.getTracks().count())
                .sum();
        System.out.println(tracks);

        tracks = albums.stream()
                .map(album -> album.getTracks().count())
                .reduce(0l, (a, b)-> a + b);
        System.out.println(tracks);


        tracks = countFeature(albums, album -> album.getTracks().count());
        System.out.println(tracks);


        BiFunction<Integer, Integer, Integer> biFunction
                = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }
        };
    }


    @Test
    public void countMusicans(){
        long musicians = albums.stream()
                .flatMap(Album::getMusicians)
                .distinct()
                .count();
        System.out.println(musicians);

        albums.stream()
                .flatMap(Album::getMusicians)
                .distinct()
                .forEach(System.out::println);

        musicians = countFeature(albums, value -> value.getMusicians().distinct().count());
        System.out.println(musicians);
    }

    private long countFeature(List<Album> albums, ToLongFunction<Album> function){
        return  albums.stream().mapToLong(function).sum();
    }

    @Test
    public void sumTraksLength(){
        /*albums.forEach(a -> {
            a.getTracks().forEach(track -> {
                System.out.println(track.getLength());
            });
        });*/

        //Use peek() to replace above forEach method to debug and output immediate value
        int totalLength = albums.stream()
                .flatMap(Album::getTracks)
                .peek(track -> {System.out.println(track.getLength());})
                    .mapToInt(Track::getLength)
                        .sum();
        System.out.println(totalLength);

    }

    @Test
    public void studyCustomBiFunction(){
        BiFunction<Integer, String, Map<String, Integer>> biFunction2
                = (a,b) -> {
            Map<String, Integer> map = new HashMap<>();
            map.put(b, a);
            return map;
        };

        Map<String, Integer> applyAsInt = biFunction2.apply(1, "A");
        System.out.println(applyAsInt);


        Map<String, Integer> applyAsInt2 =
                applyAsInt(1, "A", (a, b)->{
                    Map<String, Integer> map = new HashMap<>();
                    map.put(b.toLowerCase(), a + 10);
                    return map;
                });
        System.out.println(applyAsInt2);

        Order salesOrder = createOrderFunction(1, "94538",
                (a, b)->{
                    return new Order(a, 1, b);
                });
        System.out.println(salesOrder);

        Order backOrder = createOrderFunction(1, "94560",
                (a, b)->{
                    return new Order(a, 8, b);
                });
        System.out.println(backOrder);
    }

    private Map<String, Integer> applyAsInt(Integer i, String s, BiFunction<Integer, String, Map<String, Integer>> b){
        return b.apply(i, s);
    }


    private Order createOrderFunction(Integer i, String s, BiFunction<Integer, String, Order> b){
        return b.apply(i, s);
    }
}
