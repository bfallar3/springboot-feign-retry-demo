package com.benjsoft.feignretryableexample;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "mockClient", url = "http://www.mocky.io/v2/5e2d966a3000006200e77d2d")
public interface MockFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<Object> getObjects();
}

