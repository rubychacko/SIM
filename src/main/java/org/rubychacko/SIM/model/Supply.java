package org.rubychacko.SIM.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SupplyPrimaryKey.class)
public class Supply {
    @Id
    private String storeId;
    @Id
    private String productId;
    @Column(nullable = false)
    private Integer totalInventory;
    @Column(nullable = false)
    private Integer availableInventory;
}
