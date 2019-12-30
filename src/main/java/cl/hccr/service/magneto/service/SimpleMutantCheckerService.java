package cl.hccr.service.magneto.service;

import cl.hccr.service.magneto.domain.DnaSizeException;
import cl.hccr.service.magneto.domain.MutantRequest;
import cl.hccr.service.magneto.domain.NotAllowedCharException;
import cl.hccr.service.magneto.domain.NotNxNDnaFormatException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class SimpleMutantCheckerService implements MutantCheckerService {
    private final static String PATRON_A = "AAAA";
    private final static String PATRON_C = "CCCC";
    private final static String PATRON_G = "GGGG";
    private final static String PATRON_T = "TTTT";


    private final Pattern pattern = Pattern.compile("[ACGT]*");
    // in case you would like to ignore case sensitivity,
    // you could use this statement:
    // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);




    @Override
    public boolean isMutant(MutantRequest mutantRequest) {


        String[][] matrix = generateMatrix(mutantRequest.getDna());

        String[] horizontalArray = mutantRequest.getDna();
        String[] verticalArray = getVerticalStringArray(matrix);
        String[] forwardDiagonalArray = getForwardDiagonalArray(matrix, PATRON_A.length());
        String[] backwardDiagonalArray = getBackwardDiagonalArray(matrix, PATRON_A.length());

        int cuentaPatrones =
                 checkPattern(horizontalArray) +
                 checkPattern(verticalArray) +
                 checkPattern(forwardDiagonalArray) +
                 checkPattern(backwardDiagonalArray) ;

        return cuentaPatrones > 1;
    }



    private int checkPattern(String[] dnaArray){
        int cuentaPatrones = 0;
        for (String dnaPart : dnaArray) {
            if(dnaPart.length() >=  PATRON_A.length() * 2 ){
                //Puede contener multiples patrones por linea

                int indexOf;
                int offset = 0;
                while((indexOf = dnaPart.indexOf(PATRON_A, offset))>-1){
                    cuentaPatrones++;
                    offset = indexOf + PATRON_A.length();
                }


                offset = 0;
                while((indexOf = dnaPart.indexOf(PATRON_C, offset))>-1){
                    cuentaPatrones++;
                    offset = indexOf + PATRON_C.length();
                }

                offset = 0;
                while((indexOf = dnaPart.indexOf(PATRON_G, offset))>-1){
                    cuentaPatrones++;
                    offset = indexOf + PATRON_G.length();
                }

                offset = 0;
                while((indexOf = dnaPart.indexOf(PATRON_T, offset))>-1){
                    cuentaPatrones++;
                    offset = indexOf + PATRON_T.length();
                }

            }else{
                if(dnaPart.contains(PATRON_A)){
                    cuentaPatrones++;
                }
                if(dnaPart.contains(PATRON_C)){
                    cuentaPatrones++;
                }
                if(dnaPart.contains(PATRON_G)){
                    cuentaPatrones++;
                }
                if(dnaPart.contains(PATRON_T)){
                    cuentaPatrones++;
                }
            }
        }
        return cuentaPatrones;
    }



    public String[][] generateMatrix(String[] dna) {

        if(dna.length>64 || dna.length<4){
            throw new DnaSizeException();
        }

        String[][] matrix = new String[dna.length][dna.length];
        for(int i = 0; i<dna.length; i++){
            if(!pattern.matcher(dna[i]).matches()){
                throw new NotAllowedCharException();
            }

            matrix[i] = dna[i].split("");

            if(matrix[i].length!=dna.length){
                throw new NotNxNDnaFormatException();
            }
        }
        return matrix;
    }

    public String[] getVerticalStringArray(String[][] matrix) {
        String[] result = new String[matrix.length];
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < matrix.length;j++){
            for (int i = 0; i < matrix.length;i++){
                sb.append(matrix[i][j]);
            }
            result[j]=sb.toString();
            sb.delete(0,sb.length());
        }
        return result;


    }

    public String[] getBackwardDiagonalArray(String[][] matrix, int patternLength) {


        int rowOffset = matrix.length-patternLength;
        int columnLimit = matrix.length-patternLength + 1;
        int columnOffset = 0;

        StringBuilder sb = new StringBuilder();
        List<String> arrayList = new ArrayList<>();

        while(rowOffset>=0 && columnOffset < columnLimit){
            int i = rowOffset;
            int j = columnOffset;

            while (i<matrix.length && j<matrix.length){
                sb.append(matrix[i][j]);
                i++;
                j++;
            }

            arrayList.add(sb.toString());
            sb.delete(0,sb.length());

            if(rowOffset > 0){
                rowOffset--;
            }else{
                columnOffset++;
            }
        }

        String[] itemsArray = new String[arrayList.size()];
        return arrayList.toArray(itemsArray);
    }


    public String[] getForwardDiagonalArray(String[][] matrix, int patternLength) {


        int rowOffset = matrix.length - patternLength + 1;
        int columnOffset = 0;
        int columnLimit = matrix.length - patternLength + 1;



        StringBuilder sb = new StringBuilder();
        List<String> arrayList = new ArrayList<>();

        while(rowOffset < matrix.length && columnOffset < columnLimit ){
            int i = rowOffset;
            int j = columnOffset;

            while (i>=0 && j<matrix.length){
                sb.append(matrix[i][j]);
                i--;
                j++;
            }

            arrayList.add(sb.toString());
            sb.delete(0,sb.length());

            if(rowOffset < matrix.length-1){
                rowOffset++;
            }else{
                columnOffset++;
            }

        }

        String[] itemsArray = new String[arrayList.size()];
        return arrayList.toArray(itemsArray);
    }
}
