package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @PersistenceContext
    private final EntityManager entityManager;

    @Value("${dummyApi.users.url}")
    private String usersApiUrl;

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            try {
                if (userRepository.count() == 0) {
                    loadUsers();
                }
            } catch (Exception e) {
                logger.warn("Failed to load users on startup", e);
            }
        }
    }

    @Override
    public void loadUsers() {
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(
                usersApiUrl, JsonNode.class);
        JsonNode usersNode = Objects.requireNonNull(response.getBody()).get("users");

        List<User> users = new ArrayList<>();
        for (JsonNode userNode : usersNode) {
            User user = new User();
            user.setFirstName(userNode.get("firstName").asText());
            user.setLastName(userNode.get("lastName").asText());
            user.setEmail(userNode.get("email").asText());
            user.setRole(userNode.get("role").asText());
            user.setAge(userNode.get("age").asInt());
            user.setSsn(userNode.get("ssn").asText());

            users.add(user);
        }
        userRepository.saveAll(users);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsers(String query) {
        SearchSession searchSession = Search.session(entityManager);
        return searchSession.search(User.class)
                .where(f -> f.simpleQueryString()
                        .fields("firstName", "lastName", "ssn")
                        .matching(query + "* OR" + query))
                .fetchHits(20);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
