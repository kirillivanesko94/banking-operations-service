create table client (
    id uuid primary key,
    login text not null unique,
    full_name text not null,
    birth_date date not null,
    password text not null,
    account_number text not null unique,
    start_deposit decimal not null check (start_deposit >= 0),
    balance decimal not null check (balance >= 0)
);

create table email (
    id uuid primary key,
    client_id uuid not null,
    email text not null
);

create unique index email_unq on email (lower(email));

create table phone (
    id uuid primary key,
    client_id uuid not null,
    phone text not null
);

create unique index phone_unq on phone (phone);