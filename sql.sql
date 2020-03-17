String sql = " select nome, numero, email from telefoneuser as fone ";
			sql += " inner join userposjava as userp ";
			sql += " on fone.usuariopessoa = userp.id ";
			sql += " where userp.id = " + idUser;
			

CREATE TABLE public.telefoneuser
(
  id bigint NOT NULL DEFAULT nextval('user_telefone_seq'::regclass),
  numero character varying(255) NOT NULL,
  tipo character varying(255) NOT NULL,
  usuariopessoa bigint NOT NULL,
  CONSTRAINT telefone_pk PRIMARY KEY (id),
  CONSTRAINT telefoneuser_usuariopessoa_fkey FOREIGN KEY (usuariopessoa)
      REFERENCES public.userposjava (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.telefoneuser
  OWNER TO postgres;

  
 ALTER TABLE public.telefoneuser
  ADD CONSTRAINT telefoneuser_usuariopessoa_fkey FOREIGN KEY (usuariopessoa)
      REFERENCES public.userposjava (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;