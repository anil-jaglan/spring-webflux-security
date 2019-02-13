package com.reactive.errorhandlers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorAttributes.class);

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        LOGGER.info("Sanitizing error attributes for: {}", request.path());
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
        //map.put("status", HttpStatus.BAD_REQUEST); we can change the http_status here.
       // map.put("message", "Please contact Administrator!");
        return map;
    }
}
