CREATE TABLE CharacterDB (

    id    INTEGER PRIMARY KEY,
    name  varchar(500)

);

CREATE TABLE ScriptDB (

    id    INTEGER PRIMARY KEY

);

CREATE TABLE SceneSettingDB (

    id    INTEGER PRIMARY KEY,
    name  varchar(500)

);

CREATE TABLE SceneSettingCharWordDB(

    settingId   INTEGER,
    characterId INTEGER,
    word        VARCHAR(500),
    count       INTEGER,
    PRIMARY KEY (settingId,characterId,word)

);
