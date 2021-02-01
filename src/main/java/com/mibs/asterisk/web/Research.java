package com.mibs.asterisk.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Research implements Comparable<Research> {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

  private LocalDateTime dateTime;
  private String text;

  public Research(String text) {

    String dataTimeStr = text.split(" ")[0] + " " + text.split(" ")[1];
    
    try {
      this.dateTime = LocalDateTime.parse(dataTimeStr, formatter);
    }catch (java.time.format.DateTimeParseException e) {
      
    }
    this.text = text;

  }

  @Override
  public int compareTo(Research o) {
    // TODO Auto-generated method stub
    return dateTime.compareTo(o.getDateTime());
  }

}
