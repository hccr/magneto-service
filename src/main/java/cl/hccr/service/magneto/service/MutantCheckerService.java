package cl.hccr.service.magneto.service;

import cl.hccr.service.magneto.domain.MutantRequest;

public interface MutantCheckerService {
    boolean isMutant(MutantRequest mutantRequest);

    String horizontalOrder(String [][] matrix);
    String verticlOrder(String [][] matrix);
    String topLeftToBottomRightOrder(String [][] matrix);
    String topRightToBottomLeftOrder(String [][] matrix);
}
