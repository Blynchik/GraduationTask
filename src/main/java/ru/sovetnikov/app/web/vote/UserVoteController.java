package ru.sovetnikov.app.web.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.sovetnikov.app.error.AppException;
import ru.sovetnikov.app.model.Vote;
import ru.sovetnikov.app.repository.RestaurantRepository;
import ru.sovetnikov.app.repository.VoteRepository;
import ru.sovetnikov.app.to.VoteTo;
import ru.sovetnikov.app.util.TimeUtil;
import ru.sovetnikov.app.util.VoteUtil;
import ru.sovetnikov.app.web.SecurityUtil;


import java.net.URI;
import java.time.LocalDateTime;
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
    public ResponseEntity<VoteTo> vote(@RequestParam int restaurantId) {

        Vote vote = voteRepository.findAllByUserId(SecurityUtil.authId()).stream()
                .filter(v -> TimeUtil.checkTime(v.getCreatedAt()))
                .findFirst().orElse(null);

        if (vote == null) {
            vote = new Vote();
            vote.setCreatedAt(LocalDateTime.now());
            vote.setUser(SecurityUtil.authUser());
        }

        vote.setRestaurant(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Not found")));

        voteRepository.save(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(vote.getId())
                .toUri();

        return ResponseEntity.created(uriOfNewResource)
                .body(VoteUtil.getTo(vote));
    }
}
