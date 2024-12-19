package com.test_technique.backend.exception;

import com.test_technique.backend.dto.ReponseHttp;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GestionGlobalException {

    private static final Logger LOG = LoggerFactory.getLogger(GestionGlobalException.class);

    @ExceptionHandler(ResourceNotFound.class) // Erreur interne
    public ResponseEntity<ReponseHttp> resourceNotFound(final HttpServletRequest request, ResourceNotFound ex) {
        LOG.error(ex.getMessage());
        ex.printStackTrace();
        return createHttpResponse(request, NOT_FOUND, ex.getMessage(), ex);
    }


    @ExceptionHandler(Exception.class) // Erreur interne
    public ResponseEntity<ReponseHttp> exception(final HttpServletRequest request, Exception ex) {
        LOG.error(ex.getMessage());
        ex.printStackTrace();
        return createHttpResponse(request, INTERNAL_SERVER_ERROR,
                "Une erreur est survenue. Regarder les logs pour plus de déatil.", ex);
    }


    /**
     * Construit la réponse ReponseHttp à retourner
     */
    private ResponseEntity<ReponseHttp> createHttpResponse (
            HttpServletRequest request,
            HttpStatus httpStatus,
            String message,
            Exception exception) {

        ReponseHttp httpCustomResponse = new ReponseHttp();
        httpCustomResponse.setResourceUrl(request.getRequestURI());
        httpCustomResponse.setHttpStatus(httpStatus);
        httpCustomResponse.setHttpStatusCode(httpStatus.value());
        httpCustomResponse.setMessage(message);
        if (exception != null) {
            String devMessage =
                    " Message: " + exception.getMessage() +
                            " || ExceptionClassName: " + exception.getClass().getName();
            httpCustomResponse.setLogs(devMessage.trim());
        }
        return new ResponseEntity<>(httpCustomResponse, httpStatus);
    }

}
