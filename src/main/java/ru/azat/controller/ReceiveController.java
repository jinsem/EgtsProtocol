package ru.azat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReceiveController {

    @ResponseBody
    @PostMapping("/receive")
    public String receive(@RequestBody String data) {
        System.out.println(data);
        return "OK";
    }
}
