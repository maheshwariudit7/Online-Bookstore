package com.OnlineBookstore.OnlineBookStore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String message;
    private LocalDateTime timestamp;
    private String path;
    private String errorCode;
}
