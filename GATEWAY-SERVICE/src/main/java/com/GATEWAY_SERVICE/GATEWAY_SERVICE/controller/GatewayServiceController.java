package com.GATEWAY_SERVICE.GATEWAY_SERVICE.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GatewayServiceController {
 @GetMapping
 public String index() {
     return "Gateway Service";
 }
}
