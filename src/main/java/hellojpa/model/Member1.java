package hellojpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter@Setter
public class Member1 {
    @Id
    private String id;
    private String userName;
}
