package com.philip;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.List;
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


        tracks = countFeature(album -> album.getTracks().count());
        System.out.println(tracks);

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

        musicians = countFeature(value -> value.getMusicians().distinct().count());
        System.out.println(musicians);

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

    public long countFeature(ToLongFunction<Album> function){
        return  albums.stream().mapToLong(function).sum();
    }
}
