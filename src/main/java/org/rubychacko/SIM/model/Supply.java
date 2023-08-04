package org.rubychacko.SIM.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table
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
