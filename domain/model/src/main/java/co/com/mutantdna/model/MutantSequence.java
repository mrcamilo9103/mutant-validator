package co.com.mutantdna.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MutantSequence {
    private String current;
    private String before;

    int countCoincidences = 0;

    public void plussCount(){
        this.countCoincidences++;
    }

    public void resetCount(){
        this.countCoincidences = 1;
    }

}
