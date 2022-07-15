package hashkart.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController {
	@GetMapping("/api")
	public ResponseEntity<String> ping() {
		return new ResponseEntity<String>("In Api Gateway Service.", HttpStatus.OK);
	}
}