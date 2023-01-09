package ru.sovetnikov.app.util;

import lombok.experimental.UtilityClass;
import ru.sovetnikov.app.model.Vote;
import ru.sovetnikov.app.to.VoteTo;
import ru.sovetnikov.app.web.SecurityUtil;

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
