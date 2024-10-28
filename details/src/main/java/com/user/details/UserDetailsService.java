package com.user.details;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public Flux<UserDetails> findAll(){
        return this.userDetailsRepository.findAll();
    }

    public Mono<UserDetails> save(UserDetails userDetails){
        return this.userDetailsRepository.save(userDetails);
    }
}
