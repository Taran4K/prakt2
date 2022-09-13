package com.example.prakt2.controller;

import com.example.prakt2.models.Cars;
import com.example.prakt2.models.PTS;
import com.example.prakt2.models.Students;
import com.example.prakt2.repos.CarsRepository;
import com.example.prakt2.repos.PTSRepository;
import com.example.prakt2.repos.StudentsRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarsController {
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    private PTSRepository ptsRepository;

    @GetMapping("/cars")
    public String CarsMain(Model model)
    {
        Iterable<Cars> cars= carsRepository.findAll();
        model.addAttribute("cars", cars);
        return "cars-main";
    }
    @GetMapping("/cars/add")
    public String blogAdd(Model model) {
        Iterable<PTS> pasport = ptsRepository.findAll();
        model.addAttribute("pasport", pasport);
        return "cars-add";}

    @PostMapping("/cars/add")
    public String carsPostAdd(@ModelAttribute ("cars") @Valid Cars cars,@RequestParam String number, BindingResult bindingResult)
    {
        PTS pasport = ptsRepository.findByNumber(number);
        Cars cars1 = new Cars(cars.getMark(), cars.getVladelec(),cars.Polom, cars.getAge(), cars.getProbeg(), pasport);
        if (bindingResult.hasErrors()){
            return "cars-add";
        }
        carsRepository.save(cars1);
        return "redirect:/";
    }

//    @PostMapping("/cars/add")
//    public String blogPostAdd(@RequestParam String mark,
//                              @RequestParam String vladelec,
//                              @RequestParam Boolean polom,
//                              @RequestParam int probeg,
//                              @RequestParam int age,
//                              Model model) {
//        Cars cars = new Cars(mark, vladelec, polom, age, probeg);
//        carsRepository.save(cars);
//        return "redirect:/";
//    }

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
    public String blogEdit(@PathVariable("id")long id, Model model)
    {
        Cars res = carsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("cars",res);
        return "cars-edit";
    }
    @PostMapping("/cars/{id}/edit")
    public String blogPostUpdate(@PathVariable("id")long id, @ModelAttribute("cars")@Valid Cars cars, BindingResult bindingResult)
    {
        cars.setId(id);
        if(bindingResult.hasErrors())
        {
            return "cars-edit";
        }
        carsRepository.save(cars);
        return "redirect:/cars";
    }

    @PostMapping("/cars/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        Cars cars = carsRepository.findById(id).orElseThrow();
        carsRepository.delete(cars);
        return "redirect:/";
    }
}
