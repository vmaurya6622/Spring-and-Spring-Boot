//package org.example.TestingAndDebugging.Testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.annotations.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*
    -> unit testing means testing a unit of program in isolation
    -> it is a service method and a utility class.
    -> so it doesn't deal with server, database or spring context.
 */
class Doctor {
    private Integer id;
    private String name;

    public Doctor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
}

/* ================= Custom Exception ================= */

class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

/* ================= Repository ================= */

interface DoctorRepository {
    Optional<Doctor> findById(Integer id);
}

/* ================= Service ================= */

class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor getDoctorById(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: " + id
                        )
                );
    }
}

/* ================= Unit Test ================= */

@ExtendWith(MockitoExtension.class) // enables Mockito
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;   // fake repo

    @InjectMocks
    private DoctorService doctorService;         // class under test

    // This test case will pass
    @Test
    void shouldReturnDoctorWhenFound() {

        Doctor doctor = new Doctor(1, "Vikram");

        when(doctorRepository.findById(1))
                .thenReturn(Optional.of(doctor));

        Doctor result = doctorService.getDoctorById(1);

        assertEquals(1, result.getId());
        assertEquals("Vikram", result.getName());
    }

    // This test case will fail
    @Test
    void shouldThrowExceptionWhenDoctorNotFound() {

        when(doctorRepository.findById(5))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () ->
                        doctorService.getDoctorById(5)
                );

        assertEquals(
                "Doctor not found with id: 5",
                exception.getMessage()
        );
    }
}

/* ================= Main (Not used in unit test) ================= */

@SpringBootApplication
public class UnitTesting {
    public static void main(String[] args) {
        SpringApplication.run(UnitTesting.class, args);
    }
}