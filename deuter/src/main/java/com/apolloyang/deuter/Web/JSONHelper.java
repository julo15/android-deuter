package com.apolloyang.deuter.Web;

import com.google.common.io.CharStreams;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by julianlo on 8/29/15.
 */
public class JSONHelper {
    public static JSONObject getJSONObjectFromInputStream(InputStream inputStream) throws java.io.IOException, org.json.JSONException {
        InputStream in = new BufferedInputStream(inputStream);
        InputStreamReader reader = new InputStreamReader(in);
        final String jsonString = CharStreams.toString(reader);
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }
}

