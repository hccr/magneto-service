package cl.hccr.service.magneto.service;

import cl.hccr.service.magneto.domain.MutantRequest;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantService {
    @Override
    public boolean isMutant(MutantRequest mutantRequest) {
        return false;
    }
}
