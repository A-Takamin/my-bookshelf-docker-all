CREATE TABLE books (
    serial_no serial PRIMARY KEY,
    title VARCHAR ( 255 ) NOT NULL,
	img VARCHAR ( 255 ) NOT NULL, 
    read_at VARCHAR ( 255 ) NOT NULL,
    review smallint NOT NULL,
    comments VARCHAR ( 255 ) NOT NULL,
    user_uid VARCHAR ( 255 ) NOT NULL,
    FOREIGN KEY (user_uid) REFERENCES public.users (uid)
)
