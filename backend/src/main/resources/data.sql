
CREATE TABLE ValuableItem (
    pk INT IDENTITY PRIMARY KEY,
    id VARCHAR(250) NOT NULL,
    subscriptionFk INT NOT NULL,
    item JSON NOT NULL,
    mean INT NOT NULL,
    median INT NOT NULL,
    max INT NOT NULL,
    min INT NOT NULL,
    createdDate TIMESTAMP NOT NULL
);

create table Subscription (
    pk INT IDENTITY PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    tabIds ARRAY NOT NULL,
    currencyThreshold VARCHAR(250) NOT NULL,
    currencyType VARCHAR(250) NOT NULL,
    itemTypes ARRAY NOT NULL,
    isActive BOOLEAN NOT NULL
);

create table User (
    pk INT IDENTITY PRIMARY KEY,
    league VARCHAR(250) NOT NULL,
    accountName VARCHAR(250) NOT NULL,
    realm VARCHAR(250) NOT NULL,
    sessionId VARCHAR(250) NOT NULL
);