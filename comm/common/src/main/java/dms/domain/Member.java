package dms.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Column(name = "memberId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String memberName;
}
