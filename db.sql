CREATE DATABASE memento_mori;

CREATE TABLE memento_mori.dbo.BranchOffices (       
ID                int             NOT NULL       PRIMARY KEY IDENTITY,
Name              nvarchar(50)    NOT NULL,
Address           nvarchar(50)    NOT NULL,
Budget            float           NOT NULL
);

CREATE TABLE memento_mori.dbo.Users (       
ID                int             NOT NULL       PRIMARY KEY IDENTITY,
Login             nvarchar(256)   NOT NULL,
Password          nvarchar(512)   NOT NULL,
Role              nvarchar(50)    NOT NULL,
BranchOfficeID    int             NOT NULL       FOREIGN KEY REFERENCES BranchOffices(ID)
)

CREATE TABLE memento_mori.dbo.Inventory (
ID                int             NOT NULL       PRIMARY KEY IDENTITY,
Name              nvarchar(50)    NOT NULL,
Amount            int             NOT NULL,
BranchOfficeID    int             NOT NULL       FOREIGN KEY REFERENCES BranchOffices(ID)
);

CREATE TABLE memento_mori.dbo.BudgetHistory (       
ID                int             NOT NULL       PRIMARY KEY IDENTITY,
Action            nvarchar(50)    NOT NULL,
Description       nvarchar(350)   NOT NULL,
BranchOfficeID    int             NOT NULL       FOREIGN KEY REFERENCES BranchOffices(ID)
);

CREATE TABLE memento_mori.dbo.Personal( 
ID                int             NOT NULL       PRIMARY KEY IDENTITY, 
Name              nvarchar(100)   NOT NULL, 
Surname           nvarchar(100)   NOT NULL, 
PhoneNumber       nvarchar(50)    NOT NULL, 
Address           nvarchar(50)    NOT NULL, 
Email             nvarchar(50)    NOT NULL, 
BranchOfficeId    int             NOT NULL       FOREIGN KEY REFERENCES BranchOffices(ID),
Post              nvarchar(50)    NOT NULL, 
SalaryType        bit             NOT NULL, 
SalaryPercent     int                     , 
SalaryAmount      float         
);

CREATE TABLE memento_mori.dbo.Patients(
ID                int             NOT NULL       PRIMARY KEY IDENTITY,
Name              nvarchar(100)   NOT NULL, 
Surname           nvarchar(100)   NOT NULL, 
PhoneNumber       nvarchar(50)    NOT NULL, 
Address           nvarchar(50)    NOT NULL, 
Email             nvarchar(50)    NOT NULL, 
);

CREATE TABLE memento_mori.dbo.Schedule(
ID                int             NOT NULL       PRIMARY KEY IDENTITY,
PatientID         int             NOT NULL       FOREIGN KEY REFERENCES Patients(ID),
PostID            int             NOT NULL       FOREIGN KEY REFERENCES Personal(ID),
Date              date            NOT NULL,
Price             float           NOT NULL
);