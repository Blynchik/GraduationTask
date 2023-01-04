package ru.javaops.topjava.util;

import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.model.Vote;
import ru.javaops.topjava.to.RestaurantTo;
import ru.javaops.topjava.to.VoteTo;

public class VoteUtil {

    public static VoteTo getTo(Vote vote) {
        VoteTo voteTo = new VoteTo();
        voteTo.setCreated_at(vote.getCreatedAt());
        voteTo.setRestaurantTo(RestaurantUtil.getTo(vote.getRestaurant()));
        return voteTo;
    }
}
