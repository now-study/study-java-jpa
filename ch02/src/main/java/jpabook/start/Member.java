package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
@ToString
public class Member {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String username;
    private Integer age;
}
