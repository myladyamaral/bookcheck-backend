
```
bookcheck-backend
├─ .docker
│  └─ init.sql
├─ .idea
│  ├─ compiler.xml
│  ├─ encodings.xml
│  ├─ jarRepositories.xml
│  ├─ misc.xml
│  └─ vcs.xml
├─ docker-compose.yml
├─ Dockerfile
├─ HELP.md
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
├─ README.md
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ br
   │  │     └─ com
   │  │        └─ bookcheck
   │  │           ├─ BookcheckApplication.java
   │  │           ├─ component
   │  │           │  └─ SecurityTokenComponent.java
   │  │           ├─ config
   │  │           │  ├─ FlywayConfig.java
   │  │           │  ├─ JwtConfigProperties.java
   │  │           │  ├─ OpenApiConfig.java
   │  │           │  ├─ SpringSecurityConfig.java
   │  │           │  └─ WebConfig.java
   │  │           ├─ controller
   │  │           │  ├─ AuthController.java
   │  │           │  ├─ BibliotecaController.java
   │  │           │  ├─ CatalogoController.java
   │  │           │  ├─ ComentarioController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ request
   │  │           │  │  │  ├─ AuthenticationRequestDto.java
   │  │           │  │  │  ├─ Leitor
   │  │           │  │  │  │  ├─ BibliotecaRequestDto.java
   │  │           │  │  │  │  ├─ BibliotecaUpdateRequestDto.java
   │  │           │  │  │  │  └─ LivroDesejadoRequestDto.java
   │  │           │  │  │  ├─ Livro
   │  │           │  │  │  │  ├─ AutorRequestDto.java
   │  │           │  │  │  │  ├─ EditoraRequestDto.java
   │  │           │  │  │  │  └─ LivroRequestDto.java
   │  │           │  │  │  ├─ Sebo
   │  │           │  │  │  │  ├─ CatalogoRequestDto.java
   │  │           │  │  │  │  └─ CatalogoUpdateRequestDto.java
   │  │           │  │  │  └─ Usuario
   │  │           │  │  │     ├─ ComentarioRequestDto.java
   │  │           │  │  │     ├─ CurtidaRequestDto.java
   │  │           │  │  │     ├─ EnderecoRequestDto.java
   │  │           │  │  │     ├─ MensagemRequestDto.java
   │  │           │  │  │     ├─ PublicacaoRequestDto.java
   │  │           │  │  │     ├─ UsuarioLeitorRequestDto.java
   │  │           │  │  │     ├─ UsuarioLeitorUpdateRequestDto.java
   │  │           │  │  │     ├─ UsuarioResquestDto.java
   │  │           │  │  │     └─ UsuarioSeboRequestDto.java
   │  │           │  │  └─ response
   │  │           │  │     ├─ AuthenticationResponseDto.java
   │  │           │  │     ├─ GenericSuccessResponseDto.java
   │  │           │  │     ├─ Leitor
   │  │           │  │     │  ├─ BibliotecaResponseDto.java
   │  │           │  │     │  └─ LivroDesejadoResponseDto.java
   │  │           │  │     ├─ Livro
   │  │           │  │     │  ├─ AutorResponseDto.java
   │  │           │  │     │  ├─ EditoraResponseDto.java
   │  │           │  │     │  └─ LivroResponseDto.java
   │  │           │  │     ├─ Sebo
   │  │           │  │     │  └─ CatalogoResponseDto.java
   │  │           │  │     └─ Usuario
   │  │           │  │        ├─ ComentarioResponseDto.java
   │  │           │  │        ├─ CurtidaResponseDto.java
   │  │           │  │        ├─ EnderecoResponseDto.java
   │  │           │  │        ├─ MensagemResponseDto.java
   │  │           │  │        ├─ PublicacaoResponseDto.java
   │  │           │  │        ├─ UsuarioLeitorResponseDto.java
   │  │           │  │        ├─ UsuarioResponseDto.java
   │  │           │  │        ├─ UsuarioResumeResponseDto.java
   │  │           │  │        └─ UsuarioSeboResponseDto.java
   │  │           │  ├─ LivroDesejadoController.java
   │  │           │  ├─ MensagemController.java
   │  │           │  ├─ PublicacaoController.java
   │  │           │  ├─ UsuarioController.java
   │  │           │  ├─ UsuarioLeitorController.java
   │  │           │  └─ UsuarioSeboController.java
   │  │           ├─ domain
   │  │           │  ├─ entity
   │  │           │  │  ├─ Leitor
   │  │           │  │  │  ├─ Biblioteca.java
   │  │           │  │  │  └─ LivroDesejado.java
   │  │           │  │  ├─ Livro
   │  │           │  │  │  ├─ Autor.java
   │  │           │  │  │  ├─ Editora.java
   │  │           │  │  │  └─ Livro.java
   │  │           │  │  ├─ Sebo
   │  │           │  │  │  └─ Catalogo.java
   │  │           │  │  └─ Usuario
   │  │           │  │     ├─ Comentario.java
   │  │           │  │     ├─ Curtida.java
   │  │           │  │     ├─ Endereco.java
   │  │           │  │     ├─ Mensagem.java
   │  │           │  │     ├─ Publicacao.java
   │  │           │  │     ├─ Usuario.java
   │  │           │  │     ├─ UsuarioLeitor.java
   │  │           │  │     └─ UsuarioSebo.java
   │  │           │  ├─ enums
   │  │           │  │  ├─ converter
   │  │           │  │  │  ├─ BooleanConverter.java
   │  │           │  │  │  ├─ DisponibilidadeCatalogoConverter.java
   │  │           │  │  │  ├─ EstadoConservacaoConverter.java
   │  │           │  │  │  ├─ GenericConverter.java
   │  │           │  │  │  ├─ GeneroLiterarioConverter.java
   │  │           │  │  │  ├─ StatusLeituraConverter.java
   │  │           │  │  │  └─ TipoUsuarioConverter.java
   │  │           │  │  ├─ DisponibilidadeCatalogoEnum.java
   │  │           │  │  ├─ EstadoConservacaoEnum.java
   │  │           │  │  ├─ GeneroLiterarioEnum.java
   │  │           │  │  ├─ StatusLeituraEnum.java
   │  │           │  │  └─ TipoUsuarioEnum.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ AutorRepository.java
   │  │           │  │  ├─ BibliotecaRepository.java
   │  │           │  │  ├─ CatalogoRepository.java
   │  │           │  │  ├─ ComentarioRepository.java
   │  │           │  │  ├─ CurtidaRepository.java
   │  │           │  │  ├─ EditoraRepository.java
   │  │           │  │  ├─ EnderecoRepository.java
   │  │           │  │  ├─ LivroDesejadoRepository.java
   │  │           │  │  ├─ LivroRepository.java
   │  │           │  │  ├─ MensagemRepository.java
   │  │           │  │  ├─ PublicacaoRepository.java
   │  │           │  │  ├─ UsuarioLeitorRepository.java
   │  │           │  │  ├─ UsuarioRepository.java
   │  │           │  │  └─ UsuarioSeboRepository.java
   │  │           │  └─ view
   │  │           │     └─ UsuarioDetailView.java
   │  │           ├─ exception
   │  │           │  ├─ ControllerExceptionHandler.java
   │  │           │  ├─ EntityNotFoundException.java
   │  │           │  ├─ FieldMessage.java
   │  │           │  ├─ ServiceBusinessException.java
   │  │           │  ├─ StandardError.java
   │  │           │  ├─ UnauthorizedException.java
   │  │           │  └─ ValidationError.java
   │  │           ├─ filter
   │  │           │  └─ JwtRequestFilter.java
   │  │           ├─ mapper
   │  │           │  ├─ Leitor
   │  │           │  │  ├─ BibliotecaMapper.java
   │  │           │  │  ├─ BibliotecaMapperResolver.java
   │  │           │  │  ├─ LivroDesejadoMapper.java
   │  │           │  │  └─ LivroDesejadoMapperResolver.java
   │  │           │  ├─ Livro
   │  │           │  │  ├─ AutorMapper.java
   │  │           │  │  ├─ AutorMapperResolver.java
   │  │           │  │  ├─ EditoraMapper.java
   │  │           │  │  ├─ EditoraMapperResolver.java
   │  │           │  │  ├─ LivroMapper.java
   │  │           │  │  └─ LivroMapperResolver.java
   │  │           │  ├─ Sebo
   │  │           │  │  ├─ CatalogoMapper.java
   │  │           │  │  └─ CatalogoMapperResolver.java
   │  │           │  └─ Usuario
   │  │           │     ├─ ComentarioMapper.java
   │  │           │     ├─ ComentarioMapperResolver.java
   │  │           │     ├─ CurtidaMapper.java
   │  │           │     ├─ CurtidaMapperResolver.java
   │  │           │     ├─ EnderecoMapper.java
   │  │           │     ├─ EnderecoMapperResolver.java
   │  │           │     ├─ MensagemMapper.java
   │  │           │     ├─ MensagemMapperResolver.java
   │  │           │     ├─ PublicacaoMapper.java
   │  │           │     ├─ PublicacaoMapperResolver.java
   │  │           │     ├─ UsuarioDetailViewMapper.java
   │  │           │     └─ UsuarioMapper.java
   │  │           ├─ service
   │  │           │  ├─ Leitor
   │  │           │  │  ├─ BibliotecaService.java
   │  │           │  │  ├─ BibliotecaServiceImpl.java
   │  │           │  │  ├─ LivroDesejadoService.java
   │  │           │  │  └─ LivroDesejadoServiceImpl.java
   │  │           │  ├─ OpenLibraryService.java
   │  │           │  ├─ Sebo
   │  │           │  │  ├─ CatalogoService.java
   │  │           │  │  └─ CatalogoServiceImpl.java
   │  │           │  └─ Usuario
   │  │           │     ├─ ComentarioService.java
   │  │           │     ├─ ComentarioServiceImpl.java
   │  │           │     ├─ MensagemService.java
   │  │           │     ├─ MensagemServiceImpl.java
   │  │           │     ├─ PublicacaoService.java
   │  │           │     ├─ PublicacaoServiceImpl.java
   │  │           │     ├─ UsuarioDetailsServiceImpl.java
   │  │           │     ├─ UsuarioSecurityService.java
   │  │           │     ├─ UsuarioSecurityServiceImpl.java
   │  │           │     ├─ UsuarioService.java
   │  │           │     └─ UsuarioServiceImpl.java
   │  │           └─ util
   │  │              └─ AppUtils.java
   │  └─ resources
   │     ├─ application.yml
   │     └─ db
   │        └─ migration
   │           ├─ V1__create_tables.sql
   │           └─ V2__Insert_initial_data.sql
   └─ test
      └─ java
         └─ br
            └─ com
               └─ bookcheck
                  ├─ BookcheckApplicationTests.java
                  ├─ controller
                  │  ├─ AuthControllerTest.java
                  │  ├─ CatalogoControllerTest.java
                  │  ├─ LivroDesejadoControllerTest.java
                  │  ├─ UsuarioControllerTest.java
                  │  ├─ UsuarioLeitorControllerTest.java
                  │  └─ UsuarioSeboControllerTest.java
                  └─ service
                     ├─ Leitor
                     │  └─ LivroDesejadoServiceImplTest.java
                     ├─ Sebo
                     │  └─ CatalogoServiceImplTest.java
                     └─ Usuario
                        ├─ UsuarioDetailsServiceImplTest.java
                        ├─ UsuarioSecurityServiceImplTest.java
                        └─ UsuarioServiceImplTest.java

```