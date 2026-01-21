package org.example;
import org.example.Interfaces.computer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// IoC: Spring manages the lifecycle; you don't 'new' it yourself.
@Component
public class Alien {

    private final computer Compute;

    public Alien(/* @Qualifier("desktop") */ computer Compute) {
        this.Compute = Compute;
    }

    public void code() {
        Compute.compile();
        System.out.println("Alien is coding... 👽");
    }
}

