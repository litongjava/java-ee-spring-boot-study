package com.incesoft.ai.commonsapi.service.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.junit.Test;

public class MapSerializable {

  /**
   * 序列化Map
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  public void mapSerializable() throws FileNotFoundException, IOException {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("filter1", "litong");
    hashMap.put("filter2", "litong");
    ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream("map.txt"));
    objectStream.writeObject(hashMap);
  }
}
