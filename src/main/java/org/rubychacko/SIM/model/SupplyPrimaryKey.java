package org.rubychacko.SIM.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Embeddable
public class SupplyPrimaryKey implements Serializable {
    private String storeId;
    private String productId;
}
