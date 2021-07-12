package co.com.mutantdna.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Stats {
    private Integer countMutantDna;
    private Integer countHumanDna;
    private Double ratio;

    public Stats countMutantDna(Integer countMutantDna){
        setCountMutantDna(countMutantDna);
        return this;
    }

    public Stats countHumanDna(Integer countHumanDna){
        setCountHumanDna(countHumanDna);
        return this;
    }

    public Stats ratio() {
        long total = countMutantDna + countHumanDna;
        setRatio(1.0 * countMutantDna / total);
        return this;
    }
}
