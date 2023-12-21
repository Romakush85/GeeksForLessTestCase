package com.technical.test.geeksforless;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
//            @NotEmpty(message = "Please enter a decimal number")
            @Pattern(regexp = "^\\s*(-?\\d+(\\.\\d+)?\\s*)?$", message="Please enter a valid decimal number")
                    String> possibleRoots;

}
