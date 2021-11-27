package com.boba.bobabuddy.core.service.rating;

import com.boba.bobabuddy.core.domain.RatableObject;
import com.boba.bobabuddy.core.domain.Rating;
import com.boba.bobabuddy.core.service.ratableobject.UpdateRatableService;
import com.boba.bobabuddy.core.service.rating.impl.RemoveRatingServiceImpl;
import com.boba.bobabuddy.core.data.dao.RatingJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RemoveRatingServiceImplTest {
    @Mock
    private RatingJpaRepository repo;

    @Mock
    private FindRatingService findRating;

    @Mock
    private UpdateRatableService updateRatable;

    @InjectMocks
    private RemoveRatingServiceImpl removeRatingServiceImpl;

    @Mock
    private Rating rating;

    private RatableObject ratableObject;

    @Test
    void testRemoveById() {
        UUID id = UUID.randomUUID();
        when(findRating.findById(id)).thenReturn(rating);

        removeRatingServiceImpl.removeById(id);
        verify(updateRatable, times(1)).removeRating(ratableObject, rating);
        verify(repo, times(1)).delete(rating);
    }
}