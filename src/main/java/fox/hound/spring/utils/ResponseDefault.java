package fox.hound.spring.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.HttpResponse;

@Component
public class ResponseDefault {
		
	public static int SINGULAR = 0;
	public static int PLURAL = 1;
	
	
	/**
	 * 	Transformamos el JSON (Title)
	 * 		{
	 * 			"nombre": "Jose Duin"
	 * 			"edad": 25
	 * 		}
	 *  A
	 *   	{
	 *   		"usuario": {
	 * 				"nombre": "Jose Duin"
	 * 				"edad": 25
	 * 			}
	 * 		}
	 * 
	 *	@param body: cuerpo del json
	 *	@param classModel: model donde obtendremos el singular y plural definido
	 *	@param tipo: { SINGULAR, PLURAL }
	 *	@return respuesta HTTP - json format 
	 **/
	public static ResponseEntity<?> ok(Object body, Class<?> classModel, int tipo) {
		Map<String, Object> result = new HashMap<>();
		if (tipo == SINGULAR) {
			result.put(classModel.getAnnotation(CustomJsonRootName.class).singular(), body);	
		} else {
			result.put(classModel.getAnnotation(CustomJsonRootName.class).plural(), body);			
		}
//		if (body == null) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		return ResponseEntity.ok(result);
	}
	
	public static ResponseEntity<?> message(String messageUtil, String model) {
		return new ResponseEntity<>(new HttpResponse(String.format(messageUtil, model)), HttpStatus.OK);
	}
	
}
