package top.arturkus.MyTimePlan.services.dao;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import top.arturkus.MyTimePlan.entities.Star;
import top.arturkus.MyTimePlan.exceptions.NotFoundException;
import top.arturkus.MyTimePlan.helpers.StarHelper;
import top.arturkus.MyTimePlan.repositories.StarRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class StarDaoImpl implements StarDao {

    private StarRepository starRepository;

    @Override
    public Star create(StarHelper star) {
        return starRepository.save(new Star(star));
    }

    @Override
    public void create(List<Star> star) {
        starRepository.saveAll(star);
    }

    @Override
    public Star get(Long id) throws NotFoundException {
        return starRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Star.class, id));
    }

    @Override
    public Star update(Long id, StarHelper helper) throws NotFoundException {
        Star star = get(id);
        star.fillFields(helper);
        return starRepository.save(star);
    }

    @Override
    public void delete(Long id) {
        starRepository.deleteById(id);
    }

    //TODO Add pageable
    @Override
    public List<Star> findAll() {
        return starRepository.findAll();
    }
}
