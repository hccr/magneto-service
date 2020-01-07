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


    //Implementacion del método isMutant
    /*
        Para implementar el metodo isMutant utilizaré el método indexOf de la clase String para la
        busqueda del patrón.

        Para poder buscar en todas las direcciones deberé obtener arreglos de String que representen cada una
        de esas direcciones.
        Un arreglo horizontal (Tal cual como viene en la peticion
        Un arreglo vertical
        Un arreglo diagonal hacia adelante /
        Un arreglo diagonal hacia atras \

     */
    @Override
    public boolean isMutant(String[] dna) {

        //Primero obtenemos una matriz NxN desde el arreglo de Strings dna
        //Esta sera la base para obtener los arreglos
        String[][] matrix = generateMatrix(dna);

        String[] horizontalArray = dna;
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


/*
      Este método verifica si existe algun patron en un arreglo, independiente de si es horizontal, vertical o diagonales
      Utiliza el metodo String.indexOf() para encontrar la ubicación de cada uno de los patrones
      Un String puede contener más de un patron, eso sucede cuando el largo del String es mayor o igual que 2 veces el largo del patron
 */
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


    /*

    Este metodo genera la matriz base para poder generar los demas arreglos.
    Tambien se utiliza para lanzar las Excepciones en caso de que no cumpla con los supuestos.
     */

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

    //Obtiene el arreglo vertical recorriendo la matriz verticalmente
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

    /*
    Obtiene el arreglo diagonal hacia atras recorriendo la matriz de manera diagonal considerando el largo del patron
    supongamos la siguiente matriz y un patron de largo 3, por ejemplo AAA
     A A A A
     B B B B
     C C C C
     D D D D

     Este metodo nos devolvera
     ["BCD",
     "ABCD"
     "ABD"]

    */
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

     /*
    Obtiene el arreglo diagonal hacia adelante recorriendo la matriz de manera diagonal considerando el largo del patron
    supongamos la siguiente matriz y un patron de largo 3, por ejemplo AAA
     A A A A
     B B B B
     C C C C
     D D D D

     Este metodo nos devolvera
     ["CBA",
     "DCBA"
     "DCB"]

    */

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
