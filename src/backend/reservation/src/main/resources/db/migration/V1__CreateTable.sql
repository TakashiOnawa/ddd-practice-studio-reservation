drop table if exists accounts;

create table accounts (
  id varchar(255) not null primary key,
  name varchar(255) not null,
  created_at DateTime not null,
  updated_at DateTime not null
);
