
CREATE TABLE ValuableItem (
    pk INT IDENTITY PRIMARY KEY,
    id VARCHAR(250) NOT NULL,
    subscriptionFk INT NOT NULL,
    item JSON NOT NULL,
    estimatedPrice INT NOT NULL,
    createdDate TIMESTAMP NOT NULL
);

create table Subscription (
    pk INT IDENTITY PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    tabIds ARRAY NOT NULL,
    currencyThreshold VARCHAR(250) NOT NULL,
    currencyType VARCHAR(250) NOT NULL
);

create table ItemFilterType (
    pk INT IDENTITY PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

create table ItemFilterType_Subscription (
    pk INT IDENTITY PRIMARY KEY,
    subscription_fk INT NOT NULL, --ADD CONSTRAINT [FK_States_Countries] REFERENCES Subscription(pk) ON DELETE CASCADE,
    ItemFilterType_fk INT NOT NULL --ADD CONSTRAINT [FK_States_Countries] REFERENCES ItemFilterType(pk) ON DELETE CASCADE
);

ALTER TABLE ItemFilterType_Subscription
    ADD FOREIGN KEY (subscription_fk)
    REFERENCES Subscription(pk) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ItemFilterType_Subscription
    ADD FOREIGN KEY (ItemFilterType_fk)
    REFERENCES ItemFilterType(pk) ON DELETE CASCADE ON UPDATE CASCADE;


create table User (
    pk INT IDENTITY PRIMARY KEY,
    league VARCHAR(250) NOT NULL,
    accountName VARCHAR(250) NOT NULL,
    realm VARCHAR(250) NOT NULL,
    sessionId VARCHAR(250) NOT NULL
);


-- Default item filters to insert.
INSERT INTO ItemFilterType (name) values ('Fragments');
INSERT INTO ItemFilterType (name) values ('Delirium Orbs');
INSERT INTO ItemFilterType (name) values ('Watchstones');
INSERT INTO ItemFilterType (name) values ('Oils');
INSERT INTO ItemFilterType (name) values ('Incubators');
INSERT INTO ItemFilterType (name) values ('Scarabs');
INSERT INTO ItemFilterType (name) values ('Fossils');
INSERT INTO ItemFilterType (name) values ('Resonators');
INSERT INTO ItemFilterType (name) values ('Essences');
INSERT INTO ItemFilterType (name) values ('Divination Cards');
INSERT INTO ItemFilterType (name) values ('Prophecies');
INSERT INTO ItemFilterType (name) values ('Gems');
INSERT INTO ItemFilterType (name) values ('Unique Maps');
INSERT INTO ItemFilterType (name) values ('Maps');
INSERT INTO ItemFilterType (name) values ('Unique Jewels');
INSERT INTO ItemFilterType (name) values ('Unique Flasks');
INSERT INTO ItemFilterType (name) values ('Unique Weapons');
INSERT INTO ItemFilterType (name) values ('Unique Armours');
INSERT INTO ItemFilterType (name) values ('Unique Accessories');
INSERT INTO ItemFilterType (name) values ('Beasts');
INSERT INTO ItemFilterType (name) values ('Rare Jewels');
INSERT INTO ItemFilterType (name) values ('Rare Weapons');
INSERT INTO ItemFilterType (name) values ('Rare Armours');
INSERT INTO ItemFilterType (name) values ('Rare Accessories');