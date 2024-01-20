package com.vivek.integrated.controller;

import com.vivek.integrated.domain.Student;
import com.vivek.integrated.model.CourseDTO;
import com.vivek.integrated.repos.StudentRepository;
import com.vivek.integrated.service.CourseService;
import com.vivek.integrated.util.CustomCollectors;
import com.vivek.integrated.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentRepository studentRepository;

    public CourseController(final CourseService courseService,
            final StudentRepository studentRepository) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("studentValues", studentRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Student::getId, Student::getCreatedBy)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("course") final CourseDTO courseDTO) {
        return "course/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("course") @Valid final CourseDTO courseDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "course/add";
        }
        courseService.create(courseDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("course.create.success"));
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("course", courseService.get(id));
        return "course/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("course") @Valid final CourseDTO courseDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "course/edit";
        }
        courseService.update(id, courseDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("course.update.success"));
        return "redirect:/courses";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        courseService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("course.delete.success"));
        return "redirect:/courses";
    }

}
