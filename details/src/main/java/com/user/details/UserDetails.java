package com.user.details;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("USERDETAILS")
public record UserDetails(@Id UUID userID, String username, String email) {
}
