package com.pingcode.weekly.controller;


import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basic")
public class BasicAngularSpringBootRest {
    @GetMapping("/get")
    public void getPrint(@RequestParam("print") String print) {
        System.out.println("get : " + print);
    }

    @PostMapping("/postparam")
    public void postPrint(@RequestParam("print") String print) {
        System.out.println("post param : " + print);
    }

    @PostMapping("/postbody")
    public void postPrint(@RequestBody Print print) {
        System.out.println("post body : " + print.getPrint());
    }
}

