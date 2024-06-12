package ru.laba.sdacha.GolomakoAnekdotBot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jokes")
@Table(name = "jokes")
public class Joke {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "joke")
    private String joke;

    @Column(name = "created")
    @JsonFormat(timezone = "GMT+03:00")
    private Date created;

    @Column(name = "updated")
    @JsonFormat(timezone = "GMT+03:00")
    private Date updated;
}