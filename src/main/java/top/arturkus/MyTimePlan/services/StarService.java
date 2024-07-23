package top.arturkus.MyTimePlan.services;

import org.springframework.stereotype.Service;
import top.arturkus.MyTimePlan.entities.Star;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StarService {

    public List<Star> findClosestStars(List<Star> stars, int size) {
        return stars == null || size <= 0
                ? Collections.emptyList()
                : stars.stream()
                .sorted(Comparator.comparingLong(Star::getDistance))
                .limit(size)
                .collect(Collectors.toList());
    }

    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        return stars == null
                ? Collections.emptyMap()
                : stars.stream()
                .collect(Collectors.groupingBy(
                        Star::getDistance, TreeMap::new, Collectors.summingInt(star -> 1)));
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        if (stars == null) return Collections.emptyList();
        Map<String, Star> uniqueStarsMap = new HashMap<>();
        for (Star star : stars) {
            String name = star.getName();
            if (uniqueStarsMap.containsKey(name)) {
                if (uniqueStarsMap.get(name).getDistance() != star.getDistance()) {
                    throw new IllegalArgumentException("Stars with the same name have different distances: " + name);
                }
            } else {
                uniqueStarsMap.put(name, star);
            }
        }
        return uniqueStarsMap.values();
    }
}