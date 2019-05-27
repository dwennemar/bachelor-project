package de.dwennemar.bachelor.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
public class OperationsEndpoint {

    @PostMapping("/keyBackup")
    public void createKeyBackup() {

    }

    @PostMapping("/dataBackup")
    public void createDataBackup() {

    }
}
