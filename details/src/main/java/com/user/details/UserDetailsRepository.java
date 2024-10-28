package com.user.details;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends ReactiveMongoRepository<UserDetails, String> {
}

