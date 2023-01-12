package ru.sovetnikov.app.web.vote;

import ru.sovetnikov.app.model.User;
import ru.sovetnikov.app.model.Vote;
import ru.sovetnikov.app.to.VoteTo;
import ru.sovetnikov.app.web.MatcherFactory;
import ru.sovetnikov.app.web.restaurant.RestaurantTestData;
import ru.sovetnikov.app.web.user.UserTestData;

import java.time.LocalDateTime;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class);

//    public static Vote userVote = new Vote();
//    public static Vote adminVote = new Vote();
//    public static Vote adminVote2 = new Vote();
//    public static Vote adminVoteToUpdate = new Vote();
//
//    static{
//        prepareVotes();
//    }

//    private static void prepareVotes(){
//        userVote.setId(1);
//        userVote.setUser(UserTestData.user);
//        userVote.setRestaurant(RestaurantTestData.restaurant1);
//        userVote.setCreatedAt(LocalDateTime.now());
//    }
}
