package cn.yout.practice.pattern.factory.simpleFactory;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class SimpleFactoryTestTee extends TestCase {
    @Test
    public void testMain() throws Exception {
        Map<String, Point> points = new HashMap<>();
        Point p = new Point(1,2);
        points.put("dd",p);
        TestTee locations = new TestTee(points);
        System.out.println(locations.getLocations().toString());
        locations.setLocations("dd",2,3);
        System.out.println(locations.getLocations().toString());

        Map<String, String> d = new HashMap<>();
        d.put("d","d");
        Map<String,String> t = Collections.unmodifiableMap(d);
        d.put("d","q");
        System.out.println(d);
    }



}