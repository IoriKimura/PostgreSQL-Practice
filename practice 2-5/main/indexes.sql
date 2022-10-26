SET SCHEMA 'little_company';

CREATE INDEX client_post_address ON little_company.clients(post_address);
CREATE INDEX client_names ON little_company.clients("name");