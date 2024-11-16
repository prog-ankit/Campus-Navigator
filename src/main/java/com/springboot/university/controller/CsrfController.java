package com.springboot.university.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
* This token needs to be passed in the headers of POST request.
* It contains following response:
* {
    "parameterName": "_csrf",
    "token": "tXvMZ1vNZlpefvcZbRXuxI_lbyXenltpm9fOI0Tv_tqm4Wkr10_9UWz0XmlzTcQhVTjap7bQQkTr_GJEqeL7RieJyO7FhVwf",
    "headerName": "X-CSRF-TOKEN"
}
* */
@RestController
@RequestMapping("api/")
public class CsrfController {

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
