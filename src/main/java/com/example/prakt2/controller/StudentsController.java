package com.example.prakt2.controller;

import com.example.prakt2.models.Cars;
import com.example.prakt2.models.Students;
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

    @GetMapping("/students/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Students> post = studentsRepository.findById(id);
        ArrayList<Students> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("students", res);
        if(!studentsRepository.existsById(id))
        {
            return "redirect:/students";
        }
        return "students-details";
    }
    @GetMapping("/students/{id}/edit")
    public String blogEdit(@PathVariable("id")long id,
                           Model model)
    {
        if(!studentsRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Students> post = studentsRepository.findById(id);
        ArrayList<Students> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("students",res);
        return "students-edit";
    }
    @PostMapping("/students/{id}/edit")
    public String blogPostUpdate(@PathVariable("id")long id,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String otchestvo,
                                 @RequestParam String group,
                                 @RequestParam int age,
                                 Model model)
    {
        Students students = studentsRepository.findById(id).orElseThrow();
        students.setName(name);
        students.setSurname(surname);
        students.setOtchestvo(otchestvo);
        students.setStudent_group(group);
        students.setAge(age);
        studentsRepository.save(students);
        return "redirect:/";
    }
    @PostMapping("/students/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        Students students = studentsRepository.findById(id).orElseThrow();
        studentsRepository.delete(students);
        return "redirect:/";
    }
}
