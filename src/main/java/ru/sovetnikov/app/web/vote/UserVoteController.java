package ru.sovetnikov.app.web.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.sovetnikov.app.error.AppException;
import ru.sovetnikov.app.model.Vote;
import ru.sovetnikov.app.repository.RestaurantRepository;
import ru.sovetnikov.app.repository.VoteRepository;
import ru.sovetnikov.app.to.VoteTo;
import ru.sovetnikov.app.util.VoteUtil;
import ru.sovetnikov.app.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional(readOnly = true)
public class UserVoteController {

    static final String REST_URL = "api/profile/votes";
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public List<VoteTo> getAll() {
        return voteRepository.findAllByUserId(SecurityUtil.authId()).stream()
                .map(VoteUtil::getTo)
                .collect(Collectors.toList());
    }

    @Transactional
    @PostMapping
    public HttpStatus vote(@RequestParam int restaurantId) {

        Vote vote;

        if (voteRepository.findAllByUserId(SecurityUtil.authId()).stream()
                .anyMatch(v->v.getCreatedAt().toLocalDate().isEqual(LocalDate.now()) &&
                        LocalTime.now().isBefore(LocalTime.of(11,0)))) {

            vote = voteRepository.findAllByUserId(SecurityUtil.authId()).stream()
                    .max(Comparator.comparing(Vote::getCreatedAt))
                    .get();

        } else {
            vote = VoteUtil.getEntity();
        }

        vote.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new AppException(HttpStatus.NOT_FOUND, restaurantId + " Not found")));

        voteRepository.save(vote);

        return HttpStatus.OK;
    }
}
