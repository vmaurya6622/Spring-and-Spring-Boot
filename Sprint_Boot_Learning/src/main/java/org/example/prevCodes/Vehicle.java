package org.example.prevCodes;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
     Enginee engine;

    // Constructor for Constructor DI
//    @Autowired
    public Vehicle(Enginee engine) { this.engine = engine; }

    // Setter for Setter DI
//    @Autowired
    public void setEngine(Enginee engine) { this.engine = engine; }

    public void drive() {
        engine.start();
        System.out.println("Vehicle is moving");
    }
}
