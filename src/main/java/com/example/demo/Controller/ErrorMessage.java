package com.example.demo.Controller;

import org.springframework.http.HttpStatusCode;

public record ErrorMessage(String message, HttpStatusCode status) {
}
