package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.MensagemRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.MensagemResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Mensagem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:06:07-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class MensagemMapperImpl implements MensagemMapper {

    @Override
    public Mensagem toEntity(MensagemRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Mensagem.MensagemBuilder<?, ?> mensagem = Mensagem.builder();

        mensagem.dataHora( dto.getDataHora() );
        mensagem.texto( dto.getTexto() );

        return mensagem.build();
    }

    @Override
    public MensagemResponseDto toResponseDto(Mensagem entity) {
        if ( entity == null ) {
            return null;
        }

        MensagemResponseDto mensagemResponseDto = new MensagemResponseDto();

        mensagemResponseDto.setDataHora( entity.getDataHora() );
        mensagemResponseDto.setTexto( entity.getTexto() );

        return mensagemResponseDto;
    }
}
