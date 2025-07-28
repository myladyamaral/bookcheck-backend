-- Migration inicial para criar as tabelas do sistema BookCheck
-- Atualizada para refletir exatamente as entidades JPA

-- Tabela de Autores
CREATE TABLE IF NOT EXISTS autor (
                                     id BIGINT PRIMARY KEY,
                                     nome VARCHAR(255) NOT NULL
    );

-- Sequência para a tabela autor
CREATE SEQUENCE IF NOT EXISTS autor_seq
    START WITH 1
    INCREMENT BY 1
    OWNED BY autor.id;

COMMENT ON TABLE autor IS 'Tabela que armazena os autores dos livros';
COMMENT ON COLUMN autor.id IS 'ID gerado pela sequência autor_seq';
COMMENT ON COLUMN autor.nome IS 'Nome completo do autor';

-- Tabela de Editoras
CREATE TABLE IF NOT EXISTS editora (
                                       id BIGINT PRIMARY KEY,
                                       nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NOT NULL
    );

-- Sequência para a tabela editora
CREATE SEQUENCE IF NOT EXISTS editora_seq
    START WITH 1
    INCREMENT BY 1
    OWNED BY editora.id;

COMMENT ON TABLE editora IS 'Tabela que armazena as editoras dos livros';
COMMENT ON COLUMN editora.id IS 'ID gerado pela sequência editora_seq';
COMMENT ON COLUMN editora.nome IS 'Razão social da editora';
COMMENT ON COLUMN editora.cnpj IS 'CNPJ da editora';

-- Tabela de Livros
CREATE TABLE IF NOT EXISTS livro (
                                     id BIGINT PRIMARY KEY,
                                     isbn VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    ano INTEGER NOT NULL,
    idioma VARCHAR(255) NOT NULL,
    genero INTEGER NOT NULL,
    autor_id BIGINT NOT NULL,
    editora_id BIGINT NOT NULL,
    CONSTRAINT fk_livro_autor FOREIGN KEY (autor_id) REFERENCES autor(id),
    CONSTRAINT fk_livro_editora FOREIGN KEY (editora_id) REFERENCES editora(id)
    );

-- Sequência para a tabela livro
CREATE SEQUENCE IF NOT EXISTS livro_seq
    START WITH 1
    INCREMENT BY 1
    OWNED BY livro.id;

COMMENT ON TABLE livro IS 'Tabela principal que armazena os livros do sistema';
COMMENT ON COLUMN livro.id IS 'ID gerado pela sequência livro_seq';
COMMENT ON COLUMN livro.isbn IS 'Código ISBN do livro';
COMMENT ON COLUMN livro.titulo IS 'Título completo do livro';
COMMENT ON COLUMN livro.ano IS 'Ano de publicação do livro';
COMMENT ON COLUMN livro.idioma IS 'Idioma principal do livro';
COMMENT ON COLUMN livro.genero IS 'Código numérico do gênero literário conforme enumeração no sistema';
COMMENT ON COLUMN livro.autor_id IS 'Chave estrangeira para a tabela autor';
COMMENT ON COLUMN livro.editora_id IS 'Chave estrangeira para a tabela editora';

-- Criando índices para melhorar performance
CREATE INDEX IF NOT EXISTS idx_livro_titulo ON livro(titulo);
CREATE INDEX IF NOT EXISTS idx_livro_autor ON livro(autor_id);
CREATE INDEX IF NOT EXISTS idx_livro_editora ON livro(editora_id);
CREATE INDEX IF NOT EXISTS idx_livro_genero ON livro(genero);
CREATE UNIQUE INDEX IF NOT EXISTS uk_livro_isbn ON livro(isbn);
CREATE UNIQUE INDEX IF NOT EXISTS uk_editora_cnpj ON editora(cnpj);