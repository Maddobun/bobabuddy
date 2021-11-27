package com.boba.bobabuddy.core.service.user;

import com.boba.bobabuddy.core.domain.Rating;
import com.boba.bobabuddy.core.domain.User;
import com.boba.bobabuddy.core.domain.builder.UserBuilder;
import com.boba.bobabuddy.core.service.user.impl.UpdateUserServiceImpl;
import com.boba.bobabuddy.core.data.dao.UserJpaRepository;
import com.boba.bobabuddy.core.data.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserTest {
    String email;
    @Mock
    private UserJpaRepository repo;
    @Mock
    private Rating rating;
    @Mock
    private FindUserService findUser;
    @InjectMocks
    private UpdateUserServiceImpl updateUser;
    private User user1, user2;
    private UserDto userDto;

    @BeforeEach
    void setup() {
        email = "name@gmail.com";
        String name1 = "name1";
        String password1 = "password1";
        String name2 = "name2";
        String password2 = "password2";

        user1 = new UserBuilder().setName(name1).setEmail(email).setPassword(password1).createUser();
        user2 = new UserBuilder().setName(name2).setEmail(email).setPassword(password2).createUser();
        userDto = new UserDto(name2, email, password2);
        user1.addRating(rating);
    }

    @AfterEach
    void tearDown() {
        user1 = null;
        user2 = null;
        email = null;
    }

    @Test
    void testUpdateUser() {
        when(findUser.findByEmail(email)).thenReturn(user1);
        when(repo.save(user1)).thenReturn(user1);
        User returnedUser = updateUser.updateUser(findUser.findByEmail(email), userDto);
        user2.addRating(rating);
        assertEquals(user2.getName(), returnedUser.getName());
        assertEquals(user2.getPassword(), returnedUser.getPassword());
        assertEquals(user2.getRatings(), returnedUser.getRatings());


    }

    @Test
    void testAddRating() {
        when(findUser.findByEmail(email)).thenReturn(user2);
        when(repo.save(user2)).thenReturn(user2);
        Set<Rating> emptyRatingList = Collections.emptySet();
        Set<Rating> updatedRatingList = Set.of(rating);
        assertEquals(user2.getRatings(), emptyRatingList);
        User returnedUser = updateUser.addRating(findUser.findByEmail(email), rating);
        assertEquals(returnedUser.getRatings(), updatedRatingList);
    }
}