package javaproject.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    private Long id;
    private String businessName;
    private String director;
    private String address;
    private String number;
    private String whatCarSupply;
    private String businessBill;
    private String description;
    private Date startedWorkTogether;
    @OneToMany(mappedBy = "supplier", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<Car> car;
}
