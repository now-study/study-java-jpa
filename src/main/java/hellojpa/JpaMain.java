package hellojpa;

import hellojpa.model.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j

public class JpaMain {
    public static void main(String[] args) {
        log.debug("teste========");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        Jpa2 jp = new Jpa2(emf);
        Jpa3 jp = new Jpa3(emf);

    }


}