;             
CREATE USER IF NOT EXISTS "ADMIN" SALT '06db6632eea16eb0' HASH '3576b230a6e67d15c255737019834ab37090b6f626ef0996c9c61e6612ed3879' ADMIN;      
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_70AFBB82_C093_4B2E_8578_3195CD494789" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."HIBERNATE_SEQUENCE" START WITH 3;   
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_F59688D1_7DC7_47B5_8727_48C098CC38F4" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_8DBBB8E1_EA5F_4F4F_8670_DDABC9407C71" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9BBD0775_3630_4E87_AC4D_B29BCF953358" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_637D798C_6E7D_4D01_8FDB_903C197C8774" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_614293E8_5BCB_4C61_BFF7_27E4B6E392F0" START WITH 1 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."BANK_ACCOUNTS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_70AFBB82_C093_4B2E_8578_3195CD494789" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_70AFBB82_C093_4B2E_8578_3195CD494789",
    "BIC" VARCHAR(255),
    "IBAN" VARCHAR(255),
    "SWIFT" VARCHAR(255),
    "USER_ID" BIGINT
);              
ALTER TABLE "PUBLIC"."BANK_ACCOUNTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" PRIMARY KEY("ID");
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.BANK_ACCOUNTS;           
INSERT INTO "PUBLIC"."BANK_ACCOUNTS" VALUES
(1, 'abc', 'abc', 'abc', 1);     
CREATE MEMORY TABLE "PUBLIC"."CREDIT_CARDS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_F59688D1_7DC7_47B5_8727_48C098CC38F4" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_F59688D1_7DC7_47B5_8727_48C098CC38F4",
    "EXPIRATION_DATE" VARCHAR(255),
    "NUMBER" VARCHAR(255),
    "SECRET_CODE" VARCHAR(255),
    "USER_ID" BIGINT
);           
ALTER TABLE "PUBLIC"."CREDIT_CARDS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_8" PRIMARY KEY("ID"); 
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.CREDIT_CARDS;            
INSERT INTO "PUBLIC"."CREDIT_CARDS" VALUES
(1, NULL, 'abc', NULL, 1);        
CREATE MEMORY TABLE "PUBLIC"."FRIENDS"(
    "PERSON_ID" BIGINT NOT NULL,
    "FRIEND_ID" BIGINT NOT NULL
);
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.FRIENDS; 
INSERT INTO "PUBLIC"."FRIENDS" VALUES
(1, 2),
(2, 1);       
CREATE MEMORY TABLE "PUBLIC"."TOP_UPS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_637D798C_6E7D_4D01_8FDB_903C197C8774" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_637D798C_6E7D_4D01_8FDB_903C197C8774",
    "DATE" TIMESTAMP,
    "SUM" DOUBLE NOT NULL,
    "CARD_ID" BIGINT,
    "USER_ID" BIGINT
);        
ALTER TABLE "PUBLIC"."TOP_UPS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY("ID");      
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.TOP_UPS; 
INSERT INTO "PUBLIC"."TOP_UPS" VALUES
(1, TIMESTAMP '2020-07-15 19:11:03.854', 50.0, 1, 1);  
CREATE MEMORY TABLE "PUBLIC"."TRANSACTIONS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_614293E8_5BCB_4C61_BFF7_27E4B6E392F0" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_614293E8_5BCB_4C61_BFF7_27E4B6E392F0",
    "AMOUNT" VARCHAR(255),
    "CONNECTION" VARCHAR(255),
    "DESCRIPTION" VARCHAR(255),
    "USER_ID" BIGINT
);
ALTER TABLE "PUBLIC"."TRANSACTIONS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID"); 
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TRANSACTIONS;            
CREATE MEMORY TABLE "PUBLIC"."TRANSFERS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_9BBD0775_3630_4E87_AC4D_B29BCF953358" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9BBD0775_3630_4E87_AC4D_B29BCF953358",
    "COMMISSION" DOUBLE NOT NULL,
    "DATE" TIMESTAMP,
    "DESCRIPTION" VARCHAR(255),
    "SUM" DOUBLE NOT NULL,
    "RECEIVER_ID" BIGINT,
    "SENDER_ID" BIGINT
);            
ALTER TABLE "PUBLIC"."TRANSFERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E4" PRIMARY KEY("ID");   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.TRANSFERS;               
INSERT INTO "PUBLIC"."TRANSFERS" VALUES
(1, 0.5, TIMESTAMP '2020-07-15 19:11:03.897', '', 10.0, 2, 1);       
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ID" BIGINT NOT NULL,
    "BALANCE" DOUBLE,
    "EMAIL" VARCHAR(255),
    "FIRST_NAME" VARCHAR(255),
    "LAST_NAME" VARCHAR(255),
    "PASSWORD" VARCHAR(255)
);            
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");        
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.USERS;   
INSERT INTO "PUBLIC"."USERS" VALUES
(1, 30.0, 'test@test.com', 'hello', 'test', '202cb962ac59075b964b07152d234b70'),
(2, 9.5, 'test2@test.com', 'hello', 'test', '202cb962ac59075b964b07152d234b70');       
CREATE MEMORY TABLE "PUBLIC"."WITH_DRAWS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_8DBBB8E1_EA5F_4F4F_8670_DDABC9407C71" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_8DBBB8E1_EA5F_4F4F_8670_DDABC9407C71",
    "DATE" TIMESTAMP,
    "SUM" DOUBLE NOT NULL,
    "BANK_ID" BIGINT,
    "USER_ID" BIGINT
);     
ALTER TABLE "PUBLIC"."WITH_DRAWS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E9" PRIMARY KEY("ID");  
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.WITH_DRAWS;              
INSERT INTO "PUBLIC"."WITH_DRAWS" VALUES
(1, TIMESTAMP '2020-07-15 19:11:03.874', 10.0, 1, 1);               
ALTER TABLE "PUBLIC"."TOP_UPS" ADD CONSTRAINT "PUBLIC"."FK1UUHPA1143DO5YB17QNQ9MKD9" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;        
ALTER TABLE "PUBLIC"."FRIENDS" ADD CONSTRAINT "PUBLIC"."FKHHJKSYQJGVP1R88YV1PREWT3O" FOREIGN KEY("PERSON_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;      
ALTER TABLE "PUBLIC"."BANK_ACCOUNTS" ADD CONSTRAINT "PUBLIC"."FKAHRJ5M84HFC167GPMA9VCWE0J" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;  
ALTER TABLE "PUBLIC"."CREDIT_CARDS" ADD CONSTRAINT "PUBLIC"."FKN1THFI0PEV97G7G4OREO6G4YW" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."TRANSFERS" ADD CONSTRAINT "PUBLIC"."FKINWE0246UYU4691M6T4RR3QAV" FOREIGN KEY("RECEIVER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;  
ALTER TABLE "PUBLIC"."TRANSFERS" ADD CONSTRAINT "PUBLIC"."FKPN4X20JHIOY57YKP4BE3JNEI4" FOREIGN KEY("SENDER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;    
ALTER TABLE "PUBLIC"."TOP_UPS" ADD CONSTRAINT "PUBLIC"."FKKSF94TJVO4NTOVXN9IJNPGOXG" FOREIGN KEY("CARD_ID") REFERENCES "PUBLIC"."CREDIT_CARDS"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."WITH_DRAWS" ADD CONSTRAINT "PUBLIC"."FKTFRTEAGE0N1RS513TX6B6P0I2" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;     
ALTER TABLE "PUBLIC"."FRIENDS" ADD CONSTRAINT "PUBLIC"."FKC42EIHJTIRYERIY8AXLKPEJO7" FOREIGN KEY("FRIEND_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;      
ALTER TABLE "PUBLIC"."TRANSACTIONS" ADD CONSTRAINT "PUBLIC"."FKQWV7RMVC8VA8REP7PIIKROJDS" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."WITH_DRAWS" ADD CONSTRAINT "PUBLIC"."FKSQ7F25ETYCNN8UAQUXW8DGX74" FOREIGN KEY("BANK_ID") REFERENCES "PUBLIC"."BANK_ACCOUNTS"("ID") NOCHECK;             
