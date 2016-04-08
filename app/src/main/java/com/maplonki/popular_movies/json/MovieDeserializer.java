package com.maplonki.popular_movies.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.maplonki.popular_movies.models.MovieModel;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by hugo on 4/5/16.
 * <p/>
 * This class handles the base JSON response from the api, and
 * strips out the attributes and retrieves only the list of movies.
 */
public class MovieDeserializer implements JsonDeserializer<List<MovieModel>> {

    @Override
    public List<MovieModel> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement results = json.getAsJsonObject().get("results");
        Gson customGson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        return customGson.fromJson(results, new TypeToken<List<MovieModel>>() {
        }.getType());
    }
}
