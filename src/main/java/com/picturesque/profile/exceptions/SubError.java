package com.picturesque.profile.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

abstract class SubError {}

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ValidationError extends SubError {
  private String object;
  private String field;
  private Object rejectedValue;
  private String message;

  ValidationError(String object, String message) {
    this.object = object;
    this.message = message;
  }

  ValidationError(String object, String message, String field, Object rejectedValue) {
    this.object = object;
    this.message = message;
    this.rejectedValue = rejectedValue;
    this.field = field;
  }
}
