CREATE TYPE user_role as enum('Employee', 'Manager');
CREATE TYPE request_status as enum('Pending', 'Approved', 'Rejected');

 CREATE TABLE USERS_LOGIN (

 employee_username VARCHAR(255) NOT NULL,
 employee_role user_role default 'Employee',
 employee_password VARCHAR(255) NOT NULL,
PRIMARY KEY ( employee_username )
)

CREATE  TABLE reimbursement_ticket(

id serial PRIMARY KEY,
employee_id VARCHAR(255),
amount NUMERIC,
description TEXT,
status request_status DEFAULT 'Pending',
FOREIGN KEY(employee_id) REFERENCES users_login(employee_username)
)



INSERT INTO USERS_LOGIN(employee_username, employee_password) VALUES ('Ted', 'tedpassword');
INSERT INTO USERS_LOGIN(employee_username,employee_role, employee_password) VALUES ('John', 'Manager', 'johnpassword');
INSERT INTO USERS_LOGIN(employee_username, employee_password) VALUES ('Adams', 'adamspassword');
INSERT INTO USERS_LOGIN(employee_username,employee_role, employee_password) VALUES ('Alex', 'Manager', 'alexpassword');
INSERT INTO USERS_LOGIN(employee_username, employee_password) VALUES ('Zakir', 'zakirpassword');
INSERT INTO USERS_LOGIN(employee_username, employee_password) VALUES ('MrX', 'mrxpassword');
INSERT INTO USERS_LOGIN(employee_username, employee_password) VALUES ('MrY', 'mrypassword');

select * from users_login;



INSERT INTO reimbursement_ticket(employee_id, amount, description) VALUES ('Ted', 0, 'This is teds description');
INSERT INTO reimbursement_ticket(employee_id, amount, description) VALUES ('Adams', 200, 'This is adams description');
INSERT INTO reimbursement_ticket(employee_id, amount, description,status) VALUES('PassingusernameandpasswordOnly', 200, 'This is adams description', 'Rejected');





 select * from  reimbursement_ticket;

SELECT * FROM users_login WHERE employee_username = 'Ted';


SELECT * FROM reimbursement_ticket join  users_login
on users_login.employee_username = reimbursement_ticket.employee_id
WHERE reimbursement_ticket.status = 'Pending'
ORDER BY reimbursement_ticket.id


--update amount column to add check constraints

--Alter Table reimbursement_ticket ADD CONSTRAINT amount CHECK(amount > 0)

