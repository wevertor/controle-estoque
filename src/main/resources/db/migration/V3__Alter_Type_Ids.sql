CREATE SEQUENCE produto_id_seq AS integer START 21 OWNED BY produto.id;

ALTER TABLE produto ALTER COLUMN id SET DEFAULT nextval('produto_id_seq');

CREATE SEQUENCE movimentacao_id_seq AS integer START 11 OWNED BY movimentacao.id;

ALTER TABLE movimentacao ALTER COLUMN id SET DEFAULT nextval('movimentacao_id_seq');
