package com.vivek.integrated.controller;

import com.vivek.integrated.model.StudentDTO;
import com.vivek.integrated.service.StudentService;
import com.vivek.integrated.util.WebUtils;
import jakarta.validation.Valid;
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
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("student") final StudentDTO studentDTO) {
        return "student/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("student") @Valid final StudentDTO studentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("email") && studentService.emailExists(studentDTO.getEmail())) {
            bindingResult.rejectValue("email", "Exists.student.email");
        }
        if (bindingResult.hasErrors()) {
            return "student/add";
        }
        studentService.create(studentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("student.create.success"));
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("student", studentService.get(id));
        return "student/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("student") @Valid final StudentDTO studentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final StudentDTO currentStudentDTO = studentService.get(id);
        if (!bindingResult.hasFieldErrors("email") &&
                !studentDTO.getEmail().equalsIgnoreCase(currentStudentDTO.getEmail()) &&
                studentService.emailExists(studentDTO.getEmail())) {
            bindingResult.rejectValue("email", "Exists.student.email");
        }
        if (bindingResult.hasErrors()) {
            return "student/edit";
        }
        studentService.update(id, studentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("student.update.success"));
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = studentService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            studentService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("student.delete.success"));
        }
        return "redirect:/students";
    }

}
