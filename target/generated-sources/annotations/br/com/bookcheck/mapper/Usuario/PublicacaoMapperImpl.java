package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.PublicacaoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.ComentarioResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Comentario;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:31:17-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PublicacaoMapperImpl implements PublicacaoMapper {

    @Override
    public Publicacao toEntity(PublicacaoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Publicacao.PublicacaoBuilder<?, ?> publicacao = Publicacao.builder();

        publicacao.dataHora( dto.getDataHora() );
        publicacao.texto( dto.getTexto() );

        return publicacao.build();
    }

    @Override
    public PublicacaoResponseDto toResponseDto(Publicacao entity) {
        if ( entity == null ) {
            return null;
        }

        PublicacaoResponseDto.PublicacaoResponseDtoBuilder publicacaoResponseDto = PublicacaoResponseDto.builder();

        publicacaoResponseDto.comentarios( comentarioListToComentarioResponseDtoList( entity.getComentarios() ) );
        publicacaoResponseDto.dataHora( entity.getDataHora() );
        publicacaoResponseDto.id( entity.getId() );
        publicacaoResponseDto.texto( entity.getTexto() );

        return publicacaoResponseDto.build();
    }

    @Override
    public List<PublicacaoResponseDto> toResponseDto(List<Publicacao> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PublicacaoResponseDto> list = new ArrayList<PublicacaoResponseDto>( entities.size() );
        for ( Publicacao publicacao : entities ) {
            list.add( toResponseDto( publicacao ) );
        }

        return list;
    }

    protected ComentarioResponseDto comentarioToComentarioResponseDto(Comentario comentario) {
        if ( comentario == null ) {
            return null;
        }

        ComentarioResponseDto.ComentarioResponseDtoBuilder comentarioResponseDto = ComentarioResponseDto.builder();

        comentarioResponseDto.dataHora( comentario.getDataHora() );
        comentarioResponseDto.id( comentario.getId() );
        comentarioResponseDto.texto( comentario.getTexto() );

        return comentarioResponseDto.build();
    }

    protected List<ComentarioResponseDto> comentarioListToComentarioResponseDtoList(List<Comentario> list) {
        if ( list == null ) {
            return null;
        }

        List<ComentarioResponseDto> list1 = new ArrayList<ComentarioResponseDto>( list.size() );
        for ( Comentario comentario : list ) {
            list1.add( comentarioToComentarioResponseDto( comentario ) );
        }

        return list1;
    }
}
