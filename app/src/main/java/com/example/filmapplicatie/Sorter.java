package com.example.filmapplicatie;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorter {

    ArrayList<Movie> mObjects = new ArrayList<>();

    public static class SortAlphabetical implements Comparator<Movie> {
        public int compare(Movie o1, Movie o2)
        {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    public static class SortMostPopular implements Comparator<Movie>{
        public int compare(Movie o1, Movie o2)
        {
            return o1.getVote_average().compareTo(o2.getVote_average());
        }
    }

    public static class SortLeastPopular implements Comparator<Movie>{
        public int compare(Movie o1, Movie o2)
        {
            return o1.getVote_average().compareTo(o2.getVote_average());
        }
    }
}
