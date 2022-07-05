package com.meltwater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

/**
 * Unit test for simple App.
 */
class AppTest {

    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        assertEquals(1, 1);
    }

    @Test
    void testCreateMaskableKeywords() {
        // empty case
        String test1 = "";
        Set<String> res1 = new HashSet<>();
        assertEquals(res1, App.createMaskableKeywords(test1));

        // valid case
        String test2 = "   ,,  Hello world “Boston Red Sox”, ‘Pepperoni Pizza’, ‘Cheese Pizza’, beer, amigo enemy,  , , ,";
        Set<String> res2 = new HashSet<>();
        res2.add("Boston Red Sox");
        res2.add("world");
        res2.add("Hello");
        res2.add("enemy");
        res2.add("Pepperoni Pizza");
        res2.add("beer");
        res2.add("Cheese Pizza");
        res2.add("amigo");
        res2.add("enemy");
        assertEquals(res2, App.createMaskableKeywords(test2));

        // null case
        String test3 = null;
        Set<String> res3 = new HashSet<>();
        assertEquals(res3, App.createMaskableKeywords(test3));
    }

    @Test
    void testMaskText() {
        Set<String> testSet = new HashSet<>();
        testSet.add("Boston Red Sox");
        testSet.add("world");
        testSet.add("Hello");
        testSet.add("enemy");
        testSet.add("Pepperoni Pizza");
        testSet.add("beer");
        testSet.add("Cheese Pizza");
        testSet.add("amigo");
        testSet.add("enemy");

        // empty case
        String test1 = "";
        String res1 = "";
        assertEquals(res1, App.maskText(testSet, test1));

        // valid case
        String test2 = "I went to see the Boston Red Sox while drinking beer and eating a Pepperoni Pizza.";
        String res2 = "I went to see the XXXX while drinking XXXX and eating a XXXX.";
        assertEquals(res2, App.maskText(testSet, test2));

        // null case 1
        String test3 = null;
        String res3 = "";
        assertEquals(res3, App.maskText(testSet, test3));

        // null case 1
        String test4 = "I went to see the Boston Red Sox while drinking beer and eating a Pepperoni Pizza.";
        String res4 = "I went to see the Boston Red Sox while drinking beer and eating a Pepperoni Pizza.";
        assertEquals(res4, App.maskText(null, test4));
    }
}
