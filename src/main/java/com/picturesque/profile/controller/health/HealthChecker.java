package com.picturesque.profile.controller.health;

import com.picturesque.profile.development.TestingRepos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HealthChecker {

    @RequestMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public Healthy healthCheck() {
        TestingRepos test = new TestingRepos();
        test.testRepositories();
        return new Healthy(1, "profile system is up");
    }

}
