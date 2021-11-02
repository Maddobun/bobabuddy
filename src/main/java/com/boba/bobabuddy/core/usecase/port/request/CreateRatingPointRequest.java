package com.boba.bobabuddy.core.usecase.port.request;

import com.boba.bobabuddy.core.entity.RatableObject;
import com.boba.bobabuddy.core.entity.RatingPoint;
import com.boba.bobabuddy.core.entity.User;
import com.boba.bobabuddy.core.usecase.ratingpoint.exceptions.InvalidRatingException;

public class CreateRatingPointRequest {
    private int rating;
    private User user;
    private RatableObject ratableObject;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws InvalidRatingException {
        if (rating != 0 && rating != 1) {
            throw new InvalidRatingException();
        }
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RatableObject getRatableObject() {
        return ratableObject;
    }

    public void setRatableObject(RatableObject ratableObject) {
        this.ratableObject = ratableObject;
    }

    public RatingPoint getRatingPoint() {
        return new RatingPoint(rating, user, ratableObject);
    }
}
