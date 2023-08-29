package com.sejour.reservationsejour.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ErrorMesageResponse implements Serializable {

    private String errorMessage;
    private Date date;


}
