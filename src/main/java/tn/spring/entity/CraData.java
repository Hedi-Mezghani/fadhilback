package tn.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="CraData")
public class CraData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String choixClient ;
    private String choixProjet;
    private String[] textInputs;
    private String comment;

    private Date date;
    @Column(name="user_id")
    private Long userId;
    private Integer year;
    private Integer month;
}
