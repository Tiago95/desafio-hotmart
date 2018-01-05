package br.com.hotmart.desafiohotmart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.hotmart.desafiohotmart.builder.MessageBuilder;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controllador responsáveis por controllar as exceções da aplicação.
 * 
 * @author Tiago
 *
 */
@ControllerAdvice
public class ExceptionHandlingController {

	/**
	 * Responsável por manipular as exceções do bean validation.
	 * 
	 * @param methodArgumentNotValidException
	 * @return
	 */
	@ApiOperation(value = "Tratamento Bean Validation", nickname = "invalidInput", notes="Responsável tratar as mensagens de exceções disparadas pelo Bean Validation e devolver no formato padrão da aplicação ResponseVo<E>.")
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)
	})
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseVO<?>> invalidInput(MethodArgumentNotValidException methodArgumentNotValidException) {

		MessageBuilder<?> messageBuilder = new MessageBuilder<>();
		
		if(methodArgumentNotValidException != null){
			
			BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
			
			bindingResult.getAllErrors().forEach(error -> {
				
				if(error instanceof FieldError){
					
					messageBuilder.addErroField(((FieldError) error).getField(), error.getDefaultMessage());
					
				}
				
			});
			
		}
		
        return new ResponseEntity<>(messageBuilder.build(), HttpStatus.OK);
    }
	
}
