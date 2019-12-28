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

    /*
    //Genera una matriz a partir del array de ADN
    @Override
    public String[][] generateMatrix(String[] dna) {
        String[][] matrix = new String[dna.length][dna.length];
        for(int i = 0; i<dna.length; i++){
            matrix[i] = dna[i].split("");
        }
        return matrix;
    }

    @Override
    public String getHorizontalOrderString(String[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length;i++){
            for(int j = 0; j < matrix.length;j++){
                sb.append(matrix[i][j]);
            }
        }
        return sb.toString();
    }

    @Override
    public String getVerticlOrderString(String[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < matrix.length;j++){
            for (int i = 0; i < matrix.length;i++){
                sb.append(matrix[i][j]);
            }
        }
        return sb.toString();
    }

    @Override
    public String getTopLeftToBottomRightOrderString(String[][] matrix) {
        return null;
    }

    @Override
    public String getTopRightToBottomLeftOrderString(String[][] matrix) {
        return null;
    }
    */
}
