package cl.hccr.service.magneto.controller;

import cl.hccr.service.magneto.domain.MutantRequest;
import cl.hccr.service.magneto.service.MutantCheckerService;
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
    /*
    private MutantCheckerService mutantCheckerService;


    public MutantController(MutantCheckerService mutantCheckerService) {
        this.mutantCheckerService = mutantCheckerService;
    }
    */

    @PostMapping("mutant")
    public ResponseEntity postMutant(@RequestBody MutantRequest mutantRequest){

        /*

        if(mutantCheckerService.isMutant(mutantRequest))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            */

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
