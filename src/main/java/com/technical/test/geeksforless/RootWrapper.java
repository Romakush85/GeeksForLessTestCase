package com.technical.test.geeksforless;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class RootWrapper {

    public RootWrapper() {
        this.possibleRoots=new ArrayList<>();
    }

    private List<
    @Pattern(regexp = "^\\s*(-?\\d+(\\.\\d+)?\\s*)?$", message="Please enter a valid decimal number")
                    String> possibleRoots;

}
