--<ScriptOptions statementTerminator=";"/>
drop table activity cascade;
drop table request cascade;
drop table team cascade;
drop table player cascade;

CREATE TABLE activity (
	activity_id BIGINT NOT NULL auto_increment,
	activity_area VARCHAR(50),
	activity_time DATETIME,
	activity_players_cnt MEDIUMINT,
	activity_expense BIGINT,
	activity_type BIGINT,
	activity_player_id BIGINT,
	activity_team_id BIGINT,
	activity_opponent_team_id BIGINT,
	activity_isneed_right BIT,
	activity_status bigint(1) DEFAULT 1,
	PRIMARY KEY (activity_id)
) ENGINE=InnoDB;

CREATE TABLE request (
	request_id BIGINT NOT NULL auto_increment,
	request_player_id BIGINT,
	request_team_id BIGINT,
	team_id BIGINT,
	request_status BIGINT,
	request_type BIGINT,
	request_time DATETIME,
	request_msg VARCHAR(50),
	request_activity_id BIGINT,
	PRIMARY KEY (request_id)
) ENGINE=InnoDB;

CREATE TABLE team (
	team_id BIGINT NOT NULL auto_increment,
	team_name VARCHAR(30) NOT NULL,
	creattime DATE NOT NULL,
	creatorid BIGINT,
	memebercnt MEDIUMINT,
	PRIMARY KEY (team_id)
) ENGINE=InnoDB;

CREATE TABLE player (
player_id         bigint(20) auto_increment,
   player_name       varchar(30),
   createtime        datetime,
   phone             varchar(20),
   attendtimes       bigint(20),
   attendsuccescnt   bigint(20),
   qq                varchar(20),
   weixin            varchar(20),
   mail              varchar(40),
   sex               bigint(1),
   birthday          date,
   password          varchar(20),
	PRIMARY KEY (player_id)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX player_name_index
    ON football.player(player_name);

CREATE TABLE team_player_relation
(
   team_id      bigint(6),
   player_id    bigint(6),
   createtime   datetime,
   position     varchar(10),
   goal         bigint(3)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX activity_time_area_player_index
    ON football.activity(activity_area, activity_time, activity_player_id);

CREATE INDEX player_request_index
    ON football.request(request_activity_id, request_player_id, request_type);

CREATE INDEX team_request_index
    ON football.request(request_activity_id, team_id, request_type);

CREATE INDEX team_player_request_index
    ON football.request(request_team_id, request_player_id, request_type, request_status);

