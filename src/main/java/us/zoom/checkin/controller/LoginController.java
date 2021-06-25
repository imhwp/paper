package us.zoom.checkin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import us.zoom.checkin.entity.User;

@RestController
@RequestMapping("/api")
public class LoginController {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public User test(){
        User user = new User();
        return user;
    }
}
