package org.example.prevCodes;
import org.example.Interfaces.computer;
import org.springframework.stereotype.Component;

@Component
public class Desktop implements computer {
    @Override
    public void compile() {
        System.out.println("Desktop compiling C++ code...");
    }
}
