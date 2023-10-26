package com.kh.product.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SaveForm {
  @NotBlank
  private String pname;

  @NotNull
  @Positive
  private  Long quantity;

  @NotNull
  @Positive
  private Long price;
}
