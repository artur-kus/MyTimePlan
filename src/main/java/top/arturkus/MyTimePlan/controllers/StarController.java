package top.arturkus.MyTimePlan.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.arturkus.MyTimePlan.exceptions.NotFoundException;
import top.arturkus.MyTimePlan.helpers.StarHelper;
import top.arturkus.MyTimePlan.services.StarService;

@RestController
@RequestMapping("/stars")
@RequiredArgsConstructor
public class StarController {

    private final StarService starService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StarHelper star) {
        return new ResponseEntity<>(starService.create(star), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(starService.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody StarHelper helper) throws NotFoundException {
        return new ResponseEntity<>(starService.update(id, helper), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        starService.delete(id);
        return new ResponseEntity<>(starService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(starService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-closest-stars")
    public ResponseEntity<?> findClosestStars(@RequestParam int size) throws NotFoundException {
        return new ResponseEntity<>(starService.findClosestStars(size), HttpStatus.OK);
    }

    @GetMapping("/get-number-of-stars-by-distances")
    public ResponseEntity<?> getNumberOfStarsByDistances() throws NotFoundException {
        return new ResponseEntity<>(starService.getNumberOfStarsByDistances(), HttpStatus.OK);
    }

    @GetMapping("/get-unique-stars")
    public ResponseEntity<?> getUniqueStarsFromDB() throws NotFoundException {
        return new ResponseEntity<>(starService.getUniqueStars(), HttpStatus.OK);
    }
}