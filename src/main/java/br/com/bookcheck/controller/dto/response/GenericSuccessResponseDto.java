package br.com.bookcheck.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GenericSuccessResponseDto<T> {

    private String message;

    @JsonIgnore
    private String objectName;

    @JsonIgnore // Para evitar que o Jackson serialize o campo diretamente
    private T data;

    public GenericSuccessResponseDto(String message, String objectName, T data) {
        this.message = message;
        this.objectName = objectName;
        this.data = data;
    }

    // Retorna um mapa onde a chave é a classe do objeto e o valor é o objeto em si
    @JsonAnyGetter
    public Map<String, Object> getData() {
        Map<String, Object> response = new HashMap<>();
        response.put(objectName, data);
        return response;
    }
}