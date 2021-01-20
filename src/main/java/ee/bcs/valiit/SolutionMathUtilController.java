package ee.bcs.valiit;

import ee.bcs.valiit.tasks.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class SolutionMathUtilController {

    @GetMapping("min/{a}/{b}")
    public int min(@PathVariable("a") int aVariable, @PathVariable("b") int bVariable){
        return Lesson1MathUtil.min(aVariable, bVariable);
    }

    @GetMapping("min3")
    public int min3(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable, @RequestParam("c") int cVariable){
        return Lesson1MathUtil.min(aVariable, bVariable, cVariable);
    }


    @GetMapping("max")
    public int max(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable){
        return Lesson1MathUtil.max(aVariable, bVariable);
    }

    @GetMapping("abs")
    public int abs(@RequestParam("n") int abs){
        return Lesson1MathUtil.abs(abs);
    }

    @GetMapping("isEven")
    public boolean isEven(@RequestParam("n") int even){
        return Lesson1MathUtil.isEven(even);
    }

    @GetMapping("sum")
    public int sum(@RequestParam("a") int[] aVariable){
        return Lesson3.sum(aVariable);
    }

    @GetMapping("factorial")
    public int factorial(@RequestParam("n") int number){
        return Lesson3.factorial(number);
    }

    @GetMapping("sort")
    public int[] sort(@RequestParam("a") int[] array){
        return Lesson3.sort(array);
    }

    @GetMapping("reverseString")
    public String reverseString(@RequestParam("a") String text){
        return Lesson3.reverseString(text);
    }

    @GetMapping("isPrime/{a}")
    public boolean isPrime(@PathVariable("a") int even){
        return Lesson3.isPrime(even);
    }

    @GetMapping("deleteNth/{a}")
    public int[] deleteNth(@PathVariable("a") int[] array, @RequestParam("b") int number){
        return Lesson3Hard.deleteNth(array, number);
    }

    @GetMapping("morse/{a}")
    public String morse(@PathVariable("a") String text) {
        return Lesson3Hard.morseCode(text);

    }

    @GetMapping("e1/{a}")
    public int[] e1(@PathVariable("a") int[] text) {
        return Lesson2.exercise1Web(text);

    }

    Random random = new Random();
    int i = random.nextInt(100);
    int count = 1;
    @GetMapping("game/{a}")
    public String game(@PathVariable("a") int number) {

        if (i > number) {
            count++;
            return "Number on suurem";
        } else if (i < number) {
            count++;
            return "Number on väiksem";
        } else {
            return "Õige number, arvasid ära " + count + ". korraga!";
        }

    }




//    @PostMapping(value = "korrutus")
//    @ResponseStatus(value = HttpStatus.OK)
//    public void korrutus(@RequestParam("x") int first, @RequestParam("y") int second){
//    }

//    @GetMapping("paaris")
//    public void paaris(@RequestParam("n") int paarisCount){
//       Lesson2.exercise2(paarisCount);
//    }


}
