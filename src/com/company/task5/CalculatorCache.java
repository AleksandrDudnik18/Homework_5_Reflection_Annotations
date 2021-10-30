package com.company.task5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


public class CalculatorCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    private static final File cacheFile = new File("./src/com/company/task5/properties");

    Properties property = new Properties();

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return (size() > this.capacity);
    }

    public CalculatorCache(int capacity) {

        super(capacity, 1.0f, true);
        this.capacity = capacity;

        System.out.println(cacheFile);

        if (!cacheFile.exists()) {
            try {
                boolean newFile = cacheFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            property.load(new FileInputStream(cacheFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public V find(K key) {
        if (containsKey(key))
            return get(key);
        if (property.containsKey(key))
            return (V) property.getProperty(key.toString());
        return null;
    }

    public void set(K key, V value) {
        property.setProperty(key.toString(), value.toString());
        try {
            property.store(new FileOutputStream(cacheFile), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        put(key, value);
    }
}
