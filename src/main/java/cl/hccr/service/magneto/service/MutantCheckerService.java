package cl.hccr.service.magneto.service;

import cl.hccr.service.magneto.domain.MutantRequest;

public interface MutantCheckerService {
    boolean isMutant(MutantRequest mutantRequest);
}
