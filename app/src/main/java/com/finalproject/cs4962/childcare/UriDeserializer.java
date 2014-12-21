package com.finalproject.cs4962.childcare;

import android.net.Uri;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by akdPro on 12/20/14.
 */
public class UriDeserializer implements JsonDeserializer<Uri> {

    public UriDeserializer() {

    }


    @Override
    public Uri deserialize(final JsonElement src, final Type srcType,
                           final JsonDeserializationContext context) throws JsonParseException {
        return Uri.parse(src.getAsString());
    }
}
