package com.benjsoft.feignretryableexample;

import feign.Response;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorDecoder implements feign.codec.ErrorDecoder {
    private final feign.codec.ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if(response.status() == 400 || response.status() == 500) {
            log.error(">>> Bad request or not found error encountered! Doing a retry! <<<");

            return new RetryableException(400, response.reason(), response.request().httpMethod(),
                    null, response.request());
        }

        return defaultErrorDecoder.decode(s, response);
    }
}
