CREATE TABLE book (
                            id int8 NOT NULL,
                            created_date timestamp NULL,
                            updated_date timestamp NULL,
                            "version" int4 NULL,
                            "name" varchar(255) NULL,
                            price numeric(19, 2) NULL,
                            stock int8 NULL,
                            writer varchar(255) NULL,
                            CONSTRAINT book_pkey PRIMARY KEY (id)
);

CREATE TABLE customer (
                                id int8 NOT NULL,
                                created_date timestamp NULL,
                                updated_date timestamp NULL,
                                "version" int4 NULL,
                                email varchar(255) NULL,
                                "name" varchar(255) NULL,
                                surname varchar(255) NULL,
                                CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE orders (
                              id int8 NOT NULL,
                              created_date timestamp NULL,
                              updated_date timestamp NULL,
                              "version" int4 NULL,
                              customer_id int8 NULL,
                              total_book_count int8 NULL,
                              total_price numeric(19, 2) NULL,
                              CONSTRAINT orders_pkey PRIMARY KEY (id)
);

CREATE TABLE orders_book_list (
                                        order_id int8 NOT NULL,
                                        book_list_id int8 NOT NULL
);

ALTER TABLE orders_book_list ADD CONSTRAINT orders_book_constraint FOREIGN KEY (book_list_id) REFERENCES book(id);
ALTER TABLE orders_book_list ADD CONSTRAINT orders_order_constraint FOREIGN KEY (order_id) REFERENCES orders(id);