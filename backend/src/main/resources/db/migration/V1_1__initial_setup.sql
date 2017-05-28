CREATE TABLE hello_world (
  id               BIGINT       NOT NULL PRIMARY KEY
 ,version          INTEGER      NOT NULL
 ,value            VARCHAR(255)
 ,created_at       TIMESTAMP    NOT NULL
 ,created_by       VARCHAR(255) NOT NULL
 ,last_modified_at TIMESTAMP
 ,last_modified_by VARCHAR(255)
);

CREATE TABLE heroes (
  id               BIGINT       NOT NULL PRIMARY KEY
 ,version          INTEGER      NOT NULL
 ,name             VARCHAR(255) NOT NULL
 ,created_at       TIMESTAMP    NOT NULL
 ,created_by       VARCHAR(255) NOT NULL
 ,last_modified_at TIMESTAMP
 ,last_modified_by VARCHAR(255)
);