package top.arturkus.MyTimePlan.services.dao;

import top.arturkus.MyTimePlan.entities.Star;
import top.arturkus.MyTimePlan.exceptions.NotFoundException;
import top.arturkus.MyTimePlan.helpers.StarHelper;

import java.util.List;

public interface StarDao {

    Star create(StarHelper star);

    void create(List<Star> star);

    Star get(Long id) throws NotFoundException;

    Star update(Long id, StarHelper helper) throws NotFoundException;

    void delete(Long id);

    List<Star> findAll();
}