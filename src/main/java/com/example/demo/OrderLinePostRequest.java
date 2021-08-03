package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class OrderLinePostRequest {

    @NotNull
    private final Long productId;

    @NotNull
    private final Integer quantity;
}
