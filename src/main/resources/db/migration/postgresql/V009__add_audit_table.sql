CREATE SEQUENCE audit_id_seq;
select setval('audit_id_seq', 500, true);

CREATE TABLE audit
(
  id           BIGINT DEFAULT nextval('audit_id_seq'::regclass) PRIMARY KEY NOT NULL,
  entity_id    BIGINT                                                       NOT NULL,
  action       VARCHAR(10)                                                  NOT NULL,
  user_name    VARCHAR(50)                                                  NOT NULL,
  created_date TIMESTAMP                                                    NOT NULL,
  content      TEXT
);
