package com.cfs.BookMyShow.repository;

import com.cfs.BookMyShow.model.ShowSeat;
import com.cfs.BookMyShow.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    List<Theatre> findByShowId(String city);

}
