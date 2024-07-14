package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.PriceLevel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class handles conversion from JSON to {@link PriceLevel}.
 *
 * <p>Please see <a
 * href="https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/TypeAdapter.html">GSON
 * Type Adapter</a> for more detail.
 */
public class PriceLevelAdapter extends TypeAdapter<PriceLevel> {

  private static final Map<Integer, PriceLevel> PRICE_LEVEL_MAP = new HashMap<>();
  static {
    PRICE_LEVEL_MAP.put(0, PriceLevel.FREE);
    PRICE_LEVEL_MAP.put(1, PriceLevel.INEXPENSIVE);
    PRICE_LEVEL_MAP.put(2, PriceLevel.MODERATE);
    PRICE_LEVEL_MAP.put(3, PriceLevel.EXPENSIVE);
    PRICE_LEVEL_MAP.put(4, PriceLevel.VERY_EXPENSIVE);
  }

  @Override
  public PriceLevel read(JsonReader reader) throws IOException {
    if (reader.peek() == JsonToken.NULL) {
      reader.nextNull();
      return null;
    }

    if (reader.peek() == JsonToken.NUMBER) {
      int priceLevel = reader.nextInt();
      return PRICE_LEVEL_MAP.getOrDefault(priceLevel, PriceLevel.UNKNOWN);
    }

    return PriceLevel.UNKNOWN;
  }

  /** This method is not implemented. */
  @Override
  public void write(JsonWriter writer, PriceLevel value) throws IOException {
    throw new UnsupportedOperationException("Unimplemented method");
  }
}
