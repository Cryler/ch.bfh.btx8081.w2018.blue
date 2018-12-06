CREATE TABLE Person (ID INTEGER NOT NULL, DTYPE VARCHAR(31), ADDRESS VARCHAR(255), BIRTHDATE VARCHAR(255), CITY VARCHAR(255), EMAIL VARCHAR(255), FIRSTNAME VARCHAR(255), GENDER VARCHAR(255), LANGUAGE VARCHAR(255), LASTNAME VARCHAR(255), NATIONALITY VARCHAR(255), PHONENUMBER VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE PATIENTMODEL (ID INTEGER NOT NULL, AHVNR VARCHAR(255), INSURANCE VARCHAR(255), PRIMARY KEY (ID))
ALTER TABLE PATIENTMODEL ADD CONSTRAINT FK_PATIENTMODEL_ID FOREIGN KEY (ID) REFERENCES Person (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
