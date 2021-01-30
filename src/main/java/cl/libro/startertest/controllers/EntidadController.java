package cl.libro.startertest.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.libro.startertest.services.GeneradorPdf;
import lombok.Cleanup;

@RestController
public class EntidadController {
	
	@Autowired
	private GeneradorPdf mineducGeneradorPdfHelper;
	
	@PostMapping(value = "/pdf/generar/v2", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generarPdf(@RequestBody Object resultadoCalculo) throws IOException {
		 Map<String, Object> map = new HashMap<>();
	        map.put("parametros", resultadoCalculo);
		
		@Cleanup InputStream inputStream = mineducGeneradorPdfHelper.generarPdf("templates/certificadoAsignacionRecursos.html",map,"/templates");
		inputStream.close();
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("Content-Disposition", "attachment; filename=starter-test.pdf")
				.body(new InputStreamResource(inputStream));
	}
	
	   @PostMapping(value = "/pdf/generar", produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> generarPdf(@RequestBody RequestDTO requestDTO) throws IOException {
	        @Cleanup InputStream inputStream = mineducGeneradorPdfHelper.generarPdf("templates/convenio-pie.html",requestDTO.getParametros(),"/templates");
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .header("Content-Disposition", "attachment; filename=starter-test.pdf")
	                .body(new InputStreamResource(inputStream));
	    }

}