package ru.sovetnikov.app.web.vote;

import ru.sovetnikov.app.model.Vote;
import ru.sovetnikov.app.to.VoteTo;
import ru.sovetnikov.app.web.MatcherFactory;
import ru.sovetnikov.app.web.restaurant.RestaurantAndMealTestData;
import ru.sovetnikov.app.web.user.UserTestData;

import java.time.LocalDateTime;
import java.util.List;

public class VoteTestData {
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class, "createdAt");
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "createdAt");
    public static Vote userVote = new Vote();
    public static Vote adminVote = new Vote();
    public static Vote adminVote2 = new Vote();

    public static VoteTo userVoteTo = new VoteTo();
    public static VoteTo adminVoteTo = new VoteTo();
    public static VoteTo adminVote2To = new VoteTo();
    public static LocalDateTime NOW = LocalDateTime.now();
    public static List<VoteTo> votes;

    public static List<VoteTo> allVotes;

    static {
        adminVote2.setId(1);
        adminVote2.setUser(UserTestData.admin);
        adminVote2.setRestaurant(RestaurantAndMealTestData.RESTAURANT1);
        adminVote2.setCreatedAt(NOW.minusHours(12));

        adminVote.setId(2);
        adminVote.setUser(UserTestData.admin);
        adminVote.setRestaurant(RestaurantAndMealTestData.RESTAURANT2);
        adminVote.setCreatedAt(NOW.minusDays(1));

        userVote.setId(3);
        userVote.setUser(UserTestData.user);
        userVote.setRestaurant(RestaurantAndMealTestData.RESTAURANT2);
        userVote.setCreatedAt(NOW.minusDays(2));

        adminVote2To.setRestaurantId(RestaurantAndMealTestData.RESTAURANT1_ID);
        adminVote2To.setCreatedAt(NOW.minusHours(12));

        adminVoteTo.setRestaurantId(RestaurantAndMealTestData.RESTAURANT2_ID);
        adminVoteTo.setCreatedAt(NOW.minusDays(1));

        userVoteTo.setRestaurantId(RestaurantAndMealTestData.RESTAURANT2_ID);
        userVoteTo.setCreatedAt(NOW.minusDays(2));

        votes = List.of(userVoteTo);
        allVotes = List.of(adminVote2To, adminVoteTo, userVoteTo);
    }
}
