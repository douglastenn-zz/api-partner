package com.douglastenn.partner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Partner not found")
public class PartnerNotFoundException extends RuntimeException {

}
