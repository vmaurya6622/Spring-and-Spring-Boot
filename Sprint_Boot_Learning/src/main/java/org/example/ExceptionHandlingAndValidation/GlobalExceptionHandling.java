package org.example.ExceptionHandlingAndValidation;
/*
    -> Global exception handler handles the exception for full code
    -> How it works:
            - Pehle specific exception check hoti hai
            - Agar match mila then wahi handler call hota hai.
            - if there is not match then generic Exception.class handler chalta hai aur vahi call hojata hai.
    -> Architecture:
                Controller/ service    -> handles http requests and throws exception if this layer gives exception
                        ↓
                 Exception throw       -> when the service cannot fulfill the request.
                        ↓
                 @ControllerAdvice     -> Global exception handler and intercepts exception thrown by controllers/services and converts into structured HTTP responses
                        ↓
              HTTP response to client  -> Instead of stack trace, client gets clean and structured JSON response for error
 */

public class GlobalExceptionHandling {
}
