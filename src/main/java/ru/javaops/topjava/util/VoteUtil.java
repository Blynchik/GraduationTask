package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.model.Vote;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.repository.VoteRepository;
import ru.javaops.topjava.to.RestaurantTo;
import ru.javaops.topjava.to.VoteTo;
import ru.javaops.topjava.web.SecurityUtil;

import java.time.LocalDateTime;

@UtilityClass
public class VoteUtil {

    public static VoteTo getTo(Vote vote) {
        VoteTo voteTo = new VoteTo();
        voteTo.setRestaurantId(vote.getRestaurant().getId());
        return voteTo;
    }

    public static Vote getEntity(){
        Vote vote =  new Vote();
        vote.setCreatedAt(LocalDateTime.now());
        vote.setUser(SecurityUtil.authUser());
        return vote;
    }
}
