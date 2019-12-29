package cl.hccr.service.magneto.domain;

public class MutantRequest {
    private String [] dna;
    private boolean isMutant = false;


    public MutantRequest() {
    }

    public MutantRequest(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
