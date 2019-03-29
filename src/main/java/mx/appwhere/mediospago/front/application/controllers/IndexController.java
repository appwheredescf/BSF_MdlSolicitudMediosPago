package mx.appwhere.mediospago.front.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/04
 */
@Controller
public class IndexController {


    @GetMapping("/")
    public String index() {
        return "index";

    }
}
