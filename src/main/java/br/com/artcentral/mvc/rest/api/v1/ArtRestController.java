package br.com.artcentral.mvc.rest.api.v1;

import java.util.List;
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

import br.com.artcentral.mvc.backoffice.art.constants.ArtStatus;
import br.com.artcentral.mvc.backoffice.art.dto.ArtPayload;
import br.com.artcentral.mvc.backoffice.art.dto.MyArtResponse;
import br.com.artcentral.mvc.backoffice.art.service.ArtService;
import br.com.artcentral.mvc.backoffice.artimageproduct.service.ArtImageProductService;
import br.com.artcentral.mvc.backoffice.artimagereference.dto.ArtImageReferenceResponse;
import br.com.artcentral.mvc.backoffice.artimagereference.service.ArtImageReferenceService;

@RestController
@RequestMapping("/api/v1/art")
public class ArtRestController {
	/** Dependências */
	@Autowired
	private ArtService artService;
	@Autowired
	private ArtImageReferenceService artImageRefService;
	@Autowired
	private ArtImageProductService artImageProdService;

	/** Constantes */
	private static final HttpStatus CREATED    = HttpStatus.CREATED;
	private static final HttpStatus OK         = HttpStatus.OK;
	private static final HttpStatus NO_CONTENT = HttpStatus.NO_CONTENT;
	
	/** Endpoints */
	@PostMapping("/create")
	public ResponseEntity<MyArtResponse> doCreateAnArt(@RequestBody ArtPayload payload) {
		return ResponseEntity.status(CREATED).body(artService.doCreate(payload));
	}
	
	@GetMapping("/{artId}/show")
	public ResponseEntity<MyArtResponse> getMyArtById(@PathVariable(name = "artId") UUID artId) {
		return ResponseEntity.status(OK).body(artService.detailsById(artId));
	}
	
	@PatchMapping("/{artId}/change-status")
	public ResponseEntity<Void> updateArtStatus(@PathVariable(name = "artId") UUID artId, @RequestParam(required=true) ArtStatus status) {
		artService.atualizaStatus(status, artId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	
	/** Endpoints que acessam recursos filhos */
	
	/** Referências de imagens */
	@GetMapping("/image-ref/{refId}")
	public ResponseEntity<byte[]> getArtImageReferenceBytes(@PathVariable(name = "refId") UUID refId) {
		return ResponseEntity.status(OK).body(artImageRefService.getImageReferenceBytes(refId));
	}

	@GetMapping("/{artId}/all-image-ref")
	public ResponseEntity<List<ArtImageReferenceResponse>> getAllArtImageReferencesOfSpecifyArt(@PathVariable(name = "artId") UUID artId) {
		return ResponseEntity.status(OK).body(artImageRefService.getAllArtImageReferencesOfSpecifyArt(artId));
	}
	
	/** Imagem produto */
	@GetMapping("/image-prod/{prodId}")
	public ResponseEntity<byte[]> getArtImageProdutBytes(@PathVariable(name = "prodId") UUID prodId) {
		return ResponseEntity.status(OK).body(null);
	}
}
