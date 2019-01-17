package cn.yout.practice.pattern.factory.simpleFactory;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestTee {
    private final Map<String, Point> locations;
    private final Map<String, Point> unmodifiedMap;

    TestTee(Map<String, Point> locations){
        this.locations = new ConcurrentHashMap<String, Point>(locations);
        this.unmodifiedMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiedMap;
    }

    public void setLocations(String id , int x, int y){
        locations.get(id).set(x,y);
    }
}
