-- Run this script from within psql --
--    \i db/init_rls.sql --
-- Add wso2 user if not already there --
DO
$do$
BEGIN
   IF NOT EXISTS ( SELECT FROM pg_catalog.pg_roles WHERE  rolname = 'wso2') THEN
      CREATE USER wso2 PASSWORD 'iwref';
   END IF;
END
$do$;
-- Create templates database if not already there --
SELECT 'CREATE DATABASE rls' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'rls');\gexec
GRANT ALL PRIVILEGES ON DATABASE rls TO wso2;
-- Connect to templates db
\c rls wso2;
BEGIN TRANSACTION;

-- SYSTEM
-- ------
DROP TABLE IF EXISTS SYSTEM_TBL CASCADE;
DROP SEQUENCE IF EXISTS SYSTEM_ID_SEQUENCE;
DROP INDEX IF EXISTS IDX_SYSTEM_ID;
DROP INDEX IF EXISTS IDX_SOURCE_SYS;
CREATE SEQUENCE SYSTEM_ID_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE SYSTEM_TBL (
    SYSTEM_ID       INTEGER DEFAULT nextval('SYSTEM_ID_SEQUENCE'),
    SOURCE_SYS      VARCHAR(20) NOT NULL ,
    SYS_TYPE        VARCHAR(20) NOT NULL ,
    LOC_TYPE        VARCHAR(20),
    THIRD_PARTY_ID  VARCHAR(20),
    LOCATION        VARCHAR(50) NOT NULL ,
    CONSTRAINT SYSTEM_ID_CONSTRAINT UNIQUE (SYSTEM_ID),
    CONSTRAINT SYSTEM_KEY_CONSTRAINT UNIQUE (SOURCE_SYS,SYS_TYPE),
    PRIMARY KEY ( SOURCE_SYS, SYS_TYPE )
);
CREATE INDEX IDX_SYSTEM_ID ON SYSTEM_TBL(SYSTEM_ID);
CREATE INDEX IDX_SOURCE_SYS ON SYSTEM_TBL(SOURCE_SYS);
INSERT INTO SYSTEM_TBL (SOURCE_SYS, SYS_TYPE, LOC_TYPE, THIRD_PARTY_ID, LOCATION) VALUES ('MedTech','1','URL', 'MD-ppp', 'https://medtech/1');
INSERT INTO SYSTEM_TBL (SOURCE_SYS, SYS_TYPE, LOC_TYPE, THIRD_PARTY_ID, LOCATION) VALUES ('ManageMyHealth','1','URL', 'MMM-ppp', 'https://managemyhealth/1');
INSERT INTO SYSTEM_TBL (SOURCE_SYS, SYS_TYPE, LOC_TYPE, THIRD_PARTY_ID, LOCATION) VALUES ('Z_ManageMyHealth','1','URL', 'MMM-ppp', 'https://managemyhealth/2')
    ON CONFLICT (SOURCE_SYS,SYS_TYPE)
    DO UPDATE SET LOC_TYPE = 'HTTP', THIRD_PARTY_ID='MMM-zzz',LOCATION='https://managemyhealth/2'
    RETURNING SYSTEM_ID;
SELECT * FROM SYSTEM_TBL;

-- DATATYPE
-- --------
DROP TABLE IF EXISTS DATATYPE_TBL CASCADE;
DROP SEQUENCE IF EXISTS TYPE_ID_SEQUENCE;
DROP INDEX IF EXISTS IDX_DATA_TYPE_ID;
DROP INDEX IF EXISTS IDX_DATA_TYPE_DESC;
CREATE SEQUENCE TYPE_ID_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE DATATYPE_TBL (
    TYPE_ID        INTEGER DEFAULT nextval('TYPE_ID_SEQUENCE'),
    TYPE_DESC      VARCHAR(20) NOT NULL ,
    RANK            INTEGER ,
    CONSTRAINT TYPE_ID UNIQUE (TYPE_ID),
    CONSTRAINT DATATYPE_KEY_CONSTRAINT UNIQUE (TYPE_DESC),
    PRIMARY KEY ( TYPE_ID )
);
CREATE INDEX IDX_DATA_TYPE_ID ON DATATYPE_TBL(TYPE_ID);
CREATE INDEX IDX_DATA_TYPE_DESC ON DATATYPE_TBL(TYPE_DESC);
INSERT INTO DATATYPE_TBL (TYPE_DESC,RANK) VALUES ('typedesc1',1);
INSERT INTO DATATYPE_TBL (TYPE_DESC,RANK) VALUES ('typedesc2',2);
INSERT INTO DATATYPE_TBL (TYPE_DESC,RANK) VALUES ('typedesc1',3)
    ON CONFLICT (TYPE_DESC)
    DO UPDATE SET RANK = '3'
    RETURNING TYPE_ID;
SELECT * FROM DATATYPE_TBL;

-- LOCATOR
-- -------
DROP TABLE IF EXISTS LOCATOR_TBL;
DROP SEQUENCE IF EXISTS RLS_ID_SEQUENCE;
DROP INDEX IF EXISTS IDX_RLS_BY_RLS_ID;
DROP INDEX IF EXISTS IDX_RLS_BY_PATIENT_ID;
DROP INDEX IF EXISTS IDX_RLS_BY_LAST_UPDATED;
CREATE SEQUENCE RLS_ID_SEQUENCE START WITH 1 INCREMENT BY 1;
CREATE TABLE LOCATOR_TBL (
    RLS_ID         INTEGER DEFAULT nextval('RLS_ID_SEQUENCE'),
    SYSTEM_ID      INTEGER NOT NULL ,
    DATA_TYPE      INTEGER  NOT NULL ,
    PATIENT_ID     VARCHAR(20) NOT NULL ,
    LAST_UPDATED   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT LOCATOR_CONSTRAINT UNIQUE (SYSTEM_ID,DATA_TYPE,PATIENT_ID),
    PRIMARY KEY ( RLS_ID ),
    FOREIGN KEY (SYSTEM_ID) REFERENCES SYSTEM_TBL(SYSTEM_ID) ON DELETE CASCADE,
    FOREIGN KEY (DATA_TYPE) REFERENCES DATATYPE_TBL(TYPE_ID) ON DELETE CASCADE
);
CREATE INDEX IDX_RLS_BY_RLS_ID ON LOCATOR_TBL(RLS_ID);
CREATE INDEX IDX_RLS_BY_PATIENT_ID ON LOCATOR_TBL(PATIENT_ID);
CREATE INDEX IDX_RLS_BY_LAST_UPDATED ON LOCATOR_TBL(LAST_UPDATED);

INSERT INTO LOCATOR_TBL (SYSTEM_ID,DATA_TYPE,PATIENT_ID) VALUES (1,1,'patient1');
INSERT INTO LOCATOR_TBL (SYSTEM_ID,DATA_TYPE,PATIENT_ID) VALUES (2,1,'patient1');
INSERT INTO LOCATOR_TBL (SYSTEM_ID,DATA_TYPE,PATIENT_ID) VALUES (2,2,'patient2');
INSERT INTO LOCATOR_TBL (SYSTEM_ID,DATA_TYPE,PATIENT_ID) VALUES (2,2,'patient2')
    ON CONFLICT (SYSTEM_ID,DATA_TYPE,PATIENT_ID)
    DO UPDATE SET LAST_UPDATED = CURRENT_TIMESTAMP
    RETURNING RLS_ID;
SELECT * FROM LOCATOR_TBL;

-- AUDIT
-- -----
DROP TABLE IF EXISTS AUDIT_TBL;
DROP SEQUENCE IF EXISTS AUDIT_ID_SEQUENCE;
DROP INDEX IF EXISTS IDX_AUDIT_BY_USER_ID;
DROP INDEX IF EXISTS IDX_AUDIT_BY_USER_ID_TIME;

CREATE SEQUENCE AUDIT_ID_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE AUDIT_TBL (
    AUDIT_ID        INTEGER DEFAULT nextval('AUDIT_ID_SEQUENCE'),
    USER_ID         VARCHAR(20) NOT NULL ,
    RLS_ID          INTEGER  NOT NULL ,
    PATIENT_ID      VARCHAR(20) NOT NULL ,
    ACCESS_DATE     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ( AUDIT_ID ),
    FOREIGN KEY (RLS_ID) REFERENCES LOCATOR_TBL(RLS_ID) ON DELETE CASCADE
);
CREATE INDEX IDX_AUDIT_BY_USER_ID ON AUDIT_TBL(USER_ID);
CREATE INDEX IDX_AUDIT_BY_USER_ID_TIME ON AUDIT_TBL(USER_ID,ACCESS_DATE);
INSERT INTO AUDIT_TBL (USER_ID,RLS_ID,PATIENT_ID) VALUES ('user1',1,'patient1');
INSERT INTO AUDIT_TBL (USER_ID,RLS_ID,PATIENT_ID) VALUES ('user1',2,'patient2');
SELECT * FROM AUDIT_TBL;
commit;