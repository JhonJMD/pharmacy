DROP DATABASE IF EXISTS pharmacy;

CREATE DATABASE pharmacy;

USE pharmacy;

CREATE TABLE modeadministration(
    id INT(4) AUTO_INCREMENT,
    descriptionmode VARCHAR(60),
    CONSTRAINT pk_id_mode_administration PRIMARY KEY (id)
);

CREATE TABLE unitmeasurement(
    idum INT(4) AUTO_INCREMENT,
    nameum VARCHAR(50),
    CONSTRAINT pk_id_unit_measurement PRIMARY KEY (idum)
);

CREATE TABLE activeprinciple(
    idap INT(4) AUTO_INCREMENT,
    nameap VARCHAR(60),
    CONSTRAINT pk_id_active_principle PRIMARY KEY (idap)
);

CREATE TABLE country(
    codecountry VARCHAR(5),
    namecountry VARCHAR(50),
    CONSTRAINT pk_id_country PRIMARY KEY (codecountry)
);

CREATE TABLE region(
    codereg VARCHAR(5),
    namereg VARCHAR(50),
    codecountry VARCHAR(5),
    CONSTRAINT pk_id_region PRIMARY KEY (codereg),
    CONSTRAINT fk_codecountry_region FOREIGN KEY (codecountry) REFERENCES country(codecountry)
);

CREATE TABLE city(
    codecity VARCHAR(5),
    namecity VARCHAR(50),
    codereg VARCHAR(5),
    CONSTRAINT pk_id_city PRIMARY KEY (codecity),
    CONSTRAINT fk_codereg_city FOREIGN KEY (codereg) REFERENCES region(codereg)
);

CREATE TABLE customer(
    idcustomer VARCHAR(20),
    namecustomer VARCHAR(50),
    lastnamecustomer VARCHAR(50),
    codecitycustomer VARCHAR(5),
    emailcustomer VARCHAR(100),
    birthdate DATE,
    lon FLOAT(8),
    latitud FLOAT(8),
    CONSTRAINT pk_id_customer PRIMARY KEY (idcustomer),
    CONSTRAINT fk_codecitycustomer FOREIGN KEY (codecitycustomer) REFERENCES city(codecity)
);

CREATE TABLE laboratory(
    id INT(4) AUTO_INCREMENT,
    namelab VARCHAR(50),
    codecityreg VARCHAR(5),
    CONSTRAINT pk_id_laboratory PRIMARY KEY (id),
    CONSTRAINT fk_codecityreg_laboratory FOREIGN KEY (codecityreg) REFERENCES city(codecity)
);

CREATE TABLE pharmacy(
    idpharmacy INT(4) AUTO_INCREMENT,
    namepharmacy VARCHAR(60),
    addresspharmacy VARCHAR(100),
    longpharmacy FLOAT(8),
    latpharmacy FLOAT(8),
    codecitypharm VARCHAR(5),
    logopharmacy VARCHAR(50),
    CONSTRAINT pk_id_pharmacy PRIMARY KEY (idpharmacy),
    CONSTRAINT fk_codecitypharm_pharmacy FOREIGN KEY (codecitypharm) REFERENCES city(codecity) 
);

CREATE TABLE medicine(
    id INT(4) AUTO_INCREMENT,
    proceedings VARCHAR(10),
    namemedicine VARCHAR(100),
    healthregister VARCHAR(50),
    description TEXT,
    descriptionshort VARCHAR(60),
    codemodeadmin INT(4),
    codeap INT(4),
    codeum INT(4),
    namerol VARCHAR(100),
    codelab INT(4),
    CONSTRAINT pk_id_medicine PRIMARY KEY (id),
    CONSTRAINT fk_codemodeadmin_medicine FOREIGN KEY (codemodeadmin) REFERENCES modeadministration(id),
    CONSTRAINT fk_codeap_medicine FOREIGN KEY (codeap) REFERENCES activeprinciple(idap),
    CONSTRAINT fk_codeum_medicine FOREIGN KEY (codeum) REFERENCES unitmeasurement(idum),
    CONSTRAINT fk_codelab_medicine FOREIGN KEY (codelab) REFERENCES laboratory(id)
);

CREATE TABLE pharmacymedicine(
    idpharmacy INT(4),
    idmedicinepharm INT(4),
    price FLOAT(8),
    CONSTRAINT pk_id_pharmacymedicine PRIMARY KEY (idpharmacy, idmedicinepharm),
    CONSTRAINT fk_idpharmacy_pharmacymedicine FOREIGN KEY (idpharmacy) REFERENCES pharmacy(idpharmacy),
    CONSTRAINT fk_idmedicinepharm_pharmacymedicine FOREIGN KEY (idmedicinepharm) REFERENCES medicine(id)
);