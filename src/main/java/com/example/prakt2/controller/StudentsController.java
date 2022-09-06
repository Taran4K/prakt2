package com.example.prakt2.controller;

import com.example.prakt2.models.Students;
import com.example.prakt2.repos.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentsController {
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/")
    public String Main(Model model)
    {
        return "main";
    }

    @GetMapping("/students")
    public String StudentsMain(Model model)
    {
        Iterable<Students> students= studentsRepository.findAll();
        model.addAttribute("students", students);
        return "students-main";
    }
    @GetMapping("/students/add")
    public String blogAdd(Model model) {return "students-add";}


    @PostMapping("/students/add")
    public String blogPostAdd(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String otchestvo,
                              @RequestParam String group,
                              @RequestParam int age,
                              Model model) {
        Students students = new Students(name, surname, otchestvo,group, age);
        studentsRepository.save(students);
        return "redirect:/";
    }
    @GetMapping("/students/filter")
    public String blogfilter(Model model) {return "students-filter";}

    @PostMapping("/students/filter/resulttoch")
    public String blogResulttoch(@RequestParam String surname, Model model)
    {
        List<Students> result = studentsRepository.findBySurname(surname);
        model.addAttribute("result", result);
        return "students-filter";
    }
    @PostMapping("/students/filter/resultnetochn")
    public String blogResultnet(@RequestParam String surname, Model model)
    {
        List<Students> result = studentsRepository.findBySurnameContains(surname);
        model.addAttribute("result", result);
        return "students-filter";
    }
}
