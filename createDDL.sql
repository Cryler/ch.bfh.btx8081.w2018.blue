CREATE TABLE address (ADDRESSID INTEGER NOT NULL, CITY VARCHAR(255), STREET VARCHAR(255), STREETNR INTEGER, ZIPCODE INTEGER, PRIMARY KEY (ADDRESSID))
CREATE TABLE calendartile (DATE DATE NOT NULL, KOMMENTAR VARCHAR(255), PATIENT_ID INTEGER, PRIMARY KEY (DATE))
CREATE TABLE institution (INSTITUTIONID INTEGER NOT NULL, INSTITUTIONNAME VARCHAR(255), ADDRESS_ADDRESSID INTEGER, PRIMARY KEY (INSTITUTIONID))
CREATE TABLE person (ID INTEGER NOT NULL, DTYPE VARCHAR(31), ADDRESS VARCHAR(255), BIRTHDATE DATE, CITY VARCHAR(255), EMAIL VARCHAR(255), FIRSTNAME VARCHAR(255), GENDER VARCHAR(255), LANGUAGE VARCHAR(255), LASTNAME VARCHAR(255), NATIONALITY VARCHAR(255), PHONENUMBER VARCHAR(255), AHVNR VARCHAR(255), INSURANCE VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE usertable (USERID INTEGER NOT NULL, EMAIL VARCHAR(255), PASSWORD VARCHAR(255), USERNAME VARCHAR(255), INSTITUTION_INSTITUTIONID INTEGER, PRIMARY KEY (USERID))
ALTER TABLE calendartile ADD CONSTRAINT FK_calendartile_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES person (ID)
ALTER TABLE institution ADD CONSTRAINT FK_institution_ADDRESS_ADDRESSID FOREIGN KEY (ADDRESS_ADDRESSID) REFERENCES address (ADDRESSID)
ALTER TABLE usertable ADD CONSTRAINT FK_usertable_INSTITUTION_INSTITUTIONID FOREIGN KEY (INSTITUTION_INSTITUTIONID) REFERENCES institution (INSTITUTIONID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
