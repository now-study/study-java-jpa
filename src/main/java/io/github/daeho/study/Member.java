package io.github.daeho.study;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "StudyMember")
@Table(name = "STUDY_MEMBER")
public class Member {

    @Id
    private String id;
    @Column(name="NAME")
    private String username;
    private Integer age;

}
