package com.cfs.BookMyShow.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDto {

    private Long id;
    private String name;
    private Integer totalSeats;
    private TheatreDto theater;
}
