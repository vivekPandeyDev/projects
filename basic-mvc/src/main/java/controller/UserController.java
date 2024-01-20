package controller;

import dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import propertyEditor.LocalDateEditor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/register")
    public String getPage(@ModelAttribute("userInfo")UserDto dto){
        return "user-register";
    }

    @PostMapping("/register")
    public String postPage(@ModelAttribute("userInfo")UserDto dto){

        int id=  new Random().nextInt(1000);
        int age = LocalDate.now().getYear() - dto.getDob().getYear();
        if(age < 21){
            return "error";
        }
        dto.setAge( age);
        dto.setUserId(id);
        System.out.println(dto);
        return "display-user";
    }

    @ModelAttribute("hobbiesList")
    public List<String> getHobbies(){
        List<String> hobbies = new ArrayList<>();
        hobbies.add("cricket");
        hobbies.add("basketball");
        hobbies.add("table tennis");
        return hobbies;

    }

    @ModelAttribute("locationList")
    public List<String> populateLocation(){
        List<String> preferLoc =new ArrayList<>();
        preferLoc.add("Nodia");
        preferLoc.add("Indore");
        preferLoc.add("JK");
        preferLoc.add("Dehradun");
        return preferLoc;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, "dob", new LocalDateEditor());
    }


}
