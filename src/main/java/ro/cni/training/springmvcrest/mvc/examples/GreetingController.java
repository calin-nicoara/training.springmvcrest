package ro.cni.training.springmvcrest.mvc.examples;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GreetingController {

    @GetMapping(value = "/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:greeting";

        // Can also use relative or absolute paths
//        return "redirect:/greeting";
//        return "redirect:http://localhost:8199/greeting";
    }


}
