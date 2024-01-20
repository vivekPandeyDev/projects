package controller;

import dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/register")
    public String getPage(@ModelAttribute("userInfo")UserDto dto){
        return "user-register";
    }

    @RequestMapping("/register")
    public String postPage(@ModelAttribute("userInfo")UserDto dto){
        System.out.println(dto);
        return "user-register";
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
    public String[] populateLocation(){
        String[] preferLoc = new String[4];
        preferLoc[0]="Noida";
        preferLoc[1]="Indore";
        preferLoc[2]="JK";
        preferLoc[3] ="Dehradun";
        return preferLoc;
    }


}
