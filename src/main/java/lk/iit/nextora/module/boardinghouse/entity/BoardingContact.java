package lk.iit.nextora.module.boardinghouse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boarding_contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardingContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String contactNumber1;

    @Column(length = 15)
    private String contactNumber2;
}
