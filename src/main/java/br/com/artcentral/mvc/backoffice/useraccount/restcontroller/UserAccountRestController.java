package br.com.artcentral.mvc.backoffice.useraccount.restcontroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.artcentral.mvc.backoffice.useraccount.dto.UserAccountPayload;
import br.com.artcentral.mvc.backoffice.useraccount.dto.UserAccountResponse;
import br.com.artcentral.mvc.backoffice.useraccount.service.UserAccountService;

@RestController
@RequestMapping("/api/v1/useraccount")
public class UserAccountRestController {
	@Autowired
	private UserAccountService userAccountService;
	
	@PostMapping("/signup")
	public ResponseEntity<UserAccountResponse> doCreate(@RequestBody UserAccountPayload payload) {
		UserAccountResponse response = userAccountService.doCreate(payload);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("{accountId}/details")
	public ResponseEntity<UserAccountResponse> doCreate(@PathVariable UUID accountId) {
		UserAccountResponse response = userAccountService.detailsById(accountId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
