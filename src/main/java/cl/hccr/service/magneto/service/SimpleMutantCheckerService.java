package cl.hccr.service.magneto.service;

import cl.hccr.service.magneto.domain.MutantRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleMutantCheckerService implements MutantCheckerService {
    private final int PATTERN_LENGTH = 4;


    @Override
    public boolean isMutant(MutantRequest mutantRequest) {
        return false;
    }

    public String[][] generateMatrix(String[] dna) {
        String[][] matrix = new String[dna.length][dna.length];
        for(int i = 0; i<dna.length; i++){
            matrix[i] = dna[i].split("");
        }
        return matrix;
    }




    public String[] getVerticalStringArray(String[] dna) {
        String[][] matrix = generateMatrix(dna);
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

    public String[] getBackwardDiagonalArray(String[] dna, int patternLength) {

        String[][] matrix = generateMatrix(dna);

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


    public String[] getForwardDiagonalArray(String[] dna, int patternLength) {

        String[][] matrix = generateMatrix(dna);

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
