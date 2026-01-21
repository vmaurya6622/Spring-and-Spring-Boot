package org.example;
import org.example.Interfaces.computer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // If multiple Computer beans exist, this one will be injected by default
public class Laptop implements computer {
    @Override
    public void compile() {

        System.out.println("Laptop compiling Java code...");
    }
}
