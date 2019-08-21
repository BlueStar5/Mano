package com.example.mano;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Converters {
  @TypeConverter
  public static LocalDateTime fromTimestamp(Long value) {
    return value == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(value),
            ZoneId.systemDefault());
  }

  @TypeConverter
  public static Long dateToTimestamp(LocalDateTime dateTime) {
    return dateTime == null ? null :
            dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }
}
