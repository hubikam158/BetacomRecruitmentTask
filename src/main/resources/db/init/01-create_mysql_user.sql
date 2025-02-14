DROP USER IF EXISTS 'betacom_rekrutacja';

CREATE USER 'betacom_rekrutacja'@'localhost' IDENTIFIED BY 'betacom';
GRANT ALL PRIVILEGES ON * . * TO 'betacom_rekrutacja'@'localhost';