package com.example.prakt2.controller;

import com.example.prakt2.models.Cars;
import com.example.prakt2.models.Students;
import com.example.prakt2.repos.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String blogAdd(Students student, Model model) {return "students-add";}


    @PostMapping("/students/add")
    public String studentAdd(@ModelAttribute("students")@Valid Students student, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "students-add";
        }
        studentsRepository.save(student);
        return "redirect:/students";
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
    public String studentEdit(@PathVariable("id")long id, Model model)
    {
        Students res = studentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("students",res);
        return "students-edit";
    }
    @PostMapping("/students/{id}/edit")
    public String StudentUpdate(@PathVariable("id")long id, @ModelAttribute("students")@Valid Students student, BindingResult bindingResult)
    {
        student.setId(id);
        if(bindingResult.hasErrors())
        {
            return "students-edit";
        }
        studentsRepository.save(student);
        return "redirect:/students";
    }
    @PostMapping("/students/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        Students students = studentsRepository.findById(id).orElseThrow();
        studentsRepository.delete(students);
        return "redirect:/";
    }
}
