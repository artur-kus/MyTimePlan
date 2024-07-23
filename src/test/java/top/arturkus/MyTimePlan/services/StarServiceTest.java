package top.arturkus.MyTimePlan.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import top.arturkus.MyTimePlan.entities.Star;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class StarServiceTest {

    private final StarService starService = new StarService();

    @Test
    public void findClosestStarsTest() {
        //GIVEN
        List<Star> stars = Arrays.asList(
                new Star("Alpha", 4),
                new Star("Beta", 10),
                new Star("Gamma", 7),
                new Star("Delta", 3),
                new Star("Epsilon", 2)
        );
        //WHEN
        List<Star> result = starService.findClosestStars(stars, 3);
        //THEN
        assertEquals(3, result.size());
        assertEquals("Epsilon", result.get(0).getName());
        assertEquals("Delta", result.get(1).getName());
        assertEquals("Alpha", result.get(2).getName());
    }

    @Test
    public void getNumberOfStarsByDistancesTest() {
        //GIVEN
        List<Star> stars = Arrays.asList(
                new Star("Alpha", 4),
                new Star("Beta", 10),
                new Star("Gamma", 4),
                new Star("Delta", 10),
                new Star("Epsilon", 2)
        );
        //WHEN
        Map<Long, Integer> result = starService.getNumberOfStarsByDistances(stars);
        //THEN
        assertEquals(3, result.size());
        assertEquals(2, result.get(4L));
        assertEquals(2, result.get(10L));
        assertEquals(1, result.get(2L));
    }

    @Test
    public void getUniqueStarsTest() {
        //GIVEN
        Collection<Star> stars = Arrays.asList(
                new Star("Alpha", 4L),
                new Star("Beta", 10L),
                new Star("Alpha", 4L),
                new Star("Delta", 3L),
                new Star("Epsilon", 2L)
        );
        //WHEN
        Collection<Star> result = starService.getUniqueStars(stars);
        //THEN
        assertEquals(4, result.size());
        Set<String> uniqueNames = new HashSet<>(Arrays.asList("Alpha", "Beta", "Delta", "Epsilon"));
        result.forEach(star -> assertTrue(uniqueNames.contains(star.getName())));
    }
}