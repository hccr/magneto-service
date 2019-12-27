package cl.hccr.service.magneto.service;

import cl.hccr.service.magneto.domain.MutantRequest;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class RegexMutantCheckerServiceImpl implements MutantCheckerService {

    private final static String PATRON_A = "AAAA";
    private final static String PATRON_C = "CCCC";
    private final static String PATRON_G = "GGGG";
    private final static String PATRON_T = "TTTT";

    private final static String PATTERN = "[A]{4}|[C]{4}|[G]{4}|[T]{4}\b";



    @Override
    public boolean isMutant(MutantRequest mutantRequest) {

        //SE ASUME QUE ES UN ARREGLO NxN

        String [][] dna = new String [mutantRequest.getDna().length][mutantRequest.getDna().length];
        StringBuilder sb = new StringBuilder();

        //convierte el arreglo de Strings en arreglo de arreglos
        //Y une las cadenas de separadas en una sola larga para la verificacion horizontal
        for(int i = 0; i<mutantRequest.getDna().length; i++){
            dna[i] = mutantRequest.getDna()[i].split("");
            sb.append(mutantRequest.getDna()[i]);
        }
        String horizontal = sb.toString();
        sb.delete(0, sb.length());
        //Obtengo la cadena vertical
        for(int j=0; j < dna.length; j++){
            for(int i=0; i < dna.length; i++){
                sb.append(dna[i][j]);
            }
        }

        String vertical = sb.toString();


        String [] splitResult = horizontal.split(PATRON_A);
        String [] splitVertical = horizontal.split(PATRON_G);


        //Pattern.compile(PATTERN).
       // vertical.
        return false;
    }





}
