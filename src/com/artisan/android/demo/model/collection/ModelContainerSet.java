package com.artisan.android.demo.model.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.artisan.android.demo.model.NewsItem;
import com.artisan.android.demo.model.util.JsonRequester;
import com.artisan.android.demo.model.util.JsonSerializer;

public class ModelContainerSet<ModelType> {

	private LinkedHashSet<ModelType> modelSet;
	private final JsonSerializer<ModelType> jsonSerializer;

	public ModelContainerSet(Class<ModelType> modelClass, Context context, String filename) {
		jsonSerializer = new JsonSerializer<ModelType>(context, filename, modelClass);
		modelSet = jsonSerializer.loadFromJson();
	}
	
	public List<ModelType> getItems() {
		return new ArrayList<ModelType>(modelSet);
	}

	public boolean removeItem(ModelType item) {
		boolean wasRemoved = modelSet.remove(item);
		if (wasRemoved) {
			jsonSerializer.saveAsJson(modelSet);
		}
		return wasRemoved;
	}

	public void addItem(ModelType item) {
		boolean notAlreadyInSet = modelSet.add(item);
		if (notAlreadyInSet) {
			jsonSerializer.saveAsJson(modelSet);
		}
	}
}
