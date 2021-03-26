package website.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("manager/manager");
    }
}
