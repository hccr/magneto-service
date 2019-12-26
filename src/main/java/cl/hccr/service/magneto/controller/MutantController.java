package cl.hccr.service.magneto.controller;

import cl.hccr.service.magneto.domain.MutantRequest;
import cl.hccr.service.magneto.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MutantController {
    private MutantService mutantService;

    @Autowired
    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("mutant")
    public ResponseEntity postMutant(@RequestBody MutantRequest mutantRequest){

        if(mutantService.isMutant(mutantRequest))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
