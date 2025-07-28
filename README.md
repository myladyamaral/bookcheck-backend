
```
bookcheck-backend
├─ .idea
│  ├─ compiler.xml
│  ├─ encodings.xml
│  ├─ jarRepositories.xml
│  ├─ misc.xml
│  ├─ vcs.xml
│  └─ workspace.xml
├─ HELP.md
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
├─ README.md
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ br
│  │  │     └─ com
│  │  │        └─ bookcheck
│  │  │           ├─ BookcheckApplication.java
│  │  │           ├─ component
│  │  │           │  └─ SecurityTokenComponent.java
│  │  │           ├─ config
│  │  │           │  ├─ FlywayConfig.java
│  │  │           │  ├─ JwtConfigProperties.java
│  │  │           │  ├─ OpenApiConfig.java
│  │  │           │  ├─ SpringSecurityConfig.java
│  │  │           │  └─ WebConfig.java
│  │  │           ├─ controller
│  │  │           │  ├─ AuthController.java
│  │  │           │  ├─ CatalogoController.java
│  │  │           │  ├─ dto
│  │  │           │  │  ├─ request
│  │  │           │  │  │  ├─ AuthenticationRequestDto.java
│  │  │           │  │  │  ├─ Leitor
│  │  │           │  │  │  │  ├─ BibliotecaRequestDto.java
│  │  │           │  │  │  │  └─ LivroDesejadoRequestDto.java
│  │  │           │  │  │  ├─ Livro
│  │  │           │  │  │  │  ├─ AutorRequestDto.java
│  │  │           │  │  │  │  ├─ EditoraRequestDto.java
│  │  │           │  │  │  │  └─ LivroRequestDto.java
│  │  │           │  │  │  ├─ Sebo
│  │  │           │  │  │  │  └─ CatalogoRequestDto.java
│  │  │           │  │  │  └─ Usuario
│  │  │           │  │  │     ├─ ComentarioRequestDto.java
│  │  │           │  │  │     ├─ CurtidaRequestDto.java
│  │  │           │  │  │     ├─ EnderecoRequestDto.java
│  │  │           │  │  │     ├─ MensagemRequestDto.java
│  │  │           │  │  │     ├─ PublicacaoRequestDto.java
│  │  │           │  │  │     ├─ UsuarioLeitorRequestDto.java
│  │  │           │  │  │     ├─ UsuarioLeitorUpdateRequestDto.java
│  │  │           │  │  │     ├─ UsuarioResquestDto.java
│  │  │           │  │  │     └─ UsuarioSeboRequestDto.java
│  │  │           │  │  └─ response
│  │  │           │  │     ├─ AuthenticationResponseDto.java
│  │  │           │  │     ├─ GenericSuccessResponseDto.java
│  │  │           │  │     ├─ Leitor
│  │  │           │  │     │  ├─ BibliotecaResponseDto.java
│  │  │           │  │     │  └─ LivroDesejadoResponseDto.java
│  │  │           │  │     ├─ Livro
│  │  │           │  │     │  ├─ AutorResponseDto.java
│  │  │           │  │     │  ├─ EditoraResponseDto.java
│  │  │           │  │     │  └─ LivroResponseDto.java
│  │  │           │  │     ├─ Sebo
│  │  │           │  │     │  └─ CatalogoResponseDto.java
│  │  │           │  │     └─ Usuario
│  │  │           │  │        ├─ ComentarioResponseDto.java
│  │  │           │  │        ├─ CurtidaResponseDto.java
│  │  │           │  │        ├─ EnderecoResponseDto.java
│  │  │           │  │        ├─ MensagemResponseDto.java
│  │  │           │  │        ├─ PublicacaoResponseDto.java
│  │  │           │  │        ├─ UsuarioLeitorResponseDto.java
│  │  │           │  │        ├─ UsuarioResponseDto.java
│  │  │           │  │        ├─ UsuarioResumeResponseDto.java
│  │  │           │  │        └─ UsuarioSeboResponseDto.java
│  │  │           │  ├─ LivroDesejadoController.java
│  │  │           │  ├─ UsuarioController.java
│  │  │           │  ├─ UsuarioLeitorController.java
│  │  │           │  └─ UsuarioSeboController.java
│  │  │           ├─ domain
│  │  │           │  ├─ entity
│  │  │           │  │  ├─ Leitor
│  │  │           │  │  │  ├─ Biblioteca.java
│  │  │           │  │  │  └─ LivroDesejado.java
│  │  │           │  │  ├─ Livro
│  │  │           │  │  │  ├─ Autor.java
│  │  │           │  │  │  ├─ Editora.java
│  │  │           │  │  │  └─ Livro.java
│  │  │           │  │  ├─ Sebo
│  │  │           │  │  │  └─ Catalogo.java
│  │  │           │  │  └─ Usuario
│  │  │           │  │     ├─ Comentario.java
│  │  │           │  │     ├─ Curtida.java
│  │  │           │  │     ├─ Endereco.java
│  │  │           │  │     ├─ Mensagem.java
│  │  │           │  │     ├─ Publicacao.java
│  │  │           │  │     ├─ Usuario.java
│  │  │           │  │     ├─ UsuarioLeitor.java
│  │  │           │  │     └─ UsuarioSebo.java
│  │  │           │  ├─ enums
│  │  │           │  │  ├─ converter
│  │  │           │  │  │  ├─ BooleanConverter.java
│  │  │           │  │  │  ├─ DisponibilidadeCatalogoConverter.java
│  │  │           │  │  │  ├─ EstadoConservacaoConverter.java
│  │  │           │  │  │  ├─ GenericConverter.java
│  │  │           │  │  │  ├─ GeneroLiterarioConverter.java
│  │  │           │  │  │  ├─ StatusLeituraConverter.java
│  │  │           │  │  │  └─ TipoUsuarioConverter.java
│  │  │           │  │  ├─ DisponibilidadeCatalogoEnum.java
│  │  │           │  │  ├─ EstadoConservacaoEnum.java
│  │  │           │  │  ├─ GeneroLiterarioEnum.java
│  │  │           │  │  ├─ StatusLeituraEnum.java
│  │  │           │  │  └─ TipoUsuarioEnum.java
│  │  │           │  ├─ repository
│  │  │           │  │  ├─ AutorRepository.java
│  │  │           │  │  ├─ BibliotecaRepository.java
│  │  │           │  │  ├─ CatalogoRepository.java
│  │  │           │  │  ├─ ComentarioRepository.java
│  │  │           │  │  ├─ CurtidaRepository.java
│  │  │           │  │  ├─ EditoraRepository.java
│  │  │           │  │  ├─ EnderecoRepository.java
│  │  │           │  │  ├─ LivroDesejadoRepository.java
│  │  │           │  │  ├─ LivroRepository.java
│  │  │           │  │  ├─ MensagemRepository.java
│  │  │           │  │  ├─ PublicacaoRepository.java
│  │  │           │  │  ├─ UsuarioLeitorRepository.java
│  │  │           │  │  ├─ UsuarioRepository.java
│  │  │           │  │  └─ UsuarioSeboRepository.java
│  │  │           │  └─ view
│  │  │           │     └─ UsuarioDetailView.java
│  │  │           ├─ exception
│  │  │           │  ├─ ControllerExceptionHandler.java
│  │  │           │  ├─ EntityNotFoundException.java
│  │  │           │  ├─ FieldMessage.java
│  │  │           │  ├─ ServiceBusinessException.java
│  │  │           │  ├─ StandardError.java
│  │  │           │  ├─ UnauthorizedException.java
│  │  │           │  └─ ValidationError.java
│  │  │           ├─ filter
│  │  │           │  └─ JwtRequestFilter.java
│  │  │           ├─ mapper
│  │  │           │  ├─ Leitor
│  │  │           │  │  ├─ BibliotecaMapper.java
│  │  │           │  │  ├─ BibliotecaMapperResolver.java
│  │  │           │  │  ├─ LivroDesejadoMapper.java
│  │  │           │  │  └─ LivroDesejadoMapperResolver.java
│  │  │           │  ├─ Livro
│  │  │           │  │  ├─ AutorMapper.java
│  │  │           │  │  ├─ AutorMapperResolver.java
│  │  │           │  │  ├─ EditoraMapper.java
│  │  │           │  │  ├─ EditoraMapperResolver.java
│  │  │           │  │  ├─ LivroMapper.java
│  │  │           │  │  └─ LivroMapperResolver.java
│  │  │           │  ├─ Sebo
│  │  │           │  │  ├─ CatalogoMapper.java
│  │  │           │  │  └─ CatalogoMapperResolver.java
│  │  │           │  └─ Usuario
│  │  │           │     ├─ ComentarioMapper.java
│  │  │           │     ├─ ComentarioMapperResolver.java
│  │  │           │     ├─ CurtidaMapper.java
│  │  │           │     ├─ CurtidaMapperResolver.java
│  │  │           │     ├─ EnderecoMapper.java
│  │  │           │     ├─ EnderecoMapperResolver.java
│  │  │           │     ├─ MensagemMapper.java
│  │  │           │     ├─ MensagemMapperResolver.java
│  │  │           │     ├─ PublicacaoMapper.java
│  │  │           │     ├─ PublicacaoMapperResolver.java
│  │  │           │     ├─ UsuarioDetailViewMapper.java
│  │  │           │     └─ UsuarioMapper.java
│  │  │           ├─ service
│  │  │           │  ├─ Leitor
│  │  │           │  │  ├─ LivroDesejadoService.java
│  │  │           │  │  └─ LivroDesejadoServiceImpl.java
│  │  │           │  ├─ Sebo
│  │  │           │  │  ├─ CatalogoService.java
│  │  │           │  │  └─ CatalogoServiceImpl.java
│  │  │           │  └─ Usuario
│  │  │           │     ├─ UsuarioDetailsServiceImpl.java
│  │  │           │     ├─ UsuarioSecurityService.java
│  │  │           │     ├─ UsuarioSecurityServiceImpl.java
│  │  │           │     ├─ UsuarioService.java
│  │  │           │     └─ UsuarioServiceImpl.java
│  │  │           └─ util
│  │  │              └─ AppUtils.java
│  │  └─ resources
│  │     ├─ application.yml
│  │     └─ db
│  │        └─ migration
│  │           ├─ V1__create_tables.sql
│  │           └─ V2__Insert_initial_data.sql
│  └─ test
│     └─ java
│        └─ br
│           └─ com
│              └─ bookcheck
│                 ├─ BookcheckApplicationTests.java
│                 └─ service
│                    ├─ Leitor
│                    │  └─ LivroDesejadoServiceImplTest.java
│                    ├─ Sebo
│                    │  └─ CatalogoServiceImplTest.java
│                    └─ Usuario
│                       ├─ UsuarioDetailsServiceImplTest.java
│                       ├─ UsuarioSecurityServiceImplTest.java
│                       └─ UsuarioServiceImplTest.java
└─ target
   ├─ classes
   │  ├─ application.yml
   │  ├─ br
   │  │  └─ com
   │  │     └─ bookcheck
   │  │        ├─ BookcheckApplication$1.class
   │  │        ├─ BookcheckApplication.class
   │  │        ├─ component
   │  │        │  └─ SecurityTokenComponent.class
   │  │        ├─ config
   │  │        │  ├─ FlywayConfig.class
   │  │        │  ├─ JwtConfigProperties.class
   │  │        │  ├─ OpenApiConfig.class
   │  │        │  ├─ SpringSecurityConfig.class
   │  │        │  └─ WebConfig.class
   │  │        ├─ controller
   │  │        │  ├─ AuthController.class
   │  │        │  ├─ CatalogoController.class
   │  │        │  ├─ dto
   │  │        │  │  ├─ request
   │  │        │  │  │  ├─ AuthenticationRequestDto$AuthenticationRequestDtoBuilder.class
   │  │        │  │  │  ├─ AuthenticationRequestDto.class
   │  │        │  │  │  ├─ Leitor
   │  │        │  │  │  │  ├─ BibliotecaRequestDto$BibliotecaRequestDtoBuilder.class
   │  │        │  │  │  │  ├─ BibliotecaRequestDto.class
   │  │        │  │  │  │  ├─ LivroDesejadoRequestDto$LivroDesejadoRequestDtoBuilder.class
   │  │        │  │  │  │  └─ LivroDesejadoRequestDto.class
   │  │        │  │  │  ├─ Livro
   │  │        │  │  │  │  ├─ AutorRequestDto$AutorRequestDtoBuilder.class
   │  │        │  │  │  │  ├─ AutorRequestDto.class
   │  │        │  │  │  │  ├─ EditoraRequestDto$EditoraRequestDtoBuilder.class
   │  │        │  │  │  │  ├─ EditoraRequestDto.class
   │  │        │  │  │  │  ├─ LivroRequestDto$LivroRequestDtoBuilder.class
   │  │        │  │  │  │  └─ LivroRequestDto.class
   │  │        │  │  │  ├─ Sebo
   │  │        │  │  │  │  ├─ CatalogoRequestDto$CatalogoRequestDtoBuilder.class
   │  │        │  │  │  │  └─ CatalogoRequestDto.class
   │  │        │  │  │  └─ Usuario
   │  │        │  │  │     ├─ ComentarioRequestDto$ComentarioRequestDtoBuilder.class
   │  │        │  │  │     ├─ ComentarioRequestDto.class
   │  │        │  │  │     ├─ CurtidaRequestDto$CurtidaRequestDtoBuilder.class
   │  │        │  │  │     ├─ CurtidaRequestDto.class
   │  │        │  │  │     ├─ EnderecoRequestDto$EnderecoRequestDtoBuilder.class
   │  │        │  │  │     ├─ EnderecoRequestDto$EnderecoRequestDtoBuilderImpl.class
   │  │        │  │  │     ├─ EnderecoRequestDto.class
   │  │        │  │  │     ├─ MensagemRequestDto$MensagemRequestDtoBuilder.class
   │  │        │  │  │     ├─ MensagemRequestDto.class
   │  │        │  │  │     ├─ PublicacaoRequestDto$PublicacaoRequestDtoBuilder.class
   │  │        │  │  │     ├─ PublicacaoRequestDto.class
   │  │        │  │  │     ├─ UsuarioLeitorRequestDto$UsuarioLeitorRequestDtoBuilder.class
   │  │        │  │  │     ├─ UsuarioLeitorRequestDto$UsuarioLeitorRequestDtoBuilderImpl.class
   │  │        │  │  │     ├─ UsuarioLeitorRequestDto.class
   │  │        │  │  │     ├─ UsuarioLeitorUpdateRequestDto$UsuarioLeitorUpdateRequestDtoBuilder.class
   │  │        │  │  │     ├─ UsuarioLeitorUpdateRequestDto$UsuarioLeitorUpdateRequestDtoBuilderImpl.class
   │  │        │  │  │     ├─ UsuarioLeitorUpdateRequestDto.class
   │  │        │  │  │     ├─ UsuarioResquestDto$UsuarioResquestDtoBuilder.class
   │  │        │  │  │     ├─ UsuarioResquestDto.class
   │  │        │  │  │     ├─ UsuarioSeboRequestDto$UsuarioSeboRequestDtoBuilder.class
   │  │        │  │  │     ├─ UsuarioSeboRequestDto$UsuarioSeboRequestDtoBuilderImpl.class
   │  │        │  │  │     └─ UsuarioSeboRequestDto.class
   │  │        │  │  └─ response
   │  │        │  │     ├─ AuthenticationResponseDto.class
   │  │        │  │     ├─ GenericSuccessResponseDto.class
   │  │        │  │     ├─ Leitor
   │  │        │  │     │  ├─ BibliotecaResponseDto$BibliotecaResponseDtoBuilder.class
   │  │        │  │     │  ├─ BibliotecaResponseDto.class
   │  │        │  │     │  ├─ LivroDesejadoResponseDto$LivroDesejadoResponseDtoBuilder.class
   │  │        │  │     │  └─ LivroDesejadoResponseDto.class
   │  │        │  │     ├─ Livro
   │  │        │  │     │  ├─ AutorResponseDto$AutorResponseDtoBuilder.class
   │  │        │  │     │  ├─ AutorResponseDto.class
   │  │        │  │     │  ├─ EditoraResponseDto$EditoraResponseDtoBuilder.class
   │  │        │  │     │  ├─ EditoraResponseDto.class
   │  │        │  │     │  ├─ LivroResponseDto$LivroResponseDtoBuilder.class
   │  │        │  │     │  └─ LivroResponseDto.class
   │  │        │  │     ├─ Sebo
   │  │        │  │     │  ├─ CatalogoResponseDto$CatalogoResponseDtoBuilder.class
   │  │        │  │     │  └─ CatalogoResponseDto.class
   │  │        │  │     └─ Usuario
   │  │        │  │        ├─ ComentarioResponseDto$ComentarioResponseDtoBuilder.class
   │  │        │  │        ├─ ComentarioResponseDto.class
   │  │        │  │        ├─ CurtidaResponseDto$CurtidaResponseDtoBuilder.class
   │  │        │  │        ├─ CurtidaResponseDto.class
   │  │        │  │        ├─ EnderecoResponseDto.class
   │  │        │  │        ├─ MensagemResponseDto.class
   │  │        │  │        ├─ PublicacaoResponseDto$PublicacaoResponseDtoBuilder.class
   │  │        │  │        ├─ PublicacaoResponseDto.class
   │  │        │  │        ├─ UsuarioLeitorResponseDto$UsuarioLeitorResponseDtoBuilder.class
   │  │        │  │        ├─ UsuarioLeitorResponseDto$UsuarioLeitorResponseDtoBuilderImpl.class
   │  │        │  │        ├─ UsuarioLeitorResponseDto.class
   │  │        │  │        ├─ UsuarioResponseDto$UsuarioResponseDtoBuilder.class
   │  │        │  │        ├─ UsuarioResponseDto.class
   │  │        │  │        ├─ UsuarioResumeResponseDto$UsuarioResumeResponseDtoBuilder.class
   │  │        │  │        ├─ UsuarioResumeResponseDto$UsuarioResumeResponseDtoBuilderImpl.class
   │  │        │  │        ├─ UsuarioResumeResponseDto.class
   │  │        │  │        ├─ UsuarioSeboResponseDto$UsuarioSeboResponseDtoBuilder.class
   │  │        │  │        ├─ UsuarioSeboResponseDto$UsuarioSeboResponseDtoBuilderImpl.class
   │  │        │  │        └─ UsuarioSeboResponseDto.class
   │  │        │  ├─ LivroDesejadoController.class
   │  │        │  ├─ UsuarioController.class
   │  │        │  ├─ UsuarioLeitorController.class
   │  │        │  └─ UsuarioSeboController.class
   │  │        ├─ domain
   │  │        │  ├─ entity
   │  │        │  │  ├─ Leitor
   │  │        │  │  │  ├─ Biblioteca$BibliotecaBuilder.class
   │  │        │  │  │  ├─ Biblioteca$BibliotecaBuilderImpl.class
   │  │        │  │  │  ├─ Biblioteca.class
   │  │        │  │  │  ├─ LivroDesejado$LivroDesejadoBuilder.class
   │  │        │  │  │  ├─ LivroDesejado$LivroDesejadoBuilderImpl.class
   │  │        │  │  │  └─ LivroDesejado.class
   │  │        │  │  ├─ Livro
   │  │        │  │  │  ├─ Autor$AutorBuilder.class
   │  │        │  │  │  ├─ Autor$AutorBuilderImpl.class
   │  │        │  │  │  ├─ Autor.class
   │  │        │  │  │  ├─ Editora$EditoraBuilder.class
   │  │        │  │  │  ├─ Editora$EditoraBuilderImpl.class
   │  │        │  │  │  ├─ Editora.class
   │  │        │  │  │  ├─ Livro$LivroBuilder.class
   │  │        │  │  │  ├─ Livro$LivroBuilderImpl.class
   │  │        │  │  │  └─ Livro.class
   │  │        │  │  ├─ Sebo
   │  │        │  │  │  ├─ Catalogo$CatalogoBuilder.class
   │  │        │  │  │  ├─ Catalogo$CatalogoBuilderImpl.class
   │  │        │  │  │  └─ Catalogo.class
   │  │        │  │  └─ Usuario
   │  │        │  │     ├─ Comentario$ComentarioBuilder.class
   │  │        │  │     ├─ Comentario$ComentarioBuilderImpl.class
   │  │        │  │     ├─ Comentario.class
   │  │        │  │     ├─ Curtida$CurtidaBuilder.class
   │  │        │  │     ├─ Curtida$CurtidaBuilderImpl.class
   │  │        │  │     ├─ Curtida.class
   │  │        │  │     ├─ Endereco$EnderecoBuilder.class
   │  │        │  │     ├─ Endereco$EnderecoBuilderImpl.class
   │  │        │  │     ├─ Endereco.class
   │  │        │  │     ├─ Mensagem$MensagemBuilder.class
   │  │        │  │     ├─ Mensagem$MensagemBuilderImpl.class
   │  │        │  │     ├─ Mensagem.class
   │  │        │  │     ├─ Publicacao$PublicacaoBuilder.class
   │  │        │  │     ├─ Publicacao$PublicacaoBuilderImpl.class
   │  │        │  │     ├─ Publicacao.class
   │  │        │  │     ├─ Usuario$UsuarioBuilder.class
   │  │        │  │     ├─ Usuario.class
   │  │        │  │     ├─ UsuarioLeitor$UsuarioLeitorBuilder.class
   │  │        │  │     ├─ UsuarioLeitor$UsuarioLeitorBuilderImpl.class
   │  │        │  │     ├─ UsuarioLeitor.class
   │  │        │  │     ├─ UsuarioSebo$UsuarioSeboBuilder.class
   │  │        │  │     ├─ UsuarioSebo$UsuarioSeboBuilderImpl.class
   │  │        │  │     └─ UsuarioSebo.class
   │  │        │  ├─ enums
   │  │        │  │  ├─ converter
   │  │        │  │  │  ├─ BooleanConverter.class
   │  │        │  │  │  ├─ DisponibilidadeCatalogoConverter.class
   │  │        │  │  │  ├─ EstadoConservacaoConverter.class
   │  │        │  │  │  ├─ GenericConverter.class
   │  │        │  │  │  ├─ GeneroLiterarioConverter.class
   │  │        │  │  │  ├─ StatusLeituraConverter.class
   │  │        │  │  │  └─ TipoUsuarioConverter.class
   │  │        │  │  ├─ DisponibilidadeCatalogoEnum.class
   │  │        │  │  ├─ EstadoConservacaoEnum.class
   │  │        │  │  ├─ GeneroLiterarioEnum.class
   │  │        │  │  ├─ StatusLeituraEnum.class
   │  │        │  │  └─ TipoUsuarioEnum.class
   │  │        │  ├─ repository
   │  │        │  │  ├─ AutorRepository.class
   │  │        │  │  ├─ BibliotecaRepository.class
   │  │        │  │  ├─ CatalogoRepository.class
   │  │        │  │  ├─ ComentarioRepository.class
   │  │        │  │  ├─ CurtidaRepository.class
   │  │        │  │  ├─ EditoraRepository.class
   │  │        │  │  ├─ EnderecoRepository.class
   │  │        │  │  ├─ LivroDesejadoRepository.class
   │  │        │  │  ├─ LivroRepository.class
   │  │        │  │  ├─ MensagemRepository.class
   │  │        │  │  ├─ PublicacaoRepository.class
   │  │        │  │  ├─ UsuarioLeitorRepository.class
   │  │        │  │  ├─ UsuarioRepository.class
   │  │        │  │  └─ UsuarioSeboRepository.class
   │  │        │  └─ view
   │  │        │     ├─ UsuarioDetailView$UsuarioDetailViewBuilder.class
   │  │        │     └─ UsuarioDetailView.class
   │  │        ├─ exception
   │  │        │  ├─ ControllerExceptionHandler.class
   │  │        │  ├─ EntityNotFoundException.class
   │  │        │  ├─ FieldMessage.class
   │  │        │  ├─ ServiceBusinessException.class
   │  │        │  ├─ StandardError.class
   │  │        │  ├─ UnauthorizedException.class
   │  │        │  └─ ValidationError.class
   │  │        ├─ filter
   │  │        │  └─ JwtRequestFilter.class
   │  │        ├─ mapper
   │  │        │  ├─ Leitor
   │  │        │  │  ├─ BibliotecaMapper.class
   │  │        │  │  ├─ BibliotecaMapperImpl.class
   │  │        │  │  ├─ BibliotecaMapperResolver.class
   │  │        │  │  ├─ LivroDesejadoMapper.class
   │  │        │  │  ├─ LivroDesejadoMapperImpl.class
   │  │        │  │  └─ LivroDesejadoMapperResolver.class
   │  │        │  ├─ Livro
   │  │        │  │  ├─ AutorMapper.class
   │  │        │  │  ├─ AutorMapperImpl.class
   │  │        │  │  ├─ AutorMapperResolver.class
   │  │        │  │  ├─ EditoraMapper.class
   │  │        │  │  ├─ EditoraMapperImpl.class
   │  │        │  │  ├─ EditoraMapperResolver.class
   │  │        │  │  ├─ LivroMapper.class
   │  │        │  │  ├─ LivroMapperImpl.class
   │  │        │  │  └─ LivroMapperResolver.class
   │  │        │  ├─ Sebo
   │  │        │  │  ├─ CatalogoMapper.class
   │  │        │  │  ├─ CatalogoMapperImpl.class
   │  │        │  │  └─ CatalogoMapperResolver.class
   │  │        │  └─ Usuario
   │  │        │     ├─ ComentarioMapper.class
   │  │        │     ├─ ComentarioMapperImpl.class
   │  │        │     ├─ ComentarioMapperResolver.class
   │  │        │     ├─ CurtidaMapper.class
   │  │        │     ├─ CurtidaMapperImpl.class
   │  │        │     ├─ CurtidaMapperResolver.class
   │  │        │     ├─ EnderecoMapper.class
   │  │        │     ├─ EnderecoMapperImpl.class
   │  │        │     ├─ EnderecoMapperResolver.class
   │  │        │     ├─ MensagemMapper.class
   │  │        │     ├─ MensagemMapperImpl.class
   │  │        │     ├─ MensagemMapperResolver.class
   │  │        │     ├─ PublicacaoMapper.class
   │  │        │     ├─ PublicacaoMapperImpl.class
   │  │        │     ├─ PublicacaoMapperResolver.class
   │  │        │     ├─ UsuarioDetailViewMapper.class
   │  │        │     ├─ UsuarioDetailViewMapperImpl.class
   │  │        │     ├─ UsuarioMapper.class
   │  │        │     └─ UsuarioMapperImpl.class
   │  │        ├─ service
   │  │        │  ├─ Leitor
   │  │        │  │  ├─ LivroDesejadoService.class
   │  │        │  │  └─ LivroDesejadoServiceImpl.class
   │  │        │  ├─ Sebo
   │  │        │  │  ├─ CatalogoService.class
   │  │        │  │  └─ CatalogoServiceImpl.class
   │  │        │  └─ Usuario
   │  │        │     ├─ UsuarioDetailsServiceImpl.class
   │  │        │     ├─ UsuarioSecurityService.class
   │  │        │     ├─ UsuarioSecurityServiceImpl.class
   │  │        │     ├─ UsuarioService.class
   │  │        │     └─ UsuarioServiceImpl.class
   │  │        └─ util
   │  │           └─ AppUtils.class
   │  └─ db
   │     └─ migration
   │        ├─ V1__create_tables.sql
   │        └─ V2__Insert_initial_data.sql
   ├─ generated-sources
   │  └─ annotations
   │     └─ br
   │        └─ com
   │           └─ bookcheck
   │              └─ mapper
   │                 ├─ Leitor
   │                 │  ├─ BibliotecaMapperImpl.java
   │                 │  └─ LivroDesejadoMapperImpl.java
   │                 ├─ Livro
   │                 │  ├─ AutorMapperImpl.java
   │                 │  ├─ EditoraMapperImpl.java
   │                 │  └─ LivroMapperImpl.java
   │                 ├─ Sebo
   │                 │  └─ CatalogoMapperImpl.java
   │                 └─ Usuario
   │                    ├─ ComentarioMapperImpl.java
   │                    ├─ CurtidaMapperImpl.java
   │                    ├─ EnderecoMapperImpl.java
   │                    ├─ MensagemMapperImpl.java
   │                    ├─ PublicacaoMapperImpl.java
   │                    ├─ UsuarioDetailViewMapperImpl.java
   │                    └─ UsuarioMapperImpl.java
   ├─ generated-test-sources
   │  └─ test-annotations
   └─ test-classes
      └─ br
         └─ com
            └─ bookcheck
               ├─ BookcheckApplicationTests.class
               └─ service
                  ├─ Leitor
                  │  └─ LivroDesejadoServiceImplTest.class
                  ├─ Sebo
                  │  └─ CatalogoServiceImplTest.class
                  └─ Usuario
                     ├─ UsuarioDetailsServiceImplTest.class
                     ├─ UsuarioSecurityServiceImplTest.class
                     └─ UsuarioServiceImplTest.class

```