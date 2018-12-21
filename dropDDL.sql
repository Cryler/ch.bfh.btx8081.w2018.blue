ALTER TABLE calendartile DROP CONSTRAINT FK_calendartile_PATIENT_ID
ALTER TABLE institution DROP CONSTRAINT FK_institution_ADDRESS_ADDRESSID
ALTER TABLE usertable DROP CONSTRAINT FK_usertable_INSTITUTION_INSTITUTIONID
DROP TABLE address CASCADE
DROP TABLE calendartile CASCADE
DROP TABLE institution CASCADE
DROP TABLE usertable CASCADE
DROP TABLE person CASCADE
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
