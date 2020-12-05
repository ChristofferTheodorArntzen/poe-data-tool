package com.carnnjoh.poedatatool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Stash not found")
public class StashNotFoundException extends RuntimeException { }