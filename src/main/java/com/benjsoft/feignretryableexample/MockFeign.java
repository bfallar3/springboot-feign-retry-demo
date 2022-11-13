package com.benjsoft.feignretryableexample;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "mockClient", url = "http://localhost:8080")
public interface MockFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/api/users")
    ResponseEntity<NameModel> getObjects();
}

