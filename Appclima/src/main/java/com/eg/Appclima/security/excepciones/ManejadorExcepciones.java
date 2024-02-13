package com.eg.Appclima.security.excepciones;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.access.AccessDeniedException;
import java.security.SignatureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Anotación de Spring para el manejo de excepciones globales
public class ManejadorExcepciones {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {

        ProblemDetail pd = null;
        final String propiedad = "Error: ";

        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            pd.setProperty(propiedad, "Nombre de usuario o contraseña es incorrecto.");

            return pd;
        }

        if (exception instanceof AccountStatusException) {
            pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            pd.setProperty(propiedad, "Cuenta no habilitada.");
        }

        if (exception instanceof AccessDeniedException) {
            pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            pd.setProperty(propiedad, "Acceso denegado, no puedes acceder al recurso.");
        }

        if (exception instanceof SignatureException) {
            pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            pd.setProperty(propiedad, "La firma del JWT no es válida.");
        }

        if (exception instanceof ExpiredJwtException) {
            pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            pd.setProperty(propiedad, "Token ha expirado.");
        }

        if (pd == null) {
            pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            pd.setProperty(propiedad, "Error desconocido.");
        }

        return pd;
    }

}
