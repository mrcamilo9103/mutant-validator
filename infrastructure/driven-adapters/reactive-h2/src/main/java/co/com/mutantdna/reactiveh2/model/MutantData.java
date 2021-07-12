package co.com.mutantdna.reactiveh2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table("MUTANT_VALID")
public class MutantData {

    @Id
    private Long id;
    private String[] dna;

    @Column("IS_MUTANT")
    private boolean isMutant;

}
