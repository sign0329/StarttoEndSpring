package com.ll.testspringrestapihttp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/coffees")
public class RestAPIDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestAPIDemoController(){
        coffees.addAll(List.of(
                new Coffee("Cafe Cereza"),
                new Coffee("Cafe Grander"),
                new Coffee("Cafe larno"),
                new Coffee("Cafe Mega")
        ));
    }

//    @RequestMapping(value = "/coffees", method = RequestMethod.GET) // getCoffee /coffee에만 대답할수 있게 제한함.
//    Iterator<Coffee> getCoffee(){
//        return coffees;
//    }

    @GetMapping
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for (Coffee c : coffees) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id,
    @RequestBody Coffee coffee){

        {
            int coffeeIndex = -1;

            for (Coffee c : coffees) {
                if (c.getId().equals(id)) {
                    coffeeIndex = coffees.indexOf(c);

                    coffees.set(coffeeIndex, coffee);
                }
            }
            return (coffeeIndex == -1) ?
                    new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                    new ResponseEntity<>(coffee, HttpStatus.OK);
        }
    }

//    @PutMapping("/{id}")
//    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
//        // 클라이언트가 제공한 ID와 URL의 ID가 다르면 400 Bad Request
//        if (!id.equals(coffee.getId())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
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
        coffees.removeIf(c -> c.getId().equals(id));
    }
}


