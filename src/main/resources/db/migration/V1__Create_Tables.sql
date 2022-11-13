CREATE TABLE produto (
  id int4 NOT NULL,
  descricao varchar NOT NULL,
  valor float8 NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE movimentacao (
  id int4 NOT NULL,
  id_produto int4 NOT NULL,
  tipo int4 NOT NULL,
  data date NOT NULL,
  quantidade int4 NOT NULL,
  PRIMARY KEY (id)
);
