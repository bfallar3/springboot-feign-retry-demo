package com.benjsoft.feignretryableexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    private static Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private MockFeign mockFeign;

    // sample request: http://localhost:8081/client/getAll
    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<NameModel> getObjects() {
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
        log.info("Hello {} from {}", name, country);
        return "Hello " + name + " from " + country;
    }
}
