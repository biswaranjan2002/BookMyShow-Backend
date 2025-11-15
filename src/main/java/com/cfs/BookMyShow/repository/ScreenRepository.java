package com.cfs.BookMyShow.repository;

import com.cfs.BookMyShow.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<ScreenRepository, Long> {

    List<Screen> findByTheatreId(Long theatreId);
}
