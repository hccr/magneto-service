package cl.hccr.service.magneto.domain;

public class MutantRequest {
    private String [] dna;


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
}
