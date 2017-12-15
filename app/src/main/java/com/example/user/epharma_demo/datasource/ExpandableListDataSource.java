package com.example.user.epharma_demo.datasource;

import android.content.Context;

import com.example.user.epharma_demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ExpandableListDataSource {

    public static Map<String, List<String>> getData(Context context) {


        Map<String, List<String>> expandableListData = new TreeMap<>();

        List<String> filmGenres = Arrays.asList(context.getResources().getStringArray(R.array.film_genre));

        List<String> babyCare = Arrays.asList(context.getResources().getStringArray(R.array.baby_care));
        List<String> beautyCare = Arrays.asList(context.getResources().getStringArray(R.array.beauty_care));

        List<String> personalCare = Arrays.asList(context.getResources().getStringArray(R.array.personal_care));

        List<String> singleValue = new ArrayList<>();
        singleValue.add("1");
        expandableListData.put(filmGenres.get(0), babyCare);
        expandableListData.put(filmGenres.get(1), beautyCare);
        expandableListData.put(filmGenres.get(2), singleValue);
        expandableListData.put(filmGenres.get(3), singleValue);
        expandableListData.put(filmGenres.get(4), personalCare);
        expandableListData.put(filmGenres.get(5), singleValue);
        expandableListData.put(filmGenres.get(6), singleValue);
        expandableListData.put(filmGenres.get(7), singleValue);
        expandableListData.put(filmGenres.get(8), singleValue);
        expandableListData.put(filmGenres.get(9), singleValue);
        expandableListData.put(filmGenres.get(10), singleValue);
        expandableListData.put(filmGenres.get(11), singleValue);
        expandableListData.put(filmGenres.get(12), singleValue);
        expandableListData.put(filmGenres.get(13), singleValue);
        expandableListData.put(filmGenres.get(14), singleValue);
        expandableListData.put(filmGenres.get(15), singleValue);
        expandableListData.put(filmGenres.get(16), singleValue);


        return expandableListData;
    }
}
