package org.example;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ToyotaEngine implements Enginee {
    @Override
    public void start() {
        System.out.println("ToyotaEngine is running");
    }
}
