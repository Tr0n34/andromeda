CREATE TABLE oauth2_registered_client (
    id VARCHAR(100) PRIMARY KEY,
    client_id VARCHAR(100) UNIQUE NOT NULL,
    client_secret VARCHAR(200),
    client_id_issued_at TIMESTAMP,
    client_secret_expires_at TIMESTAMP,
    client_name VARCHAR(200),
    client_authentication_methods VARCHAR(1000),
    authorization_grant_types VARCHAR(1000),
    redirect_uris VARCHAR(2000),
    scopes VARCHAR(2000),
    client_settings VARCHAR(2000),
    token_settings VARCHAR(2000)
);

CREATE TABLE oauth2_authorization (
    id VARCHAR(100) PRIMARY KEY,
    registered_client_id VARCHAR(100) NOT NULL,
    principal_name VARCHAR(200),
    authorization_grant_type VARCHAR(100),
    attributes TEXT,
    FOREIGN KEY (registered_client_id) REFERENCES oauth2_registered_client(id)
);

CREATE TABLE oauth2_authorization_consent (
    id VARCHAR(100) PRIMARY KEY,
    registered_client_id VARCHAR(100) NOT NULL,
    principal_name VARCHAR(200),
    authorities VARCHAR(2000),
    FOREIGN KEY (registered_client_id) REFERENCES oauth2_registered_client(id)
);

CREATE TABLE oauth2_authorization_token (
    id VARCHAR(100) PRIMARY KEY,
    authorization_id VARCHAR(100) NOT NULL,
    token_type VARCHAR(100),
    token_value TEXT,
    issued_at TIMESTAMP,
    expires_at TIMESTAMP,
    scopes VARCHAR(2000),
    FOREIGN KEY (authorization_id) REFERENCES oauth2_authorization(id)
);