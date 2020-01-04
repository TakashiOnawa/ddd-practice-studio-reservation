package org.taonaw.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.taonaw.reservation.application.ReserveStudioAppService;

@Controller
public class HelloWorldController {

    @Autowired
    private ReserveStudioAppService reserveStudioAppService;

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "HelloWorld";
    }
}
