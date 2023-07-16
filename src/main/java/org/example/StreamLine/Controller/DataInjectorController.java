package org.example.StreamLine.Controller;

import org.example.StreamLine.Utils.DataInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/data")
public class DataInjectorController {

    @Autowired
    private DataInjector dataInjector;

    @GetMapping("/inject-users")
    public ResponseEntity<String> generateUsers() {
        dataInjector.generateUsers();
        return new ResponseEntity<String>("100 users generated", HttpStatus.OK);
    }

    @GetMapping("/inject-posts")
    public ResponseEntity<String> generatePosts() {
        dataInjector.generatePosts();
        return new ResponseEntity<String>("100 posts generated", HttpStatus.OK);
    }


}
