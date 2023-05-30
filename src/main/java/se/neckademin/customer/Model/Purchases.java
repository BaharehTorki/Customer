package se.neckademin.customer.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchases {
    private Long id;
    private String date;
    private Long customerId;
    private List<Long> Items;
}
