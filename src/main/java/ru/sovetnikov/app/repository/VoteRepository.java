package ru.sovetnikov.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sovetnikov.app.model.Vote;

import java.util.List;

@Repository
public interface VoteRepository extends BaseRepository<Vote> {


    @Query("SELECT v FROM Vote v WHERE v.user.id = :id")
    List<Vote> findAllByUserId(int id);

}
