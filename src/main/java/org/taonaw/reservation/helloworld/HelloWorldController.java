package org.taonaw.reservation.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "HelloWorld";
    }
}
