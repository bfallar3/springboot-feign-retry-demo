package com.benjsoft.feignretryableexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private MockFeign mockFeign;

    // sample request: http://localhost:8080/client/getAll
    @GetMapping(value = "/getAll", produces = "text/html")
    public List<Object> getObjects() {
        return mockFeign.getObjects();
    }

}
