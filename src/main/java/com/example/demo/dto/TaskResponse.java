package com.example.demo.dto;

public record TaskResponse(

        Long id,
        String title,
        boolean completed

) {
}