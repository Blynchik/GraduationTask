package ru.javaops.topjava.web.vote;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public List<VoteTo> getAll(){
        return voteRepository.findAllByUserId(SecurityUtil.authUser().getId()).stream()
                .map(VoteUtil::getTo)
                .collect(Collectors.toList());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestParam int restaurantId){
        Vote vote = VoteUtil.getEntity();


        if(voteRepository.findAllByUserId(SecurityUtil.authId()).stream()
                .anyMatch(v -> v.getCreatedAt().toLocalDate().isEqual(LocalDate.now()))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        vote.setRestaurant(restaurantRepository.get(restaurantId));
        voteRepository.save(vote);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
