package top.arturkus.MyTimePlan.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.arturkus.MyTimePlan.entities.Star;
import top.arturkus.MyTimePlan.exceptions.NotFoundException;
import top.arturkus.MyTimePlan.helpers.DefaultResponse;
import top.arturkus.MyTimePlan.helpers.StarHelper;
import top.arturkus.MyTimePlan.services.dao.StarDao;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StarService {

    private final StarDao starDao;

    protected List<Star> findClosestStars(List<Star> stars, int size) throws NotFoundException {
        if (stars == null) throw new NotFoundException(Star.class, "empty.list");
        return stars.stream()
                .sorted(Comparator.comparingLong(Star::getDistance))
                .limit(size)
                .collect(Collectors.toList());
    }

    protected Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) throws NotFoundException {
        if (stars == null) throw new NotFoundException(Star.class, "empty.list");
        return stars.stream()
                .collect(Collectors.groupingBy(
                        Star::getDistance, TreeMap::new, Collectors.summingInt(star -> 1)));
    }

    protected Collection<Star> getUniqueStars(Collection<Star> stars) throws NotFoundException {
        if (stars == null) throw new NotFoundException(Star.class, "empty.list");
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

    public List<Star> findAll() {
        return starDao.findAll();
    }

    public Star create(StarHelper star) {
        return starDao.create(star);
    }

    public void create(List<Star> stars) {
        starDao.create(stars);
    }

    public Star get(Long id) throws NotFoundException {
        return starDao.get(id);
    }

    public Star update(Long id, StarHelper helper) throws NotFoundException {
        return starDao.update(id, helper);
    }

    public DefaultResponse delete(Long id) {
        starDao.delete(id);
        return new DefaultResponse(true, "success", new Date());
    }

    public List<Star> findClosestStars(int size) throws NotFoundException {
        return findClosestStars(findAll(), size);
    }

    public Map<Long, Integer> getNumberOfStarsByDistances() throws NotFoundException {
        return getNumberOfStarsByDistances(findAll());
    }

    public Collection<Star> getUniqueStars() throws NotFoundException {
        return getUniqueStars(findAll());
    }
}