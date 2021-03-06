package com.dynnoil.ui.components;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;

import java.util.Date;

/**
 * Created by krukov on 16.10.2015.
 */

@Import(library = "initializers.js")
public class JSONDemo {

    @Property
    @Persist
    private int number;

    @Inject
    private Request request;

    Object onIncrement() {
        this.number++;

        JSONObject parameters = new JSONObject(
                "id", "status",
                "newContent", "Updated on " + new Date()
        );

        JSONObject replaceContent = new JSONObject();
        replaceContent.put(
                "replaceContent",
                new JSONArray(parameters));

        final JSONObject json = new JSONObject();

        json.put(
                "content",
                String.format("<p>Current value: %s</p>", number));

        json.put("inits", new JSONArray(replaceContent));

        return request.isXHR()
                ? json
                : this;
    }
}