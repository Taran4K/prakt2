package com.example.prakt2.controller;

import com.example.prakt2.models.Cars;
import com.example.prakt2.models.Students;
import com.example.prakt2.repos.CarsRepository;
import com.example.prakt2.repos.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarsController {
    @Autowired
    private CarsRepository carsRepository;

    @GetMapping("/cars")
    public String CarsMain(Model model)
    {
        Iterable<Cars> cars= carsRepository.findAll();
        model.addAttribute("cars", cars);
        return "cars-main";
    }
    @GetMapping("/cars/add")
    public String blogAdd(Model model) {return "cars-add";}


    @PostMapping("/cars/add")
    public String blogPostAdd(@RequestParam String mark,
                              @RequestParam String vladelec,
                              @RequestParam Boolean polom,
                              @RequestParam int probeg,
                              @RequestParam int age,
                              Model model) {
        Cars cars = new Cars(mark, vladelec, polom, age, probeg);
        carsRepository.save(cars);
        return "redirect:/";
    }
    @GetMapping("/cars/filter")
    public String blogfilter(Model model) {return "cars-filter";}

    @PostMapping("/cars/filter/resulttoch")
    public String blogResulttoch(@RequestParam String mark, Model model)
    {
        List<Cars> result = carsRepository.findByMark(mark);
        model.addAttribute("result", result);
        return "cars-filter";
    }
    @PostMapping("/cars/filter/resultnetochn")
    public String blogResultnet(@RequestParam String mark, Model model)
    {
        List<Cars> result = carsRepository.findByMarkContains(mark);
        model.addAttribute("result", result);
        return "cars-filter";
    }
    @GetMapping("/cars/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Cars> post = carsRepository.findById(id);
        ArrayList<Cars> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("cars", res);
        if(!carsRepository.existsById(id))
        {
            return "redirect:/cars";
        }
        return "cars-details";
    }
    @GetMapping("/cars/{id}/edit")
    public String blogEdit(@PathVariable("id")long id,
                           Model model)
    {
        if(!carsRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Cars> post = carsRepository.findById(id);
        ArrayList<Cars> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("cars",res);
        return "cars-edit";
    }
    @PostMapping("/cars/{id}/edit")
    public String blogPostUpdate(@PathVariable("id")long id,
                                 @RequestParam String mark,
                                 @RequestParam String vladelec,
                                 @RequestParam Boolean polom,
                                 @RequestParam int probeg,
                                 @RequestParam int age,
                                 Model model)
    {
        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setMark(mark);
        cars.setVladelec(vladelec);
        cars.setPolom(polom);
        cars.setProbeg(probeg);
        cars.setAge(age);
        carsRepository.save(cars);
        return "redirect:/";
    }
    @PostMapping("/cars/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        Cars cars = carsRepository.findById(id).orElseThrow();
        carsRepository.delete(cars);
        return "redirect:/";
    }
}
