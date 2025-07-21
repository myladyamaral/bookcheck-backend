
-- Inserções para a tabela autor
INSERT INTO autor (id, nome) VALUES (1, 'J.K. Rowling');
INSERT INTO autor (id, nome) VALUES (2, 'George Orwell');
INSERT INTO autor (id, nome) VALUES (3, 'Machado de Assis');
INSERT INTO autor (id, nome) VALUES (4, 'Clarice Lispector');
INSERT INTO autor (id, nome) VALUES (5, 'Stephen King');
INSERT INTO autor (id, nome) VALUES (6, 'Agatha Christie');
INSERT INTO autor (id, nome) VALUES (7, 'J.R.R. Tolkien');
INSERT INTO autor (id, nome) VALUES (8, 'Gabriel García Márquez');
INSERT INTO autor (id, nome) VALUES (9, 'Jane Austen');
INSERT INTO autor (id, nome) VALUES (10, 'Fiódor Dostoiévski');

-- Inserções para a tabela editora
INSERT INTO editora (id, nome, cnpj) VALUES (1, 'Companhia das Letras', '12345678000101');
INSERT INTO editora (id, nome, cnpj) VALUES (2, 'Editora Rocco', '12345678000102');
INSERT INTO editora (id, nome, cnpj) VALUES (3, 'Editora Record', '12345678000103');
INSERT INTO editora (id, nome, cnpj) VALUES (4, 'Editora Abril', '12345678000104');
INSERT INTO editora (id, nome, cnpj) VALUES (5, 'Editora Globo', '12345678000105');
INSERT INTO editora (id, nome, cnpj) VALUES (6, 'Editora Nova Fronteira', '12345678000106');
INSERT INTO editora (id, nome, cnpj) VALUES (7, 'Editora Arqueiro', '12345678000107');
INSERT INTO editora (id, nome, cnpj) VALUES (8, 'Editora Intrínseca', '12345678000108');
INSERT INTO editora (id, nome, cnpj) VALUES (9, 'Editora Martin Claret', '12345678000109');
INSERT INTO editora (id, nome, cnpj) VALUES (10, 'Editora 34', '12345678000110');

-- Inserções para a tabela livro usando códigos do enum GeneroLiterarioEnum
INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (1, '9788532530786', 'Harry Potter e a Pedra Filosofal', 1997, 'Português', 3, 1, 2); -- FANTASIA(3)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (2, '9788535914849', '1984', 1949, 'Português', 7, 2, 1); -- DISTOPIA(7)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (3, '9788520926335', 'Dom Casmurro', 1899, 'Português', 1, 3, 3); -- ROMANCE(1)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (4, '9788520921231', 'A Hora da Estrela', 1977, 'Português', 12, 4, 3); -- DRAMA(12)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (5, '9788532529384', 'It - A Coisa', 1986, 'Português', 4, 5, 7); -- TERROR(4)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (6, '9788525405183', 'Assassinato no Expresso do Oriente', 1934, 'Português', 6, 6, 6); -- MISTERIO(6)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (7, '9788595084742', 'O Senhor dos Anéis: A Sociedade do Anel', 1954, 'Português', 3, 7, 8); -- FANTASIA(3)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (8, '9788526016981', 'Cem Anos de Solidão', 1967, 'Português', 1, 8, 5); -- ROMANCE(1)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (9, '9788535914122', 'Orgulho e Preconceito', 1813, 'Português', 1, 9, 1); -- ROMANCE(1)

INSERT INTO livro (id, isbn, titulo, ano, idioma, genero, autor_id, editora_id) VALUES
    (10, '9788525432134', 'Crime e Castigo', 1866, 'Português', 12, 10, 9); -- DRAMA(12)