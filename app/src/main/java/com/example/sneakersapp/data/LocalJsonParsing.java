package com.example.sneakersapp.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sneakersapp.model.Sneakers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LocalJsonParsing {

    private static final String TAG = LocalJsonParsing.class.getName();
    public static List<Sneakers> listOfSneakers = new ArrayList<>();

    @NonNull
    public static List<Sneakers> getListOfSneakers(Context context) {

        try {
            InputStream inputStream = context.getAssets().open("sneakers_json_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json;
            int max;

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            max = jsonArray.length();

            // get json data and put into SneakersViewData
            for (int i = 0; i < max; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String brandName = jsonObject.getString("brand_name");
                String productName = jsonObject.getString("name");
                String productSlugs = jsonObject.getString("slug");
                String originalPictureUrl = jsonObject.getString("original_picture_url");
                String gridPictureUrl = jsonObject.getString("grid_picture_url");
                int id = jsonObject.getInt("id");
                int price = jsonObject.getInt("retail_price_cents");

                ArrayList<String> imagesUrlList = new ArrayList<>();

                imagesUrlList.add(originalPictureUrl);
                imagesUrlList.add(originalPictureUrl);
                imagesUrlList.add(originalPictureUrl);

                Sneakers sneakers =
                        new Sneakers(brandName, productName, productSlugs, id, price, imagesUrlList, gridPictureUrl);
                listOfSneakers.add(sneakers);
            }

        } catch (Exception e) {
            Log.e(TAG, "Load Json Error " + e);
        }
        return listOfSneakers;
    }

    @NonNull
    public static List<Sneakers> getListOfSearchedString(String input) {
        List<Sneakers> searchedSneakers = new ArrayList<>();

        for (int i = 0; i < listOfSneakers.size(); i++) {
            Sneakers sneakers = listOfSneakers.get(i);

            String brandName = sneakers.getBrandName();
            if (isSubstring(brandName.toLowerCase(), input.toLowerCase()) || isSubstring(input.toLowerCase(), brandName.toLowerCase())) {
                searchedSneakers.add(sneakers);
            }
        }
        return searchedSneakers;
    }

    private static boolean isSubstring(String s1, @NonNull String s2) {

        return s2.contains(s1);
    }
}
