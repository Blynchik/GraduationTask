package ru.sovetnikov.app.web.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sovetnikov.app.repository.VoteRepository;
import ru.sovetnikov.app.to.VoteTo;
import ru.sovetnikov.app.util.VoteUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional(readOnly = true)
public class AdminVoteController {

    public static final String REST_URL = "/api/admin/votes";
    private final VoteRepository voteRepository;

    @GetMapping
    public List<VoteTo> getAll() {
        return voteRepository.findAll().stream()
                .map(VoteUtil::getTo)
                .collect(Collectors.toList());
    }
}
