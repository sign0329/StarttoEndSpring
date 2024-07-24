package com.ll.testspringrestapihttp;

import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/coffees")
public class RestAPIDemoController {
    private final CoffeeRepository coffeeRepository;

    public RestAPIDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Component
    class DataLoader {
        private final CoffeeRepository coffeeRepository;
        public DataLoader(CoffeeRepository coffeeRepository) {
            this.coffeeRepository = coffeeRepository;
        }

        @PostConstruct
        private void loadData() {
            coffeeRepository.saveAll(List.of(
                    new Coffee("cage one"),
                    new Coffee("cage two"),
                    new Coffee("cage three"),
                    new Coffee("cage four")
            ));
        }
    }
//    @RequestMapping(value = "/coffees", method = RequestMethod.GET) // getCoffee /coffee에만 대답할수 있게 제한함.
//    Iterator<Coffee> getCoffee(){
//        return coffees;
//    }

    @GetMapping
    Iterable<Coffee> getCoffees(){
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee){
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id,
                                     @RequestBody Coffee coffee){
        return (coffeeRepository.existsById(id))
                ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK)
                : new ResponseEntity<>((coffeeRepository.save(coffee)),
                HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
//        // 클라이언트가 제공한 ID와 URL의 ID가 다르면 400 Bad Request
//        if (!id.equals(coffee.getId())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }ㅇ
//
//        // 기존 리스트에서 객체를 찾거나 새로 추가
//        for (int i = 0; i < coffees.size(); i++) {
//            Coffee c = coffees.get(i);
//            if (c.getId().equals(id)) {
//                coffee.setId(id); // 기존 ID 유지
//                coffees.set(i, coffee);
//                return new ResponseEntity<>(coffee, HttpStatus.OK);
//            }
//        }
//
//        // 객체가 리스트에 없으면 새로 추가
//        coffee.setId(id); // 클라이언트가 제공한 ID로 설정
//        coffees.add(coffee);
//        return new ResponseEntity<>(coffee, HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id){
        coffeeRepository.deleteById(id);
    }
}

