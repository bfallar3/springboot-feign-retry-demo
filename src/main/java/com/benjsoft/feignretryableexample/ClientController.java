package com.benjsoft.feignretryableexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private MockFeign mockFeign;

    // sample request: http://localhost:8080/client/getAll
    @GetMapping(value = "/getAll", produces = "application/json")
    public List<Object> getObjects() {
        return mockFeign.getObjects();
    }

    @GetMapping("/divideBy/{num}")
    public int divideWith(@PathVariable int num)
    {
        try {
            return 100 / num;
        }
        catch (Exception e) {
            // String err = ExceptionUtils.getStackTrace(e);
            log.error("Arithmetic divide by zero encountered", e);
        }
        return 0;
    }

    @GetMapping("/greet/{country}/{name}")
    public String sayName(@PathVariable String country, @PathVariable String name)
    {
        return "Hello " + name + " from " + country;
    }
}
