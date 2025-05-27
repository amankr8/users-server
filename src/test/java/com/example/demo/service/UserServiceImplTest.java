package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.search.engine.search.query.dsl.SearchQueryOptionsStep;
import org.hibernate.search.engine.search.query.dsl.SearchQuerySelectStep;
import org.hibernate.search.engine.search.query.dsl.SearchQueryWhereStep;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private SearchSession searchSession;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userService, "usersApiUrl", "https://dummyjson.com/users");
    }

//    @Test
//    void testLoadUsers_successful() {
//        JsonNode mockUsersNode = new ObjectMapper().createObjectNode().set("users", new ObjectMapper().valueToTree(List.of(
//                Map.of(
//                        "firstName", "John",
//                        "lastName", "Doe",
//                        "email", "john@example.com",
//                        "role", "USER",
//                        "age", 30,
//                        "ssn", "123-45-6789"
//                )
//        )));
//
//        ResponseEntity<JsonNode> mockResponse = ResponseEntity.ok(mockUsersNode);
//        when(restTemplate.getForEntity(anyString(), eq(JsonNode.class))).thenReturn(mockResponse);
//
//        userService.loadUsers();
//
//        verify(userRepository).saveAll(argThat(users -> {
//            users.size() == 1 &&
//            users.get(0).getFirstName().equals("John") &&
//            users.get(0).getEmail().equals("john@example.com");
//        }));
//    }
//
//    @Test
//    void testGetAllUsers() {
//        List<User> users = List.of(new User(1L, "John", "Doe", "john@example.com", "USER", 30, "123-45-6789"));
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<User> result = userService.getAllUsers();
//
//        assertEquals(1, result.size());
//        assertEquals("John", result.get(0).getFirstName());
//    }
//
//    @Test
//    void testGetUserById_found() {
//        User user = new User(1L, "John", "Doe", "john@example.com", "USER", 30, "123-45-6789");
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        User result = userService.getUserById(1L);
//
//        assertEquals("john@example.com", result.getEmail());
//    }
//
//    @Test
//    void testGetUserById_notFound() {
//        when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
//    }
//
//    @Test
//    void testGetUserByEmail_found() {
//        User user = new User(1L, "John", "Doe", "john@example.com", "USER", 30, "123-45-6789");
//        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
//
//        User result = userService.getUserByEmail("john@example.com");
//
//        assertEquals("John", result.getFirstName());
//    }
//
//    @Test
//    void testSearchUsers() {
//        when(entityManager.getEntityManagerFactory()).thenReturn(entityManagerFactory);
//        when(Search.session(entityManager)).thenReturn(searchSession);
//
//        SearchQuerySelectStep<?, User, ?, ?, ?, ?> query = mock(SearchQuerySelectStep.class);
//        SearchQueryWhereStep<?, User, ?, ?, ?> whereStep = mock(SearchQueryWhereStep.class);
//        SearchQueryOptionsStep<?, User, ?> optionsStep = mock(SearchQueryOptionsStep.class);
//
//        when(searchSession.search(User.class)).thenReturn((SearchQuerySelectStep) query);
//        when(query.where(any())).thenReturn((SearchQueryOptionsStep) optionsStep);
//        when(optionsStep.fetchHits(20)).thenReturn(List.of(new User(1L, "Test", "User", "test@example.com", "ADMIN", 25, "321")));
//
//        List<User> results = userService.searchUsers("Test");
//
//        assertEquals(1, results.size());
//        assertEquals("Test", results.get(0).getFirstName());
//    }
}
