package br.com.artcentral.mvc.art.bacoffice.rest.api.v1;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.artcentral.mvc.art.constants.ArtStatus;
import br.com.artcentral.mvc.art.dto.ArtPayload;
import br.com.artcentral.mvc.art.dto.MyArtResponse;
import br.com.artcentral.mvc.art.service.ArtService;

@RestController
@RequestMapping("/api/v1/art")
public class ArtRestController {
	/** Dependências */
	@Autowired
	private ArtService artService;

	/** Constantes */
	private static final HttpStatus CREATED    = HttpStatus.CREATED;
	private static final HttpStatus OK         = HttpStatus.OK;
	private static final HttpStatus NO_CONTENT = HttpStatus.NO_CONTENT;
	
	/** Endpoints */
	@PostMapping("/create")
	public ResponseEntity<MyArtResponse> doCreateAnArt(@RequestBody ArtPayload payload) {
		return ResponseEntity.status(CREATED).body(artService.doCreate(payload));
	}
	
	@GetMapping("/show/{artId}")
	public ResponseEntity<MyArtResponse> getMyArtById(@PathVariable(name = "artId") UUID artId) {
		return ResponseEntity.status(OK).body(artService.detailsById(artId));
	}
	
	@PatchMapping("/change-status/{artId}")
	public ResponseEntity<Void> updateArtStatus(@PathVariable(name = "artId") UUID artId, @RequestParam(required=true) ArtStatus status) {
		artService.atualizaStatus(status, artId);
		return ResponseEntity.status(NO_CONTENT).build();
	}

}
