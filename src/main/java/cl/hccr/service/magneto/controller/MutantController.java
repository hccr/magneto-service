package cl.hccr.service.magneto.controller;

import cl.hccr.service.magneto.domain.MutantRequest;
import cl.hccr.service.magneto.service.MutantCheckerService;
import cl.hccr.service.magneto.service.QueueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MutantController {

    private MutantCheckerService mutantCheckerService;
    private QueueService queueService;

    public MutantController(MutantCheckerService mutantCheckerService, QueueService queueService) {
        this.mutantCheckerService = mutantCheckerService;
        this.queueService = queueService;
    }


    @PostMapping("mutant")
    public ResponseEntity postMutant(@RequestBody MutantRequest mutantRequest){

        //Verifica si es mutante, si lo es envía respuesta 200 y envia mensaje a QueueService
        //Si no es mutante responde con 403 y envia mensaje a QueueService
        if(mutantCheckerService.isMutant(mutantRequest)){
            mutantRequest.setMutant(true);
            queueService.queueMutantRequest(mutantRequest);
            return ResponseEntity.ok().build();
        }else {
            mutantRequest.setMutant(false);
            queueService.queueMutantRequest(mutantRequest);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
