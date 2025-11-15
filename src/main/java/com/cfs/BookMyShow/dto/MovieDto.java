package com.cfs.BookMyShow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto
{
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String language;
    private Integer durationMins;
    private String releaseDate;
    private String posterUrl;
}
