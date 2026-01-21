package org.example;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class    Runner implements CommandLineRunner {

    private final Alien alien;

    public Runner(Alien alien) {
        this.alien = alien;
    }

    @Override
    public void run(String... args) {
        alien.code();
    }
}
