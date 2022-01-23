
DROP DATABASE IF EXISTS dronedb;
CREATE DATABASE dronedb  CHARACTER SET utf8 COLLATE utf8_general_ci ;
DROP USER IF EXISTS 'musala'@'%';
CREATE USER 'musala'@'%' IDENTIFIED  BY 'musala';
GRANT SELECT, INSERT, UPDATE, DELETE ON dronedb.* TO 'musala'@'%';


--
-- DB objects creation
-- Table structure for table roles
--



DROP TABLE IF EXISTS dronedb.model;
CREATE TABLE dronedb.model (
id int ,
model_name varchar(100) NOT NULL,
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
created_by int(3) DEFAULT 1,
modified TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
modified_by int(3) DEFAULT 1,
CONSTRAINT pk_model primary key(id),
CONSTRAINT uq_model_name UNIQUE (model_name)
) ;

-- Fill main data model
insert into dronedb.model(id,model_name) values (1,'Lightweight');
insert into dronedb.model(id,model_name) values (2,'Middleweight');
insert into dronedb.model(id,model_name) values (3,'Cruiserweight');
insert into dronedb.model(id,model_name) values (4,'Heavyweight');


DROP TABLE IF EXISTS dronedb.state;
CREATE TABLE dronedb.state (
id int ,
state_name varchar(100) NOT NULL,
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
created_by int(3) DEFAULT 1,
modified TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
modified_by int(3)  DEFAULT 1,
CONSTRAINT pk_state primary key(id),
CONSTRAINT uq_state_name UNIQUE (state_name)
) ;

-- -- Fill main data state
insert into dronedb.state(id,state_name) values (1,'IDLE');
insert into dronedb.state(id,state_name) values (2,'LOADING');
insert into dronedb.state(id,state_name) values (3,'LOADED');
insert into dronedb.state(id,state_name) values (4,'DELIVERING');
insert into dronedb.state(id,state_name) values (5,'DELIVERED');
insert into dronedb.state(id,state_name) values (6,'RETURNING');






DROP TABLE IF EXISTS dronedb.drone;
CREATE TABLE dronedb.drone (
id int  NOT NULL AUTO_INCREMENT,
serial_number varchar(100) NOT NULL,
model_id int NOT NULL,
weight_limit int,
battery  int,
state_id int,
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
created_by int(3) DEFAULT 1,
modified TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
modified_by int(3)  DEFAULT 1,
CONSTRAINT pk_drone primary key(id),
CONSTRAINT uq_serial_number UNIQUE (serial_number),
CONSTRAINT fk_model_id foreign key(model_id) references model(id),
CONSTRAINT fk_state_id foreign key(state_id) references state(id)
) ;





DROP TABLE IF EXISTS dronedb.cargo;
CREATE TABLE dronedb.cargo (
id int  NOT NULL AUTO_INCREMENT,
drone_id int NOT NULL,
medication_code varchar(100) NOT NULL,
medication_name varchar(100) NOT NULL,
medication_weight int,
medication_image  blob,
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
created_by int(3) DEFAULT 1,
modified TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
modified_by int(3) DEFAULT 1,
CONSTRAINT pk_cargo primary key(id),
CONSTRAINT fk_drone_id foreign key(drone_id) references drone(id)
) ;


DROP TABLE IF EXISTS dronedb.drone_battery_audit;
CREATE TABLE dronedb.drone_audit (
id int  NOT NULL AUTO_INCREMENT,
drone_id int NOT NULL,
activity_id int NOT NULL,
old_value varchar(100) NOT NULL,
new_value varchar(100) NOT NULL,
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_drone_battery_audit primary key(id),
CONSTRAINT fk_audit_drone_id foreign key(drone_id) references drone(id)
) ;





