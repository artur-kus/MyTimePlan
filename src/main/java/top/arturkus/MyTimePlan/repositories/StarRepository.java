package top.arturkus.MyTimePlan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.arturkus.MyTimePlan.entities.Star;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {
}