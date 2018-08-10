package com.ansosoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestController {

    @RequestMapping(value= "/Monitor", method = RequestMethod.GET)
    @ResponseBody
    public String monitor() {
        return "SUCCESS";
    }

}
