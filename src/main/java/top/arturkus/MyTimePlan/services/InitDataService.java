package top.arturkus.MyTimePlan.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import top.arturkus.MyTimePlan.entities.Star;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitDataService {

    private final StarService starService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        List<Star> starsToInit = List.of(
                new Star("Alpha", 4),
                new Star("Beta", 10),
                new Star("Gamma", 7),
                new Star("Delta", 3),
                new Star("Epsilon", 2));
        starService.create(starsToInit);
    }
}