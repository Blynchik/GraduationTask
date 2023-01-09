package ru.javaops.topjava.web.vote;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.error.AppException;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.model.Vote;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.repository.VoteRepository;
import ru.javaops.topjava.to.RestaurantTo;
import ru.javaops.topjava.to.VoteTo;
import ru.javaops.topjava.util.RestaurantUtil;
import ru.javaops.topjava.util.VoteUtil;
import ru.javaops.topjava.util.validation.ValidationUtil;
import ru.javaops.topjava.web.AuthUser;
import ru.javaops.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
