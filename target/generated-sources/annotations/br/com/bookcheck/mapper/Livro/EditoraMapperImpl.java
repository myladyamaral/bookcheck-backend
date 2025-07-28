package br.com.bookcheck.mapper.Livro;

import br.com.bookcheck.controller.dto.request.Livro.EditoraRequestDto;
import br.com.bookcheck.controller.dto.response.Livro.EditoraResponseDto;
import br.com.bookcheck.domain.entity.Livro.Editora;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T12:16:55-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EditoraMapperImpl implements EditoraMapper {

    @Override
    public Editora toEntity(EditoraRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Editora.EditoraBuilder<?, ?> editora = Editora.builder();

        editora.cnpj( dto.getCnpj() );

        return editora.build();
    }

    @Override
    public EditoraResponseDto toResponseDto(Editora entity) {
        if ( entity == null ) {
            return null;
        }

        EditoraResponseDto.EditoraResponseDtoBuilder editoraResponseDto = EditoraResponseDto.builder();

        editoraResponseDto.cnpj( entity.getCnpj() );
        editoraResponseDto.id( entity.getId() );
        editoraResponseDto.nome( entity.getNome() );

        return editoraResponseDto.build();
    }
}
