package com.user.details;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDetailsService userDetailsService;


    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/save")
    public Mono<UserDetails> save(UserDetails userDetails) {
        return this.userDetailsService.save(userDetails);
    }

    @GetMapping("/")
    public Flux<UserDetails> findAll() {
        return this.userDetailsService.findAll();
    }
}
