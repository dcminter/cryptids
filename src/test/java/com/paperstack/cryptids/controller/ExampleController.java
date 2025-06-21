package com.paperstack.cryptids.controller;

import com.paperstack.cryptids.model.SimplePayload;
import com.paperstack.cryptids.model.TrickyDeserializationPayload;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @RequestMapping(path = "/example", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    public String postExample(@RequestBody final SimplePayload payload) {
        return payload.toString();
    }

    @RequestMapping(path = "/tricky", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    public String postTrickyExample(@RequestBody final TrickyDeserializationPayload payload) {
        return payload.toString();
    }
}
