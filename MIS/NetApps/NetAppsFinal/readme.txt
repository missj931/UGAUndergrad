README

Admin account is
user:admin@mvollk.com
password: 0

I cascade on DELETE and on UPDATE
in MySQL server instead of creating loops
and subqueries on the web application.
I am not sure if this will be ported
properly when I send you the database.

Database:
a many to many relationship exists between
the users and courses on their primary keys, respectively.

READ:
min and max are placed in a WHERE clause on search.
The view being searched was written on the database
side. The view has a many to many related tables

Create:
when a course is created, TBA is created by defualt
for the TEACHER_EMAIL record. This is added to
the users table if a TBA user does not already exist

UPDATE:
When users update their account info, it updates
the relevant records. These forms are prefilled.

Delete:
When users and teachers are deleted, they are dropped
from their relevant courses.  When courses are deleted,
all users are removed from enrollement.